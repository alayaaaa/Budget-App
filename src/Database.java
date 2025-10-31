import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    //Fields
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Marion\\Documents\\Budget App\\resources\\transactions.db";
    private Connection connection;
    
    //Constructor
    //Establishes a connection to transactions.db
    //If file does not exist, create a new file with that file name
    public Database() {

        try {

                this.connection = DriverManager.getConnection(DB_URL);
                DatabaseMetaData meta = connection.getMetaData();

                System.out.println("Connection to SQLite has been established.");
                createTable();

        } catch(SQLException e) {

            System.err.println(e.getMessage());

        }

    }

    //Methods
    //Creates a transaction table if it doesn't exist.
    //Otherwise, just move on
    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Transactions (" +
                    " transactionID  INTEGER PRIMARY KEY     AUTOINCREMENT, " +
                    " Amount         REAL     NOT NULL, " +
                    " Description    TEXT     NOT NULL, " +
                    " Category       TEXT     NOT NULL, " +
                    " Date           TEXT     NOT NULL)";

        try (Statement stmt = connection.createStatement()){

            stmt.execute(sql);

        }catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }

    //Inserts data from user to the database
    public void insert(List<Transactions> transactions) {

        String sql = "INSERT INTO Transactions (Amount, Description, Category, Date)" +
                    " VALUES (?,?,?,?)"; 

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            for(Transactions transaction : transactions) {

                pstmt.setDouble(1, transaction.getAmount());
                pstmt.setString(2, transaction.getDescription());
                pstmt.setString(3, transaction.getCategory());
                pstmt.setString(4, transaction.getDate());

                pstmt.executeUpdate();

            }

        }catch(SQLException e) {

            System.err.println("Insertion failed. " + e.getMessage());

        }

    }

    //Deletes data from the database through TransactionID
    public void delete(int id) {

        String sql = "DELETE FROM Transactions WHERE TransactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            System.out.println("Transaction deleted successfully.");

        }catch(SQLException e) {

            System.err.println("Deletion failed. " + e.getMessage());

        }

    }

    //Method to access Transactions for server-side use
    public List<Transactions> viewTransactions() {

        List<Transactions> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions"; 

        try(Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                int id = rs.getInt("transactionID");
                double amount = rs.getDouble("Amount");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                String date = rs.getString("Date");

                transactions.add(new Transactions(id, amount, category, description, date));
                                
            }

        }catch(SQLException e) {

            System.out.println("Task failed. " + e.getMessage());

        }

        return transactions;

    }


    //Method to edit transaction amount based on transactionID
    public void updateTransactionAmount(int id, double amount) {

        String sql = "UPDATE Transactions SET Amount = ? WHERE transactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setDouble(2, amount);

            pstmt.executeUpdate();
            System.out.println("Information updated successfully.");

        }catch(SQLException e) {

            System.out.println("Update information failed." + e.getMessage());
            e.printStackTrace();

        }

    }

    //Method to edit transaction description based on transactionID
    public void updateTransactionDescription(int id, String description) {

        String sql = "UPDATE Transactions SET Description = ? WHERE transactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, description);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("Information updated successfully.");


        }catch(SQLException e) {

            System.out.println("Update information failed." + e.getMessage());
            e.printStackTrace();

        }

    }

    //Method to edit transaction category based on transactionID
    public void updateTransactionCategory(int id, String category) {

        String sql = "UPDATE Transactions SET Category = ? WHERE transactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, category);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("Information updated successfully.");

        }catch(SQLException e) {

            System.out.println("Update information failed." + e.getMessage());
            e.printStackTrace();

        }

    }

    //Method to edit transaction date based on transactionID
    public void updateTransactionDate(int id, String date) {

        String sql = "UPDATE Transactions SET Date = ? WHERE transactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, date);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("Information updated successfully.");

        }catch(SQLException e) {

            System.out.println("Update information failed." + e.getMessage());
            e.printStackTrace();

        }

    }

}
