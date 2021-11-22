package application;

import actors.Institution;
import actors.Person;
import actors.Miner;
import actors.Tesla;
import btc.BtcNetwork;
import btc.Wallet;
import config.Configuration;
import util.ICryptoLogger;
import util.LoggerBTC;


public class Application {
    public static final String logFilePath = "logFile.txt";

    public static void main(String[] args) {
        BtcNetwork btcNetwork = new BtcNetwork();
        ICryptoLogger logger = new LoggerBTC(logFilePath);
        btcNetwork.registerLogger(logger);

        btcNetwork.registerMiner(new Miner("Bob", btcNetwork));
        btcNetwork.registerMiner(new Miner("Eve", btcNetwork));
        btcNetwork.registerMiner(new Miner("Sam", btcNetwork));

        Person carDealer = new Person("Jimmy", "CarDealer");
        for(int i = 0; i<5; i++) {
            carDealer.getTeslasInPossession().add(new Tesla("S"));
        }

        Person nakamoto = new Person("Satoshi", "Nakamoto");
        Wallet wallet0 = new Wallet(btcNetwork);
        nakamoto.setWallet(wallet0);
        Institution germanChildrensCancerAid = new Institution("German children's cancer aid");

        //todo check where transactionInputId comes from
        //Transaction transaction = new Transaction(null, nakamoto.getWallet().getPublicKey(), 100,  );

        Person ho = new Person("Maria", "Ho");
        Wallet wallet1 = new Wallet(btcNetwork);
        ho.setWallet(wallet1);
        Person negreanu = new Person( "Daniel", "Negreanu");
        Wallet wallet2 = new Wallet(btcNetwork);
        negreanu.setWallet(wallet2);
        Person ivey = new Person("Phil", "Ivey");
        Wallet wallet3 = new Wallet(btcNetwork);
        ivey.setWallet(wallet3);

        winnerOfWSOP(ho);
        winnerOfWSOP(negreanu);
        winnerOfWSOP(ivey);

        buyBTCWithEuro(ho, nakamoto, 18.92f);
        buyBTCWithEuro(negreanu, nakamoto, 18.92f);
        buyBTCWithEuro(ivey, nakamoto, 18.92f);

        nakamoto.donateWalletToInstitution(germanChildrensCancerAid);
        nakamoto.donateEuroToInstitution(germanChildrensCancerAid, nakamoto.getEuro());
        nakamoto.liveAContentLifeWithoutBTCAndMuchMoney();

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

    public static void winnerOfWSOP(Person winnerOfTheWSOP){
        winnerOfTheWSOP.receiveEuro(Configuration.instance.WSOPPriceMoney);
    }
}
