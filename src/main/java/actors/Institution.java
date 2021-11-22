package actors;

import btc.Wallet;

import java.util.ArrayList;

public class Institution {
    private String name;
    private final ArrayList<Wallet> wallets = new ArrayList<>();
    private float euros;

    public Institution(String name) {
        this.name = name;
    }

    public void receiveDonation(Wallet donatedWallet){
        if(this.wallets.contains(donatedWallet)) return;
        this.wallets.add(donatedWallet);
    }

    public void receiveDonation(float donatedEuros){
        this.euros += donatedEuros;
    }

    @Override
    public String toString() {
        float sumBTC = 0;
        for (Wallet wallet : this.wallets){
            sumBTC += wallet.getBalance();
        }
        return "Institution{" +
                "name='" + name + '\'' +
                ", BTCs=" + sumBTC +
                ", euros=" + euros +
                '}';
    }
}
