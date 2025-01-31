package com.ivan.blockchain.contracts.javaWrapper;

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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class CommercialOperations extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b50610de28061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610034575f3560e01c8063226784d9146100385780632ba243f614610054575b5f80fd5b610052600480360381019061004d919061068f565b610084565b005b61006e600480360381019061006991906107b8565b6101c0565b60405161007b91906109dc565b60405180910390f35b5f6040518060e001604052808981526020018881526020018781526020018681526020018581526020018481526020018381525090505f808981526020019081526020015f2081908060018154018082558091505060019003905f5260205f2090600702015f909190919091505f820151815f0155602082015181600101908161010e9190610bf6565b5060408201518160020190816101249190610bf6565b50606082015181600301908161013a9190610bf6565b5060808201518160040190816101509190610bf6565b5060a0820151816005015560c08201518160060190816101709190610bf6565b5050507fe2dbcdb576af09eee073ea3e3d259ffa3e514eba6fc3bea1b5da2e6cccb2e72e888888888888886040516101ae9796959493929190610d1c565b60405180910390a15050505050505050565b60605f808381526020019081526020015f20805480602002602001604051908101604052809291908181526020015f905b82821015610504578382905f5260205f2090600702016040518060e00160405290815f820154815260200160018201805461022b90610a29565b80601f016020809104026020016040519081016040528092919081815260200182805461025790610a29565b80156102a25780601f10610279576101008083540402835291602001916102a2565b820191905f5260205f20905b81548152906001019060200180831161028557829003601f168201915b505050505081526020016002820180546102bb90610a29565b80601f01602080910402602001604051908101604052809291908181526020018280546102e790610a29565b80156103325780601f1061030957610100808354040283529160200191610332565b820191905f5260205f20905b81548152906001019060200180831161031557829003601f168201915b5050505050815260200160038201805461034b90610a29565b80601f016020809104026020016040519081016040528092919081815260200182805461037790610a29565b80156103c25780601f10610399576101008083540402835291602001916103c2565b820191905f5260205f20905b8154815290600101906020018083116103a557829003601f168201915b505050505081526020016004820180546103db90610a29565b80601f016020809104026020016040519081016040528092919081815260200182805461040790610a29565b80156104525780601f1061042957610100808354040283529160200191610452565b820191905f5260205f20905b81548152906001019060200180831161043557829003601f168201915b505050505081526020016005820154815260200160068201805461047590610a29565b80601f01602080910402602001604051908101604052809291908181526020018280546104a190610a29565b80156104ec5780601f106104c3576101008083540402835291602001916104ec565b820191905f5260205f20905b8154815290600101906020018083116104cf57829003601f168201915b505050505081525050815260200190600101906101f1565b505050509050919050565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b61053281610520565b811461053c575f80fd5b50565b5f8135905061054d81610529565b92915050565b5f80fd5b5f80fd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6105a18261055b565b810181811067ffffffffffffffff821117156105c0576105bf61056b565b5b80604052505050565b5f6105d261050f565b90506105de8282610598565b919050565b5f67ffffffffffffffff8211156105fd576105fc61056b565b5b6106068261055b565b9050602081019050919050565b828183375f83830152505050565b5f61063361062e846105e3565b6105c9565b90508281526020810184848401111561064f5761064e610557565b5b61065a848285610613565b509392505050565b5f82601f83011261067657610675610553565b5b8135610686848260208601610621565b91505092915050565b5f805f805f805f60e0888a0312156106aa576106a9610518565b5b5f6106b78a828b0161053f565b975050602088013567ffffffffffffffff8111156106d8576106d761051c565b5b6106e48a828b01610662565b965050604088013567ffffffffffffffff8111156107055761070461051c565b5b6107118a828b01610662565b955050606088013567ffffffffffffffff8111156107325761073161051c565b5b61073e8a828b01610662565b945050608088013567ffffffffffffffff81111561075f5761075e61051c565b5b61076b8a828b01610662565b93505060a061077c8a828b0161053f565b92505060c088013567ffffffffffffffff81111561079d5761079c61051c565b5b6107a98a828b01610662565b91505092959891949750929550565b5f602082840312156107cd576107cc610518565b5b5f6107da8482850161053f565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b61081581610520565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610852578082015181840152602081019050610837565b5f8484015250505050565b5f6108678261081b565b6108718185610825565b9350610881818560208601610835565b61088a8161055b565b840191505092915050565b5f60e083015f8301516108aa5f86018261080c565b50602083015184820360208601526108c2828261085d565b915050604083015184820360408601526108dc828261085d565b915050606083015184820360608601526108f6828261085d565b91505060808301518482036080860152610910828261085d565b91505060a083015161092560a086018261080c565b5060c083015184820360c086015261093d828261085d565b9150508091505092915050565b5f6109558383610895565b905092915050565b5f602082019050919050565b5f610973826107e3565b61097d81856107ed565b93508360208202850161098f856107fd565b805f5b858110156109ca57848403895281516109ab858261094a565b94506109b68361095d565b925060208a01995050600181019050610992565b50829750879550505050505092915050565b5f6020820190508181035f8301526109f48184610969565b905092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610a4057607f821691505b602082108103610a5357610a526109fc565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610ab57fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610a7a565b610abf8683610a7a565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610afa610af5610af084610520565b610ad7565b610520565b9050919050565b5f819050919050565b610b1383610ae0565b610b27610b1f82610b01565b848454610a86565b825550505050565b5f90565b610b3b610b2f565b610b46818484610b0a565b505050565b5b81811015610b6957610b5e5f82610b33565b600181019050610b4c565b5050565b601f821115610bae57610b7f81610a59565b610b8884610a6b565b81016020851015610b97578190505b610bab610ba385610a6b565b830182610b4b565b50505b505050565b5f82821c905092915050565b5f610bce5f1984600802610bb3565b1980831691505092915050565b5f610be68383610bbf565b9150826002028217905092915050565b610bff8261081b565b67ffffffffffffffff811115610c1857610c1761056b565b5b610c228254610a29565b610c2d828285610b6d565b5f60209050601f831160018114610c5e575f8415610c4c578287015190505b610c568582610bdb565b865550610cbd565b601f198416610c6c86610a59565b5f5b82811015610c9357848901518255600182019150602085019450602081019050610c6e565b86831015610cb05784890151610cac601f891682610bbf565b8355505b6001600288020188555050505b505050505050565b610cce81610520565b82525050565b5f82825260208201905092915050565b5f610cee8261081b565b610cf88185610cd4565b9350610d08818560208601610835565b610d118161055b565b840191505092915050565b5f60e082019050610d2f5f83018a610cc5565b8181036020830152610d418189610ce4565b90508181036040830152610d558188610ce4565b90508181036060830152610d698187610ce4565b90508181036080830152610d7d8186610ce4565b9050610d8c60a0830185610cc5565b81810360c0830152610d9e8184610ce4565b90509897505050505050505056fea2646970667358221220394ba9277dbe8ebdc898ce4a9e44a97b384f7f992fa1060acf9ff3eb772f0c5664736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDOPERATIONSTATE = "addOperationState";

    public static final String FUNC_GETOPERATIONSTATES = "getOperationStates";

    public static final Event OPERATIONSTATECREATED_EVENT = new Event("OperationStateCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected CommercialOperations(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CommercialOperations(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CommercialOperations(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CommercialOperations(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<OperationStateCreatedEventResponse> getOperationStateCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OPERATIONSTATECREATED_EVENT, transactionReceipt);
        ArrayList<OperationStateCreatedEventResponse> responses = new ArrayList<OperationStateCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OperationStateCreatedEventResponse typedResponse = new OperationStateCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identification = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operationType = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.producer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.consumer = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OperationStateCreatedEventResponse getOperationStateCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OPERATIONSTATECREATED_EVENT, log);
        OperationStateCreatedEventResponse typedResponse = new OperationStateCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.identification = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.operationType = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.status = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.producer = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.consumer = (String) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.data = (String) eventValues.getNonIndexedValues().get(6).getValue();
        return typedResponse;
    }

    public Flowable<OperationStateCreatedEventResponse> operationStateCreatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOperationStateCreatedEventFromLog(log));
    }

    public Flowable<OperationStateCreatedEventResponse> operationStateCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPERATIONSTATECREATED_EVENT));
        return operationStateCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> send_addOperationState(BigInteger identification,
            String operationType, String status, String producer, String consumer,
            BigInteger timestamp, String data) {
        final Function function = new Function(
                FUNC_ADDOPERATIONSTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                new org.web3j.abi.datatypes.Utf8String(operationType), 
                new org.web3j.abi.datatypes.Utf8String(status), 
                new org.web3j.abi.datatypes.Utf8String(producer), 
                new org.web3j.abi.datatypes.Utf8String(consumer), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                new org.web3j.abi.datatypes.Utf8String(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> call_getOperationStates(BigInteger identification) {
        final Function function = new Function(FUNC_GETOPERATIONSTATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<OperationState>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> send_getOperationStates(
            BigInteger identification) {
        final Function function = new Function(
                FUNC_GETOPERATIONSTATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CommercialOperations load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommercialOperations(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CommercialOperations load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommercialOperations(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CommercialOperations load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CommercialOperations(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CommercialOperations load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CommercialOperations(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CommercialOperations> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CommercialOperations.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<CommercialOperations> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CommercialOperations.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<CommercialOperations> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CommercialOperations.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<CommercialOperations> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CommercialOperations.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

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

    public static class OperationState extends DynamicStruct {
        public BigInteger identification;

        public String operationType;

        public String status;

        public String producer;

        public String consumer;

        public BigInteger timestamp;

        public String data;

        public OperationState(BigInteger identification, String operationType, String status,
                String producer, String consumer, BigInteger timestamp, String data) {
            super(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                    new org.web3j.abi.datatypes.Utf8String(operationType), 
                    new org.web3j.abi.datatypes.Utf8String(status), 
                    new org.web3j.abi.datatypes.Utf8String(producer), 
                    new org.web3j.abi.datatypes.Utf8String(consumer), 
                    new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                    new org.web3j.abi.datatypes.Utf8String(data));
            this.identification = identification;
            this.operationType = operationType;
            this.status = status;
            this.producer = producer;
            this.consumer = consumer;
            this.timestamp = timestamp;
            this.data = data;
        }

        public OperationState(Uint256 identification, Utf8String operationType, Utf8String status,
                Utf8String producer, Utf8String consumer, Uint256 timestamp, Utf8String data) {
            super(identification, operationType, status, producer, consumer, timestamp, data);
            this.identification = identification.getValue();
            this.operationType = operationType.getValue();
            this.status = status.getValue();
            this.producer = producer.getValue();
            this.consumer = consumer.getValue();
            this.timestamp = timestamp.getValue();
            this.data = data.getValue();
        }
    }

    public static class OperationStateCreatedEventResponse extends BaseEventResponse {
        public BigInteger identification;

        public String operationType;

        public String status;

        public String producer;

        public String consumer;

        public BigInteger timestamp;

        public String data;
    }
}
