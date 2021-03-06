/**
 * Copyright(c) 2018
 * Ulord core developers
 */
package one.ulord.upaas.ucwallet.client;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author haibo
 * @since 7/4/18
 */
public class UCWalletDemoApplication implements TransactionActionHandler{

    public void testContentContract(){
        try {
            System.out.println("Create a content contract object....");
            ContentContract contentContract = new ContentContract(
                    "http://testnet.usc.ulord.one:58858",
                    "0xc3f4727b0388ecf04762172585b8e7f82ced166c", // UShare UX
                    "0xcea7d4f4a367be785628b3b49c9e8563414be3fb",
                    "0xba6baf26e4d7aa8062a8921843ad4b5714afbb57",
                    "keystore/no6.keystore",
                    "12345678",
                    this
                    );

            // get gas balance
            System.out.println("SUT balance:" + contentContract.getGasBalance().toString());
            try {
                System.out.println("Token balance main:" + contentContract.getTokenBalance().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (false) {
                // set gas balance
                System.out.println("Transfer some SUT to...");
                contentContract.transferGas("transfer gas", "0x2e836371bf20107837da6ad9bb4d08d8f53f65ba",
                        new BigInteger("1000000000000"));
            }

            if (true) {
                // set gas balance
                System.out.println("Transfer some SUT to...");
                String hash = contentContract.transferGas("0x2e836371bf20107837da6ad9bb4d08d8f53f65ba",
                        new BigInteger("1000000000000"));
                System.out.println(hash);
            }

            if (false) {
                // test token transfer
                System.out.println("Transfer 100000000000000000(1Token) to address 0x24fd610e1769f1f051e6d25a9099588df13d7feb... ");
                contentContract.transferToken("tranfer token:",
                        "0x24fd610e1769f1f051e6d25a9099588df13d7feb",
                        BigInteger.valueOf(1000000000));
            }

            if (false) {
                // test publish content
                System.out.println("Connect to UDFS network...");
                UDFSClient udfsClient = new UDFSClient("/ip4/114.67.37.2/tcp/20418"); // Test UDFS network
                System.out.println("Publish a sentence to UDFS ...");
                String udfsHash = udfsClient.publishResource("test",
                        ("Hello Ulord Platform" + Calendar.getInstance().toString()).getBytes());
                byte[] udfsContent = udfsClient.getContent(udfsHash);
                if (udfsContent != null) {
                    System.out.println(new String(udfsContent));
                } else {
                    System.out.println("Cannot get content from UDFS.");
                }

                System.out.println("publish a resource " + udfsHash
                        + " to ulord using address:0x3f16131ac9203656a9ca790f23878ae165c3eb4f");
                contentContract.publishResource("publish resource", udfsHash,
                        "0x3f16131ac9203656a9ca790f23878ae165c3eb4f",
                        BigInteger.valueOf(100000000000000L), false);
            }

            // first, we nedd to approve publish contract to using current user address tokens
            // !! A user address only can approve once, you can approve it again after approve with a ZERO parameter
            // Current action must be successfully execute before transfer tokens
//            contentContract.approveContractQuality("Clear Approve", BigInteger.ZERO);
//            contentContract.approveContractQuality("Approve", new BigInteger("10000000000000000000"));

            // test multiple transfer action
            if (false) {
                System.out.println("Transfer some quality to multiple address");
                List<String> addressList = new ArrayList<>();
                addressList.add("0x3f16131ac9203656a9ca790f23878ae165c3eb4f");
                addressList.add("0x2e836371bf20107837da6ad9bb4d08d8f53f65ba");
                addressList.add("0x597ed0bf61b741a80d4774a4a4d318b431a07b07");
                addressList.add("0x9babfbae60ad466a5b68d29e127bb59429828216");
                List<BigInteger> valueList = new ArrayList<>();
                valueList.add(new BigInteger("200000000000000000"));
                valueList.add(new BigInteger("300000000000000000"));
                valueList.add(new BigInteger("400000000000000000"));
                valueList.add(new BigInteger("500000000000000000"));
                contentContract.transferTokens("transferMultipleAddress", addressList, valueList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        UCWalletDemoApplication application = new UCWalletDemoApplication();
        application.testContentContract();
    }

    @Override
    public void success(String id, String txhash) {
        System.out.println("--->id:" + id + ", txhash:" + txhash);
    }

    @Override
    public void fail(String id, String message) {
        System.out.println("--->id:" + id + ", message:" + message);
    }
}
