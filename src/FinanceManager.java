import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

    //Fields
    private final Database database;
    private List<Transactions> transactions;
    private double budget;
    
    //Constructors
    public FinanceManager(Database database) {

        this.database = database;
        this.budget = 0;
        this.transactions = new ArrayList<>();

        loadTransactions();

    }

    public double getBudget() {

        return budget;

    }

    public void loadTransactions() {

        List<Transactions> loadTransactions = database.viewTransactions();

        if(loadTransactions != null) {

            this.transactions = loadTransactions;

        }else {

            loadTransactions = new ArrayList<>();
            System.out.println("No data found from the database.");

        }

    }

    //Returns the total amount of transactions
    public double totalTransactionAmount() {

        int amount = 0;

        for(int i = 0; i < transactions.size(); i++) {

            amount += transactions.get(i).getAmount();

        }

        return amount;

    }

}
