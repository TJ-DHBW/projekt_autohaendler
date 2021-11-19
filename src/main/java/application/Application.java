package application;

import actors.Person;
import actors.Miner;
import actors.Tesla;
import btc.BtcNetwork;
import btc.Transaction;
import btc.Wallet;
import util.ICryptoLogger;
import util.LoggerBTC;


public class Application {
    // TODO decide logFilePath
    public static final String logFilePath = "logFile.txt";

    public static void main(String[] args) {
        BtcNetwork btcNetwork = new BtcNetwork();
        ICryptoLogger logger = new LoggerBTC(logFilePath);
        btcNetwork.registerLogger(logger);

        Miner bob = new Miner("Bob");
        bob.setActiveNetwork(btcNetwork);
        Miner eve = new Miner("Eve");
        eve.setActiveNetwork(btcNetwork);
        Miner sam = new Miner("Sam");
        sam.setActiveNetwork(btcNetwork);

        Person carDealer = new Person("Jimmy", "CarDealer");
        for(int i = 0; i<5; i++) {
            carDealer.getTeslasInPossession().add(new Tesla("S"));
        }

        Person nakamoto = new Person("Satoshi", "Nakamoto");
        Wallet wallet0 = new Wallet();
        nakamoto.setWallet(wallet0);

        //todo check where transactionInputId comes from
        //Transaction transaction = new Transaction(null, nakamoto.getWallet().getPublicKey(), 100,  );

        Person ho = new Person("Maria", "Ho");
        Wallet wallet1 = new Wallet();
        ho.setWallet(wallet1);
        Person negreanu = new Person( "Daniel", "Negreanu");
        Wallet wallet2 = new Wallet();
        negreanu.setWallet(wallet2);
        Person ivey = new Person("Phil", "Ivey");
        Wallet wallet3 = new Wallet();
        ivey.setWallet(wallet3);
    }
}
