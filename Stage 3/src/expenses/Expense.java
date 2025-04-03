package expenses;

/**
 *
 * @author George Candal
 */
public class Expense {
    private float cost;

    public Expense(Accounting accounting, float cost) {
        this.cost = cost;
        accounting.addExpense(this);
    }
    
    

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }  
}
