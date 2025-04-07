package expenses;

/**
 *
 * @author George Candal
 */
public class Expense {
    private float cost;
    private int expenseId;
    private static int nextExpenseId = 1000;

    public Expense(Accounting accounting, float cost) {
        this.expenseId = nextExpenseId++;
        this.cost = cost;
        accounting.addExpense(this);
    }   

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }  

    public int getExpenseId() {
        return expenseId;
    }
    
}
