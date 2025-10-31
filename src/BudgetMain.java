public class BudgetMain {

    public static void main(String[] args) {

        Database db = new Database();
        FinanceManager fm = new FinanceManager(db);
        Shell shell = new Shell(db);

        shell.start();

    }

}
