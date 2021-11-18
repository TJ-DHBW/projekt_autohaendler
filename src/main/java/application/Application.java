package application;

import actors.Customer;
import actors.Miner;
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

        Customer ho = new Customer("Maria", "Ho");
        Customer negreanu = new Customer( "Daniel", "Negreanu");
        Customer ivey = new Customer("Phil", "Ivey");
    }
}
