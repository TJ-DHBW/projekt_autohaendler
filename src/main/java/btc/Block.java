package btc;

import java.util.Date;

public class Block {
    private final String guid;
    private final String previousHash;
    private final long timeStamp;
    private final int difficulty;
    private String hash;
    private int nonce;

    public Block(String guid, String previousHash, int difficulty) {
        this.guid = guid;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = this.sha256();
        this.difficulty = difficulty;
    }

    public String sha256() {
        return Hashing.sha256(previousHash + timeStamp + nonce + guid);
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
}