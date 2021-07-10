package com.bcos.asset.client;

import com.example.model.Evidence;
import com.example.service.impl.bytetohash;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import com.bcos.asset.contract.EDV;
import org.fisco.bcos.channel.client.Service;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EDVclient {
    static Logger logger = LoggerFactory.getLogger(EDVclient.class);

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
            EDV edv = EDV.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy Asset success, contract address is " + edv.getContractAddress());

            recordAssetAddr(edv.getContractAddress());
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

            EDV edv = EDV.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple5<String, String, String, String, String> result = edv.select(assetAccount).send();
                List<String> list = new ArrayList<>();
                list.add(result.getValue1());
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
    public void put(Evidence evidence,String hash) {
        try {
            String contractAddress = loadAssetAddr();

            EDV edv = EDV.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = edv.insert(evidence.getEvidenceId().toString(),evidence.getDepartmentIds(),hash,evidence.getNote(), BigInteger.valueOf(Integer.valueOf(evidence.getCaseId()))).send();
            List<EDV.InsertEventEventResponse> response = edv.getInsertEventEvents(receipt);
              System.out.println(" post  success ");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();

            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        EDVclient edVclient = new EDVclient();
        edVclient.initialize();
        //edVclient.deployAssetAndRecordAddr();
      Evidence evidence = new Evidence();
        evidence.setEvidenceId(5);
        evidence.setDepartmentIds("4");
        evidence.setNote("3");
        evidence.setCaseId("12");
       edVclient.put(evidence,"12");
       edVclient.queryEDV("5");
    }

}
