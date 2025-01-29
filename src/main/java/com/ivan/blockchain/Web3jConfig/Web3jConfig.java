package com.ivan.blockchain.Web3jConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

@Configuration
public class Web3jConfig {


    private String networkUrl = "http://127.0.0.1:61759";

    private String privateKey = "bcdf20249abf0ed6d944c0288fad489e33f66b3960d9e6229c1cd214ed3bbe31"; // Add your private key here
    //private String privateKey = "a492823c3e193d6c595f37a18e3c06650cf4c74558cc818b16130b293716106f";

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(networkUrl));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey);
    }

    @Bean
    public TransactionManager transactionManager2(Web3j web3j, Credentials credentials) {
        return new RawTransactionManager(web3j, credentials);
    }
}