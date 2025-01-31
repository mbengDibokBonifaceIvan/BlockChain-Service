package com.ivan.blockchain.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import com.ivan.blockchain.contracts.javaWrapper.CommercialOperations;
import com.ivan.blockchain.contracts.javaWrapper.ResourceTraceability;

@Service
public class ContractService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private static String ComOpsV2Address;
    private static String resourceV2Address;

    public static String getComOpsV2Address() {
        return ComOpsV2Address;
    }

    public static String getResourceV2Address() {
        return resourceV2Address;
    }

    public ContractService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    public void deployContract() throws Exception {
        CommercialOperations ComOpsContract = CommercialOperations
                .deploy(web3j, transactionManager, new DefaultGasProvider()).send();
        ComOpsV2Address = ComOpsContract.getContractAddress();

        ResourceTraceability resourceTraceability = ResourceTraceability
                .deploy(web3j, transactionManager, new DefaultGasProvider()).send();
        resourceV2Address = resourceTraceability.getContractAddress();
    }

}