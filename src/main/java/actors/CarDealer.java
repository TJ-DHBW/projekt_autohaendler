package actors;

import config.Configuration;

public class CarDealer extends Person{
    private float teslaModelSPriceBTC = Configuration.instance.teslaPriceBTC;
    private float teslaModelSPriceEUR = Configuration.instance.teslaPriceEUR;

    public CarDealer(String fistName, String lastName) {
        super(fistName, lastName);
    }

    public float getTeslaModelSPrice(String currency){
        switch (currency){
            case "EUR":
                return this.teslaModelSPriceEUR;
            case "BTC":
                return this.teslaModelSPriceBTC;

            default:
                throw new IllegalArgumentException("The currency " + currency + " is not accepted by the CarDealer.");
        }
    }
}
