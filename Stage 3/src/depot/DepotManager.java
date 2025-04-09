
package depot;

import java.util.ArrayList;

import bus.Bus;

/**
 * this class manages all depots within
 * the bus company
 * @author Adrian Zielinski
 */
public class DepotManager {

    private final ArrayList<Depot> depots;

    /**
     * default constructor
     */
    public DepotManager() {
        depots = new ArrayList<>();
    }

    /**
     * add bus depot to list of depots
     * @param depot the bus depot to be added
     */
    public void addDepot(Depot depot) {
        depots.add(depot);
    }

    /**
     * remove bus depot from list of depots
     * @param depot the depot to be removed
     */
    public void removeDepot(Depot depot) {
        depots.remove(depot);
    }

    /**
     * get all depots
     * @return list of all depots
     */
    public ArrayList<Depot> getAllDepots() {
        return depots;
    }

    /**
     * get the depot associated with the depot it
     * @param depotId the id of the depot
     * @return the depot associated with depotId
     */
    public Depot findDepotById(int depotId) {
        for (Depot d : depots) {
            if (d.getDepotId() == depotId) {
                return d;
            }
        }
        return null;
    }

    /**
     * assign a bus to a depot
     * @param depotId the id of the depot
     * @param bus the bus to be assigned
     * @return
     */
    public boolean assignBusToDepot(int depotId, Bus bus) {
        Depot depot = findDepotById(depotId);
        if (depot != null) {
            depot.assignBus(bus);
            return true;
        }
        return false;
    }

    /**
     * unassign a bus from a depot
     * @param depotId the id of the depot
     * @param bus the bus to be assigned
     * @return
     */
    public boolean removeBusFromDepot(int depotId, Bus bus) {
        Depot depot = findDepotById(depotId);
        if (depot != null) {
            depot.removeBus(bus);
            return true;
        }
        return false;
    }
}
