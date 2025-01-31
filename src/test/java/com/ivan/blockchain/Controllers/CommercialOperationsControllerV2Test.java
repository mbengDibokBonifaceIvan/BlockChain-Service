package com.ivan.blockchain.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.blockchain.contracts.javaWrapper.CommercialOperations;
import com.ivan.blockchain.controller.CommercialOperationsControllerV2;
import com.ivan.blockchain.service.CommercialOperationsServiceV2;
import com.ivan.blockchain.util.OperationStateRequest;

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

@WebMvcTest(CommercialOperationsControllerV2.class)
public class CommercialOperationsControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommercialOperationsServiceV2 service;

    @Autowired
    private ObjectMapper objectMapper;

    private OperationStateRequest testRequest;

    @BeforeEach
    void setUp() {
        testRequest = new OperationStateRequest(
                BigInteger.valueOf(1),
                "TEST_TYPE",
                "EN_COURS",
                "PRODUCER_1",
                "CONSUMER_1",
                BigInteger.valueOf(System.currentTimeMillis()),
                "Test Data");
    }

    @Test
    void addOperationState_Success() throws Exception {
        mockMvc.perform(post("/api/v2/operations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Operation state added successfully!"));
    }

    @Test
    void addOperationState_Error() throws Exception {
        doThrow(new RuntimeException("Test error"))
                .when(service)
                .addOperationState(any(), any(), any(), any(), any(), any(), any());

        mockMvc.perform(post("/api/v2/operations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error adding operation state: Test error"));
    }

    @Test
    void getOperationStates_Success() throws Exception {
        List<CommercialOperations.OperationState> mockStates = Arrays.asList(
                new CommercialOperations.OperationState(
                        BigInteger.ONE,
                        "TYPE_1",
                        "STATUS_1",
                        "PRODUCER_1",
                        "CONSUMER_1",
                        BigInteger.valueOf(System.currentTimeMillis()),
                        "Data 1"));

        when(service.getOperationStates(BigInteger.ONE)).thenReturn(mockStates);

        mockMvc.perform(get("/api/v2/operations/states/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identification").value(1))
                .andExpect(jsonPath("$[0].type").value("TYPE_1"))
                .andExpect(jsonPath("$[0].status").value("STATUS_1"));
    }
}
