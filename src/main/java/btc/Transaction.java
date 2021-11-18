package btc;

import actors.Person;

public class Transaction {
    private Person person1;
    private Person person2;
    private float amount;

    public Transaction(Person person1, Person person2, float amount) {
        this.person1 = person1;
        this.person2 = person2;
        this.amount = amount;
    }
}
