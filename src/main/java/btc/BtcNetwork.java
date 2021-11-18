package btc;

import actors.Miner;

import java.util.ArrayList;

public class BtcNetwork {
    public ArrayList<Miner> registeredMiner;

    public void registerMiner(Miner miner){
        registeredMiner.add(miner);
    }
}