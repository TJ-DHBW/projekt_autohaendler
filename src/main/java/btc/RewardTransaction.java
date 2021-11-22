package btc;

import config.Configuration;

import java.security.PublicKey;
import java.util.ArrayList;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RewardTransaction extends Transaction {
    public RewardTransaction(PublicKey from, boolean isGenesis) {
        super(from, isGenesis? Configuration.instance.genesisValue : Configuration.instance.miningReward);
    }

    public boolean processTransaction() {
        id = calculateHash();
        outputs.add(new TransactionOutput(recipient, value, id));
        outputs.add(new TransactionOutput(sender, 0, id));

        for (TransactionOutput o : outputs) {
            Configuration.instance.utx0Map.put(o.getID(), o);
        }

        return true;
    }
}
