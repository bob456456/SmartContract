import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class SKELTokenContract extends Contract {

    private static final String CONTRACT_ADDRESS = "Your_Contract_Address"; // Update with your contract address
    private static final String PRIVATE_KEY = "Your_Private_Key"; // Update with your private key

    public static SKELTokenContract loadContract() {
        Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/your_infura_project_id")); // Update with your Infura project ID
        Credentials credentials = Credentials.create(PRIVATE_KEY);
        return SKELTokenContract.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());
    }

    public static void main(String[] args) {
        SKELTokenContract skelTokenContract = loadContract();
        try {
            BigInteger totalSupply = skelTokenContract.totalSupply().send();
            System.out.println("Total Supply of SKEL Tokens: " + totalSupply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected SKELTokenContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super("", contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SKELTokenContract(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, Contract.GasProvider gasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, gasProvider);
    }

    public BigInteger totalSupply() throws Exception {
        return contract.totalSupply().send();
    }
}
