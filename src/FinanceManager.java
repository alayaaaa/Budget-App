import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

    //Fields
    private final Database database;
    private List<Transactions> transactions;
    private final double budget;
    
    //Constructors
    public FinanceManager() {

        this.database = new Database();
        this.transactions = new ArrayList<>();

        loadTransactions();

    }

    public FinanceManager(double budget) {

        this.budget = budget;

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

    public double 

}
