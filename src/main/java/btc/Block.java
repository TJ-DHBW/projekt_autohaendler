package btc;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    private final String previousHash;
    private final long timeStamp;
    private String hash;
    private int nonce;
    private final ArrayList<Transaction> transactions;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = this.sha256();
        this.transactions = new ArrayList<>();
    }
    //TODO This class might have to be remade. sha should probably hash over more stuff etc.

    public String sha256() {
        return Hashing.sha256(previousHash + timeStamp + nonce);
    }

    public void incrementNonce() {
        nonce++;
    }

    public void calculateHash() {
        hash = sha256();
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void addTransaction(Transaction transactionToAdd){
        if (transactions.contains(transactionToAdd)) return;
        transactions.add(transactionToAdd);
    }
}