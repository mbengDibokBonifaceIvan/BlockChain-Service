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
import org.web3j.abi.datatypes.Bool;
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
import org.web3j.tuples.generated.Tuple9;
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
public class Resource extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b506115818061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610055575f3560e01c80631e8fca2d14610059578063244d6daa1461008957806386d4e69e146100c1578063a13268d8146100f1578063b977cb2b14610121575b5f80fd5b610073600480360381019061006e9190610b91565b610151565b6040516100809190610d2c565b60405180910390f35b6100a3600480360381019061009e9190610b91565b6104e6565b6040516100b899989796959493929190610da3565b60405180910390f35b6100db60048036038101906100d69190610f76565b610756565b6040516100e89190611018565b60405180910390f35b61010b60048036038101906101069190610b91565b610919565b6040516101189190611018565b60405180910390f35b61013b60048036038101906101369190610f76565b610936565b6040516101489190611018565b60405180910390f35b610159610b06565b8160015f8281526020019081526020015f205f9054906101000a900460ff166101b7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101ae9061107b565b60405180910390fd5b5f5b5f805490508110156104a457835f82815481106101d9576101d8611099565b5b905f5260205f2090600902015f015403610491575f8181548110610200576101ff611099565b5b905f5260205f209060090201604051806101200160405290815f82015481526020016001820154815260200160028201805461023b906110f3565b80601f0160208091040260200160405190810160405280929190818152602001828054610267906110f3565b80156102b25780601f10610289576101008083540402835291602001916102b2565b820191905f5260205f20905b81548152906001019060200180831161029557829003601f168201915b505050505081526020016003820180546102cb906110f3565b80601f01602080910402602001604051908101604052809291908181526020018280546102f7906110f3565b80156103425780601f1061031957610100808354040283529160200191610342565b820191905f5260205f20905b81548152906001019060200180831161032557829003601f168201915b5050505050815260200160048201805461035b906110f3565b80601f0160208091040260200160405190810160405280929190818152602001828054610387906110f3565b80156103d25780601f106103a9576101008083540402835291602001916103d2565b820191905f5260205f20905b8154815290600101906020018083116103b557829003601f168201915b505050505081526020016005820180546103eb906110f3565b80601f0160208091040260200160405190810160405280929190818152602001828054610417906110f3565b80156104625780601f1061043957610100808354040283529160200191610462565b820191905f5260205f20905b81548152906001019060200180831161044557829003601f168201915b5050505050815260200160068201548152602001600782015481526020016008820154815250509250506104e0565b808061049c90611150565b9150506101b9565b506040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104d7906111e1565b60405180910390fd5b50919050565b5f81815481106104f4575f80fd5b905f5260205f2090600902015f91509050805f01549080600101549080600201805461051f906110f3565b80601f016020809104026020016040519081016040528092919081815260200182805461054b906110f3565b80156105965780601f1061056d57610100808354040283529160200191610596565b820191905f5260205f20905b81548152906001019060200180831161057957829003601f168201915b5050505050908060030180546105ab906110f3565b80601f01602080910402602001604051908101604052809291908181526020018280546105d7906110f3565b80156106225780601f106105f957610100808354040283529160200191610622565b820191905f5260205f20905b81548152906001019060200180831161060557829003601f168201915b505050505090806004018054610637906110f3565b80601f0160208091040260200160405190810160405280929190818152602001828054610663906110f3565b80156106ae5780601f10610685576101008083540402835291602001916106ae565b820191905f5260205f20905b81548152906001019060200180831161069157829003601f168201915b5050505050908060050180546106c3906110f3565b80601f01602080910402602001604051908101604052809291908181526020018280546106ef906110f3565b801561073a5780601f106107115761010080835404028352916020019161073a565b820191905f5260205f20905b81548152906001019060200180831161071d57829003601f168201915b5050505050908060060154908060070154908060080154905089565b5f60015f8581526020019081526020015f205f9054906101000a900460ff16156107b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107ac90611249565b60405180910390fd5b5f6040518061012001604052808681526020014281526020018581526020018481526020018481526020018581526020014281526020014381526020014281525090505f81908060018154018082558091505060019003905f5260205f2090600902015f909190919091505f820151815f01556020820151816001015560408201518160020190816108479190611404565b50606082015181600301908161085d9190611404565b5060808201518160040190816108739190611404565b5060a08201518160050190816108899190611404565b5060c0820151816006015560e08201518160070155610100820151816008015550506001805f8781526020019081526020015f205f6101000a81548160ff021916908315150217905550847fc659dcbbbc7e96ef991c091b1b44d99889256841e6f47040adfac80d5186171e85856040516109059291906114d3565b60405180910390a260019150509392505050565b6001602052805f5260405f205f915054906101000a900460ff1681565b5f8360015f8281526020019081526020015f205f9054906101000a900460ff16610995576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161098c9061107b565b60405180910390fd5b5f5b5f80549050811015610af957855f82815481106109b7576109b6611099565b5b905f5260205f2090600902015f015403610ae657845f82815481106109df576109de611099565b5b905f5260205f20906009020160040190816109fa9190611404565b50835f8281548110610a0f57610a0e611099565b5b905f5260205f2090600902016005019081610a2a9190611404565b50425f8281548110610a3f57610a3e611099565b5b905f5260205f20906009020160060181905550435f8281548110610a6657610a65611099565b5b905f5260205f20906009020160070181905550425f8281548110610a8d57610a8c611099565b5b905f5260205f20906009020160080181905550857fcdae7ef1e76ded2393f7c71c6fc8188c715538e3a53f849d7cab4eee3ba729cd868642604051610ad493929190611508565b60405180910390a26001925050610afe565b8080610af190611150565b915050610997565b505f91505b509392505050565b6040518061012001604052805f81526020015f8152602001606081526020016060815260200160608152602001606081526020015f81526020015f81526020015f81525090565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b610b7081610b5e565b8114610b7a575f80fd5b50565b5f81359050610b8b81610b67565b92915050565b5f60208284031215610ba657610ba5610b56565b5b5f610bb384828501610b7d565b91505092915050565b610bc581610b5e565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610c02578082015181840152602081019050610be7565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610c2782610bcb565b610c318185610bd5565b9350610c41818560208601610be5565b610c4a81610c0d565b840191505092915050565b5f61012083015f830151610c6b5f860182610bbc565b506020830151610c7e6020860182610bbc565b5060408301518482036040860152610c968282610c1d565b91505060608301518482036060860152610cb08282610c1d565b91505060808301518482036080860152610cca8282610c1d565b91505060a083015184820360a0860152610ce48282610c1d565b91505060c0830151610cf960c0860182610bbc565b5060e0830151610d0c60e0860182610bbc565b50610100830151610d21610100860182610bbc565b508091505092915050565b5f6020820190508181035f830152610d448184610c55565b905092915050565b610d5581610b5e565b82525050565b5f82825260208201905092915050565b5f610d7582610bcb565b610d7f8185610d5b565b9350610d8f818560208601610be5565b610d9881610c0d565b840191505092915050565b5f61012082019050610db75f83018c610d4c565b610dc4602083018b610d4c565b8181036040830152610dd6818a610d6b565b90508181036060830152610dea8189610d6b565b90508181036080830152610dfe8188610d6b565b905081810360a0830152610e128187610d6b565b9050610e2160c0830186610d4c565b610e2e60e0830185610d4c565b610e3c610100830184610d4c565b9a9950505050505050505050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610e8882610c0d565b810181811067ffffffffffffffff82111715610ea757610ea6610e52565b5b80604052505050565b5f610eb9610b4d565b9050610ec58282610e7f565b919050565b5f67ffffffffffffffff821115610ee457610ee3610e52565b5b610eed82610c0d565b9050602081019050919050565b828183375f83830152505050565b5f610f1a610f1584610eca565b610eb0565b905082815260208101848484011115610f3657610f35610e4e565b5b610f41848285610efa565b509392505050565b5f82601f830112610f5d57610f5c610e4a565b5b8135610f6d848260208601610f08565b91505092915050565b5f805f60608486031215610f8d57610f8c610b56565b5b5f610f9a86828701610b7d565b935050602084013567ffffffffffffffff811115610fbb57610fba610b5a565b5b610fc786828701610f49565b925050604084013567ffffffffffffffff811115610fe857610fe7610b5a565b5b610ff486828701610f49565b9150509250925092565b5f8115159050919050565b61101281610ffe565b82525050565b5f60208201905061102b5f830184611009565b92915050565b7f5265736f7572636520646f6573206e6f742065786973740000000000000000005f82015250565b5f611065601783610d5b565b915061107082611031565b602082019050919050565b5f6020820190508181035f83015261109281611059565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52603260045260245ffd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f600282049050600182168061110a57607f821691505b60208210810361111d5761111c6110c6565b5b50919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f61115a82610b5e565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff820361118c5761118b611123565b5b600182019050919050565b7f5265736f75726365206e6f7420666f756e6400000000000000000000000000005f82015250565b5f6111cb601283610d5b565b91506111d682611197565b602082019050919050565b5f6020820190508181035f8301526111f8816111bf565b9050919050565b7f5265736f7572636520616c7265616479206578697374730000000000000000005f82015250565b5f611233601783610d5b565b915061123e826111ff565b602082019050919050565b5f6020820190508181035f83015261126081611227565b9050919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f600883026112c37fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82611288565b6112cd8683611288565b95508019841693508086168417925050509392505050565b5f819050919050565b5f6113086113036112fe84610b5e565b6112e5565b610b5e565b9050919050565b5f819050919050565b611321836112ee565b61133561132d8261130f565b848454611294565b825550505050565b5f90565b61134961133d565b611354818484611318565b505050565b5b818110156113775761136c5f82611341565b60018101905061135a565b5050565b601f8211156113bc5761138d81611267565b61139684611279565b810160208510156113a5578190505b6113b96113b185611279565b830182611359565b50505b505050565b5f82821c905092915050565b5f6113dc5f19846008026113c1565b1980831691505092915050565b5f6113f483836113cd565b9150826002028217905092915050565b61140d82610bcb565b67ffffffffffffffff81111561142657611425610e52565b5b61143082546110f3565b61143b82828561137b565b5f60209050601f83116001811461146c575f841561145a578287015190505b61146485826113e9565b8655506114cb565b601f19841661147a86611267565b5f5b828110156114a15784890151825560018201915060208501945060208101905061147c565b868310156114be57848901516114ba601f8916826113cd565b8355505b6001600288020188555050505b505050505050565b5f6040820190508181035f8301526114eb8185610d6b565b905081810360208301526114ff8184610d6b565b90509392505050565b5f6060820190508181035f8301526115208186610d6b565b905081810360208301526115348185610d6b565b90506115436040830184610d4c565b94935050505056fea26469706673582212200cff6ceedbfac83e5a5f8f1707e89a139a288ac7f47ed5e2d94c50f00814a70f64736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_RESOURCEEXISTS = "resourceExists";

    public static final String FUNC_RESOURCES = "resources";

    public static final String FUNC_CREATERESOURCE = "createResource";

    public static final String FUNC_UPDATERESOURCE = "updateResource";

    public static final String FUNC_GETRESOURCE = "getResource";

    public static final Event RESOURCECREATED_EVENT = new Event("ResourceCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event RESOURCEUPDATED_EVENT = new Event("ResourceUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Resource(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Resource(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Resource(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Resource(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ResourceCreatedEventResponse> getResourceCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESOURCECREATED_EVENT, transactionReceipt);
        ArrayList<ResourceCreatedEventResponse> responses = new ArrayList<ResourceCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResourceCreatedEventResponse typedResponse = new ResourceCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.creator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.originLocation = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceCreatedEventResponse getResourceCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCECREATED_EVENT, log);
        ResourceCreatedEventResponse typedResponse = new ResourceCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.creator = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.originLocation = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<ResourceCreatedEventResponse> resourceCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getResourceCreatedEventFromLog(log));
    }

    public Flowable<ResourceCreatedEventResponse> resourceCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESOURCECREATED_EVENT));
        return resourceCreatedEventFlowable(filter);
    }

    public static List<ResourceUpdatedEventResponse> getResourceUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESOURCEUPDATED_EVENT, transactionReceipt);
        ArrayList<ResourceUpdatedEventResponse> responses = new ArrayList<ResourceUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResourceUpdatedEventResponse typedResponse = new ResourceUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.currentLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.currentOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lastUpdateDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceUpdatedEventResponse getResourceUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCEUPDATED_EVENT, log);
        ResourceUpdatedEventResponse typedResponse = new ResourceUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.currentLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.currentOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.lastUpdateDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ResourceUpdatedEventResponse> resourceUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getResourceUpdatedEventFromLog(log));
    }

    public Flowable<ResourceUpdatedEventResponse> resourceUpdatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESOURCEUPDATED_EVENT));
        return resourceUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> resourceExists(BigInteger param0) {
        final Function function = new Function(FUNC_RESOURCEEXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple9<BigInteger, BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger>> resources(
            BigInteger param0) {
        final Function function = new Function(FUNC_RESOURCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple9<BigInteger, BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple9<BigInteger, BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<BigInteger, BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createResource(BigInteger _resourceId,
            String _creator, String _originLocation) {
        final Function function = new Function(
                FUNC_CREATERESOURCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId), 
                new org.web3j.abi.datatypes.Utf8String(_creator), 
                new org.web3j.abi.datatypes.Utf8String(_originLocation)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateResource(BigInteger _resourceId,
            String _newLocation, String _newOwner) {
        final Function function = new Function(
                FUNC_UPDATERESOURCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId), 
                new org.web3j.abi.datatypes.Utf8String(_newLocation), 
                new org.web3j.abi.datatypes.Utf8String(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<ResourceData> getResource(BigInteger _resourceId) {
        final Function function = new Function(FUNC_GETRESOURCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ResourceData>() {}));
        return executeRemoteCallSingleValueReturn(function, ResourceData.class);
    }

    @Deprecated
    public static Resource load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Resource(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Resource load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Resource(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Resource load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Resource(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Resource load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Resource(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Resource> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Resource.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Resource> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Resource.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Resource> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Resource.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Resource> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Resource.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ResourceData extends DynamicStruct {
        public BigInteger resourceId;

        public BigInteger creationDate;

        public String creator;

        public String originLocation;

        public String currentLocation;

        public String currentOwner;

        public BigInteger lastUpdateDate;

        public BigInteger blockNumber;

        public BigInteger blockTimestamp;

        public ResourceData(BigInteger resourceId, BigInteger creationDate, String creator,
                String originLocation, String currentLocation, String currentOwner,
                BigInteger lastUpdateDate, BigInteger blockNumber, BigInteger blockTimestamp) {
            super(new org.web3j.abi.datatypes.generated.Uint256(resourceId), 
                    new org.web3j.abi.datatypes.generated.Uint256(creationDate), 
                    new org.web3j.abi.datatypes.Utf8String(creator), 
                    new org.web3j.abi.datatypes.Utf8String(originLocation), 
                    new org.web3j.abi.datatypes.Utf8String(currentLocation), 
                    new org.web3j.abi.datatypes.Utf8String(currentOwner), 
                    new org.web3j.abi.datatypes.generated.Uint256(lastUpdateDate), 
                    new org.web3j.abi.datatypes.generated.Uint256(blockNumber), 
                    new org.web3j.abi.datatypes.generated.Uint256(blockTimestamp));
            this.resourceId = resourceId;
            this.creationDate = creationDate;
            this.creator = creator;
            this.originLocation = originLocation;
            this.currentLocation = currentLocation;
            this.currentOwner = currentOwner;
            this.lastUpdateDate = lastUpdateDate;
            this.blockNumber = blockNumber;
            this.blockTimestamp = blockTimestamp;
        }

        public ResourceData(Uint256 resourceId, Uint256 creationDate, Utf8String creator,
                Utf8String originLocation, Utf8String currentLocation, Utf8String currentOwner,
                Uint256 lastUpdateDate, Uint256 blockNumber, Uint256 blockTimestamp) {
            super(resourceId, creationDate, creator, originLocation, currentLocation, currentOwner, lastUpdateDate, blockNumber, blockTimestamp);
            this.resourceId = resourceId.getValue();
            this.creationDate = creationDate.getValue();
            this.creator = creator.getValue();
            this.originLocation = originLocation.getValue();
            this.currentLocation = currentLocation.getValue();
            this.currentOwner = currentOwner.getValue();
            this.lastUpdateDate = lastUpdateDate.getValue();
            this.blockNumber = blockNumber.getValue();
            this.blockTimestamp = blockTimestamp.getValue();
        }
    }

    public static class ResourceCreatedEventResponse extends BaseEventResponse {
        public BigInteger resourceId;

        public String creator;

        public String originLocation;
    }

    public static class ResourceUpdatedEventResponse extends BaseEventResponse {
        public BigInteger resourceId;

        public String currentLocation;

        public String currentOwner;

        public BigInteger lastUpdateDate;
    }
}
