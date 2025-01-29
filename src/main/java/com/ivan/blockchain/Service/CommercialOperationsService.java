package com.ivan.blockchain.Service;
import com.ivan.blockchain.SmartContracts.CommercialOperations;
import com.ivan.blockchain.util.CommercialOperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.List;

@Service
public class CommercialOperationsService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;

   // private CommercialOperations contract;
    @Autowired
    public CommercialOperationsService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
      //  this.contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j, transactionManager, new DefaultGasProvider());
    }

    public CommercialOperationDTO createOperation(CommercialOperationDTO dto) throws Exception {
       
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j,
                transactionManager, new DefaultGasProvider());
       
        TransactionReceipt receipt = contract.send_createOperation(
                dto.getId(),
                dto.getOperationType(),
                dto.getStatus(),
                dto.getSeller(),
                dto.getBuyer()
        ).send();

        return dto;
    }

    public CommercialOperationDTO getOperation(BigInteger id) throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j, transactionManager, new DefaultGasProvider());

        CommercialOperations.Operation contractOperation = contract.call_getOperation(id).send();

        return new CommercialOperationDTO(
                contractOperation.id,
                contractOperation.operationType,
                contractOperation.status,
                contractOperation.seller,
                contractOperation.buyer,
                contractOperation.blockNumber,
                contractOperation.blockTimestamp
        );
    }

    public List<BigInteger> getAllOperationIds() throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j, transactionManager, new DefaultGasProvider());

        return contract.call_getAllOperationIds().send();
    }

    public void updateOperationStatus(BigInteger id, String newStatus) throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j, transactionManager, new DefaultGasProvider());

        contract.send_updateOperationStatus(id, newStatus).send();
    }

    public Tuple2<BigInteger, BigInteger> getOperationBlockInfo(BigInteger id) throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsAddress(), web3j, transactionManager, new DefaultGasProvider());

        return contract.call_getOperationBlockInfo(id).send();
    }
}