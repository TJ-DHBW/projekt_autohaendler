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
        this.teslasInPossession = new ArrayList<>();
        this.euro = 0f;
    }

    public ArrayList<Tesla> getTeslasInPossession() {
        return teslasInPossession;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean sendEuro(Person receiver, float amount) {
        if (amount > this.euro) return false;
        this.euro -= amount;
        receiver.euro += amount;
        return true;
    }

    public boolean transferTesla(Person receiver) {
        if (this.teslasInPossession.size() < 1) return false;
        Tesla teslaToTransfer = this.teslasInPossession.get(0);
        this.teslasInPossession.remove(teslaToTransfer);
        receiver.teslasInPossession.add(teslaToTransfer);
        return true;
    }

    public void donateEuroToInstitution(Institution institution, float euroToDonate) {
        if (euroToDonate > this.euro) return;
        this.euro -= euroToDonate;
        institution.receiveDonation(euroToDonate);
    }

    public void donateWalletToInstitution(Institution institution) {
        if (this.wallet == null) return;
        institution.receiveDonation(this.wallet);
        this.wallet = null;
    }

    public void liveAContentLifeWithoutBTCAndMuchMoney() {
        if (this.wallet != null || this.euro > 100) {
            throw new IllegalStateException("A content life can only be lived if you donate all your belongings.");
        }
    }

    public void receiveEuro(float euroReceived) {
        this.euro += euroReceived;
    }

    public float getEuro() {
        return euro;
    }
}
