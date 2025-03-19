import java.util.ArrayList;

/**
 *
 * @author Adrian Zielinski
 */
public class Depot {
    
    public String address;
    public ArrayList<Bus> buses;
    public int depotId;

    /**
     * constructor that initializes
     * the address
     * @param address, the address of the depot
     */
    public Depot(String address) {
        this.address = address;
    }
    
    /**
     * gets the address of a depot
     * @param depotId, the id of the depot
     * @return the address as a String
     */
    public String getDepotAddress(int depotId) {
        return address;
    }
    
    /**
     * assign a bus to a depot location
     * @param depotId, the id of the depot
     * @param bus, the bus to be assigned
     */
    public void assignBusToDepot(int depotId, Bus bus) {
        //
    }
}
