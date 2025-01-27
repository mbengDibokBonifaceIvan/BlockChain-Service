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
import org.web3j.tuples.generated.Tuple2;
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
public class CommercialOperations extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b5061146d8061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061007b575f3560e01c80636838fd94116100595780636838fd94146100e95780637faef32814610105578063b2e9949d14610135578063d2b903d81461016b5761007b565b8063202e39241461007f578063599a39f8146100af5780635fc89e7c146100cd575b5f80fd5b61009960048036038101906100949190610a39565b61019c565b6040516100a69190610bab565b60405180910390f35b6100b7610477565b6040516100c49190610c73565b60405180910390f35b6100e760048036038101906100e29190610dbf565b6104cd565b005b61010360048036038101906100fe9190610e19565b610546565b005b61011f600480360381019061011a9190610a39565b6106bf565b60405161012c9190610f0f565b60405180910390f35b61014f600480360381019061014a9190610a39565b6106df565b6040516101629796959493929190610f70565b60405180910390f35b61018560048036038101906101809190610a39565b610934565b604051610193929190610ff9565b60405180910390f35b6101a46109bb565b5f805f8481526020019081526020015f205f0154036101f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101ef9061106a565b60405180910390fd5b5f808381526020019081526020015f206040518060e00160405290815f820154815260200160018201805461022c906110b5565b80601f0160208091040260200160405190810160405280929190818152602001828054610258906110b5565b80156102a35780601f1061027a576101008083540402835291602001916102a3565b820191905f5260205f20905b81548152906001019060200180831161028657829003601f168201915b505050505081526020016002820180546102bc906110b5565b80601f01602080910402602001604051908101604052809291908181526020018280546102e8906110b5565b80156103335780601f1061030a57610100808354040283529160200191610333565b820191905f5260205f20905b81548152906001019060200180831161031657829003601f168201915b5050505050815260200160038201805461034c906110b5565b80601f0160208091040260200160405190810160405280929190818152602001828054610378906110b5565b80156103c35780601f1061039a576101008083540402835291602001916103c3565b820191905f5260205f20905b8154815290600101906020018083116103a657829003601f168201915b505050505081526020016004820180546103dc906110b5565b80601f0160208091040260200160405190810160405280929190818152602001828054610408906110b5565b80156104535780601f1061042a57610100808354040283529160200191610453565b820191905f5260205f20905b81548152906001019060200180831161043657829003601f168201915b50505050508152602001600582015481526020016006820154815250509050919050565b606060018054806020026020016040519081016040528092919081815260200182805480156104c357602002820191905f5260205f20905b8154815260200190600101908083116104af575b5050505050905090565b5f805f8481526020019081526020015f205f015403610521576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105189061106a565b60405180910390fd5b805f808481526020019081526020015f2060020190816105419190611282565b505050565b5f805f8781526020019081526020015f205f01541461059a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610591906113c1565b60405180910390fd5b6040518060e00160405280868152602001858152602001848152602001838152602001828152602001438152602001428152505f808781526020019081526020015f205f820151815f015560208201518160010190816105fa9190611282565b5060408201518160020190816106109190611282565b5060608201518160030190816106269190611282565b50608082015181600401908161063c9190611282565b5060a0820151816005015560c08201518160060155905050600185908060018154018082558091505060019003905f5260205f20015f9091909190915055847fe2c96ff9888acb7bdbf83e8269af748959bd59c00213e2b38e2c5f3476f108ea858484436040516106b094939291906113df565b60405180910390a25050505050565b600181815481106106ce575f80fd5b905f5260205f20015f915090505481565b5f602052805f5260405f205f91509050805f015490806001018054610703906110b5565b80601f016020809104026020016040519081016040528092919081815260200182805461072f906110b5565b801561077a5780601f106107515761010080835404028352916020019161077a565b820191905f5260205f20905b81548152906001019060200180831161075d57829003601f168201915b50505050509080600201805461078f906110b5565b80601f01602080910402602001604051908101604052809291908181526020018280546107bb906110b5565b80156108065780601f106107dd57610100808354040283529160200191610806565b820191905f5260205f20905b8154815290600101906020018083116107e957829003601f168201915b50505050509080600301805461081b906110b5565b80601f0160208091040260200160405190810160405280929190818152602001828054610847906110b5565b80156108925780601f1061086957610100808354040283529160200191610892565b820191905f5260205f20905b81548152906001019060200180831161087557829003601f168201915b5050505050908060040180546108a7906110b5565b80601f01602080910402602001604051908101604052809291908181526020018280546108d3906110b5565b801561091e5780601f106108f55761010080835404028352916020019161091e565b820191905f5260205f20905b81548152906001019060200180831161090157829003601f168201915b5050505050908060050154908060060154905087565b5f805f805f8581526020019081526020015f205f01540361098a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109819061106a565b60405180910390fd5b5f808481526020019081526020015f20600501545f808581526020019081526020015f206006015491509150915091565b6040518060e001604052805f8152602001606081526020016060815260200160608152602001606081526020015f81526020015f81525090565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b610a1881610a06565b8114610a22575f80fd5b50565b5f81359050610a3381610a0f565b92915050565b5f60208284031215610a4e57610a4d6109fe565b5b5f610a5b84828501610a25565b91505092915050565b610a6d81610a06565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610aaa578082015181840152602081019050610a8f565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610acf82610a73565b610ad98185610a7d565b9350610ae9818560208601610a8d565b610af281610ab5565b840191505092915050565b5f60e083015f830151610b125f860182610a64565b5060208301518482036020860152610b2a8282610ac5565b91505060408301518482036040860152610b448282610ac5565b91505060608301518482036060860152610b5e8282610ac5565b91505060808301518482036080860152610b788282610ac5565b91505060a0830151610b8d60a0860182610a64565b5060c0830151610ba060c0860182610a64565b508091505092915050565b5f6020820190508181035f830152610bc38184610afd565b905092915050565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b5f610bff8383610a64565b60208301905092915050565b5f602082019050919050565b5f610c2182610bcb565b610c2b8185610bd5565b9350610c3683610be5565b805f5b83811015610c66578151610c4d8882610bf4565b9750610c5883610c0b565b925050600181019050610c39565b5085935050505092915050565b5f6020820190508181035f830152610c8b8184610c17565b905092915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610cd182610ab5565b810181811067ffffffffffffffff82111715610cf057610cef610c9b565b5b80604052505050565b5f610d026109f5565b9050610d0e8282610cc8565b919050565b5f67ffffffffffffffff821115610d2d57610d2c610c9b565b5b610d3682610ab5565b9050602081019050919050565b828183375f83830152505050565b5f610d63610d5e84610d13565b610cf9565b905082815260208101848484011115610d7f57610d7e610c97565b5b610d8a848285610d43565b509392505050565b5f82601f830112610da657610da5610c93565b5b8135610db6848260208601610d51565b91505092915050565b5f8060408385031215610dd557610dd46109fe565b5b5f610de285828601610a25565b925050602083013567ffffffffffffffff811115610e0357610e02610a02565b5b610e0f85828601610d92565b9150509250929050565b5f805f805f60a08688031215610e3257610e316109fe565b5b5f610e3f88828901610a25565b955050602086013567ffffffffffffffff811115610e6057610e5f610a02565b5b610e6c88828901610d92565b945050604086013567ffffffffffffffff811115610e8d57610e8c610a02565b5b610e9988828901610d92565b935050606086013567ffffffffffffffff811115610eba57610eb9610a02565b5b610ec688828901610d92565b925050608086013567ffffffffffffffff811115610ee757610ee6610a02565b5b610ef388828901610d92565b9150509295509295909350565b610f0981610a06565b82525050565b5f602082019050610f225f830184610f00565b92915050565b5f82825260208201905092915050565b5f610f4282610a73565b610f4c8185610f28565b9350610f5c818560208601610a8d565b610f6581610ab5565b840191505092915050565b5f60e082019050610f835f83018a610f00565b8181036020830152610f958189610f38565b90508181036040830152610fa98188610f38565b90508181036060830152610fbd8187610f38565b90508181036080830152610fd18186610f38565b9050610fe060a0830185610f00565b610fed60c0830184610f00565b98975050505050505050565b5f60408201905061100c5f830185610f00565b6110196020830184610f00565b9392505050565b7f4f7065726174696f6e20646f6573206e6f7420657869737400000000000000005f82015250565b5f611054601883610f28565b915061105f82611020565b602082019050919050565b5f6020820190508181035f83015261108181611048565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f60028204905060018216806110cc57607f821691505b6020821081036110df576110de611088565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f600883026111417fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82611106565b61114b8683611106565b95508019841693508086168417925050509392505050565b5f819050919050565b5f61118661118161117c84610a06565b611163565b610a06565b9050919050565b5f819050919050565b61119f8361116c565b6111b36111ab8261118d565b848454611112565b825550505050565b5f90565b6111c76111bb565b6111d2818484611196565b505050565b5b818110156111f5576111ea5f826111bf565b6001810190506111d8565b5050565b601f82111561123a5761120b816110e5565b611214846110f7565b81016020851015611223578190505b61123761122f856110f7565b8301826111d7565b50505b505050565b5f82821c905092915050565b5f61125a5f198460080261123f565b1980831691505092915050565b5f611272838361124b565b9150826002028217905092915050565b61128b82610a73565b67ffffffffffffffff8111156112a4576112a3610c9b565b5b6112ae82546110b5565b6112b98282856111f9565b5f60209050601f8311600181146112ea575f84156112d8578287015190505b6112e28582611267565b865550611349565b601f1984166112f8866110e5565b5f5b8281101561131f578489015182556001820191506020850194506020810190506112fa565b8683101561133c5784890151611338601f89168261124b565b8355505b6001600288020188555050505b505050505050565b7f4f7065726174696f6e2077697468207468697320494420616c726561647920655f8201527f7869737473000000000000000000000000000000000000000000000000000000602082015250565b5f6113ab602583610f28565b91506113b682611351565b604082019050919050565b5f6020820190508181035f8301526113d88161139f565b9050919050565b5f6080820190508181035f8301526113f78187610f38565b9050818103602083015261140b8186610f38565b9050818103604083015261141f8185610f38565b905061142e6060830184610f00565b9594505050505056fea26469706673582212204e88561b1e9bb9b22f584ee99e52d4067dd96319893a29e8e665b109606dde4c64736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_OPERATIONIDS = "operationIds";

    public static final String FUNC_OPERATIONS = "operations";

    public static final String FUNC_CREATEOPERATION = "createOperation";

    public static final String FUNC_GETOPERATIONBLOCKINFO = "getOperationBlockInfo";

    public static final String FUNC_GETOPERATION = "getOperation";

    public static final String FUNC_GETALLOPERATIONIDS = "getAllOperationIds";

    public static final String FUNC_UPDATEOPERATIONSTATUS = "updateOperationStatus";

    public static final Event OPERATIONCREATED_EVENT = new Event("OperationCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
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

    public static List<OperationCreatedEventResponse> getOperationCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OPERATIONCREATED_EVENT, transactionReceipt);
        ArrayList<OperationCreatedEventResponse> responses = new ArrayList<OperationCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OperationCreatedEventResponse typedResponse = new OperationCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operationType = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.blockNumber = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OperationCreatedEventResponse getOperationCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OPERATIONCREATED_EVENT, log);
        OperationCreatedEventResponse typedResponse = new OperationCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.operationType = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.blockNumber = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<OperationCreatedEventResponse> operationCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOperationCreatedEventFromLog(log));
    }

    public Flowable<OperationCreatedEventResponse> operationCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPERATIONCREATED_EVENT));
        return operationCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> operationIds(BigInteger param0) {
        final Function function = new Function(FUNC_OPERATIONIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger>> operations(
            BigInteger param0) {
        final Function function = new Function(FUNC_OPERATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createOperation(BigInteger _id, String _type,
            String _status, String _seller, String _buyer) {
        final Function function = new Function(
                FUNC_CREATEOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.Utf8String(_type), 
                new org.web3j.abi.datatypes.Utf8String(_status), 
                new org.web3j.abi.datatypes.Utf8String(_seller), 
                new org.web3j.abi.datatypes.Utf8String(_buyer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getOperationBlockInfo(
            BigInteger _id) {
        final Function function = new Function(FUNC_GETOPERATIONBLOCKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Operation> getOperation(BigInteger _id) {
        final Function function = new Function(FUNC_GETOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Operation>() {}));
        return executeRemoteCallSingleValueReturn(function, Operation.class);
    }

    public RemoteFunctionCall<List> getAllOperationIds() {
        final Function function = new Function(FUNC_GETALLOPERATIONIDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> updateOperationStatus(BigInteger _id,
            String _newStatus) {
        final Function function = new Function(
                FUNC_UPDATEOPERATIONSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.Utf8String(_newStatus)), 
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

    public static class Operation extends DynamicStruct {
        public BigInteger id;

        public String operationType;

        public String status;

        public String seller;

        public String buyer;

        public BigInteger blockNumber;

        public BigInteger blockTimestamp;

        public Operation(BigInteger id, String operationType, String status, String seller,
                String buyer, BigInteger blockNumber, BigInteger blockTimestamp) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id), 
                    new org.web3j.abi.datatypes.Utf8String(operationType), 
                    new org.web3j.abi.datatypes.Utf8String(status), 
                    new org.web3j.abi.datatypes.Utf8String(seller), 
                    new org.web3j.abi.datatypes.Utf8String(buyer), 
                    new org.web3j.abi.datatypes.generated.Uint256(blockNumber), 
                    new org.web3j.abi.datatypes.generated.Uint256(blockTimestamp));
            this.id = id;
            this.operationType = operationType;
            this.status = status;
            this.seller = seller;
            this.buyer = buyer;
            this.blockNumber = blockNumber;
            this.blockTimestamp = blockTimestamp;
        }

        public Operation(Uint256 id, Utf8String operationType, Utf8String status, Utf8String seller,
                Utf8String buyer, Uint256 blockNumber, Uint256 blockTimestamp) {
            super(id, operationType, status, seller, buyer, blockNumber, blockTimestamp);
            this.id = id.getValue();
            this.operationType = operationType.getValue();
            this.status = status.getValue();
            this.seller = seller.getValue();
            this.buyer = buyer.getValue();
            this.blockNumber = blockNumber.getValue();
            this.blockTimestamp = blockTimestamp.getValue();
        }
    }

    public static class OperationCreatedEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String operationType;

        public String seller;

        public String buyer;

        public BigInteger blockNumber;
    }
}
