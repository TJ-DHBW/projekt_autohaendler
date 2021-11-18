package btc;

import actors.Miner;
import config.Configuration;
import util.ICryptoLogger;

import java.util.ArrayList;

public class BtcNetwork {
    private final ArrayList<Block> validBlockChain = new ArrayList<>();
    public ArrayList<Miner> registeredMiner;

    private final ArrayList<ICryptoLogger> loggers = new ArrayList<>();

    /***
     * Make sure you dont want to call Miner.setActiveNetwork instead of this
     * @param miner The miner to register for the network.
     */
    public void registerMiner(Miner miner){
        if (registeredMiner.contains(miner)) return;
        registeredMiner.add(miner);
    }

    private Miner getRandomMiner(){
        if (registeredMiner.isEmpty()) return null;
        int randomIndex = Configuration.instance.r.nextInt(registeredMiner.size());
        return registeredMiner.get(randomIndex);
    }

    public ArrayList<Block> getBlockChain() {
        // TODO This is a problem, because miners could modify the blockchain.
        return validBlockChain;
    }

    public boolean broadcastTransaction(Transaction transaction){
        loggers.forEach(logger -> logger.onBroadcast(transaction));
        if (!verifyTransaction(transaction)) return false;
        handleNewTransaction(transaction);
        return true;
    }

    private void handleNewTransaction(Transaction newTransaction) {
        // TODO Implement logic for a new valid Transaction.
    }

    private boolean verifyProofOfWork(Block blockToVerify){
        for (Miner miner : registeredMiner){
            if (!miner.verifyProofOfWork(blockToVerify)) return false;
        }
        loggers.forEach(loggers -> loggers.onProofOfWorkVerification(blockToVerify));
        return true;
    }

    private boolean verifyTransaction(Transaction transactionToVerify){
        for (Miner miner : registeredMiner){
            if (!miner.verifyTransaction(transactionToVerify)) return false;
        }
        loggers.forEach(logger -> logger.onTransactionVerification(transactionToVerify));
        return true;
    }

    public void registerLogger(ICryptoLogger logger){
        if (loggers.contains(logger)) return;
        loggers.add(logger);
    }
}