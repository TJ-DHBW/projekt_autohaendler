package application;

import actors.Person;
import actors.Miner;
import actors.Tesla;
import btc.BtcNetwork;
import btc.Wallet;
import config.Configuration;
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

        buyBTCWithEuro(ho, nakamoto, 18.92f);
        buyBTCWithEuro(negreanu, nakamoto, 18.92f);
        buyBTCWithEuro(ivey, nakamoto, 18.92f);

        buyTeslaS(ho, carDealer);
        buyTeslaS(ho, carDealer);
        buyTeslaS(negreanu, carDealer);
        buyTeslaS(negreanu, carDealer);
        buyTeslaS(ivey, carDealer);

        System.out.println("--------Overview--------");
        System.out.println("Ho: BTC=" + carDealer.getWallet().getBalance() + ", Teslas=" + carDealer.getTeslasInPossession().size() + ", EUR=" + carDealer.getEuro());
        System.out.println("Ho: BTC=" + ho.getWallet().getBalance() + ", Teslas=" + ho.getTeslasInPossession().size() + ", EUR=" + ho.getEuro());
        System.out.println("Ho: BTC=" + negreanu.getWallet().getBalance() + ", Teslas=" + negreanu.getTeslasInPossession().size() + ", EUR=" + negreanu.getEuro());
        System.out.println("Ho: BTC=" + ivey.getWallet().getBalance() + ", Teslas=" + ivey.getTeslasInPossession().size() + ", EUR=" + ivey.getEuro());
        System.out.println("--------End--------");
    }

    public static void buyTeslaS(Person buyer, Person seller){
        //TODO Implement helper function for buying a tesla.
        if (!seller.transferTesla(buyer)){
            System.out.println("Can not transfer a tesla. Payment should be revoked.");
        }
    }

    public static void buyBTCWithEuro(Person buyer, Person seller, float bitcoinsToBuy){
        float priceEUR = (float) (bitcoinsToBuy/ Configuration.instance.EURtoBTC);
        if (!buyer.sendEuro(seller, priceEUR)) return;
        //TODO Implement helper function for buying bitcoin
    }
}
