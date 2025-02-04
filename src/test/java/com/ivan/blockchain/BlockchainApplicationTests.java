package com.ivan.blockchain;

import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;


import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import com.ivan.blockchain.service.ContractService;

@SpringBootTest
public class BlockchainApplicationTests {

	@Mock
	private Web3j web3j;

	@Mock
	private TransactionManager transactionManager;

	@Mock
	private ContractService contractService;
}