package util.exportModels;

import btc.Transaction;
import btc.TransactionInput;
import btc.TransactionOutput;

import java.util.ArrayList;

public class Transaction2 {
    protected final String sender;
    protected final String recipient;
    protected final float value;
    protected final ArrayList<TransactionOutput2> outputs = new ArrayList<>();
    protected final ArrayList<TransactionInput2> inputs = new ArrayList();
    protected String id;
    protected byte[] signature;

    public Transaction2(Transaction tx) {
        this.sender = tx.getSender().toString();
        this.recipient = tx.getRecipient().toString();
        this.value = tx.getValue();
        this.id = tx.getId();
        this.signature = tx.getSignature();

        for (TransactionInput in : tx.getInputs()){
            this.inputs.add(new TransactionInput2(in));
        }
        for (TransactionOutput out : tx.getOutputs()){
            this.outputs.add(new TransactionOutput2(out));
        }
    }
}
