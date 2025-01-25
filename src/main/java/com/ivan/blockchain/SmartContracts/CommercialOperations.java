package com.ivan.blockchain.SmartContracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.context.annotation.Bean;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")

public class CommercialOperations extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b506113f58061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061007b575f3560e01c80635fc89e7c116100595780635fc89e7c146100e95780637faef32814610105578063b2e9949d14610135578063d2b903d81461016b5761007b565b8063202e39241461007f57806353f3d664146100af578063599a39f8146100cb575b5f80fd5b610099600480360381019061009491906109ab565b61019c565b6040516100a69190610b4e565b60405180910390f35b6100c960048036038101906100c49190610cc4565b610401565b005b6100d3610606565b6040516100e09190610e1b565b60405180910390f35b61010360048036038101906100fe9190610e3b565b61065c565b005b61011f600480360381019061011a91906109ab565b6106d5565b60405161012c9190610ea4565b60405180910390f35b61014f600480360381019061014a91906109ab565b6106f5565b6040516101629796959493929190610f14565b60405180910390f35b610185600480360381019061018091906109ab565b61087c565b604051610193929190610f8f565b60405180910390f35b6101a4610903565b5f805f8481526020019081526020015f205f0154036101f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101ef90611000565b60405180910390fd5b5f808381526020019081526020015f206040518060e00160405290815f820154815260200160018201805461022c9061104b565b80601f01602080910402602001604051908101604052809291908181526020018280546102589061104b565b80156102a35780601f1061027a576101008083540402835291602001916102a3565b820191905f5260205f20905b81548152906001019060200180831161028657829003601f168201915b505050505081526020016002820180546102bc9061104b565b80601f01602080910402602001604051908101604052809291908181526020018280546102e89061104b565b80156103335780601f1061030a57610100808354040283529160200191610333565b820191905f5260205f20905b81548152906001019060200180831161031657829003601f168201915b50505050508152602001600382015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600482015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600582015481526020016006820154815250509050919050565b5f805f8781526020019081526020015f205f015414610455576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161044c906110eb565b60405180910390fd5b6040518060e001604052808681526020018581526020018481526020018373ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff168152602001438152602001428152505f808781526020019081526020015f205f820151815f015560208201518160010190816104e191906112a6565b5060408201518160020190816104f791906112a6565b506060820151816003015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506080820151816004015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a0820151816005015560c08201518160060155905050600185908060018154018082558091505060019003905f5260205f20015f9091909190915055847f3340fa8c5e7ef56a9f65d79f51bc34880714eb9c22f173493d4ae83a85b0f365858484436040516105f79493929190611375565b60405180910390a25050505050565b6060600180548060200260200160405190810160405280929190818152602001828054801561065257602002820191905f5260205f20905b81548152602001906001019080831161063e575b5050505050905090565b5f805f8481526020019081526020015f205f0154036106b0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106a790611000565b60405180910390fd5b805f808481526020019081526020015f2060020190816106d091906112a6565b505050565b600181815481106106e4575f80fd5b905f5260205f20015f915090505481565b5f602052805f5260405f205f91509050805f0154908060010180546107199061104b565b80601f01602080910402602001604051908101604052809291908181526020018280546107459061104b565b80156107905780601f1061076757610100808354040283529160200191610790565b820191905f5260205f20905b81548152906001019060200180831161077357829003601f168201915b5050505050908060020180546107a59061104b565b80601f01602080910402602001604051908101604052809291908181526020018280546107d19061104b565b801561081c5780601f106107f35761010080835404028352916020019161081c565b820191905f5260205f20905b8154815290600101906020018083116107ff57829003601f168201915b505050505090806003015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806004015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060050154908060060154905087565b5f805f805f8581526020019081526020015f205f0154036108d2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108c990611000565b60405180910390fd5b5f808481526020019081526020015f20600501545f808581526020019081526020015f206006015491509150915091565b6040518060e001604052805f815260200160608152602001606081526020015f73ffffffffffffffffffffffffffffffffffffffff1681526020015f73ffffffffffffffffffffffffffffffffffffffff1681526020015f81526020015f81525090565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b61098a81610978565b8114610994575f80fd5b50565b5f813590506109a581610981565b92915050565b5f602082840312156109c0576109bf610970565b5b5f6109cd84828501610997565b91505092915050565b6109df81610978565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610a1c578082015181840152602081019050610a01565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610a41826109e5565b610a4b81856109ef565b9350610a5b8185602086016109ff565b610a6481610a27565b840191505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f610a9882610a6f565b9050919050565b610aa881610a8e565b82525050565b5f60e083015f830151610ac35f8601826109d6565b5060208301518482036020860152610adb8282610a37565b91505060408301518482036040860152610af58282610a37565b9150506060830151610b0a6060860182610a9f565b506080830151610b1d6080860182610a9f565b5060a0830151610b3060a08601826109d6565b5060c0830151610b4360c08601826109d6565b508091505092915050565b5f6020820190508181035f830152610b668184610aae565b905092915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610bac82610a27565b810181811067ffffffffffffffff82111715610bcb57610bca610b76565b5b80604052505050565b5f610bdd610967565b9050610be98282610ba3565b919050565b5f67ffffffffffffffff821115610c0857610c07610b76565b5b610c1182610a27565b9050602081019050919050565b828183375f83830152505050565b5f610c3e610c3984610bee565b610bd4565b905082815260208101848484011115610c5a57610c59610b72565b5b610c65848285610c1e565b509392505050565b5f82601f830112610c8157610c80610b6e565b5b8135610c91848260208601610c2c565b91505092915050565b610ca381610a8e565b8114610cad575f80fd5b50565b5f81359050610cbe81610c9a565b92915050565b5f805f805f60a08688031215610cdd57610cdc610970565b5b5f610cea88828901610997565b955050602086013567ffffffffffffffff811115610d0b57610d0a610974565b5b610d1788828901610c6d565b945050604086013567ffffffffffffffff811115610d3857610d37610974565b5b610d4488828901610c6d565b9350506060610d5588828901610cb0565b9250506080610d6688828901610cb0565b9150509295509295909350565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b5f610da783836109d6565b60208301905092915050565b5f602082019050919050565b5f610dc982610d73565b610dd38185610d7d565b9350610dde83610d8d565b805f5b83811015610e0e578151610df58882610d9c565b9750610e0083610db3565b925050600181019050610de1565b5085935050505092915050565b5f6020820190508181035f830152610e338184610dbf565b905092915050565b5f8060408385031215610e5157610e50610970565b5b5f610e5e85828601610997565b925050602083013567ffffffffffffffff811115610e7f57610e7e610974565b5b610e8b85828601610c6d565b9150509250929050565b610e9e81610978565b82525050565b5f602082019050610eb75f830184610e95565b92915050565b5f82825260208201905092915050565b5f610ed7826109e5565b610ee18185610ebd565b9350610ef18185602086016109ff565b610efa81610a27565b840191505092915050565b610f0e81610a8e565b82525050565b5f60e082019050610f275f83018a610e95565b8181036020830152610f398189610ecd565b90508181036040830152610f4d8188610ecd565b9050610f5c6060830187610f05565b610f696080830186610f05565b610f7660a0830185610e95565b610f8360c0830184610e95565b98975050505050505050565b5f604082019050610fa25f830185610e95565b610faf6020830184610e95565b9392505050565b7f4f7065726174696f6e20646f6573206e6f7420657869737400000000000000005f82015250565b5f610fea601883610ebd565b9150610ff582610fb6565b602082019050919050565b5f6020820190508181035f83015261101781610fde565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f600282049050600182168061106257607f821691505b6020821081036110755761107461101e565b5b50919050565b7f4f7065726174696f6e2077697468207468697320494420616c726561647920655f8201527f7869737473000000000000000000000000000000000000000000000000000000602082015250565b5f6110d5602583610ebd565b91506110e08261107b565b604082019050919050565b5f6020820190508181035f830152611102816110c9565b9050919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f600883026111657fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8261112a565b61116f868361112a565b95508019841693508086168417925050509392505050565b5f819050919050565b5f6111aa6111a56111a084610978565b611187565b610978565b9050919050565b5f819050919050565b6111c383611190565b6111d76111cf826111b1565b848454611136565b825550505050565b5f90565b6111eb6111df565b6111f68184846111ba565b505050565b5b818110156112195761120e5f826111e3565b6001810190506111fc565b5050565b601f82111561125e5761122f81611109565b6112388461111b565b81016020851015611247578190505b61125b6112538561111b565b8301826111fb565b50505b505050565b5f82821c905092915050565b5f61127e5f1984600802611263565b1980831691505092915050565b5f611296838361126f565b9150826002028217905092915050565b6112af826109e5565b67ffffffffffffffff8111156112c8576112c7610b76565b5b6112d2825461104b565b6112dd82828561121d565b5f60209050601f83116001811461130e575f84156112fc578287015190505b611306858261128b565b86555061136d565b601f19841661131c86611109565b5f5b828110156113435784890151825560018201915060208501945060208101905061131e565b86831015611360578489015161135c601f89168261126f565b8355505b6001600288020188555050505b505050505050565b5f6080820190508181035f83015261138d8187610ecd565b905061139c6020830186610f05565b6113a96040830185610f05565b6113b66060830184610e95565b9594505050505056fea2646970667358221220941211a3416c17a33b266337e38715b66af6e8aa49ad2d18a99197288aaf341e64736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_OPERATIONIDS = "operationIds";

    public static final String FUNC_OPERATIONS = "operations";

    public static final String FUNC_CREATEOPERATION = "createOperation";

    public static final String FUNC_GETOPERATIONBLOCKINFO = "getOperationBlockInfo";

    public static final String FUNC_GETOPERATION = "getOperation";

    public static final String FUNC_GETALLOPERATIONIDS = "getAllOperationIds";

    public static final String FUNC_UPDATEOPERATIONSTATUS = "updateOperationStatus";

    public static final Event OPERATIONCREATED_EVENT = new Event("OperationCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteFunctionCall<BigInteger> call_operationIds(BigInteger param0) {
        final Function function = new Function(FUNC_OPERATIONIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> send_operationIds(BigInteger param0) {
        final Function function = new Function(
                FUNC_OPERATIONIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, BigInteger>> call_operations(
            BigInteger param0) {
        final Function function = new Function(FUNC_OPERATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> send_operations(BigInteger param0) {
        final Function function = new Function(
                FUNC_OPERATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send_createOperation(BigInteger _id, String _type,
            String _status, String _seller, String _buyer) {
        final Function function = new Function(
                FUNC_CREATEOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.Utf8String(_type), 
                new org.web3j.abi.datatypes.Utf8String(_status), 
                new org.web3j.abi.datatypes.Address(_seller), 
                new org.web3j.abi.datatypes.Address(_buyer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> call_getOperationBlockInfo(
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

    public RemoteFunctionCall<TransactionReceipt> send_getOperationBlockInfo(BigInteger _id) {
        final Function function = new Function(
                FUNC_GETOPERATIONBLOCKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Operation> call_getOperation(BigInteger _id) {
        final Function function = new Function(FUNC_GETOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Operation>() {}));
        return executeRemoteCallSingleValueReturn(function, Operation.class);
    }

    public RemoteFunctionCall<TransactionReceipt> send_getOperation(BigInteger _id) {
        final Function function = new Function(
                FUNC_GETOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> call_getAllOperationIds() {
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

    public RemoteFunctionCall<TransactionReceipt> send_getAllOperationIds() {
        final Function function = new Function(
                FUNC_GETALLOPERATIONIDS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send_updateOperationStatus(BigInteger _id,
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
                    new org.web3j.abi.datatypes.Address(seller), 
                    new org.web3j.abi.datatypes.Address(buyer), 
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

        public Operation(Uint256 id, Utf8String operationType, Utf8String status, Address seller,
                Address buyer, Uint256 blockNumber, Uint256 blockTimestamp) {
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
