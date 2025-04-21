package expenses;

/**
 * This class represents an expense for the bus company. It has 3 instance 
 * variables.
 * cost: The cost of the expense
 * expenseID: The ID of the expense
 * nextExpenseID: A static variable that saves the expenseID of the next expense
 * to be made.
 * @author George Candal
 */
public class Expense {
    private float cost;
    private int expenseId;
    private static int nextExpenseId = 1000;

    /**
     * Constructor for the class. Creates the expense and adds it to the list
     * of expenses in the Accounting database.
     * @param accounting THe Accounting object used as a database
     * @param cost The cost of the expense
     */
    public Expense(Accounting accounting, float cost) {
        this.expenseId = nextExpenseId++;
        this.cost = cost;
        accounting.addExpense(this);
    }   

    /**
     * Getter for the cost
     * @return The cost of the expense
     */
    public float getCost() {
        return cost;
    }

    /**
     * Setter for the cost
     * @param cost 
     */
    public void setCost(float cost) {
        this.cost = cost;
    }  

    /**
     * Getter for the Expense ID
     * @return THe ID of the expense
     */
    public int getExpenseId() {
        return expenseId;
    }
    
}
