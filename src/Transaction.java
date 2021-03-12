public class Transaction {
    private int year;
    private int month;
    private int day;
    private double amount;
    private String currencyID;
    private String referenceID;
    private String cardNumber;
    private String owner;

    public Transaction(int year, int month, int day,
                       double amount, String currencyID,
                       String referenceID, String cardNumber,
                       String owner) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.amount = amount;
        this.currencyID = currencyID;
        this.referenceID = referenceID;
        this.cardNumber = cardNumber;
        this.owner = owner;
    }

    public boolean sameMonth(int year, int month){
        return year == this.year && month == this.month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyID() {
        return currencyID;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getOwner() {
        return owner;
    }
}
