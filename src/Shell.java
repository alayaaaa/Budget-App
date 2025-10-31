import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.List;

public class Shell {
    
    //Fields
    private Database database;
    private Scanner scan;

    //Constructor
    public Shell(Database database) {

        this.database = database;
        this.scan = new Scanner(System.in);

    }

    //Methods
    //Method that formats user input into a standard date format
    private static String TransactionDateFormatter(String userInput) {

        DateTimeFormatter userFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter sqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            LocalDate transactionDate = LocalDate.parse(userInput, userFormatter);

            return transactionDate.format(sqlFormatter);

        }catch(IllegalFormatException e) {

            throw e;

        }

    }

    public void start() {

        boolean isRunning = true;
        while(isRunning) {

            System.out.println("Type help for list of commands");
            System.out.println("Enter your command: ");

            String input = scan.nextLine().toLowerCase();
            switch(input) {

                case "add":

                    add();
                    break;
                
                case "delete":

                    delete();
                    break;

                case "edit":

                    edit();
                    break;

                case "exit":
                
                    isRunning = false;
                    break;

                case "help":
                
                    System.out.println("\nHere are the commands:");
                    System.out.println("Add: Add a new transaction");
                    System.out.println("Delete: Delete a transaction");
                    System.out.println("Edit: Edit a transaction");
                    System.out.println("Exit: Terminates the program \n");

            }         


        }

    }

    private void add() {

        List<Transactions> transactions = new ArrayList<>();

        boolean enterTransaction = true;
        while(enterTransaction) {

            System.out.println("Enter the amount: ");
            while(!scan.hasNextDouble()) {

                System.out.println("Not a valid input. Try Again.");
                scan.next();

            }
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the category: ");
            String category = scan.nextLine();

            System.out.println("Enter a description (Optional): ");
            String description = scan.nextLine();

            System.out.println("Enter the transaction date (MM-DD-YYYY): ");
            String date = TransactionDateFormatter(scan.nextLine());

            transactions.add(new Transactions(amount, category, description, date));

            System.out.println("Add more transactions? (Y/N): ");
            String addTransaction = scan.nextLine();

            switch(addTransaction) {

                case "N":
                    enterTransaction = false;

            }

        }

        if(!transactions.isEmpty()) {

            database.insert(transactions);

        }

    }

    private void delete() {

        boolean deleteTransaction = true;
        while(deleteTransaction) {

            System.out.println("What is the ID of the transaction you want to delete? ");
            int id = scan.nextInt();
            scan.nextLine();

            database.delete(id);

            System.out.println("Delete more transactions? (Y/N): ");
            String delTransaction = scan.nextLine();

            switch(delTransaction) {

                case "N":
                    deleteTransaction = false;

            }

        }

    }

    private void edit() {

        boolean editTransaction = true;
        while(editTransaction) {

            System.out.println("What is the ID of the transaction you want to edit? ");
            int id = scan.nextInt();
            scan.nextLine();

            System.out.println("What is the information you want to edit? (Amount, Category, Description, Date): ");
            String parameter = scan.nextLine().toLowerCase();
            
            switch(parameter) {

                case "amount":
                    System.out.println("Enter the new amount: ");
                    double amount = scan.nextDouble();
                    scan.nextLine();

                    database.updateTransactionAmount(id, amount);
                    break;
                
                case "category":
                    System.out.println("Enter the new category: ");
                    String category = scan.nextLine();

                    database.updateTransactionCategory(id, category);
                    break;
                
                case "description":
                    System.out.println("Enter the new description: ");
                    String description = scan.nextLine();

                    database.updateTransactionDescription(id, description);
                    break;

                case "date":
                    System.out.println("Enter the new date (MM-DD-YYYY): ");
                    String date = TransactionDateFormatter(scan.nextLine());

                    database.updateTransactionDate(id, date);
                    break;
                

            }

            System.out.println("Edit more transactions? (Y/N): ");
            String boolTransaction = scan.nextLine();

            switch(boolTransaction) {

                case "N":
                    editTransaction = false;

            }

        }

    }
}
