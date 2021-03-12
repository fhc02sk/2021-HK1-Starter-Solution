import java.util.HashMap;

public class CurrencyExchangeService {
    private static final HashMap<String, Double> s_rates;

    static {
        s_rates = new HashMap<>();
        s_rates.put("HUF", 355.94);
        s_rates.put("EUR", 1.00);
        s_rates.put("USD", 1.21);
        s_rates.put("CAD", 1.55);
        s_rates.put("SEK", 10.17);
        s_rates.put("NOK", 10.37);
    }

    public static double exchange(String transactionCurrencyID, String cardCurrencyID, double amount){
        return amount / s_rates.get(transactionCurrencyID) * s_rates.get(cardCurrencyID);
    }
}
