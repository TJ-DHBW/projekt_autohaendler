package util;

import actors.Miner;
import btc.Block;
import btc.Transaction;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerBTC implements ICryptoLogger{
    private final Logger logger;
    private final FileHandler fh;

    public LoggerBTC(String filePath) {
        FileHandler fh1;
        this.logger = Logger.getLogger("BitcoinLogger");
        try {
            fh1 = new FileHandler(filePath);
        } catch (IOException e) {
            System.out.println("Was not able to access the logging file. No logs will be written to disk!");
            fh1 = null;
        }
        this.fh = fh1;
        logger.addHandler(this.fh);
        logger.setLevel(Level.ALL);
    }
    // TODO Use the logger

    public void onTransaction(Transaction createdTransaction) {
        // TODO Check if this is accurate
        logger.info("A new Transaction has been created: " + createdTransaction);
    }

    public void onBroadcast(Transaction broadcastTransaction) {
        // TODO Check if this is accurate
        logger.info("Transaction is being transmitted to miners for verification: " + broadcastTransaction);
    }

    public void onTransactionVerification(Transaction verifiedTransaction) {
        // TODO Check if this is accurate
        logger.info("Transaction has been verified: " + verifiedTransaction);
    }

    public void onStructuring(Object argument) {
        // TODO Implement logging
    }

    public void onProofOfWork(Miner chosenMiner, Block blockToMine) {
        // TODO Check if this is accurate
        logger.info("The miner " + chosenMiner + " is solving the complex mathematical problem for block: " + blockToMine);
    }

    public void onBlockTransmission(Block transmittingBlock) {
        // TODO Check if this is accurate
        logger.info("Block is being transmitted to miners for verification: " + transmittingBlock);
    }

    public void onProofOfWorkVerification(Block verifiedBlock) {
        // TODO Check if this is accurate
        logger.info("Block has been verified: " + verifiedBlock);
    }

    public void onBlockAdded(Block addedBlock) {
        logger.info("Block has been added to the chain: " + addedBlock);
    }
}
