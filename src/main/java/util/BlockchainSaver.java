package util;

import btc.Block;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BlockchainSaver {
    static void asJson(List<Block> blockchain, String pathName){
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(new File(pathName)));
            writer.write(blockchainJson);
            writer.close();
        } catch (IOException e) {
            System.out.println("Was not able to access file: " + pathName + ". Blockchain was not saved.");
        }
    }
}
