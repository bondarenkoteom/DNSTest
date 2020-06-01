package dns.pages;

import java.util.HashMap;
import java.util.Map;

public class BasketV {

    private static Map<String, Double> prices = new HashMap<>();

    public static void addInBasket(String name, Double price) {
        prices.put(name, price);
    }

    public static Map<String, Double> getPrices() {
        return prices;
    }


}
