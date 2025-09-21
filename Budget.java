public class Budget {

    private double budgetAmount;
    private String timePeriod;
    
    public Budget(double budgetAmount, String timePeriod) {

        this.budgetAmount = budgetAmount;
        this.timePeriod = timePeriod;

    }

    public double budgetAmount() {

        return budgetAmount;

    }

    public String timePeriod() {

        return timePeriod;

    }
    

    
}
