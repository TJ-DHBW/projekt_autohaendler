package actors;

import btc.*;
import config.Configuration;

import java.util.ArrayList;

public class Miner {
    private String name;
    private Wallet wallet;

    public Miner(String name, BtcNetwork networkToMineOn) {
        this.name = name;
        wallet = new Wallet(networkToMineOn);
    }

    public void mineValidBlock(Block blockToMine, int difficulty) {
        // TODO Miner needs to add its own reward here
        RewardTransaction rewardTransaction = new RewardTransaction(this.wallet.getPublicKey());
        blockToMine.addTransaction(rewardTransaction);
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
}