package util;

import actors.Miner;
import btc.Block;
import btc.Transaction;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.logging.*;

public class LoggerBTC implements ICryptoLogger {
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
        fh.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());
                return formatter.format(record.getInstant()) + " - " + record.getMessage() + "\n\n";
            }
        });
        logger.addHandler(this.fh);
        logger.setLevel(Level.ALL);
    }

    public void onTransaction(Transaction createdTransaction) {
        logger.info("A new Transaction has been created: " + createdTransaction);
    }

    public void onBroadcast(Transaction broadcastTransaction) {
        logger.info("Transaction is being transmitted to miners for verification: " + broadcastTransaction);
    }

    public void onTransactionVerification(Transaction verifiedTransaction) {
        logger.info("Transaction has been verified: " + verifiedTransaction);
    }

    public void onStructuring(Transaction addedTransaction, Block newBlock) {
        logger.info("Transaction " + addedTransaction + " has been added to block " + newBlock);
    }

    public void onProofOfWork(Miner chosenMiner, Block blockToMine) {
        logger.info("The miner " + chosenMiner.getName() + " is solving the complex mathematical problem for block: " + blockToMine);
    }

    public void onBlockTransmission(Block transmittingBlock) {
        logger.info("Block is being transmitted to miners for verification: " + transmittingBlock);
    }

    public void onProofOfWorkVerification(Block verifiedBlock) {
        logger.info("Block has been verified: " + verifiedBlock);
    }

    public void onBlockAdded(Block addedBlock) {
        logger.info("Block has been added to the chain: " + addedBlock);
    }
}