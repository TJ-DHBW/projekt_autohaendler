package btc;

import actors.Miner;
import config.Configuration;

import java.util.ArrayList;

public class BtcNetwork {
    private final ArrayList<Block> validBlockChain = new ArrayList<>();
    public ArrayList<Miner> registeredMiner;

    /***
     * Make sure you dont want to call Miner.setActiveNetwork instead of this
     * @param miner The miner to register for the network.
     */
    public void registerMiner(Miner miner){
        if (registeredMiner.contains(miner)) return;
        registeredMiner.add(miner);
    }

    public Miner getRandomMiner(){
        if (registeredMiner.isEmpty()) return null;
        int randomIndex = Configuration.instance.r.nextInt(registeredMiner.size());
        return registeredMiner.get(randomIndex);
    }

    public ArrayList<Block> getBlockChain() {
        // TODO This is a problem, because miners could modify the blockchain.
        return validBlockChain;
    }
}