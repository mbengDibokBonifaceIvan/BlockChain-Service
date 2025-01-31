package com.GI2025.blockchain.contracts.javaWrapper;

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
public class ResourceTraceability extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b50610e388061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610034575f3560e01c80635a12f51d146100385780635f1809e214610068575b5f80fd5b610052600480360381019061004d9190610572565b610084565b60405161005f91906107ba565b60405180910390f35b610082600480360381019061007d9190610906565b6103de565b005b60605f808381526020019081526020015f20805480602002602001604051908101604052809291908181526020015f905b828210156103d3578382905f5260205f209060080201604051806101000160405290815f8201548152602001600182015481526020016002820180546100fa90610a70565b80601f016020809104026020016040519081016040528092919081815260200182805461012690610a70565b80156101715780601f1061014857610100808354040283529160200191610171565b820191905f5260205f20905b81548152906001019060200180831161015457829003601f168201915b5050505050815260200160038201805461018a90610a70565b80601f01602080910402602001604051908101604052809291908181526020018280546101b690610a70565b80156102015780601f106101d857610100808354040283529160200191610201565b820191905f5260205f20905b8154815290600101906020018083116101e457829003601f168201915b5050505050815260200160048201805461021a90610a70565b80601f016020809104026020016040519081016040528092919081815260200182805461024690610a70565b80156102915780601f1061026857610100808354040283529160200191610291565b820191905f5260205f20905b81548152906001019060200180831161027457829003601f168201915b505050505081526020016005820180546102aa90610a70565b80601f01602080910402602001604051908101604052809291908181526020018280546102d690610a70565b80156103215780601f106102f857610100808354040283529160200191610321565b820191905f5260205f20905b81548152906001019060200180831161030457829003601f168201915b505050505081526020016006820154815260200160078201805461034490610a70565b80601f016020809104026020016040519081016040528092919081815260200182805461037090610a70565b80156103bb5780601f10610392576101008083540402835291602001916103bb565b820191905f5260205f20905b81548152906001019060200180831161039e57829003601f168201915b505050505081525050815260200190600101906100b5565b505050509050919050565b5f6040518061010001604052808a81526020018981526020018881526020018781526020018681526020018581526020018481526020018381525090505f808a81526020019081526020015f2081908060018154018082558091505060019003905f5260205f2090600802015f909190919091505f820151815f01556020820151816001015560408201518160020190816104799190610c3d565b50606082015181600301908161048f9190610c3d565b5060808201518160040190816104a59190610c3d565b5060a08201518160050190816104bb9190610c3d565b5060c0820151816006015560e08201518160070190816104db9190610c3d565b5050507f5cf4782cf7a227402b06e71bac4391943cb6349b33cd7bab10cda8c05ebc72c2898989898989898960405161051b989796959493929190610d63565b60405180910390a1505050505050505050565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b6105518161053f565b811461055b575f80fd5b50565b5f8135905061056c81610548565b92915050565b5f6020828403121561058757610586610537565b5b5f6105948482850161055e565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b6105cf8161053f565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b8381101561060c5780820151818401526020810190506105f1565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610631826105d5565b61063b81856105df565b935061064b8185602086016105ef565b61065481610617565b840191505092915050565b5f61010083015f8301516106755f8601826105c6565b50602083015161068860208601826105c6565b50604083015184820360408601526106a08282610627565b915050606083015184820360608601526106ba8282610627565b915050608083015184820360808601526106d48282610627565b91505060a083015184820360a08601526106ee8282610627565b91505060c083015161070360c08601826105c6565b5060e083015184820360e086015261071b8282610627565b9150508091505092915050565b5f610733838361065f565b905092915050565b5f602082019050919050565b5f6107518261059d565b61075b81856105a7565b93508360208202850161076d856105b7565b805f5b858110156107a857848403895281516107898582610728565b94506107948361073b565b925060208a01995050600181019050610770565b50829750879550505050505092915050565b5f6020820190508181035f8301526107d28184610747565b905092915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b61081882610617565b810181811067ffffffffffffffff82111715610837576108366107e2565b5b80604052505050565b5f61084961052e565b9050610855828261080f565b919050565b5f67ffffffffffffffff821115610874576108736107e2565b5b61087d82610617565b9050602081019050919050565b828183375f83830152505050565b5f6108aa6108a58461085a565b610840565b9050828152602081018484840111156108c6576108c56107de565b5b6108d184828561088a565b509392505050565b5f82601f8301126108ed576108ec6107da565b5b81356108fd848260208601610898565b91505092915050565b5f805f805f805f80610100898b03121561092357610922610537565b5b5f6109308b828c0161055e565b98505060206109418b828c0161055e565b975050604089013567ffffffffffffffff8111156109625761096161053b565b5b61096e8b828c016108d9565b965050606089013567ffffffffffffffff81111561098f5761098e61053b565b5b61099b8b828c016108d9565b955050608089013567ffffffffffffffff8111156109bc576109bb61053b565b5b6109c88b828c016108d9565b94505060a089013567ffffffffffffffff8111156109e9576109e861053b565b5b6109f58b828c016108d9565b93505060c0610a068b828c0161055e565b92505060e089013567ffffffffffffffff811115610a2757610a2661053b565b5b610a338b828c016108d9565b9150509295985092959890939650565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610a8757607f821691505b602082108103610a9a57610a99610a43565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610afc7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610ac1565b610b068683610ac1565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610b41610b3c610b378461053f565b610b1e565b61053f565b9050919050565b5f819050919050565b610b5a83610b27565b610b6e610b6682610b48565b848454610acd565b825550505050565b5f90565b610b82610b76565b610b8d818484610b51565b505050565b5b81811015610bb057610ba55f82610b7a565b600181019050610b93565b5050565b601f821115610bf557610bc681610aa0565b610bcf84610ab2565b81016020851015610bde578190505b610bf2610bea85610ab2565b830182610b92565b50505b505050565b5f82821c905092915050565b5f610c155f1984600802610bfa565b1980831691505092915050565b5f610c2d8383610c06565b9150826002028217905092915050565b610c46826105d5565b67ffffffffffffffff811115610c5f57610c5e6107e2565b5b610c698254610a70565b610c74828285610bb4565b5f60209050601f831160018114610ca5575f8415610c93578287015190505b610c9d8582610c22565b865550610d04565b601f198416610cb386610aa0565b5f5b82811015610cda57848901518255600182019150602085019450602081019050610cb5565b86831015610cf75784890151610cf3601f891682610c06565b8355505b6001600288020188555050505b505050505050565b610d158161053f565b82525050565b5f82825260208201905092915050565b5f610d35826105d5565b610d3f8185610d1b565b9350610d4f8185602086016105ef565b610d5881610617565b840191505092915050565b5f61010082019050610d775f83018b610d0c565b610d84602083018a610d0c565b8181036040830152610d968189610d2b565b90508181036060830152610daa8188610d2b565b90508181036080830152610dbe8187610d2b565b905081810360a0830152610dd28186610d2b565b9050610de160c0830185610d0c565b81810360e0830152610df38184610d2b565b9050999850505050505050505056fea26469706673582212206491789c9756a3e7e361dff721009ed7e073169e3d75320e54ba0c12109ca2b864736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDRESOURCESTATE = "addResourceState";

    public static final String FUNC_GETRESOURCESTATES = "getResourceStates";

    public static final Event RESOURCESTATECREATED_EVENT = new Event("ResourceStateCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected ResourceTraceability(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ResourceTraceability(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ResourceTraceability(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ResourceTraceability(String contractAddress, Web3j web3j,
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
            typedResponse.creationLocation = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.location = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(7).getValue();
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
        typedResponse.creationLocation = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.location = (String) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.owner = (String) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
        typedResponse.data = (String) eventValues.getNonIndexedValues().get(7).getValue();
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

    public RemoteFunctionCall<TransactionReceipt> send_addResourceState(BigInteger identification,
            BigInteger creationTimestamp, String creator, String creationLocation, String location,
            String owner, BigInteger timestamp, String data) {
        final Function function = new Function(
                FUNC_ADDRESOURCESTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                new org.web3j.abi.datatypes.generated.Uint256(creationTimestamp), 
                new org.web3j.abi.datatypes.Utf8String(creator), 
                new org.web3j.abi.datatypes.Utf8String(creationLocation), 
                new org.web3j.abi.datatypes.Utf8String(location), 
                new org.web3j.abi.datatypes.Utf8String(owner), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                new org.web3j.abi.datatypes.Utf8String(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> call_getResourceStates(BigInteger identification) {
        final Function function = new Function(FUNC_GETRESOURCESTATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ResourceState>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> send_getResourceStates(
            BigInteger identification) {
        final Function function = new Function(
                FUNC_GETRESOURCESTATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(identification)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ResourceTraceability load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ResourceTraceability(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ResourceTraceability load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ResourceTraceability(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ResourceTraceability load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ResourceTraceability(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ResourceTraceability load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ResourceTraceability(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ResourceTraceability> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ResourceTraceability.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ResourceTraceability> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ResourceTraceability.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ResourceTraceability> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ResourceTraceability.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ResourceTraceability> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ResourceTraceability.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

        public String creationLocation;

        public String location;

        public String owner;

        public BigInteger timestamp;

        public String data;

        public ResourceState(BigInteger identification, BigInteger creationTimestamp,
                String creator, String creationLocation, String location, String owner,
                BigInteger timestamp, String data) {
            super(new org.web3j.abi.datatypes.generated.Uint256(identification), 
                    new org.web3j.abi.datatypes.generated.Uint256(creationTimestamp), 
                    new org.web3j.abi.datatypes.Utf8String(creator), 
                    new org.web3j.abi.datatypes.Utf8String(creationLocation), 
                    new org.web3j.abi.datatypes.Utf8String(location), 
                    new org.web3j.abi.datatypes.Utf8String(owner), 
                    new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                    new org.web3j.abi.datatypes.Utf8String(data));
            this.identification = identification;
            this.creationTimestamp = creationTimestamp;
            this.creator = creator;
            this.creationLocation = creationLocation;
            this.location = location;
            this.owner = owner;
            this.timestamp = timestamp;
            this.data = data;
        }

        public ResourceState(Uint256 identification, Uint256 creationTimestamp, Utf8String creator,
                Utf8String creationLocation, Utf8String location, Utf8String owner,
                Uint256 timestamp, Utf8String data) {
            super(identification, creationTimestamp, creator, creationLocation, location, owner, timestamp, data);
            this.identification = identification.getValue();
            this.creationTimestamp = creationTimestamp.getValue();
            this.creator = creator.getValue();
            this.creationLocation = creationLocation.getValue();
            this.location = location.getValue();
            this.owner = owner.getValue();
            this.timestamp = timestamp.getValue();
            this.data = data.getValue();
        }
    }

    public static class ResourceStateCreatedEventResponse extends BaseEventResponse {
        public BigInteger identification;

        public BigInteger creationTimestamp;

        public String creator;

        public String creationLocation;

        public String location;

        public String owner;

        public BigInteger timestamp;

        public String data;
    }
}
