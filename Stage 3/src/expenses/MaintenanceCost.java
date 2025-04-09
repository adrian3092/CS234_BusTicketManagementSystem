package expenses;

import bus.Bus;

/**
 * This class represents a maintenance cost and inherits from expense. It has 1
 * instance variable 
 * bus: A Bus object associated with the cost
 * @author George Candal
 */
public class MaintenanceCost extends Expense {
    private Bus bus;

    /**
     * Constructor for the class
     * @param accounting The Accounting object used as a database to store the
     * expenses
     * @param cost The cost associated with the expense
     * @param bus The bus object associated with the expense
     */
    public MaintenanceCost(Accounting accounting, float cost, Bus bus) {
        super(accounting, cost);
        this.bus = bus;
    }

    /**
     * Getter for the bus
     * @return The Bus associated with the cost
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * Setter for the bus
     * @param bus The Bus object 
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }  
    
    /**
     * Used when printing the object
     * @return a string including the expense ID, bus ID and cost
     */
    @Override
    public String toString() {
        return super.getExpenseId() + " Maintenance: Bus " + bus.getBusId() + " $" + this.getCost();
    }
}
