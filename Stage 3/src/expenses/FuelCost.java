package expenses;

import bus.Bus;
/**
 *
 * @author George Candal
 */
public class FuelCost extends Expense {
    private Bus bus;

    public FuelCost(Accounting accounting, float cost, Bus bus) {
        super(accounting, cost);
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }  
    
    @Override
    public String toString() {
        return super.getExpenseId() + " Fuel: Bus " + bus.getBusId() + " $" + this.getCost();
    }
}
