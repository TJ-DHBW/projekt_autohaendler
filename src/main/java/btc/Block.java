package btc;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    private final String previousHash;
    private final long timeStamp;
    private final ArrayList<Transaction> transactions;
    private String hash;
    private int nonce;
    private String merkleRoot;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = this.sha256();
        this.transactions = new ArrayList<>();
    }

    public String sha256() {

        return Hashing.sha256(previousHash + timeStamp + nonce + merkleRoot);
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

    public void addTransaction(Transaction transactionToAdd) {
        if (transactions.contains(transactionToAdd)) return;
        transactions.add(transactionToAdd);
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    @Override
    public String toString() {
        return "Block{" +
                "previousHash='" + previousHash + '\'' +
                ", timeStamp=" + timeStamp +
                ", transactions=" + transactions +
                '}';
    }
}