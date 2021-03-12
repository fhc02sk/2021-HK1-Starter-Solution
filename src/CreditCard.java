import java.util.HashMap;

public class CreditCard extends CreditCardBase {

    private int monthlyLimit;

    public CreditCard(String cardNumber, String owner, String currencyID, int monthlyLimit) {
        super(cardNumber, owner, currencyID);
        this.monthlyLimit = monthlyLimit;
    }

    public double monthlySpendings(int year, int month){
        double sumMonth = 0;
        for (Transaction t : transactions) {
            double amountLocalCurrency = CurrencyExchangeService.exchange(t.getCurrencyID(), currencyID, t.getAmount());

            if (t.sameMonth(year, month)){
                sumMonth += amountLocalCurrency;
            }
        }
        return sumMonth;
    }

    @Override
    public boolean authorizeTransaction(Transaction transaction) {

        double amountLocalCurrency =
                CurrencyExchangeService.exchange(transaction.getCurrencyID(), currencyID, transaction.getAmount());

        if (transaction.getCardNumber().equals(cardNumber)
            && transaction.getOwner().equals(owner)
                && (monthlySpendings(transaction.getYear(), transaction.getMonth())
                        + amountLocalCurrency < monthlyLimit)){

            transactions.add(transaction);
            return true;
        }
        return false;
    }

    public HashMap<Pair<Integer, Integer>, Double> getStatisticsToMonth(){
        HashMap<Pair<Integer, Integer>, Double> result = new HashMap<>();
        for (Transaction t : transactions) {
            Pair<Integer, Integer> p = new Pair<>(t.getYear(), t.getMonth());
            double amountLocalCurrency = CurrencyExchangeService.exchange(t.getCurrencyID(), currencyID, t.getAmount());

            if (result.containsKey(p))
                result.put(p, result.get(p) + amountLocalCurrency);
            else
                result.put(p, amountLocalCurrency);
        }
        return result;
    }
}
