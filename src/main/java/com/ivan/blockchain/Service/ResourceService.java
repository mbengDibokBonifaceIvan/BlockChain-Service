//package com.ivan.blockchain.Service;
//
//
//import com.ivan.blockchain.SmartContracts.ResourceManagement;
//import com.ivan.blockchain.util.ResourceDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.tuples.generated.Tuple7;
//import org.web3j.tx.gas.ContractGasProvider;
//import java.math.BigInteger;
//
//@Service
//@Slf4j
//public class ResourceService {
//
//    private final Web3j web3j;
//    private final ContractGasProvider gasProvider;
//    private final ResourceManagement contract;
//
//    public ResourceService(
//            Web3j web3j,
//            ContractGasProvider gasProvider,
//            @Value("${ethereum.contract.address}") String contractAddress,
//            @Value("${ethereum.account.private-key}") String privateKey
//    ) {
//        this.web3j = web3j;
//        this.gasProvider = gasProvider;
//        Credentials credentials = Credentials.create(privateKey);
//        this.contract = ResourceManagement.load(contractAddress, web3j, credentials, gasProvider);
//    }
//
//    public BigInteger createResource(String originLocation) throws Exception {
//        var receipt = contract.createResource(originLocation).send();
//        var events = ResourceManagement.getResourceCreatedEvents(receipt);
//        if (!events.isEmpty()) {
//            return events.get(0).resourceId;
//        }
//        throw new RuntimeException("Resource creation event not found");
//    }
//
//    public void updateLocation(BigInteger resourceId, String newLocation) throws Exception {
//        contract.updateLocation(resourceId, newLocation).send();
//    }
//
//    public void transferOwnership(BigInteger resourceId, String newOwner) throws Exception {
//        contract.transferOwnership(resourceId, newOwner).send();
//    }
//
//    public ResourceDTO getResource(BigInteger resourceId) throws Exception {
//        Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> result =
//                contract.getResource(resourceId).send();
//
//        ResourceDTO dto = new ResourceDTO();
//        dto.setResourceId(result.component1());
//        dto.setCreationDate(result.component2());
//        dto.setCreator(result.component3());
//        dto.setOriginLocation(result.component4());
//        dto.setCurrentLocation(result.component5());
//        dto.setCurrentOwner(result.component6());
//        dto.setLastUpdateDate(result.component7());
//
//        return dto;
//    }
//}
