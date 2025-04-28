package depot;

import java.util.ArrayList;

import bus.Bus;

/**
 * this class represents a depot at the bus company
 * buses are assigned to depots
 * @author Adrian Zielinski
 */
public class Depot {

    private String address;
    private final ArrayList<Bus> buses;
    private final int depotId;
    private static int nextDepotId = 1000;

    /**
     * constructor that initializes the address
     * @param address the address of the depot
     */
    public Depot(String address) {
        depotId = nextDepotId++;
        this.address = address;
        this.buses = new ArrayList<>();
    }
    
    /**
     * constructor that initializes the address with a specific depot ID
     * used for loading depots from CSV
     * @param address the address of the depot
     * @param depotId the specific depot ID to use
     */
    public Depot(String address, int depotId) {
        this.depotId = depotId;
        this.address = address;
        this.buses = new ArrayList<>();
        
        // update nextDepotId if this id is greater than or equal to the current nextDepotId
        if (depotId >= nextDepotId) {
            nextDepotId = depotId + 1;
        }
    }

    /**
     * get the id of the depot
     * @return the depot id
     */
    public int getDepotId() {
        return depotId;
    }

    /**
     * gets the address of a depot
     * @return the address as a String
     */
    public String getDepotAddress() {
        return address;
    }

    /**
     * sets the address for the depot
     * @param address the new address
     */
    public void setDepotAddress(String address) {
        this.address = address;
    }

    /**
     * assigns a bus to a depot
     * @param bus the bus to be assigned
     */
    public void assignBus(Bus bus) {
        // check if bus already exists by ID
        boolean exists = false;
        for (Bus b : buses) {
            if (b.getBusId() == bus.getBusId()) {
                exists = true;
                break;
            }
        }
        
        if (!exists) {
            buses.add(bus);
        }
    }

    /**
     * unassign a bus from a depot
     * @param bus the bus to be unassigned
     */
    public void removeBus(Bus bus) {
        // find the bus by ID and remove it
        for (int i = 0; i < buses.size(); i++) {
            if (buses.get(i).getBusId() == bus.getBusId()) {
                buses.remove(i);
                break;
            }
        }
    }

    /**
     * get all buses assigned to a specifc depot
     * @return a list of buses assigned to a specific depot
     */
    public ArrayList<Bus> getBuses() {
        return new ArrayList<>(buses);
    }

    /**
     * get the bus associated with the bus id
     * @param busId the id of the bus
     * @return the bus associated with busId
     */
    public Bus findBusById(int busId) {
        for (Bus b : buses) {
            if (b.getBusId() == busId) return b;
        }
        return null;
    }

    /**
     * used to print the object
     * @return a string with the depot ID and address
     */
    @Override
    public String toString() {
        return "Depot ID: " + depotId + ", Address: " + address;
    }
}
