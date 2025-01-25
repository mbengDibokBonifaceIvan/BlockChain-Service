package com.ivan.blockchain.SmartContracts;


import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class ShipIt extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b50610d0d8061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061003f575f3560e01c80637a495f7d14610043578063d51cd4ac1461007a578063ea9bef1e146100b1575b5f80fd5b61005d600480360381019061005891906105c7565b6100cd565b60405161007198979695949392919061072e565b60405180910390f35b610094600480360381019061008f91906105c7565b61022b565b6040516100a898979695949392919061072e565b60405180910390f35b6100cb60048036038101906100c69190610931565b61033e565b005b5f805f805f8060605f805f808b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f209050805f015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16816001015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1682600201548360030154846004015f9054906101000a900460ff168560050154866006018760070154818054610191906109f1565b80601f01602080910402602001604051908101604052809291908181526020018280546101bd906109f1565b80156102085780601f106101df57610100808354040283529160200191610208565b820191905f5260205f20905b8154815290600101906020018083116101eb57829003601f168201915b505050505091509850985098509850985098509850985050919395975091939597565b5f602052805f5260405f205f91509050805f015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002015490806003015490806004015f9054906101000a900460ff16908060050154908060060180546102b7906109f1565b80601f01602080910402602001604051908101604052809291908181526020018280546102e3906109f1565b801561032e5780601f106103055761010080835404028352916020019161032e565b820191905f5260205f20905b81548152906001019060200180831161031157829003601f168201915b5050505050908060070154905088565b6040518061010001604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1681526020014281526020018581526020015f808111156103a05761039f610619565b5b8152602001848152602001838152602001828152505f803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f820151815f015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020155606082015181600301556080820151816004015f6101000a81548160ff02191690835f8111156104b8576104b7610619565b5b021790555060a0820151816005015560c08201518160060190816104dc9190610bbe565b5060e082015181600701559050508473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fc5aa1f2072d4bb89e53eae2bc5b7e456af3957db575f929d8a64a74384a20e384286868660405161054d9493929190610c8d565b60405180910390a35050505050565b5f604051905090565b5f80fd5b5f80fd5b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6105968261056d565b9050919050565b6105a68161058c565b81146105b0575f80fd5b50565b5f813590506105c18161059d565b92915050565b5f602082840312156105dc576105db610565565b5b5f6105e9848285016105b3565b91505092915050565b6105fb8161058c565b82525050565b5f819050919050565b61061381610601565b82525050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602160045260245ffd5b6001811061065757610656610619565b5b50565b5f81905061066782610646565b919050565b5f6106768261065a565b9050919050565b6106868161066c565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b838110156106c35780820151818401526020810190506106a8565b5f8484015250505050565b5f601f19601f8301169050919050565b5f6106e88261068c565b6106f28185610696565b93506107028185602086016106a6565b61070b816106ce565b840191505092915050565b5f819050919050565b61072881610716565b82525050565b5f610100820190506107425f83018b6105f2565b61074f602083018a6105f2565b61075c604083018961060a565b610769606083018861060a565b610776608083018761067d565b61078360a083018661060a565b81810360c083015261079581856106de565b90506107a460e083018461071f565b9998505050505050505050565b6107ba81610601565b81146107c4575f80fd5b50565b5f813590506107d5816107b1565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610819826106ce565b810181811067ffffffffffffffff82111715610838576108376107e3565b5b80604052505050565b5f61084a61055c565b90506108568282610810565b919050565b5f67ffffffffffffffff821115610875576108746107e3565b5b61087e826106ce565b9050602081019050919050565b828183375f83830152505050565b5f6108ab6108a68461085b565b610841565b9050828152602081018484840111156108c7576108c66107df565b5b6108d284828561088b565b509392505050565b5f82601f8301126108ee576108ed6107db565b5b81356108fe848260208601610899565b91505092915050565b61091081610716565b811461091a575f80fd5b50565b5f8135905061092b81610907565b92915050565b5f805f805f60a0868803121561094a57610949610565565b5b5f610957888289016105b3565b9550506020610968888289016107c7565b9450506040610979888289016107c7565b935050606086013567ffffffffffffffff81111561099a57610999610569565b5b6109a6888289016108da565b92505060806109b78882890161091d565b9150509295509295909350565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610a0857607f821691505b602082108103610a1b57610a1a6109c4565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610a7d7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610a42565b610a878683610a42565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610ac2610abd610ab884610601565b610a9f565b610601565b9050919050565b5f819050919050565b610adb83610aa8565b610aef610ae782610ac9565b848454610a4e565b825550505050565b5f90565b610b03610af7565b610b0e818484610ad2565b505050565b5b81811015610b3157610b265f82610afb565b600181019050610b14565b5050565b601f821115610b7657610b4781610a21565b610b5084610a33565b81016020851015610b5f578190505b610b73610b6b85610a33565b830182610b13565b50505b505050565b5f82821c905092915050565b5f610b965f1984600802610b7b565b1980831691505092915050565b5f610bae8383610b87565b9150826002028217905092915050565b610bc78261068c565b67ffffffffffffffff811115610be057610bdf6107e3565b5b610bea82546109f1565b610bf5828285610b35565b5f60209050601f831160018114610c26575f8415610c14578287015190505b610c1e8582610ba3565b865550610c85565b601f198416610c3486610a21565b5f5b82811015610c5b57848901518255600182019150602085019450602081019050610c36565b86831015610c785784890151610c74601f891682610b87565b8355505b6001600288020188555050505b505050505050565b5f608082019050610ca05f83018761060a565b610cad602083018661060a565b8181036040830152610cbf81856106de565b9050610cce606083018461071f565b9594505050505056fea264697066735822122060e7c7527422a31c74760295c12fc1d5b9206f74b5c1472f03423f3045cf595464736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_SHIPMENTS = "shipments";

    public static final String FUNC_CREATESHIPMENT = "createShipment";

    public static final String FUNC_GETSHIPMENT = "getShipment";

    public static final Event SHIPMENTCREATED_EVENT = new Event("ShipmentCreated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected ShipIt(String contractAddress, Web3j web3j, Credentials credentials,
                     BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ShipIt(String contractAddress, Web3j web3j, Credentials credentials,
                     ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ShipIt(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                     BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ShipIt(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                     ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ShipmentCreatedEventResponse> getShipmentCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SHIPMENTCREATED_EVENT, transactionReceipt);
        ArrayList<ShipmentCreatedEventResponse> responses = new ArrayList<ShipmentCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ShipmentCreatedEventResponse typedResponse = new ShipmentCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.receiver = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.creationDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.packageWeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.depot = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.receiverHash = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ShipmentCreatedEventResponse getShipmentCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SHIPMENTCREATED_EVENT, log);
        ShipmentCreatedEventResponse typedResponse = new ShipmentCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.receiver = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.creationDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.packageWeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.depot = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.receiverHash = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<ShipmentCreatedEventResponse> shipmentCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getShipmentCreatedEventFromLog(log));
    }

    public Flowable<ShipmentCreatedEventResponse> shipmentCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SHIPMENTCREATED_EVENT));
        return shipmentCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>> call_shipments(
            String param0) {
        final Function function = new Function(FUNC_SHIPMENTS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>>(function,
                new Callable<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>>() {
                    @Override
                    public Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]> call(
                    ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (String) results.get(6).getValue(),
                                (byte[]) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> send_shipments(String param0) {
        final Function function = new Function(
                FUNC_SHIPMENTS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send_createShipment(String _receiver,
                                                                      BigInteger _departureDate, BigInteger _packageWeight, String _depot,
                                                                      byte[] _receiverHash) {
        final Function function = new Function(
                FUNC_CREATESHIPMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_receiver),
                        new org.web3j.abi.datatypes.generated.Uint256(_departureDate),
                        new org.web3j.abi.datatypes.generated.Uint256(_packageWeight),
                        new org.web3j.abi.datatypes.Utf8String(_depot),
                        new org.web3j.abi.datatypes.generated.Bytes32(_receiverHash)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>> call_getShipment(
            String _sender) {
        final Function function = new Function(FUNC_GETSHIPMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_sender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>>(function,
                new Callable<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>>() {
                    @Override
                    public Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]> call(
                    ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, byte[]>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (String) results.get(6).getValue(),
                                (byte[]) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> send_getShipment(String _sender) {
        final Function function = new Function(
                FUNC_GETSHIPMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_sender)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ShipIt load(String contractAddress, Web3j web3j, Credentials credentials,
                              BigInteger gasPrice, BigInteger gasLimit) {
        return new ShipIt(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ShipIt load(String contractAddress, Web3j web3j,
                              TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ShipIt(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ShipIt load(String contractAddress, Web3j web3j, Credentials credentials,
                              ContractGasProvider contractGasProvider) {
        return new ShipIt(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ShipIt load(String contractAddress, Web3j web3j,
                              TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ShipIt(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ShipIt> deploy(Web3j web3j, Credentials credentials,
                                            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ShipIt.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ShipIt> deploy(Web3j web3j, Credentials credentials,
                                            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ShipIt.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ShipIt> deploy(Web3j web3j, TransactionManager transactionManager,
                                            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ShipIt.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ShipIt> deploy(Web3j web3j, TransactionManager transactionManager,
                                            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ShipIt.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

   /* public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }
*/
    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ShipmentCreatedEventResponse extends BaseEventResponse {
        public String sender;

        public String receiver;

        public BigInteger creationDate;

        public BigInteger packageWeight;

        public String depot;

        public byte[] receiverHash;
    }
}

