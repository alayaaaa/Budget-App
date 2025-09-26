import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetMain {

    public static void main(String[] args) {

        List<Transactions> transactions = new ArrayList<>();
        Database db = new Database();
        Scanner scan = new Scanner(System.in);
        boolean enterTransaction = true;

        while(enterTransaction) {

            System.out.println("Enter the amount: ");
            while(!scan.hasNextDouble()) {

                System.out.println("Not a valid input. Try Again.");
                scan.next();

            }
            double amount = scan.nextDouble();

            System.out.println("Enter the category: ");
            String category = scan.next();

            System.out.println("Enter a description (Optional): ");
            String description = scan.next();

            transactions.add(new Transactions(amount, category, description, LocalDate.now()));

            System.out.println("Add more transactions? (Y/N): ");
            String addTransaction = scan.next();

            switch(addTransaction) {

                case "N":
                    enterTransaction = false;

            }
        
        }

        db.insert(transactions);

        scan.close();

    }

}
