import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BudgetMain {

    //Save file method
    //Appends to a JSON file
    //WIP
    public static void saveFile(List<Transactions> transactions) {

        JSONParser parser = new JSONParser();
        JSONArray transactionsList = new JSONArray();

        // Read existing data from the file to a JSON array
        try (FileReader reader = new FileReader("C:\\Users\\Marion\\Documents\\Budget App\\transactions.json")) {
            
            Object oldFile = parser.parse(reader);
            transactionsList = (JSONArray) oldFile;

        } catch (Exception e) {
            
            System.out.println("File not found or invalid. Creating a new JSON array...");
            e.printStackTrace();

        }

        // Add transactions to the list
        for(int i = 0; i <transactions.size(); i++) {

            JSONObject transactionDetails = new JSONObject();

            transactionDetails.put("amount", transactions.get(i).getAmount());
            transactionDetails.put("description", transactions.get(i).getDescription());
            transactionDetails.put("category", transactions.get(i).getCategory());
            transactionDetails.put("date", transactions.get(i).getDate());

            transactionsList.add(transactionDetails);

        }
        
        // Creates a new file with the list, updates the list whenever needed
        try(FileWriter file = new FileWriter("transactions.json")) {

            file.write(transactionsList.toJSONString());
            file.flush();

            System.out.println("Succesfully copied JSON Object to a file.");
            System.out.println("JSON Object: " + transactionsList);

        }catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {

        List<Transactions> transactions = new ArrayList<>();
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

        System.out.println("Do you want to save your file? (Y/N): ");
        String saveFileConfirm = scan.next(); 

        switch(saveFileConfirm) {

                case "Y":
                    saveFile(transactions);

            }

        double totalAmount = 0;

            for(int i = 0; i < transactions.size(); i++) {

                totalAmount += transactions.get(i).getAmount();

            }

            System.out.println(totalAmount);

        scan.close();

    }

}
