package btc;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    private final String previousHash;
    private final long timeStamp;
    private final int difficulty;
    private String hash;
    private int nonce;
    private ArrayList<Transaction> transactions;

    public Block(String previousHash, int difficulty) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = this.sha256();
        this.difficulty = difficulty;
        this.transactions = new ArrayList<>();
    }



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

    public int getDifficulty() {
        return difficulty;
    }
}