package util.scuffed;

import btc.TransactionInput;

public class TransactionInput2 {
    private final String id;
    private TransactionOutput2 utx0;

    public TransactionInput2(TransactionInput in) {
        this.id = in.getId();
        this.utx0 = new TransactionOutput2(in.getUTX0());
    }
}
