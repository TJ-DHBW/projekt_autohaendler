package actors;

import btc.Block;
import btc.BtcNetwork;
import btc.Transaction;
import config.Configuration;

import java.util.ArrayList;

public class Miner {
    private BtcNetwork activeNetwork;
    private String name;

    public Miner(String name) {
        this.name = name;
    }

    public void mineValidBlock(Block blockToMine, int difficulty) {
        // TODO Miner needs to add its own reward here
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!blockToMine.getHash().substring(0, difficulty).equals(target)) {
            blockToMine.incrementNonce();
            blockToMine.calculateHash();
        }
    }

    public boolean verifyTransaction(Transaction transaction){
        //todo Check if correct
        if (transaction.getId() == null){
            return transaction.processTransaction();
        }else{
            return !transaction.verifySignature();
        }
    }

    public boolean verifyProofOfWork(Block blockToVerify){
        //TODO Check if correct
        //TODO Do we have to check the transactions again?
        int difficulty = Configuration.instance.difficulty;
        String target = new String(new char[difficulty]).replace('\0', '0');
        return blockToVerify.getHash().substring(0, difficulty).equals(target);
    }

    public void setActiveNetwork(BtcNetwork activeNetwork) {
        this.activeNetwork = activeNetwork;
        activeNetwork.registerMiner(this);
    }
}