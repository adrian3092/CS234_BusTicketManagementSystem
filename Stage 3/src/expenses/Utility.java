package expenses;

import depot.Depot;

/**
 * This class represents a utility cost for a depot. It inherits from Expense.
 * It has one instance variable.
 * depot: The Depot associated with the expense
 * @author George Candal
 */
public class Utility extends Expense {
    private Depot depot;

    /**
     * Constructor for the class
     * @param accounting The Accounting object used as a database for the 
     * expenses
     * @param cost The cost of the utility
     * @param depot The Depot associated with the expense
     */
    public Utility(Accounting accounting, float cost, Depot depot) {
        super(accounting, cost);
        this.depot = depot;
    }

    /**
     * Getter for the depot
     * @return The Depot associated with the expense
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Setter for the Depot
     * @param depot The Depot to set for the expense
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }   
    
    /**
     * Used when printing the object
     * @return A string with expense ID, Depot ID, and cost
     */
    @Override
    public String toString() {
        return super.getExpenseId() + " Utilities: Depot " + depot.getDepotId() + " $" + this.getCost();
    }
}
