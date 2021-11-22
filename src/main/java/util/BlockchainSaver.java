package util;

import btc.Block;
import com.google.gson.GsonBuilder;
import util.exportModels.Block2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BlockchainSaver {
    public static void asJson(ArrayList<Block> blockchain, String pathName) {
        ArrayList<Block2> blocks = blockchainToScuffedBlockchain(blockchain);
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocks);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(new File(pathName)));
            writer.write(blockchainJson);
            writer.close();
        } catch (IOException e) {
            System.out.println("Was not able to access file: " + pathName + ". Blockchain was not saved.");
        }
    }

    private static ArrayList<Block2> blockchainToScuffedBlockchain(ArrayList<Block> blockchain) {
        ArrayList<Block2> ret = new ArrayList<>();
        for (Block blk : blockchain) {
            ret.add(new Block2(blk));
        }
        return ret;
    }
}
