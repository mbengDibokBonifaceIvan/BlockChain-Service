package com.ivan.blockchain.Service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import com.ivan.blockchain.SmartContracts.Travel_Resource;
import com.ivan.blockchain.util.ResourceStateRequest;

@Service
public class TravelService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;


    public TravelService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    public void addResourceState(ResourceStateRequest resourceStateRequest) throws Exception {

        Travel_Resource travel_Resource = Travel_Resource.load(ContractService.getTravelAdress(), web3j,
                transactionManager, new DefaultGasProvider());
        travel_Resource.addResourceState(
                resourceStateRequest.getIdentification(),
                resourceStateRequest.getCreationTimestamp(),
                resourceStateRequest.getCreator(),
                resourceStateRequest.getBirthPlace(),
                resourceStateRequest.getLocation(),
                resourceStateRequest.getOwner(),
                resourceStateRequest.getTimestamp()

        ).send();
    }

    public Travel_Resource.ResourceState getResourceState(BigInteger identification) throws Exception {

        Travel_Resource travel_Resource = Travel_Resource.load(ContractService.getTravelAdress(), web3j,
                transactionManager, new DefaultGasProvider());
        return travel_Resource.getResourceState(identification).send();
    }

    public List<Travel_Resource.ResourceStateCreatedEventResponse> getResourceStates(BigInteger identification)
            throws Exception {

        Travel_Resource travel_Resource = Travel_Resource.load(ContractService.getTravelAdress(), web3j,
                transactionManager, new DefaultGasProvider());
        BigInteger start = travel_Resource.deploymentBlockNumber().send();
        BigInteger current = travel_Resource.getCurrentBlockNumber().send();
        return travel_Resource
                .resourceStateCreatedEventFlowable(new DefaultBlockParameterNumber(start),
                        new DefaultBlockParameterNumber(current))
                .filter(event -> event.identification.equals(identification))
                .toList()
                .blockingGet();
    }

}
