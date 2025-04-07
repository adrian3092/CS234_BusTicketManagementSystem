package expenses;

import depot.Depot;

/**
 *
 * @author George Candal
 */
public class Utility extends Expense {
    private Depot depot;

    public Utility(Accounting accounting, float cost, Depot depot) {
        super(accounting, cost);
        this.depot = depot;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }   
    
    @Override
    public String toString() {
        return super.getExpenseId() + " Utilities: Depot " + depot.getDepotId() + " $" + this.getCost();
    }
}
