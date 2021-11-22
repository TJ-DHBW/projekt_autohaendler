package util.scuffed;

import btc.Block;
import btc.Transaction;

import java.util.ArrayList;

public class Block2 {
    private final String previousHash;
    private final long timeStamp;
    private String hash;
    private int nonce;
    private final ArrayList<Transaction2> transactions;

    public Block2(Block block) {
        this.previousHash = block.getPreviousHash();
        this.timeStamp = block.getTimeStamp();
        this.hash = block.getHash();
        this.nonce = block.getNonce();
        this.transactions = new ArrayList<>();
        for (Transaction tx : block.getTransactions()){
            this.transactions.add(new Transaction2(tx));
        }
    }
}
