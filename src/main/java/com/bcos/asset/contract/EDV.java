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
import org.fisco.bcos.web3j.abi.datatypes.Address;
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
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
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
public class EDV extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550612184806100606000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063680575421461005c578063efc81a8c146101b5578063fcd7e3c1146101e0575b600080fd5b34801561006857600080fd5b5061019f600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610472565b6040518082815260200191505060405180910390f35b3480156101c157600080fd5b506101ca610f5b565b6040518082815260200191505060405180910390f35b3480156101ec57600080fd5b50610247600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611268565b60405180806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b8381101561029757808201518184015260208101905061027c565b50505050905090810190601f1680156102c45780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b838110156102fd5780820151818401526020810190506102e2565b50505050905090810190601f16801561032a5780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b83811015610363578082015181840152602081019050610348565b50505050905090810190601f1680156103905780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b838110156103c95780820151818401526020810190506103ae565b50505050905090810190601f1680156103f65780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b8381101561042f578082015181840152602081019050610414565b50505050905090810190601f16801561045c5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b600080600060606000803373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610540576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601e8152602001807f417574683a206f6e6c79206f776e657220697320617574686f72697a6564000081525060200191505060405180910390fd5b61100194508473ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260098152602001807f7374755f73636f72650000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1580156105e557600080fd5b505af11580156105f9573d6000803e3d6000fd5b505050506040513d602081101561060f57600080fd5b810190808051906020019092919050505093508a92508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561068957600080fd5b505af115801561069d573d6000803e3d6000fd5b505050506040513d60208110156106b357600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168c6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f45766964656e6365496400000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561078657808201518184015260208101905061076b565b50505050905090810190601f1680156107b35780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156107d357600080fd5b505af11580156107e7573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168b6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600c8152602001807f45766964656e6365547970650000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156108ab578082015181840152602081019050610890565b50505050905090810190601f1680156108d85780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156108f857600080fd5b505af115801561090c573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260108152602001807f45766964656e636546696c655061746800000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156109d05780820151818401526020810190506109b5565b50505050905090810190601f1680156109fd5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610a1d57600080fd5b505af1158015610a31573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260138152602001807f45766964656e63654465736372697074696f6e00000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610af5578082015181840152602081019050610ada565b50505050905090810190601f168015610b225780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610b4257600080fd5b505af1158015610b56573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74886040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252601e8152602001807f5772697474656e4465636973696f6e4f66436173655f66696c696e674964000081525060200192505050600060405180830381600087803b158015610c0257600080fd5b505af1158015610c16573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff166331afac3684846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610cd5578082015181840152602081019050610cba565b50505050905090810190601f168015610d025780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610d2257600080fd5b505af1158015610d36573d6000803e3d6000fd5b505050506040513d6020811015610d4c57600080fd5b810190808051906020019092919050505090507f4da46722bf98ed7c18e482f8d36942d4ad2bb4a078075cf7139c2cb158a72d2a8b8b8b8b8b604051808060200180602001806020018060200186815260200185810385528a818151815260200191508051906020019080838360005b83811015610dd7578082015181840152602081019050610dbc565b50505050905090810190601f168015610e045780820380516001836020036101000a031916815260200191505b50858103845289818151815260200191508051906020019080838360005b83811015610e3d578082015181840152602081019050610e22565b50505050905090810190601f168015610e6a5780820380516001836020036101000a031916815260200191505b50858103835288818151815260200191508051906020019080838360005b83811015610ea3578082015181840152602081019050610e88565b50505050905090810190601f168015610ed05780820380516001836020036101000a031916815260200191505b50858103825287818151815260200191508051906020019080838360005b83811015610f09578082015181840152602081019050610eee565b50505050905090810190601f168015610f365780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390a1809550505050505095945050505050565b60008060003373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffff","ffffffffffffffffffffffffffffff16141515611024576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601e8152602001807f417574683a206f6e6c79206f776e657220697320617574686f72697a6564000081525060200191505060405180910390fd5b61100191508173ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260098152602001807f7374755f73636f726500000000000000000000000000000000000000000000008152506020018481038352600a8152602001807f45766964656e6365496400000000000000000000000000000000000000000000815250602001848103825260508152602001807f45766964656e6365547970652c45766964656e636546696c65506174682c457681526020017f6964656e63654465736372697074696f6e2c5772697474656e4465636973696f81526020017f6e4f66436173655f66696c696e674964000000000000000000000000000000008152506060019350505050602060405180830381600087803b15801561118757600080fd5b505af115801561119b573d6000803e3d6000fd5b505050506040513d60208110156111b157600080fd5b810190808051906020019092919050505090507f652d47259e1438252824d3fab2d8cb81df0e4d4302fa39f57de03740d666ea0833604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825260098152602001807f7374755f73636f726500000000000000000000000000000000000000000000008152506020019250505060405180910390a1809250505090565b6060806060806060600080606060008061100194508473ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260098152602001807f7374755f73636f72650000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561131d57600080fd5b505af1158015611331573d6000803e3d6000fd5b505050506040513d602081101561134757600080fd5b810190808051906020019092919050505093508a92508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156113c157600080fd5b505af11580156113d5573d6000803e3d6000fd5b505050506040513d60208110156113eb57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f45766964656e6365496400000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156114be5780820151818401526020810190506114a3565b50505050905090810190601f1680156114eb5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561150b57600080fd5b505af115801561151f573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663e8434e3984846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156115de5780820151818401526020810190506115c3565b50505050905090810190601f16801561160b5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561162b57600080fd5b505af115801561163f573d6000803e3d6000fd5b505050506040513d602081101561165557600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156116ce57600080fd5b505af11580156116e2573d6000803e3d6000fd5b505050506040513d60208110156116f857600080fd5b810190808051906020019092919050505014156117f857826040805190810160405280600481526020017f6e756c6c00000000000000000000000000000000000000000000000000000000815250906040805190810160405280600481526020017f6e756c6c000000000000000000000000000000000000000000000000000000008152506040805190810160405280600481526020017f6e756c6c000000000000000000000000000000000000000000000000000000008152506040805190810160405280600481526020017f6e756c6c000000000000000000000000000000000000000000000000000000008152509950995099509950995061214a565b8073ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561186857600080fd5b505af115801561187c573d6000803e3d6000fd5b505050506040513d602081101561189257600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600a8152602001807f45766964656e6365496400000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b15801561194257600080fd5b505af1158015611956573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561198057600080fd5b81019080805164010000000081111561199857600080fd5b828101905060208101848111156119ae57600080fd5b81518560018202830111640100000000821117156119cb57600080fd5b50509291905050508173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611a4357600080fd5b505af1158015611a57573d6000803e3d6000fd5b505050506040513d6020811015611a6d57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600c8152602001807f45766964656e6365547970650000000000000000000000000000000000000000815250602001915050600060405180830381600087803b158015611b1d57600080fd5b505af1158015611b31573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015611b5b57600080fd5b810190808051640100000000811115611b7357600080fd5b82810190506020810184811115611b8957600080fd5b8151856001820283011164010000000082111715611ba657600080fd5b50509291905050508273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611c1e57600080fd5b505af1158015611c32573d6000803e3d6000fd5b505050506040513d6020811015611c4857600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260108152602001807f45766964656e636546696c655061746800000000000000000000000000000000815250602001915050600060405180830381600087803b158015611cf857600080fd5b505af1158015611d0c573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015611d3657600080fd5b810190808051640100000000811115611d4e57600080fd5b82810190506020810184811115611d6457600080fd5b8151856001820283011164010000000082111715611d8157600080fd5b50509291905050508373ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611df957600080fd5b505af1158015611e0d573d6000803e3d6000fd5b505050506040513d6020811015611e2357600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260138152602001807f45766964656e63654465736372697074696f6e00000000000000000000000000815250602001915050600060405180830381600087803b158015611ed357600080fd5b505af1158015611ee7573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015611f1157600080fd5b810190808051640100000000811115611f2957600080fd5b82810190506020810184811115611f3f57600080fd5b8151856001820283011164010000000082111715611f5c57600080fd5b50509291905050508473ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000","000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611fd457600080fd5b505af1158015611fe8573d6000803e3d6000fd5b505050506040513d6020811015611ffe57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252601e8152602001807f5772697474656e4465636973696f6e4f66436173655f66696c696e6749640000815250602001915050600060405180830381600087803b1580156120ae57600080fd5b505af11580156120c2573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060208110156120ec57600080fd5b81019080805164010000000081111561210457600080fd5b8281019050602081018481111561211a57600080fd5b815185600182028301116401000000008211171561213757600080fd5b5050929190505050995099509950995099505b5050505050919395909294505600a165627a7a72305820edc2c8279ba76e854aefe368324a41acc08c0445d2cd19b1a7aa827eca8cee540029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"EvidenceId\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceType\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceFilePath\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceDescription\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"WrittenDecisionOfCase_filingId\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"name\":\"insert\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"type\":\"function\",\"payable\":false,\"stateMutability\":\"nonpayable\"},{\"constant\":false,\"inputs\":[],\"name\":\"create\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"type\":\"function\",\"payable\":false,\"stateMutability\":\"nonpayable\"},{\"constant\":true,\"inputs\":[{\"name\":\"EvidenceId\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"type\":\"function\",\"payable\":false,\"stateMutability\":\"view\"},{\"constant\":false,\"inputs\":[],\"name\":null,\"outputs\":null,\"type\":\"constructor\",\"payable\":false,\"stateMutability\":\"nonpayable\"},{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\",\"type0\":null,\"indexed\":false},{\"name\":\"tableName\",\"type\":\"string\",\"type0\":null,\"indexed\":false}],\"name\":\"createEvent\",\"outputs\":null,\"type\":\"event\",\"payable\":false,\"stateMutability\":null},{\"constant\":false,\"inputs\":[{\"name\":\"EvidenceId\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceType\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceFilePath\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"EvidenceDescription\",\"type\":\"string\",\"type0\":null,\"indexed\":false},{\"name\":\"WrittenDecisionOfCase_filingId\",\"type\":\"int256\",\"type0\":null,\"indexed\":false}],\"name\":\"insertEvent\",\"outputs\":null,\"type\":\"event\",\"payable\":false,\"stateMutability\":null}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_SELECT = "select";

    public static final Event CREATEEVENT_EVENT = new Event("createEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event INSERTEVENT_EVENT = new Event("insertEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected EDV(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EDV(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EDV(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EDV(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return BINARY;
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> insert(String EvidenceId, String EvidenceType, String EvidenceFilePath, String EvidenceDescription, BigInteger WrittenDecisionOfCase_filingId) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceId),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceType),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceFilePath),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceDescription),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(WrittenDecisionOfCase_filingId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void insert(String EvidenceId, String EvidenceType, String EvidenceFilePath, String EvidenceDescription, BigInteger WrittenDecisionOfCase_filingId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceId),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceType),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceFilePath),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceDescription),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(WrittenDecisionOfCase_filingId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String insertSeq(String EvidenceId, String EvidenceType, String EvidenceFilePath, String EvidenceDescription, BigInteger WrittenDecisionOfCase_filingId) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceId),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceType),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceFilePath),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceDescription),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(WrittenDecisionOfCase_filingId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple5<String, String, String, String, BigInteger> getInsertInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_INSERT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple5<String, String, String, String, BigInteger>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (BigInteger) results.get(4).getValue()
        );
    }

    public Tuple1<BigInteger> getInsertOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_INSERT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> create() {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createSeq() {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getCreateOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public RemoteCall<Tuple5<String, String, String, String, String>> select(String EvidenceId) {
        final Function function = new Function(FUNC_SELECT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(EvidenceId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple5<String, String, String, String, String>>(
                new Callable<Tuple5<String, String, String, String, String>>() {
                    @Override
                    public Tuple5<String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (String) results.get(4).getValue());
                    }
                });
    }

    public List<CreateEventEventResponse> getCreateEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateEventEventResponse> responses = new ArrayList<CreateEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateEventEventResponse typedResponse = new CreateEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tableName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registercreateEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopics, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATEEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void registercreateEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATEEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<InsertEventEventResponse> getInsertEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INSERTEVENT_EVENT, transactionReceipt);
        ArrayList<InsertEventEventResponse> responses = new ArrayList<InsertEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InsertEventEventResponse typedResponse = new InsertEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.EvidenceId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.EvidenceType = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.EvidenceFilePath = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.EvidenceDescription = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.WrittenDecisionOfCase_filingId = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerinsertEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopics, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(INSERTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void registerinsertEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(INSERTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static EDV load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EDV(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EDV load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EDV(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EDV load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EDV(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EDV load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EDV(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EDV> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EDV.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    public static RemoteCall<EDV> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EDV.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EDV> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EDV.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EDV> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EDV.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class CreateEventEventResponse {
        public Log log;

        public String owner;

        public String tableName;
    }

    public static class InsertEventEventResponse {
        public Log log;

        public String EvidenceId;

        public String EvidenceType;

        public String EvidenceFilePath;

        public String EvidenceDescription;

        public BigInteger WrittenDecisionOfCase_filingId;
    }
}
