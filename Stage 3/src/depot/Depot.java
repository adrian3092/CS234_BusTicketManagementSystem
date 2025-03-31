package depot;

import java.util.ArrayList;

import bus.Bus;

/**
 *
 * @author Adrian Zielinski
 */
public class Depot {

    private String address;
    private final ArrayList<Bus> buses;
    private final int depotId;

    /**
     * constructor that initializes the address
     * @param depotId, the id of the depot
     * @param address, the address of the depot
     */
    public Depot(int depotId, String address) {
        this.depotId = depotId;
        this.address = address;
        this.buses = new ArrayList<>();
    }

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
     * @param bus, the bus to be assigned
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
     * @param bus, the bus to be unassigned
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
     * @return
     */
    public ArrayList<Bus> getBuses() {
        return new ArrayList<>(buses);
    }

    /**
     * get the bus associated with the bus id
     * @param busId, the id of the bus
     * @return, the bus associated with busId
     */
    public Bus findBusById(int busId) {
        for (Bus b : buses) {
            if (b.getBusId() == busId) return b;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Depot ID: " + depotId + ", Address: " + address;
    }

}
