package com.bcos.asset.client;

/**
 * @author: 谢佳辉
 * @date 2021/1/12 4:11 下午
 */


import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.channel.client.Service;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.bcos.asset.contract.SetGetRecord;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SetCaseClient {
    static Logger logger = LoggerFactory.getLogger(SetCaseClient.class);

    private Web3j web3j;

    private Credentials credentials;

    public Web3j getWeb3j() {
        return web3j;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }
    public void deployAssetAndRecordAddr() {

        try {
           SetGetRecord setGetRecord = SetGetRecord.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();

            System.out.println(" deploy Asset success, contract address is " + setGetRecord.getContractAddress());

            recordAssetAddr(setGetRecord.getContractAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy edv contract failed, error message is  " + e.getMessage());
        }
    }
    public void initialize() throws Exception {

        // init the Service
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        Web3j web3j = Web3j.build(channelEthereumService, 1);

        // init Credentials
        Credentials credentials = GenCredential.create();

        setCredentials(credentials);
        setWeb3j(web3j);

        logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
    }

    private static BigInteger gasPrice = new BigInteger("30000000");
    private static BigInteger gasLimit = new BigInteger("30000000");
    public List<String> queryEDV(String assetAccount) {
        try {
            String contractAddress = loadAssetAddr();
            SetGetRecord setGetRecord = SetGetRecord.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple4<Boolean, String, String, String> result = setGetRecord.get(assetAccount).send();
            List<String> list = new ArrayList<>();
            list.add(assetAccount);
            list.add(result.getValue2());
            list.add(result.getValue3());
            list.add(result.getValue4());
            System.out.println(list);


            return  list;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            logger.error(" queryAssetAmount exception, error message is {}", e.getMessage());

            System.out.printf(" query asset account failed, error message is %s\n", e.getMessage());
        }
        return new ArrayList<>();
    }
    public void put() {
        try {
            String contractAddress = loadAssetAddr();
            SetGetRecord setGetRecord = SetGetRecord.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));


            TransactionReceipt receipt = setGetRecord.set("1","2","3","4").send();
            List<SetGetRecord.SetResultEventResponse> response = setGetRecord.getSetResultEvents(receipt);
            System.out.println(" post  success ");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();

            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        SetCaseClient setCaseClient = new SetCaseClient();
     setCaseClient.initialize();

     setCaseClient.deployAssetAndRecordAddr();


        List<String> list =setCaseClient.queryEDV("1");
        System.out.println(list.get(2));

    }
}
