package com.ivan.blockchain.Service;


import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import com.ivan.blockchain.SmartContracts.Resource;
import com.ivan.blockchain.SmartContracts.Resource.ResourceData;
import com.ivan.blockchain.util.ResourceDTO;
import com.ivan.blockchain.util.ResourceResponseDTO;

@Service
public class ResourceService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;

    @Autowired
    public ResourceService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    public ResourceDTO createResource(ResourceDTO dto) throws Exception {

        Resource contract = Resource.load(ContractService.getResourceAddress(), web3j,
                transactionManager, new DefaultGasProvider());

        TransactionReceipt receipt = contract.createResource(
                dto.getResourceId(),
                dto.getCreator(),
                dto.getOriginLocation()

        ).send();

        return dto;
    }
    
    public ResourceResponseDTO getResource(BigInteger id) throws Exception {
        Resource contract = Resource.load(ContractService.getResourceAddress(), web3j, transactionManager,
                new DefaultGasProvider());

        ResourceData contractResource = contract.getResource(id).send();

        return new ResourceResponseDTO(contractResource);
    }

    
}
