package actors;

import btc.Wallet;

import java.util.ArrayList;

public class Person {
    private Wallet wallet;
    private String fistName;
    private String lastName;
    private ArrayList<Tesla> teslasInPossession;
    private float euro;

    public Person(String fistName, String lastName) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.wallet = new Wallet();
        this.teslasInPossession = new ArrayList<>();
        this.euro = 0f;
    }

    public ArrayList<Tesla> getTeslasInPossession() {
        return teslasInPossession;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean sendEuro(Person receiver, float amount){
        if (amount > this.euro) return false;
        this.euro -= amount;
        receiver.euro += amount;
        return true;
    }

    public boolean transferTesla(Person receiver){
        if (this.teslasInPossession.size() < 1) return false;
        Tesla teslaToTransfer = this.teslasInPossession.get(0);
        this.teslasInPossession.remove(teslaToTransfer);
        receiver.teslasInPossession.add(teslaToTransfer);
        return true;
    }

    public float getEuro() {
        return euro;
    }
}
