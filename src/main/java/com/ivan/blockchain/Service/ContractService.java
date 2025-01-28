package com.ivan.blockchain.Service;


import com.ivan.blockchain.SmartContracts.CommercialOperations;
import com.ivan.blockchain.SmartContracts.ResourceManagement;
import com.ivan.blockchain.SmartContracts.ShipIt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class ContractService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private static String contractAddress;
    private static String ComOpsAddress;
    private static String resourceAddress;

    public static String getResourceAddress() {
        return resourceAddress;
    }

    public static String getComOpsAddress() {
        return ComOpsAddress;
    }

    @Autowired
    public ContractService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    public void deployContract() throws Exception {
        ShipIt contract = ShipIt.deploy(web3j, transactionManager, new DefaultGasProvider()).send();
        contractAddress = contract.getContractAddress();
        CommercialOperations ComOpsContract = CommercialOperations.deploy(web3j, transactionManager, new DefaultGasProvider()).send();
        ComOpsAddress = ComOpsContract.getContractAddress();
        ResourceManagement resourceManagement = ResourceManagement.deploy(web3j, transactionManager, new DefaultGasProvider()).send();
        resourceAddress = resourceManagement.getContractAddress();


    }

    public static String getContractAddress() {
        return contractAddress;
    }

}