package btc;

import config.Configuration;

import java.security.PublicKey;
import java.util.ArrayList;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RewardTransaction extends Transaction {
    public RewardTransaction(PublicKey from) {
        super(from);
    }

    public boolean processTransaction() {
        //todo check if needed
//        if (verifySignature()) {
//            System.out.println("#transaction signature failed to verify");
//            return false;
//        }

        id = calculateHash();
        outputs.add(new TransactionOutput(recipient, value, id));
        outputs.add(new TransactionOutput(sender, 0, id));

        for (TransactionOutput o : outputs) {
            Configuration.instance.utx0Map.put(o.getID(), o);
        }

        return true;
    }
}
