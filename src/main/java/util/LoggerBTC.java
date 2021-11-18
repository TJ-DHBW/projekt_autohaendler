package util;

import btc.Block;

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

    public void onTransaction(String event, Object argument) {
        // TODO Implement logging
    }

    public void onBroadcast(String event, Object argument) {
        // TODO Implement logging
    }

    public void onBroadcastVerification(String event, Object argument) {
        // TODO Implement logging
    }

    public void onStructuring(String event, Object argument) {
        // TODO Implement logging
    }

    public void onProofOfWork(String event, Object argument) {
        // TODO Implement logging
    }

    public void onBlockTransmission(String event, Block transmittingBlock) {
        // TODO Check if this is accurate.
        logger.info("Block is being transmitted: " + transmittingBlock);
    }

    public void onTransmissionVerification(String event, Object argument) {
        // TODO Implement logging
    }

    public void onBlockAdded(String event, Block addedBlock) {
        logger.info("Block has been added to the chain: " + addedBlock);
    }
}
