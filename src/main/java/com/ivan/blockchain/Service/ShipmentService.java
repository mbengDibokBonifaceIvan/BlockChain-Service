package com.ivan.blockchain.Service;

import com.ivan.blockchain.SmartContracts.ShipIt;
import com.ivan.blockchain.util.ShipmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
//import com.example.demo.contract.ShipIt; // Replace with the actual package name of your generated wrapper class

@Service
public class ShipmentService {

    private final Web3j web3j;
    private final TransactionManager transactionManager;

    @Autowired
    public ShipmentService(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    public void createShipment(String receiver, long departureDate, int packageWeight, String depot, String receiverHash) throws Exception {
        ShipIt contract = ShipIt.load(ContractService.getContractAddress(), web3j, transactionManager, new DefaultGasProvider());
        byte[] receiverHash2 = new byte[] {
                0x12, 0x34, 0x56, 0x78, (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0,
                0x12, 0x34, 0x56, 0x78, (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0,
                0x12, 0x34, 0x56, 0x78, (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0,
                0x12, 0x34, 0x56, 0x78, (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0
        };
        contract.send_createShipment(receiver, BigInteger.valueOf(departureDate), BigInteger.valueOf(packageWeight), depot, receiverHash2).send();
    }


    public ShipmentDetails getShipment(String senderAddress) throws Exception {
        ShipIt contract = ShipIt.load(ContractService.getContractAddress(), web3j, transactionManager, new DefaultGasProvider());
        Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]> shipment = contract.call_getShipment(senderAddress).send();

        return new ShipmentDetails(
                shipment.getValue1(),
                shipment.getValue2(),
                shipment.getValue3().longValue(),
                shipment.getValue4().longValue(),
                shipment.getValue5().intValue(),
                shipment.getValue6().intValue(),
                shipment.getValue7(),
                shipment.getValue8()
        );
    }

}
