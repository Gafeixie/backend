package com.bcos.asset.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version null.
 */
@SuppressWarnings("unchecked")
public class SetGetRecord extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506110106000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c92a78016040805190810160405280600981526020017f745f6b76746573747300000000000000000000000000000000000000000000008152506040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845285818151815260200191508051906020019080838360005b8381101561013957808201518184015260208101905061011e565b50505050905090810190601f1680156101665780820380516001836020036101000a031916815260200191505b50848103835260078152602001807f436173655f696400000000000000000000000000000000000000000000000000815250602001848103825260248152602001807f436173655f6e616d652c20436173655f6465736372697074696f6e2c5374616681526020017f665f696400000000000000000000000000000000000000000000000000000000815250604001945050505050602060405180830381600087803b15801561021557600080fd5b505af1158015610229573d6000803e3d6000fd5b505050506040513d602081101561023f57600080fd5b810190808051906020019092919050505050611212806102606000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680637b1b8e0314610051578063cc8af29e14610216575b600080fd5b34801561005d57600080fd5b506100b8600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610365565b6040518085151515158152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b8381101561010a5780820151818401526020810190506100ef565b50505050905090810190601f1680156101375780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b83811015610170578082015181840152602081019050610155565b50505050905090810190601f16801561019d5780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b838110156101d65780820151818401526020810190506101bb565b50505050905090810190601f1680156102035780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561022257600080fd5b5061034f600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506109ba565b6040518082815260200191505060405180910390f35b60006060806060600080600060608060606000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166359a48b656040805190810160405280600981526020017f745f6b76746573747300000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610454578082015181840152602081019050610439565b50505050905090810190601f1680156104815780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156104a057600080fd5b505af11580156104b4573d6000803e3d6000fd5b505050506040513d60208110156104ca57600080fd5b81019080805190602001909291905050509550600094508573ffffffffffffffffffffffffffffffffffffffff16637b1b8e038c6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561056957808201518184015260208101905061054e565b50505050905090810190601f1680156105965780820380516001836020036101000a031916815260200191505b50925050506040805180830381600087803b1580156105b457600080fd5b505af11580156105c8573d6000803e3d6000fd5b505050506040513d60408110156105de57600080fd5b810190808051906020019092919080519060200190929190505050809550819650505084156109a1578373ffffffffffffffffffffffffffffffffffffffff16639bca41e86040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260098152602001807f436173655f6e616d650000000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b1580156106a757600080fd5b505af11580156106bb573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060208110156106e557600080fd5b8101908080516401000000008111156106fd57600080fd5b8281019050602081018481111561071357600080fd5b815185600182028301116401000000008211171561073057600080fd5b505092919050505092508373ffffffffffffffffffffffffffffffffffffffff16639bca41e86040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260118152602001807f436173655f6465736372697074696f6e68000000000000000000000000000000815250602001915050600060405180830381600087803b1580156107da57600080fd5b505af11580156107ee573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561081857600080fd5b81019080805164010000000081111561083057600080fd5b8281019050602081018481111561084657600080fd5b815185600182028301116401000000008211171561086357600080fd5b505092919050505091508373ffffffffffffffffffffffffffffffffffffffff16639bca41e86040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260088152602001807f53746166665f6964000000000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b15801561090d57600080fd5b505af1158015610921573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561094b57600080fd5b81019080805164010000000081111561096357600080fd5b8281019050602081018481111561097957600080fd5b815185600182028301116401000000008211171561099657600080fd5b505092919050505090505b8483838399509950995099505050505050509193509193565b6000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166359a48b656040805190810160405280600981526020017f745f6b76746573747300000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610a9e578082015181840152602081019050610a83565b50505050905090810190601f168015610acb5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610aea57600080fd5b505af1158015610afe573d6000803e3d6000fd5b505050506040513d6020811015610b1457600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b8b57600080fd5b505af1158015610b9f573d6000803e3d6000fd5b505050506040513d6020811015610bb557600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff16631a391cb4896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f436173655f696400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610c88578082015181840152602081019050610c6d565b50505050905090810190601f168015610cb55780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610cd557600080fd5b505af1158015610ce9573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16631a391cb4886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260098152602001807f436173655f6e616d650000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610dad578082015181","840152602081019050610d92565b50505050905090810190601f168015610dda5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610dfa57600080fd5b505af1158015610e0e573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16631a391cb4876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260108152602001807f436173655f6465736372697074696f6e00000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610ed2578082015181840152602081019050610eb7565b50505050905090810190601f168015610eff5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610f1f57600080fd5b505af1158015610f33573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16631a391cb4866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260088152602001807f53746166665f6964000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610ff7578082015181840152602081019050610fdc565b50505050905090810190601f1680156110245780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561104457600080fd5b505af1158015611058573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663517c4dd989846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156111175780820151818401526020810190506110fc565b50505050905090810190601f1680156111445780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561116457600080fd5b505af1158015611178573d6000803e3d6000fd5b505050506040513d602081101561118e57600080fd5b810190808051906020019092919050505090507f8425f908744b84757cbeb3eda153cd3971c5a3fdecc6e6d18a6a223d52ca763f816040518082815260200191505060405180910390a18093505050509493505050505600a165627a7a72305820d52635f3b3b8e37ddb539b1a9efabf3effad627edbce2ad4fd7c4bb386f3718e0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"Case_id\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"type\":\"function\",\"payable\":false,\"stateMutability\":\"view\"},{\"constant\":false,\"inputs\":[{\"name\":\"Case_id\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"Case_name\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"Case_description\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"Staff_id\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"name\":\"set\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"type\":\"function\",\"payable\":false,\"stateMutability\":\"nonpayable\"},{\"constant\":false,\"inputs\":[],\"name\":null,\"outputs\":null,\"type\":\"constructor\",\"payable\":false,\"stateMutability\":\"nonpayable\"},{\"constant\":false,\"inputs\":[{\"name\":\"count\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"name\":\"SetResult\",\"outputs\":null,\"type\":\"event\",\"payable\":false,\"stateMutability\":null}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GET = "get";

    public static final String FUNC_SET = "set";

    public static final Event SETRESULT_EVENT = new Event("SetResult",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected SetGetRecord(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SetGetRecord(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SetGetRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SetGetRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return BINARY;
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple4<Boolean, String, String, String>> get(String Case_id) {
        final Function function = new Function(FUNC_GET,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<Boolean, String, String, String>>(
                new Callable<Tuple4<Boolean, String, String, String>>() {
                    @Override
                    public Tuple4<Boolean, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Boolean, String, String, String>(
                                (Boolean) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> set(String Case_id, String Case_name, String Case_description, String Staff_id) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_id),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_name),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_description),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Staff_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(String Case_id, String Case_name, String Case_description, String Staff_id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_id),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_name),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_description),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Staff_id)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(String Case_id, String Case_name, String Case_description, String Staff_id) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_id),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_name),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Case_description),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Staff_id)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, String, String> getSetInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, String, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue()
        );
    }

    public Tuple1<BigInteger> getSetOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public List<SetResultEventResponse> getSetResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SETRESULT_EVENT, transactionReceipt);
        ArrayList<SetResultEventResponse> responses = new ArrayList<SetResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetResultEventResponse typedResponse = new SetResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSetResultEventLogFilter(String fromBlock, String toBlock, List<String> otherTopics, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETRESULT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void registerSetResultEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETRESULT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static SetGetRecord load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SetGetRecord(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SetGetRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SetGetRecord(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SetGetRecord load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SetGetRecord(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SetGetRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SetGetRecord(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SetGetRecord> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SetGetRecord.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    public static RemoteCall<SetGetRecord> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SetGetRecord.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SetGetRecord> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SetGetRecord.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SetGetRecord> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SetGetRecord.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class SetResultEventResponse {
        public Log log;

        public BigInteger count;
    }
}
