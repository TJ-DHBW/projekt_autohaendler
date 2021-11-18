package actors;

import btc.Wallet;

import java.util.ArrayList;

public class Person {
    private Wallet wallet;
    private String fistName;
    private String lastName;
    private ArrayList<Tesla> teslasInPossession;

    public Person(String fistName, String lastName) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.wallet = new Wallet();
        this.teslasInPossession = new ArrayList<>();
    }

    public ArrayList<Tesla> getTeslasInPossession() {
        return teslasInPossession;
    }
}
