

public class Transactions {
    
    //Variables
    private double amount;
    private String category;
    private String description;
    private java.time.LocalDate date;

    //Constructor
    public Transactions(double amount, String category, String description, java.time.LocalDate date) {

        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;

    }

    //Getters
    public double getAmount() {

        return amount;

    }

    public String getCategory() {

        return category;

    }

    public String getDescription() {

        return description;

    }

    public java.time.LocalDate getDate() {

        return date;

    }

}
