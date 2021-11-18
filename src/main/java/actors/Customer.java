package actors;

import btc.Wallet;

public class Customer {
    private Wallet wallet;
    private String fistName;
    private String lastName;

    public Customer(String fistName, String lastName) {
        this.fistName = fistName;
        this.lastName = lastName;
        wallet = new Wallet();
    }
}
