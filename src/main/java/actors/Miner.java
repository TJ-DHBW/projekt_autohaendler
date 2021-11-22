package actors;

import btc.*;
import config.Configuration;
import util.StringUtility;

public class Miner {
    private String name;
    private Wallet wallet;

    public Miner(String name, BtcNetwork networkToMineOn) {
        this.name = name;
        wallet = new Wallet(networkToMineOn);
    }

    public void mineValidBlock(Block blockToMine, int difficulty) {
        RewardTransaction rewardTransaction = new RewardTransaction(this.wallet.getPublicKey(), false);
        rewardTransaction.generateSignature(wallet.getPrivateKey());
        rewardTransaction.processTransaction();
        blockToMine.addTransaction(rewardTransaction);
        blockToMine.setMerkleRoot(StringUtility.getMerkleRoot(blockToMine.getTransactions()));
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!blockToMine.getHash().substring(0, difficulty).equals(target)) {
            blockToMine.incrementNonce();
            blockToMine.calculateHash();
        }
    }

    public void mineGenesisBlock(Block genesisBlock, int difficulty) {
        genesisBlock.setMerkleRoot(StringUtility.getMerkleRoot(genesisBlock.getTransactions()));
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!genesisBlock.getHash().substring(0, difficulty).equals(target)) {
            genesisBlock.incrementNonce();
            genesisBlock.calculateHash();
        }
    }

    public boolean verifyTransaction(Transaction transaction) {
        if (transaction.getId() == null) {
            return transaction.processTransaction();
        } else {
            return !transaction.verifySignature();
        }
    }

    public boolean verifyProofOfWork(Block blockToVerify) {
        int difficulty = Configuration.instance.difficulty;
        String target = new String(new char[difficulty]).replace('\0', '0');
        return blockToVerify.getHash().substring(0, difficulty).equals(target);
    }

    public String getName() {
        return name;
    }
}