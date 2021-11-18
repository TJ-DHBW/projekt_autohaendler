package application;

import actors.Person;
import actors.Miner;
import actors.Tesla;
import btc.BtcNetwork;
import util.ICryptoLogger;
import util.LoggerBTC;


public class Application {
    // TODO decide logFilePath
    public static final String logFilePath = "";

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

        Person ho = new Person("Maria", "Ho");
        Person negreanu = new Person( "Daniel", "Negreanu");
        Person ivey = new Person("Phil", "Ivey");
    }
}
