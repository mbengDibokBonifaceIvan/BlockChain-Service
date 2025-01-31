package com.ivan.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContractDeploymentRunner implements CommandLineRunner {

    @Autowired
    private ContractService contractService;

    @Override
    public void run(String... args) throws Exception {
        contractService.deployContract();
        System.out.println("Commercial Operations Contract deployed successfully! Contract address: "
                + ContractService.getComOpsV2Address());
        System.out.println("Resource Traceability Contract deployed successfully! Contract address: "
                + ContractService.getResourceV2Address());
    }
}
