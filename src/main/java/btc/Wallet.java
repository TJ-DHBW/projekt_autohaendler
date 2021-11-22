package btc;

import config.Configuration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public HashMap<String, TransactionOutput> utx0Map = new HashMap<>();
    private final BtcNetwork associatedNetwork;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public Wallet(BtcNetwork network) {
        this.associatedNetwork = network;
        KeyPair keyPair= generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            keyGen.initialize(ecSpec, random);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public float getBalance() {
        float total = 0;

        for (Map.Entry<String, TransactionOutput> item : Configuration.instance.utx0Map.entrySet()) {
            TransactionOutput utx0 = item.getValue();
            if (utx0.isMine(publicKey)) {
                utx0Map.put(utx0.getID(), utx0);
                total += utx0.getValue();
            }
        }

        return total;
    }

    public boolean sendFunds(PublicKey recipient, float value){
        Transaction transaction = createTransaction(recipient, value);
        if (transaction == null) return false;
        return this.associatedNetwork.broadcastTransaction(transaction);
    }

    public boolean createGenesis(){
        RewardTransaction genesisTransaction = new RewardTransaction(publicKey, true);
        genesisTransaction.generateSignature(privateKey);

        return this.associatedNetwork.broadcastTransaction(genesisTransaction);
    }

    private Transaction createTransaction(PublicKey recipient, float value) {
        if (getBalance() < value) {
            System.out.println("#not enough funds to send transaction - transaction discarded");
            return null;
        }

        ArrayList<TransactionInput> inputs = new ArrayList<>();

        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : utx0Map.entrySet()) {
            TransactionOutput utx0 = item.getValue();
            total += utx0.getValue();
            inputs.add(new TransactionInput(utx0.getID()));
            if (total > value) {
                break;
            }
        }

        Transaction transaction = new Transaction(publicKey, recipient, value, inputs);
        transaction.generateSignature(privateKey);

        for (TransactionInput input : inputs) {
            utx0Map.remove(input.getId());
        }

        return transaction;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
