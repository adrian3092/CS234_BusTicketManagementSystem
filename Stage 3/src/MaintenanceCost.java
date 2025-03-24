/**
 *
 * @author George Candal
 */
public class MaintenanceCost extends Expense {
    private Bus bus;

    public MaintenanceCost(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }   
}
