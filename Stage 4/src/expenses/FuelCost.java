package expenses;

import bus.Bus;
/**
 * This class represents a fuel cost expense and inherits from Expense. It has 
 * one instance variable
 * bus: A bus object that is associated with the expense
 * @author George Candal
 */
public class FuelCost extends Expense {
    private Bus bus;

    /**
     * Constructor for the class. 
     * @param accounting The Accounting object used as a database for the 
     * expenses
     * @param cost The cost of the expense
     * @param bus The Bus object associated with the expense
     */
    public FuelCost(Accounting accounting, float cost, Bus bus) {
        super(accounting, cost);
        this.bus = bus;
    }

    /**
     * Getter for the Bus
     * @return The Bus object associated with the cost
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * Setter for the Bus
     * @param bus The Bus object associated with the cost
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }  
    
    /**
     * Used when printing the object. 
     * @return a String with the expense ID, Fuel: Bus ID $cost
     */
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s $%-20.2f", 
            super.getExpenseId(), "Fuel", "Bus " + bus.getBusId(), this.getCost());
    }
}
