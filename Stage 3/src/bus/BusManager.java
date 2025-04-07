package bus;

import java.util.ArrayList;

import depot.Depot;
import depot.DepotManager;

/**
 *
 * @author Adrian Zielinski
 */
public class BusManager {

    private final ArrayList<Bus> allBuses;
    private final DepotManager depotManager;

    /**
     * default constructor
     * @param depotManager
     */
    public BusManager(DepotManager depotManager) {
        this.depotManager = depotManager;
        this.allBuses = new ArrayList<>();
    }

    /**
     * add a bus to list of buses
     * @param bus the bus to be added
     */
    public void addBus(Bus bus) {
        allBuses.add(bus);
    }

    /**
     * remove a bus from list of buses
     * @param bus the bus to be removed
     */
    public void removeBus (Bus bus) {
        allBuses.remove(bus);
    }

    /**
     * get all buses in the fleet
     * @return a list of all buses
     */
    public ArrayList<Bus> getAllBuses() {
        return new ArrayList<>(allBuses);
    }

    /**
     * get the bus associated with the bus id
     * @param busId the id of the bus
     * @return the bus associated with busId
     */
    public Bus findBusById(int busId) {
        for (Bus bus : allBuses) {
            if (bus.getBusId() == busId)
                return bus;
        }
        return null;
    }

    /**
     * find which depot a specific bus is assigned to
     * @param busId the id of the bus
     * @return the depot where the bus is located
     */
    public Depot findDepotByBusId(int busId) {
        for (Depot d : depotManager.getAllDepots()) {
            if (d.findBusById(busId) != null) {
                return d;
            }
        }
        return null;
    }
}
