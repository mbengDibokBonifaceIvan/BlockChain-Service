package com.ivan.blockchain.Web3jConfig;
//
//import lombok.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.tx.ChainId;
//import org.web3j.tx.RawTransactionManager;
//import org.web3j.tx.TransactionManager;
//
//@Configuration
//public class Web3jConfig {
//
//
//    private String ethereumNodeUrl = "http://192.168.43.245:63595";
//
//    @Bean
//    public Web3j web3j() {
//        return Web3j.build(new HttpService(ethereumNodeUrl));
//    }
//
//    @Bean
//    public Credentials getCredentials(String privateKey) {
//        return Credentials.create(privateKey);
//    }
//
//    @Bean
//    public TransactionManager transactionManager2(Web3j web3j, Credentials credentials) {
//        return new RawTransactionManager(
//                web3j,
//                credentials,
//                81810 // Adjust based on network 585858
//        );
//    }
//}



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
public class Web3jConfig {


    private String networkUrl = "http://192.168.43.57:32775";


    private String privateKey = "bcdf20249abf0ed6d944c0288fad489e33f66b3960d9e6229c1cd214ed3bbe31";

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(networkUrl));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new StaticGasProvider(
                BigInteger.valueOf(20000000000L), // prix du gas
                BigInteger.valueOf(6721975L)      // limite de gas
        );
    }

    @Bean
    public TransactionManager transactionManager2(Web3j web3j, Credentials credentials) {
        return new RawTransactionManager(web3j, credentials);
    }
}