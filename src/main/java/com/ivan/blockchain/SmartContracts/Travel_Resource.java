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
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.2.
 */
@SuppressWarnings("rawtypes")
public class Travel_Resource extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b5043600181905550610f47806100245f395ff3fe608060405234801561000f575f80fd5b5060043610610055575f3560e01c8063637d6e32146100595780636d6af4de146100895780636fd902e1146100bf578063c8169fd0146100dd578063cf004217146100f9575b5f80fd5b610073600480360381019061006e9190610784565b610117565b60405161008091906108f6565b60405180910390f35b6100a3600480360381019061009e9190610784565b61039e565b6040516100b6979695949392919061096d565b60405180910390f35b6100c76105f3565b6040516100d491906109f6565b60405180910390f35b6100f760048036038101906100f29190610b3b565b6105fa565b005b610101610700565b60405161010e91906109f6565b60405180910390f35b61011f610706565b5f808381526020019081526020015f206040518060e00160405290815f82015481526020016001820154815260200160028201805461015d90610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461018990610c75565b80156101d45780601f106101ab576101008083540402835291602001916101d4565b820191905f5260205f20905b8154815290600101906020018083116101b757829003601f168201915b505050505081526020016003820180546101ed90610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461021990610c75565b80156102645780601f1061023b57610100808354040283529160200191610264565b820191905f5260205f20905b81548152906001019060200180831161024757829003601f168201915b5050505050815260200160048201805461027d90610c75565b80601f01602080910402602001604051908101604052809291908181526020018280546102a990610c75565b80156102f45780601f106102cb576101008083540402835291602001916102f4565b820191905f5260205f20905b8154815290600101906020018083116102d757829003601f168201915b5050505050815260200160058201805461030d90610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461033990610c75565b80156103845780601f1061035b57610100808354040283529160200191610384565b820191905f5260205f20905b81548152906001019060200180831161036757829003601f168201915b505050505081526020016006820154815250509050919050565b5f602052805f5260405f205f91509050805f0154908060010154908060020180546103c890610c75565b80601f01602080910402602001604051908101604052809291908181526020018280546103f490610c75565b801561043f5780601f106104165761010080835404028352916020019161043f565b820191905f5260205f20905b81548152906001019060200180831161042257829003601f168201915b50505050509080600301805461045490610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461048090610c75565b80156104cb5780601f106104a2576101008083540402835291602001916104cb565b820191905f5260205f20905b8154815290600101906020018083116104ae57829003601f168201915b5050505050908060040180546104e090610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461050c90610c75565b80156105575780601f1061052e57610100808354040283529160200191610557565b820191905f5260205f20905b81548152906001019060200180831161053a57829003601f168201915b50505050509080600501805461056c90610c75565b80601f016020809104026020016040519081016040528092919081815260200182805461059890610c75565b80156105e35780601f106105ba576101008083540402835291602001916105e3565b820191905f5260205f20905b8154815290600101906020018083116105c657829003601f168201915b5050505050908060060154905087565b5f43905090565b6040518060e00160405280888152602001878152602001868152602001858152602001848152602001838152602001828152505f808981526020019081526020015f205f820151815f01556020820151816001015560408201518160020190816106649190610e42565b50606082015181600301908161067a9190610e42565b5060808201518160040190816106909190610e42565b5060a08201518160050190816106a69190610e42565b5060c082015181600601559050507fb115248de181c617472eeec9836e68dc8773ea7bdb967c3af187ab1a42e003c7878787878787876040516106ef979695949392919061096d565b60405180910390a150505050505050565b60015481565b6040518060e001604052805f81526020015f8152602001606081526020016060815260200160608152602001606081526020015f81525090565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b61076381610751565b811461076d575f80fd5b50565b5f8135905061077e8161075a565b92915050565b5f6020828403121561079957610798610749565b5b5f6107a684828501610770565b91505092915050565b6107b881610751565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b838110156107f55780820151818401526020810190506107da565b5f8484015250505050565b5f601f19601f8301169050919050565b5f61081a826107be565b61082481856107c8565b93506108348185602086016107d8565b61083d81610800565b840191505092915050565b5f60e083015f83015161085d5f8601826107af565b50602083015161087060208601826107af565b50604083015184820360408601526108888282610810565b915050606083015184820360608601526108a28282610810565b915050608083015184820360808601526108bc8282610810565b91505060a083015184820360a08601526108d68282610810565b91505060c08301516108eb60c08601826107af565b508091505092915050565b5f6020820190508181035f83015261090e8184610848565b905092915050565b61091f81610751565b82525050565b5f82825260208201905092915050565b5f61093f826107be565b6109498185610925565b93506109598185602086016107d8565b61096281610800565b840191505092915050565b5f60e0820190506109805f83018a610916565b61098d6020830189610916565b818103604083015261099f8188610935565b905081810360608301526109b38187610935565b905081810360808301526109c78186610935565b905081810360a08301526109db8185610935565b90506109ea60c0830184610916565b98975050505050505050565b5f602082019050610a095f830184610916565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610a4d82610800565b810181811067ffffffffffffffff82111715610a6c57610a6b610a17565b5b80604052505050565b5f610a7e610740565b9050610a8a8282610a44565b919050565b5f67ffffffffffffffff821115610aa957610aa8610a17565b5b610ab282610800565b9050602081019050919050565b828183375f83830152505050565b5f610adf610ada84610a8f565b610a75565b905082815260208101848484011115610afb57610afa610a13565b5b610b06848285610abf565b509392505050565b5f82601f830112610b2257610b21610a0f565b5b8135610b32848260208601610acd565b91505092915050565b5f805f805f805f60e0888a031215610b5657610b55610749565b5b5f610b638a828b01610770565b9750506020610b748a828b01610770565b965050604088013567ffffffffffffffff811115610b9557610b9461074d565b5b610ba18a828b01610b0e565b955050606088013567ffffffffffffffff811115610bc257610bc161074d565b5b610bce8a828b01610b0e565b945050608088013567ffffffffffffffff811115610bef57610bee61074d565b5b610bfb8a828b01610b0e565b93505060a088013567ffffffffffffffff811115610c1c57610c1b61074d565b5b610c288a828b01610b0e565b92505060c0610c398a828b01610770565b91505092959891949750929550565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610c8c57607f821691505b602082108103610c9f57610c9e610c48565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610d017fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610cc6565b610d0b8683610cc6565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610d46610d41610d3c84610751565b610d23565b610751565b9050919050565b5f819050919050565b610d5f83610d2c565b610d73610d6b82610d4d565b848454610cd2565b825550505050565b5f90565b610d87610d7b565b610d92818484610d56565b505050565b5b81811015610db557610daa5f82610d7f565b600181019050610d98565b5050565b601f821115610dfa57610dcb81610ca5565b610dd484610cb7565b81016020851015610de3578190505b610df7610def85610cb7565b830182610d97565b50505b505050565b5f82821c905092915050565b5f610e1a5f1984600802610dff565b1980831691505092915050565b5f610e328383610e0b565b9150826002028217905092915050565b610e4b826107be565b67ffffffffffffffff811115610e6457610e63610a17565b5b610e6e8254610c75565b610e79828285610db9565b5f60209050601f831160018114610eaa575f8415610e98578287015190505b610ea28582610e27565b865550610f09565b601f198416610eb886610ca5565b5f5b82811015610edf57848901518255600182019150602085019450602081019050610eba565b86831015610efc5784890151610ef8601f891682610e0b565b8355505b6001600288020188555050505b50505050505056fea264697066735822122070db6cd246a262a5d3c9a1c00ca1d79165e8b0f0591055b51c46ec34ec2f2d9064736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DEPLOYMENTBLOCKNUMBER = "deploymentBlockNumber";

    public static final String FUNC_RESOURCESTATES = "resourceStates";

    public static final String FUNC_ADDRESOURCESTATE = "addResourceState";

    public static final String FUNC_GETRESOURCESTATE = "getResourceState";

    public static final String FUNC_GETCURRENTBLOCKNUMBER = "getCurrentBlockNumber";

    public static final Event RESOURCESTATECREATED_EVENT = new Event("ResourceStateCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Travel_Resource(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Travel_Resource(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Travel_Resource(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Travel_Resource(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ResourceStateCreatedEventResponse> getResourceStateCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESOURCESTATECREATED_EVENT, transactionReceipt);
        ArrayList<ResourceStateCreatedEventResponse> responses = new ArrayList<ResourceStateCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResourceStateCreatedEventResponse typedResponse = new ResourceStateCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identification = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.creationTimestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.creator = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.birthPlace = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.location = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceStateCreatedEventResponse getResourceStateCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCESTATECREATED_EVENT, log);
        ResourceStateCreatedEventResponse typedResponse = new ResourceStateCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.identification = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.creationTimestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.creator = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.birthPlace = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.location = (String) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.owner = (String) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
        return typedResponse;
    }

    public Flowable<ResourceStateCreatedEventResponse> resourceStateCreatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getResourceStateCreatedEventFromLog(log));
    }

    public Flowable<ResourceStateCreatedEventResponse> resourceStateCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESOURCESTATECREATED_EVENT));
        return resourceStateCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> deploymentBlockNumber() {
        final Function function = new Function(FUNC_DEPLOYMENTBLOCKNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>> resourceStates(
            BigInteger param0) {
        final Function function = new Function(FUNC_RESOURCESTATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>>(function,
                new Callable<Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> addResourceState(BigInteger identification,
            BigInteger creationTimestamp, String creator, String birthPlace, String location,
            String owner, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_ADDRESOURCESTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                new org.web3j.abi.datatypes.generated.Uint256(creationTimestamp), 
                new org.web3j.abi.datatypes.Utf8String(creator), 
                new org.web3j.abi.datatypes.Utf8String(birthPlace), 
                new org.web3j.abi.datatypes.Utf8String(location), 
                new org.web3j.abi.datatypes.Utf8String(owner), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<ResourceState> getResourceState(BigInteger identification) {
        final Function function = new Function(FUNC_GETRESOURCESTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ResourceState>() {}));
        return executeRemoteCallSingleValueReturn(function, ResourceState.class);
    }

    public RemoteFunctionCall<BigInteger> getCurrentBlockNumber() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Travel_Resource load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Travel_Resource(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Travel_Resource load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Travel_Resource(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Travel_Resource load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Travel_Resource(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Travel_Resource load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Travel_Resource(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Travel_Resource> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Travel_Resource.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<Travel_Resource> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Travel_Resource.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Travel_Resource> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Travel_Resource.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Travel_Resource> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Travel_Resource.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ResourceState extends DynamicStruct {
        public BigInteger identification;

        public BigInteger creationTimestamp;

        public String creator;

        public String birthPlace;

        public String location;

        public String owner;

        public BigInteger timestamp;

        public ResourceState(BigInteger identification, BigInteger creationTimestamp,
                String creator, String birthPlace, String location, String owner,
                BigInteger timestamp) {
            super(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                    new org.web3j.abi.datatypes.generated.Uint256(creationTimestamp), 
                    new org.web3j.abi.datatypes.Utf8String(creator), 
                    new org.web3j.abi.datatypes.Utf8String(birthPlace), 
                    new org.web3j.abi.datatypes.Utf8String(location), 
                    new org.web3j.abi.datatypes.Utf8String(owner), 
                    new org.web3j.abi.datatypes.generated.Uint256(timestamp));
            this.identification = identification;
            this.creationTimestamp = creationTimestamp;
            this.creator = creator;
            this.birthPlace = birthPlace;
            this.location = location;
            this.owner = owner;
            this.timestamp = timestamp;
        }

        public ResourceState(Uint256 identification, Uint256 creationTimestamp, Utf8String creator,
                Utf8String birthPlace, Utf8String location, Utf8String owner, Uint256 timestamp) {
            super(identification, creationTimestamp, creator, birthPlace, location, owner, timestamp);
            this.identification = identification.getValue();
            this.creationTimestamp = creationTimestamp.getValue();
            this.creator = creator.getValue();
            this.birthPlace = birthPlace.getValue();
            this.location = location.getValue();
            this.owner = owner.getValue();
            this.timestamp = timestamp.getValue();
        }
    }

    public static class ResourceStateCreatedEventResponse extends BaseEventResponse {
        public BigInteger identification;

        public BigInteger creationTimestamp;

        public String creator;

        public String birthPlace;

        public String location;

        public String owner;

        public BigInteger timestamp;
    }
}
