package com.ivan.blockchain.Service;

import com.ivan.blockchain.SmartContracts.CommercialOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import java.math.BigInteger;

@Service
@Slf4j
public class CommercialOperationsService {
    private final CommercialOperations contract;

    public CommercialOperationsService(
            Web3j web3j,
            @Value("${ethereum.contract.address}") String contractAddress,
            @Value("${ethereum.account.private-key}") String privateKey,
            ContractGasProvider gasProvider
    ) {
        Credentials credentials = Credentials.create(privateKey);
        this.contract = CommercialOperations.load(contractAddress, web3j, credentials, gasProvider);
    }

    public void createOperation(BigInteger id, String type, String status, String seller, String buyer) {
        try {
            contract.createOperation(id, type, status, seller, buyer).send();
            //log.info("Operation created with ID: {}", id);
        } catch (Exception e) {
            //log.error("Error creating operation: ", e);
            throw new RuntimeException("Failed to create operation", e);
        }
    }

    public CommercialOperations.Operation getOperation(BigInteger id) {
        try {
            return contract.getOperation(id).send();
        } catch (Exception e) {
            //log.error("Error getting operation: ", e);
            throw new RuntimeException("Failed to get operation", e);
        }
    }

    public void updateOperationStatus(BigInteger id, String newStatus) {
        try {
            contract.updateOperationStatus(id, newStatus).send();
           // log.info("Operation status updated for ID: {}", id);
        } catch (Exception e) {
           // log.error("Error updating operation status: ", e);
            throw new RuntimeException("Failed to update operation status", e);
        }
    }
}