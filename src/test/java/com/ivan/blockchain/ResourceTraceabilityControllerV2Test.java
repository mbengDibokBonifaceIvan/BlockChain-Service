package com.ivan.blockchain;

import com.ivan.blockchain.service.ResourceTraceabilityServiceV2;
import com.ivan.blockchain.util.ResourceStateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.ivan.blockchain.contracts.javaWrapper.ResourceTraceability;
import com.ivan.blockchain.controller.ResourceTraceabilityControllerV2;

class ResourceTraceabilityControllerV2Test {

    @Mock
    private ResourceTraceabilityServiceV2 resourceTraceabilityService;

    @InjectMocks
    private ResourceTraceabilityControllerV2 controller;

    @Mock
    private ResourceTraceability contract;

    private ResourceStateRequest createSampleRequest() {
        return new ResourceStateRequest(
                BigInteger.valueOf(1),
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "creator123",
                "Yaounde",
                "Douala",
                "owner456",
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "additional data");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddResourceState_Success() throws Exception {
        // Arrange
        ResourceStateRequest request = createSampleRequest();
        doNothing().when(resourceTraceabilityService).addResourceState(request);

        // Act
        ResponseEntity<String> response = controller.addResourceState(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("État de la ressource ajouté avec succès !", response.getBody());
        verify(resourceTraceabilityService, times(1)).addResourceState(request);
    }

    @Test
    void testAddResourceState_Exception() throws Exception {
        // Arrange
        ResourceStateRequest request = createSampleRequest();
        doThrow(new RuntimeException("Blockchain error")).when(resourceTraceabilityService).addResourceState(request);

        // Act
        ResponseEntity<String> response = controller.addResourceState(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Erreur lors de l'ajout de l'état de la ressource"));
        verify(resourceTraceabilityService, times(1)).addResourceState(request);
    }

    @Test
    void testGetResourceStates_Success() throws Exception {
        // Arrange
        BigInteger identification = BigInteger.valueOf(1);
        ResourceTraceability.ResourceState mockState1 = mock(ResourceTraceability.ResourceState.class);
        ResourceTraceability.ResourceState mockState2 = mock(ResourceTraceability.ResourceState.class);

        // Correctly set up the mock states
        mockState1.identification = identification;
        mockState1.creationTimestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        mockState1.creator = "creator123";
        mockState1.creationLocation = "Yaounde";
        mockState1.location = "Douala";
        mockState1.owner = "owner456";
        mockState1.timestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        mockState1.data = "additional data";

        mockState2.identification = BigInteger.valueOf(2);
        mockState2.creationTimestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 1000);
        mockState2.creator = "creator789";
        mockState2.creationLocation = "Kumba";
        mockState2.location = "Yaounde";
        mockState2.owner = "owner123";
        mockState2.timestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 2000);
        mockState2.data = "additional data 2";

        // Mock the service method call
        when(resourceTraceabilityService.getResourceStates(identification))
                .thenReturn(List.of(mockState1, mockState2));

        // Act
        List<ResourceStateRequest> results = controller.getResourceStates(identification);

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(identification, results.get(0).getIdentification());
        assertEquals(BigInteger.valueOf(System.currentTimeMillis() / 1000), results.get(0).getCreationTimestamp());
        assertEquals("creator123", results.get(0).getCreator());
        assertEquals("Yaounde", results.get(0).getCreationLocation());
        assertEquals("Douala", results.get(0).getLocation());
        assertEquals("owner456", results.get(0).getOwner());
        assertEquals(BigInteger.valueOf(System.currentTimeMillis() / 1000), results.get(0).getTimestamp());
        assertEquals("additional data", results.get(0).getData());

        verify(resourceTraceabilityService, times(1)).getResourceStates(identification);

    }

    @Test
    void testGetResourceStates_Exception() throws Exception {
        // Arrange
        BigInteger identification = BigInteger.valueOf(1);
        when(resourceTraceabilityService.getResourceStates(identification))
                .thenThrow(new RuntimeException("Blockchain error"));

        // Act and Assert
        List<ResourceStateRequest> results = controller.getResourceStates(identification);
        assertNull(results);

        verify(resourceTraceabilityService, times(1)).getResourceStates(identification);
    }

}
