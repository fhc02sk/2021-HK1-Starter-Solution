public class PrepaidCreditCard extends CreditCardBase {

    private double availableAmount;

    public PrepaidCreditCard(String cardNumber, String owner, String currencyID) {
        super(cardNumber, owner, currencyID);
        this.availableAmount = 0;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void load(double amount){
        this.availableAmount += amount;
    }

    @Override
    public boolean authorizeTransaction(Transaction transaction) {
        double amountLocalCurrency =
                CurrencyExchangeService.exchange(transaction.getCurrencyID(), currencyID, transaction.getAmount());

        if (transaction.getOwner().equals(owner) &&
                transaction.getCardNumber().equals(cardNumber) &&
                availableAmount >= amountLocalCurrency){
            transactions.add(transaction);
            availableAmount -= amountLocalCurrency;
            return true;
        }
        return false;
    }

    public double totalSpendingsInYear(int year) {
        double sumAmount = 0;
        for (Transaction t : transactions) {
            if (t.getYear() == year) {
                double amountLocalCurrency =
                        CurrencyExchangeService.exchange(t.getCurrencyID(), currencyID, t.getAmount());

                sumAmount += amountLocalCurrency;
            }
        }
        return sumAmount;
    }
}
