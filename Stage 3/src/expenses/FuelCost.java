package expenses;

import bus.Bus;
/**
 *
 * @author George Candal
 */
public class FuelCost extends Expense {
    private Bus bus;

    public FuelCost(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }    
}
