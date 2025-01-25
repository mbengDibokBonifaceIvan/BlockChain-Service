package com.ivan.blockchain.Service;


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
        System.out.println("Contract deployed successfully! Contract address: " + ContractService.getContractAddress());
        System.out.println("Com Ops Contract deployed successfully! Contract address: " + ContractService.getComOpsAddress());

    }
}