package util.scuffed;

import btc.TransactionOutput;

public class TransactionOutput2 {
    private final String id;
    private final String recipient;
    private final float value;

    public TransactionOutput2(TransactionOutput output) {
        this.id = output.getID();
        this.recipient = output.getRecipient().toString();
        this.value = output.getValue();
    }
}
