package btc;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import config.Configuration;
import util.StringUtility;

public class Transaction {
    protected final PublicKey sender;
    protected final PublicKey recipient;
    protected final float value;
    protected final ArrayList<TransactionOutput> outputs = new ArrayList<>();
    protected final ArrayList<TransactionInput> inputs;
    protected String id = null;
    protected byte[] signature;

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }

    protected Transaction(PublicKey from, float value) {
        this.sender = from;
        this.recipient = from;
        this.value = value;
        this.inputs = new ArrayList<>();
    }

    protected String calculateHash() {
        Configuration.instance.transactionSequence++;
        return StringUtility.applySha256(StringUtility.getStringFromKey(sender) + StringUtility.getStringFromKey(recipient)
                + value + Configuration.instance.transactionSequence);
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtility.getStringFromKey(sender) + StringUtility.getStringFromKey(recipient) + value;
        signature = StringUtility.applyECDSASig(privateKey, data);
    }

    /**
     * Verifies a signature
     * @return false if verified.
     */
    public boolean verifySignature() {
        String data = StringUtility.getStringFromKey(sender) + StringUtility.getStringFromKey(recipient) + value;
        return !StringUtility.verifyECDSASig(sender, data, signature);

    }

    public boolean processTransaction() {
        if (verifySignature()) {
            System.out.println("#transaction signature failed to verify");
            return false;
        }

        for (TransactionInput i : inputs) {
            i.setUtx0(Configuration.instance.utx0Map.get(i.getId()));
        }

        if (getInputsValue() < Configuration.instance.minimumTransaction) {
            System.out.println("#transaction input to small | " + getInputsValue());
            return false;
        }

        float leftOver = getInputsValue() - value;
        id = calculateHash();
        outputs.add(new TransactionOutput(recipient, value, id));
        outputs.add(new TransactionOutput(sender, leftOver, id));

        for (TransactionOutput o : outputs) {
            Configuration.instance.utx0Map.put(o.getID(), o);
        }

        for (TransactionInput i : inputs) {
            if (i.getUTX0() == null) {
                continue;
            }
            Configuration.instance.utx0Map.remove(i.getUTX0().getID());
        }

        return true;
    }

    public float getInputsValue() {
        float total = 0;

        for (TransactionInput i : inputs) {
            if (i.getUTX0() == null) {
                continue;
            }
            total += i.getUTX0().getValue();
        }

        return total;
    }

    public float getOutputsValue() {
        float total = 0;

        for (TransactionOutput o : outputs) {
            total += o.getValue();
        }

        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PublicKey getSender() {
        return sender;
    }

    public PublicKey getRecipient() {
        return recipient;
    }

    public float getValue() {
        return value;
    }

    public ArrayList<TransactionInput> getInputs() {
        return inputs;
    }

    public ArrayList<TransactionOutput> getOutputs() {
        return outputs;
    }
}
