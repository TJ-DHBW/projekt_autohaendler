package config;

import util.Signing;

import java.security.KeyPairGenerator;
import java.util.Random;

public enum Configuration {
        instance;
        public int difficulty = 2;
        public final Random r = new Random();
        public final KeyPairGenerator keyGen = Signing.getKeyPairGenerator();
}
