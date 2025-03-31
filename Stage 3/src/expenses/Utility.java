package expenses;

import depot.Depot;

/**
 *
 * @author George Candal
 */
public class Utility extends Expense {
    private Depot depot;

    public Utility(Depot depot) {
        this.depot = depot;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }   
}
