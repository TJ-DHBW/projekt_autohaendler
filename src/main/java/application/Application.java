package application;

import actors.Person;
import actors.Miner;
import actors.Tesla;
import btc.Block;
import btc.BtcNetwork;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        ArrayList<Block> blockChain = new ArrayList<>();
        BtcNetwork btcNetwork = new BtcNetwork();

        Miner bob = new Miner(blockChain, "Bob");
        btcNetwork.registerMiner(bob);
        Miner eve = new Miner(blockChain, "Eve");
        btcNetwork.registerMiner(eve);
        Miner sam = new Miner(blockChain, "Sam");
        btcNetwork.registerMiner(sam);

        Person carDealer = new Person("Jimmy", "CarDealer");
        for(int i = 0; i<5; i++) {
            carDealer.getTeslasInPossession().add(new Tesla("S"));
        }

        Person ho = new Person("Maria", "Ho");
        Person negreanu = new Person( "Daniel", "Negreanu");
        Person ivey = new Person("Phil", "Ivey");
    }
}
