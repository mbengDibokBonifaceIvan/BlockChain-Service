package com.ivan.blockchain.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.blockchain.contracts.javaWrapper.ResourceTraceability;
import com.ivan.blockchain.controller.ResourceTraceabilityControllerV2;
import com.ivan.blockchain.service.ResourceTraceabilityServiceV2;
import com.ivan.blockchain.util.ResourceStateRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResourceTraceabilityControllerV2.class)
public class ResourceTraceabilityControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResourceTraceabilityServiceV2 service;

    @Autowired
    private ObjectMapper objectMapper;

    private ResourceStateRequest testRequest;

    @BeforeEach
    void setUp() {
        testRequest = new ResourceStateRequest(
                BigInteger.valueOf(1),
                BigInteger.valueOf(System.currentTimeMillis()),
                "CREATOR_1",
                "LOCATION_1",
                "CURRENT_LOCATION",
                "OWNER_1",
                BigInteger.valueOf(System.currentTimeMillis()),
                "Test Data");
    }

    @Test
    void addResourceState_Success() throws Exception {
        mockMvc.perform(post("/api/v2/resource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Resource state added successfully!"));
    }

    @Test
    void addResourceState_Error() throws Exception {
        doThrow(new RuntimeException("Test error"))
                .when(service)
                .addResourceState(any());

        mockMvc.perform(post("/api/v2/resource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error adding resource state: Test error"));
    }

    @Test
    void getResourceStates_Success() throws Exception {
        List<ResourceTraceability.ResourceState> mockStates = Arrays.asList(
                new ResourceTraceability.ResourceState(
                        BigInteger.ONE,
                        BigInteger.valueOf(System.currentTimeMillis()),
                        "CREATOR_1",
                        "LOCATION_1",
                        "CURRENT_LOCATION",
                        "OWNER_1",
                        BigInteger.valueOf(System.currentTimeMillis()),
                        "Data 1"));

        when(service.getResourceStates(BigInteger.ONE)).thenReturn(mockStates);

        mockMvc.perform(get("/api/v2/resource/states/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identification").value(1))
                .andExpect(jsonPath("$[0].creator").value("CREATOR_1"))
                .andExpect(jsonPath("$[0].location").value("CURRENT_LOCATION"));
    }
}