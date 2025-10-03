import java.sql.*;
import java.util.List;

public class Database {

    //Variables
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Marion\\Documents\\Budget App\\transactions.db";
    private Connection connection;
    
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

    //Creates a transaction table if it doesn't exist.
    //Otherwise, just move on
    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Transactions (" +
                    " transactionID INTEGER PRIMARY KEY     AUTOINCREMENT, " +
                    " Amount         REAL     NOT NULL, " +
                    " Description    TEXT, " +
                    " Category       TEXT    NOT NULL, " +
                    " Date           TEXT)";

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
                pstmt.setString(4, transaction.getDate().toString());

                pstmt.executeUpdate();

            }

        }catch(Exception e) {

            System.err.println(e.getMessage());

        }

    }

    //Deletes data from the database through TransactionID
    public void delete(List<Transactions> transactions, int id) {

        String sql = "DELETE FROM Transactions WHERE TransactionID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        }catch(Exception e) {

            System.err.println(e.getMessage());

        }

    }

    //WIP
    //MIGHT NOT NEED TO USE
    public void view(List<Transactions> transactions) {

        String sql = "SELECT * FROM Transactions"; 

        try(Statement stmt = connection.createStatement()) {

            stmt.executeQuery(sql);

        }catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }

    //WIP
    /* Edits data from the database
    public void edit(List<Transactions> transactions, int id) {

        String sql = "UPDATE Transactions " +
                    "SET "

    } */


}
