package btc;

import config.Configuration;
import util.StringUtility;

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

        for (TransactionOutput o : outputs) {
            Configuration.instance.utx0Map.put(o.getID(), o);
        }
        return true;
    }
    public boolean verifySignature() {
        String data = StringUtility.getStringFromKey(sender) + StringUtility.getStringFromKey(recipient) + value;
        if(signature == null){
            int i = 0;
        }
        return !StringUtility.verifyECDSASig(sender, data, signature);
    }
}
