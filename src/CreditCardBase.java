import java.util.ArrayList;

public abstract class CreditCardBase {
    protected String cardNumber;
    protected String owner;
    protected String currencyID;
    protected ArrayList<Transaction> transactions;

    public CreditCardBase(String cardNumber, String owner, String currencyID) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.currencyID = currencyID;
        this.transactions = new ArrayList<>();
    }

    public abstract boolean authorizeTransaction(Transaction transaction);
}
