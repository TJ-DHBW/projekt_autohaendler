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
}
