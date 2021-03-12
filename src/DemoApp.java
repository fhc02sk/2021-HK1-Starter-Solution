import java.util.ArrayList;

public class DemoApp {
    public static void main(String[] args) {


        ArrayList<Transaction> listOfTransactions = new ArrayList<>();
        listOfTransactions.add(new Transaction(2021, 01, 12, 123,
                "USD", "US123456", "3056930009020004", "John Doe"));
        listOfTransactions.add(new Transaction(2021, 02, 01, 450,
                "EUR", "0019238423", "3056930009020004", "John Doe"));
        listOfTransactions.add(new Transaction(2021, 01, 14, 1250,
                "EUR", "MediaMarkt-12345897", "3056930009020004", "John Doe"));
        listOfTransactions.add(new Transaction(2021, 01, 01, 9999,
                "EUR", "0019238423", "3056930009020009", "John Doe")); // should fail: wrong card number
        listOfTransactions.add(new Transaction(2021, 01, 19, 1800,
                "EUR", "Tui-5541354897", "3056930009020004", "John Doe")); // should fail: limit
        listOfTransactions.add(new Transaction(2021, 01, 25, 18000,
                "HUF", "HU-99887766", "3056930009020004", "John Doe"));
        listOfTransactions.add(new Transaction(2020, 12, 16, 72.88,
                "EUR", "8123158122-1", "4000056655665556", "John Doe Junior"));
        listOfTransactions.add(new Transaction(2020, 12, 23, 12,
                "EUR", "AT87.2132342", "4000056655665556", "John Doe Junior"));
        listOfTransactions.add(new Transaction(2020, 12, 27, 85,
                "EUR", "LK81341684", "4000056655665556", "John Doe Junior")); // should fail: limit

        CreditCard creditCard = new CreditCard(
                "3056930009020004", "John Doe",
                "EUR", 2000);
        PrepaidCreditCard prepaidCreditCard = new PrepaidCreditCard(
                "4000056655665556", "John Doe Junior", "EUR");

        prepaidCreditCard.load(100);

        for (Transaction t : listOfTransactions) {
            System.out.println("Trying to authorize transaction " + t.getReferenceID() + " to CreditCard > Result: " + creditCard.authorizeTransaction(t));
            System.out.println("Trying to authorize transaction " + t.getReferenceID() + " to PrepaidCreditCard > Result: "
                    + prepaidCreditCard.authorizeTransaction(t) + " > Available Amount: " + prepaidCreditCard.getAvailableAmount());
        }

        System.out.println("creditCard.monthlySpendings(2021, 1) = " + creditCard.monthlySpendings(2021, 1)); // 1402.22
        System.out.println("creditCard.monthlySpendings(2021, 2) = " + creditCard.monthlySpendings(2021, 2)); // 450.0
        System.out.println("creditCard.getStatisticsToMonth() = " + creditCard.getStatisticsToMonth()); // {2021-1=1402.2232134025746, 2021-2=450.0}
        System.out.println("prepaidCreditCard.totalSpendingsInYear(2020) = " + prepaidCreditCard.totalSpendingsInYear(2020)); //84.88
    }
}
