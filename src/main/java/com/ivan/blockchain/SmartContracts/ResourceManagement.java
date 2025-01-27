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
public class ResourceManagement extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b506114328061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610060575f3560e01c80630789bf9a1461006457806313190c52146100825780631e8fca2d146100b2578063244d6daa146100e857806329507f731461011e5780634ec0c9e91461013a575b5f80fd5b61006c610156565b6040516100799190610ad2565b60405180910390f35b61009c60048036038101906100979190610c38565b61015c565b6040516100a99190610ad2565b60405180910390f35b6100cc60048036038101906100c79190610ca9565b61031b565b6040516100df9796959493929190610d8d565b60405180910390f35b61010260048036038101906100fd9190610ca9565b610564565b6040516101159796959493929190610d8d565b60405180910390f35b61013860048036038101906101339190610e32565b6106eb565b005b610154600480360381019061014f9190610e70565b610903565b005b60015481565b5f60015f81548092919061016f90610ef7565b91905055506040518060e0016040528060015481526020014281526020013373ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018381526020013373ffffffffffffffffffffffffffffffffffffffff168152602001428152505f8060015481526020019081526020015f205f820151815f0155602082015181600101556040820151816002015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160030190816102549190611138565b50608082015181600401908161026a9190611138565b5060a0820151816005015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060c082015181600601559050503373ffffffffffffffffffffffffffffffffffffffff166001547f99669c3163b4fe715b81481950c9cf02c195d03d222bf266de62d5faaa8fc9508442604051610309929190611207565b60405180910390a36001549050919050565b5f805f6060805f805f805f8a81526020019081526020015f206040518060e00160405290815f820154815260200160018201548152602001600282015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016003820180546103b790610f6b565b80601f01602080910402602001604051908101604052809291908181526020018280546103e390610f6b565b801561042e5780601f106104055761010080835404028352916020019161042e565b820191905f5260205f20905b81548152906001019060200180831161041157829003601f168201915b5050505050815260200160048201805461044790610f6b565b80601f016020809104026020016040519081016040528092919081815260200182805461047390610f6b565b80156104be5780601f10610495576101008083540402835291602001916104be565b820191905f5260205f20905b8154815290600101906020018083116104a157829003601f168201915b50505050508152602001600582015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016006820154815250509050805f015181602001518260400151836060015184608001518560a001518660c00151975097509750975097509750975050919395979092949650565b5f602052805f5260405f205f91509050805f015490806001015490806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030180546105b390610f6b565b80601f01602080910402602001604051908101604052809291908181526020018280546105df90610f6b565b801561062a5780601f106106015761010080835404028352916020019161062a565b820191905f5260205f20905b81548152906001019060200180831161060d57829003601f168201915b50505050509080600401805461063f90610f6b565b80601f016020809104026020016040519081016040528092919081815260200182805461066b90610f6b565b80156106b65780601f1061068d576101008083540402835291602001916106b6565b820191905f5260205f20905b81548152906001019060200180831161069957829003601f168201915b505050505090806005015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060060154905087565b3373ffffffffffffffffffffffffffffffffffffffff165f808481526020019081526020015f206005015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461078b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610782906112a5565b60405180910390fd5b5f73ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036107f9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107f09061130d565b60405180910390fd5b5f805f8481526020019081526020015f206005015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050815f808581526020019081526020015f206005015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550425f808581526020019081526020015f20600601819055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16847f4bf2d89ffff80f4f49f1bb5a636989885158f3df24a4085406f17737618df781426040516108f69190610ad2565b60405180910390a4505050565b3373ffffffffffffffffffffffffffffffffffffffff165f808481526020019081526020015f206005015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146109a3576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161099a9061139b565b60405180910390fd5b5f805f8481526020019081526020015f2060040180546109c290610f6b565b80601f01602080910402602001604051908101604052809291908181526020018280546109ee90610f6b565b8015610a395780601f10610a1057610100808354040283529160200191610a39565b820191905f5260205f20905b815481529060010190602001808311610a1c57829003601f168201915b50505050509050815f808581526020019081526020015f206004019081610a609190611138565b50425f808581526020019081526020015f2060060181905550827f6f9eeccdee3a8f65c0a9aa6dde38d9c8d4d6633c8c2851b040212a4b994dac8c828442604051610aad939291906113b9565b60405180910390a2505050565b5f819050919050565b610acc81610aba565b82525050565b5f602082019050610ae55f830184610ac3565b92915050565b5f604051905090565b5f80fd5b5f80fd5b5f80fd5b5f80fd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610b4a82610b04565b810181811067ffffffffffffffff82111715610b6957610b68610b14565b5b80604052505050565b5f610b7b610aeb565b9050610b878282610b41565b919050565b5f67ffffffffffffffff821115610ba657610ba5610b14565b5b610baf82610b04565b9050602081019050919050565b828183375f83830152505050565b5f610bdc610bd784610b8c565b610b72565b905082815260208101848484011115610bf857610bf7610b00565b5b610c03848285610bbc565b509392505050565b5f82601f830112610c1f57610c1e610afc565b5b8135610c2f848260208601610bca565b91505092915050565b5f60208284031215610c4d57610c4c610af4565b5b5f82013567ffffffffffffffff811115610c6a57610c69610af8565b5b610c7684828501610c0b565b91505092915050565b610c8881610aba565b8114610c92575f80fd5b50565b5f81359050610ca381610c7f565b92915050565b5f60208284031215610cbe57610cbd610af4565b5b5f610ccb84828501610c95565b91505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f610cfd82610cd4565b9050919050565b610d0d81610cf3565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610d4a578082015181840152602081019050610d2f565b5f8484015250505050565b5f610d5f82610d13565b610d698185610d1d565b9350610d79818560208601610d2d565b610d8281610b04565b840191505092915050565b5f60e082019050610da05f83018a610ac3565b610dad6020830189610ac3565b610dba6040830188610d04565b8181036060830152610dcc8187610d55565b90508181036080830152610de08186610d55565b9050610def60a0830185610d04565b610dfc60c0830184610ac3565b98975050505050505050565b610e1181610cf3565b8114610e1b575f80fd5b50565b5f81359050610e2c81610e08565b92915050565b5f8060408385031215610e4857610e47610af4565b5b5f610e5585828601610c95565b9250506020610e6685828601610e1e565b9150509250929050565b5f8060408385031215610e8657610e85610af4565b5b5f610e9385828601610c95565b925050602083013567ffffffffffffffff811115610eb457610eb3610af8565b5b610ec085828601610c0b565b9150509250929050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610f0182610aba565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610f3357610f32610eca565b5b600182019050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610f8257607f821691505b602082108103610f9557610f94610f3e565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610ff77fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610fbc565b6110018683610fbc565b95508019841693508086168417925050509392505050565b5f819050919050565b5f61103c61103761103284610aba565b611019565b610aba565b9050919050565b5f819050919050565b61105583611022565b61106961106182611043565b848454610fc8565b825550505050565b5f90565b61107d611071565b61108881848461104c565b505050565b5b818110156110ab576110a05f82611075565b60018101905061108e565b5050565b601f8211156110f0576110c181610f9b565b6110ca84610fad565b810160208510156110d9578190505b6110ed6110e585610fad565b83018261108d565b50505b505050565b5f82821c905092915050565b5f6111105f19846008026110f5565b1980831691505092915050565b5f6111288383611101565b9150826002028217905092915050565b61114182610d13565b67ffffffffffffffff81111561115a57611159610b14565b5b6111648254610f6b565b61116f8282856110af565b5f60209050601f8311600181146111a0575f841561118e578287015190505b611198858261111d565b8655506111ff565b601f1984166111ae86610f9b565b5f5b828110156111d5578489015182556001820191506020850194506020810190506111b0565b868310156111f257848901516111ee601f891682611101565b8355505b6001600288020188555050505b505050505050565b5f6040820190508181035f83015261121f8185610d55565b905061122e6020830184610ac3565b9392505050565b7f5365756c206c652070726f7072696574616972652070657574207472616e73665f8201527f65726572206c612070726f707269657465000000000000000000000000000000602082015250565b5f61128f603183610d1d565b915061129a82611235565b604082019050919050565b5f6020820190508181035f8301526112bc81611283565b9050919050565b7f4164726573736520696e76616c696465000000000000000000000000000000005f82015250565b5f6112f7601083610d1d565b9150611302826112c3565b602082019050919050565b5f6020820190508181035f830152611324816112eb565b9050919050565b7f5365756c206c652070726f7072696574616972652070657574206d6f646966695f8201527f6572206c61206c6f63616c69736174696f6e0000000000000000000000000000602082015250565b5f611385603283610d1d565b91506113908261132b565b604082019050919050565b5f6020820190508181035f8301526113b281611379565b9050919050565b5f6060820190508181035f8301526113d18186610d55565b905081810360208301526113e58185610d55565b90506113f46040830184610ac3565b94935050505056fea26469706673582212203ca193a2e65f341f4a8cdbfa44e0980c0c19df1c71ec988574ea3b31752e879e64736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_RESOURCECOUNTER = "resourceCounter";

    public static final String FUNC_RESOURCES = "resources";

    public static final String FUNC_CREATERESOURCE = "createResource";

    public static final String FUNC_UPDATELOCATION = "updateLocation";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETRESOURCE = "getResource";

    public static final Event RESOURCECREATED_EVENT = new Event("ResourceCreated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RESOURCELOCATIONUPDATED_EVENT = new Event("ResourceLocationUpdated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RESOURCEOWNERSHIPTRANSFERRED_EVENT = new Event("ResourceOwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected ResourceManagement(String contractAddress, Web3j web3j, Credentials credentials,
                                 BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ResourceManagement(String contractAddress, Web3j web3j, Credentials credentials,
                                 ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ResourceManagement(String contractAddress, Web3j web3j,
                                 TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ResourceManagement(String contractAddress, Web3j web3j,
                                 TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
            typedResponse.creator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.originLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.creationDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceCreatedEventResponse getResourceCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCECREATED_EVENT, log);
        ResourceCreatedEventResponse typedResponse = new ResourceCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.creator = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.originLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.creationDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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

    public static List<ResourceLocationUpdatedEventResponse> getResourceLocationUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESOURCELOCATIONUPDATED_EVENT, transactionReceipt);
        ArrayList<ResourceLocationUpdatedEventResponse> responses = new ArrayList<ResourceLocationUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResourceLocationUpdatedEventResponse typedResponse = new ResourceLocationUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.oldLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newLocation = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.updateDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceLocationUpdatedEventResponse getResourceLocationUpdatedEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCELOCATIONUPDATED_EVENT, log);
        ResourceLocationUpdatedEventResponse typedResponse = new ResourceLocationUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.oldLocation = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newLocation = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.updateDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ResourceLocationUpdatedEventResponse> resourceLocationUpdatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getResourceLocationUpdatedEventFromLog(log));
    }

    public Flowable<ResourceLocationUpdatedEventResponse> resourceLocationUpdatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESOURCELOCATIONUPDATED_EVENT));
        return resourceLocationUpdatedEventFlowable(filter);
    }

    public static List<ResourceOwnershipTransferredEventResponse> getResourceOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESOURCEOWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<ResourceOwnershipTransferredEventResponse> responses = new ArrayList<ResourceOwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResourceOwnershipTransferredEventResponse typedResponse = new ResourceOwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.updateDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ResourceOwnershipTransferredEventResponse getResourceOwnershipTransferredEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESOURCEOWNERSHIPTRANSFERRED_EVENT, log);
        ResourceOwnershipTransferredEventResponse typedResponse = new ResourceOwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.resourceId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.updateDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ResourceOwnershipTransferredEventResponse> resourceOwnershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getResourceOwnershipTransferredEventFromLog(log));
    }

    public Flowable<ResourceOwnershipTransferredEventResponse> resourceOwnershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESOURCEOWNERSHIPTRANSFERRED_EVENT));
        return resourceOwnershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> resourceCounter() {
        final Function function = new Function(FUNC_RESOURCECOUNTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>> resources(
            BigInteger param0) {
        final Function function = new Function(FUNC_RESOURCES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> createResource(String _originLocation) {
        final Function function = new Function(
                FUNC_CREATERESOURCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_originLocation)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateLocation(BigInteger _resourceId,
                                                                 String _newLocation) {
        final Function function = new Function(
                FUNC_UPDATELOCATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId),
                        new org.web3j.abi.datatypes.Utf8String(_newLocation)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(BigInteger _resourceId,
                                                                    String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId),
                        new org.web3j.abi.datatypes.Address(_newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>> getResource(
            BigInteger _resourceId) {
        final Function function = new Function(FUNC_GETRESOURCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resourceId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
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

    @Deprecated
    public static ResourceManagement load(String contractAddress, Web3j web3j,
                                          Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ResourceManagement(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ResourceManagement load(String contractAddress, Web3j web3j,
                                          TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ResourceManagement(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ResourceManagement load(String contractAddress, Web3j web3j,
                                          Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ResourceManagement(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ResourceManagement load(String contractAddress, Web3j web3j,
                                          TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ResourceManagement(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ResourceManagement> deploy(Web3j web3j, Credentials credentials,
                                                        ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ResourceManagement.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ResourceManagement> deploy(Web3j web3j, Credentials credentials,
                                                        BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ResourceManagement.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ResourceManagement> deploy(Web3j web3j,
                                                        TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ResourceManagement.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ResourceManagement> deploy(Web3j web3j,
                                                        TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ResourceManagement.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ResourceCreatedEventResponse extends BaseEventResponse {
        public BigInteger resourceId;

        public String creator;

        public String originLocation;

        public BigInteger creationDate;
    }

    public static class ResourceLocationUpdatedEventResponse extends BaseEventResponse {
        public BigInteger resourceId;

        public String oldLocation;

        public String newLocation;

        public BigInteger updateDate;
    }

    public static class ResourceOwnershipTransferredEventResponse extends BaseEventResponse {
        public BigInteger resourceId;

        public String previousOwner;

        public String newOwner;

        public BigInteger updateDate;
    }
}
