package actors;

import btc.Block;
import btc.Transaction;
import config.Configuration;

import java.util.ArrayList;

public class Miner {
    private ArrayList<Block> blockchain;
    private String name;

    public Miner(ArrayList<Block> blockchain, String name) {
        this.blockchain = blockchain;
        this.name = name;
    }

    public void mineValidBlock(String guid) {
        String previousHash = !blockchain.isEmpty() ? blockchain.get(blockchain.size()-1).getHash() : "0";
        int difficulty = Configuration.instance.difficulty;
        Block newBlock = new Block(guid, previousHash, difficulty);
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!newBlock.getHash().substring(0, difficulty).equals(target)) {
            newBlock.incrementNonce();
            newBlock.calculateHash();
        }

        blockchain.add(newBlock);
    }

    public boolean verifyTransaction(Transaction transaction){
        //todo implement
        return false;
    }

    public boolean verifyProofOfWork(Block block){
        //todo implement
        return false;
    }


}