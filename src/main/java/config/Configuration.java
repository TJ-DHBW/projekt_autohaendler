package config;

import btc.TransactionOutput;
import java.util.HashMap;
import java.util.Random;

public enum Configuration {
        instance;
        public int difficulty = 2;
        public final Random r = new Random();
        public HashMap<String, TransactionOutput> utx0Map = new HashMap<>();
        public float minimumTransaction = 0.1f;
        public int transactionSequence = 0;
        public final double EURtoBTC = 0.000019;
        public final float teslaPriceBTC = 1.93781f;
        public final float teslaPriceEUR = 101990;
        public final float miningReward = 0.025f;
        public final float genesisValue = 100f;
        public final float WSOPPriceMoney = 1000000;
}
