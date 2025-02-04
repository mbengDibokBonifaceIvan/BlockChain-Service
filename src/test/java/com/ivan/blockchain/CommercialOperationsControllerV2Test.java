package com.ivan.blockchain;

import com.ivan.blockchain.service.CommercialOperationsServiceV2;
import com.ivan.blockchain.util.OperationStateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ivan.blockchain.contracts.javaWrapper.CommercialOperations;
import com.ivan.blockchain.controller.CommercialOperationsControllerV2;

class CommercialOperationsControllerV2Test {

    @Mock
    private CommercialOperationsServiceV2 commercialOperationsService;

    @InjectMocks
    private CommercialOperationsControllerV2 controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOperationState_Success() throws Exception {
        // Arrange
        OperationStateRequest request = new OperationStateRequest(
                BigInteger.valueOf(1),
                "VENTE",
                "EN_COURS",
                "producer123",
                "consumer456",
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "additional data");

        doNothing().when(commercialOperationsService).addOperationState(
                request.getIdentification(),
                request.getType(),
                request.getStatus(),
                request.getProducer(),
                request.getConsumer(),
                request.getTimestamp(),
                request.getData());

        // Act
        ResponseEntity<String> response = controller.addOperationState(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Operation state added successfully!", response.getBody());
        verify(commercialOperationsService, times(1)).addOperationState(
                request.getIdentification(),
                request.getType(),
                request.getStatus(),
                request.getProducer(),
                request.getConsumer(),
                request.getTimestamp(),
                request.getData());
    }

    @Test
    void testAddOperationState_Exception() throws Exception {
        // Arrange
        OperationStateRequest request = new OperationStateRequest(
                BigInteger.valueOf(1),
                "VENTE",
                "EN_COURS",
                "producer123",
                "consumer456",
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "additional data");

        doThrow(new RuntimeException("Blockchain error")).when(commercialOperationsService).addOperationState(
                request.getIdentification(),
                request.getType(),
                request.getStatus(),
                request.getProducer(),
                request.getConsumer(),
                request.getTimestamp(),
                request.getData());

        // Act
        ResponseEntity<String> response = controller.addOperationState(request);

        // Assert
        assertEquals(500, response.getStatusCode().value());
        assertTrue(response.getBody().contains("Error adding operation state"));
        verify(commercialOperationsService, times(1)).addOperationState(
                request.getIdentification(),
                request.getType(),
                request.getStatus(),
                request.getProducer(),
                request.getConsumer(),
                request.getTimestamp(),
                request.getData());
    }

    @Test
    void testGetOperationStates_Success() throws Exception {
        // Arrange
        BigInteger identification = BigInteger.valueOf(1);
        CommercialOperations.OperationState mockState1 = mock(CommercialOperations.OperationState.class);
        CommercialOperations.OperationState mockState2 = mock(CommercialOperations.OperationState.class);

        // Correctly set up the mock states
        mockState1.identification = identification;
        mockState1.operationType = "VENTE";
        mockState1.status = "EN_COURS";
        mockState1.producer = "producer123";
        mockState1.consumer = "consumer456";
        mockState1.timestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        mockState1.data = "data1";

        mockState2.identification = identification;
        mockState2.operationType = "ACHAT";
        mockState2.status = "TERMINE";
        mockState2.producer = "producer456";
        mockState2.consumer = "consumer789";
        mockState2.timestamp = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        mockState2.data = "data2";

        // Mock the service method call
        when(commercialOperationsService.getOperationStates(identification))
                .thenReturn(Arrays.asList(mockState1, mockState2));

        // Act
        List<OperationStateRequest> result = controller.getOperationStates(identification);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(identification, result.get(0).getIdentification());
        assertEquals("VENTE", result.get(0).getType());
        assertEquals("EN_COURS", result.get(0).getStatus());
        assertEquals("producer123", result.get(0).getProducer());
        assertEquals("consumer456", result.get(0).getConsumer());

        verify(commercialOperationsService, times(1)).getOperationStates(identification);
    }

    @Test
    void testGetOperationStates_Exception() throws Exception {
        // Arrange
        BigInteger identification = BigInteger.valueOf(1);
        when(commercialOperationsService.getOperationStates(identification))
                .thenThrow(new RuntimeException("Blockchain error"));

        // Act
        List<OperationStateRequest> result = controller.getOperationStates(identification);

        // Assert
        assertNull(result);
        verify(commercialOperationsService, times(1)).getOperationStates(identification);
    }
}
