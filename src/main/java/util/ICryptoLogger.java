package util;

import actors.Miner;
import btc.Block;
import btc.Transaction;

public interface ICryptoLogger {
    void onTransaction(Transaction createdTransaction);

    void onBroadcast(Transaction broadcastTransaction);

    void onTransactionVerification(Transaction verifiedTransaction);

    void onStructuring(Transaction addedTransaction, Block newBlock);

    void onProofOfWork(Miner chosenMiner, Block blockToMine);

    void onBlockTransmission(Block transmittingBlock);

    void onProofOfWorkVerification(Block verifiedBlock);

    void onBlockAdded(Block addedBlock);
}
