
package depot;

import java.util.ArrayList;
import java.util.List;

import bus.Bus;
import csv.CSVHandler;

/**
 * this class manages all depots within
 * the bus company
 * @author Adrian Zielinski
 */
public class DepotManager {

    private final ArrayList<Depot> depots;
    private static final String CSV_FILE_PATH ="data/depots.csv";
    private static final String DEPOT_BUS_CSV_FILE_PATH = "data/depot_buses.csv";

    /**
     * default constructor
     */
    public DepotManager() {
        depots = new ArrayList<>();
        loadDepotsFromCSV();
    }

    /**
     * add bus depot to list of depots
     * @param depot the bus depot to be added
     */
    public void addDepot(Depot depot) {
        depots.add(depot);
        saveDepotsToCSV();
    }

    /**
     * remove bus depot from list of depots
     * @param depot the depot to be removed
     */
    public void removeDepot(Depot depot) {
        depots.remove(depot);
        saveDepotsToCSV();
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
            saveDepotBusAssignmentsToCSV();
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
            saveDepotBusAssignmentsToCSV();
            return true;
        }
        return false;
    }
    
    /**
     * Load depots from CSV file
     */
    private void loadDepotsFromCSV() {
        List<String[]> data = CSVHandler.readCSV(CSV_FILE_PATH);
        
        // clear existing depots
        depots.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("depotId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int depotId = Integer.parseInt(row[0]);
            String address = row[1];
            
            // create depot with constructor that doesn't auto-generate the ID
            Depot depot = new Depot(address, depotId);
            depots.add(depot);
        }
        
        // load bus assignments
        loadDepotBusAssignmentsFromCSV();
    }
    
    /**
     * save depots to CSV file
     */
    public void saveDepotsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Depot depot : depots) {
            String[] row = new String[]{
                String.valueOf(depot.getDepotId()),
                depot.getDepotAddress()
            };
            data.add(row);
        }

        CSVHandler.writeCSV(CSV_FILE_PATH, data);
        
        // save bus assignments
        saveDepotBusAssignmentsToCSV();
    }
    
    /**
     * load depot-bus assignments from CSV file
     * @param busManager the bus manager to use for finding buses by ID
     */
    public void loadDepotBusAssignmentsFromCSV(bus.BusManager busManager) {
        List<String[]> data = CSVHandler.readCSV(DEPOT_BUS_CSV_FILE_PATH);
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("depotId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int depotId = Integer.parseInt(row[0]);
            int busId = Integer.parseInt(row[1]);
            
            // Find the depot and bus
            Depot depot = findDepotById(depotId);
            Bus bus = busManager.findBusById(busId);
            
            // Assign the bus to the depot if both exist
            if (depot != null && bus != null) {
                depot.assignBus(bus);
            }
        }
    }
    
    /**
     * load depot-bus assignments from CSV file
     */
    private void loadDepotBusAssignmentsFromCSV() {
        //
    }
    
    /**
     * save depot-bus assignments to CSV file
     */
    public void saveDepotBusAssignmentsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Depot depot : depots) {
            for (Bus bus : depot.getBuses()) {
                String[] row = new String[]{
                    String.valueOf(depot.getDepotId()),
                    String.valueOf(bus.getBusId())
                };
                data.add(row);
            }
        }
        CSVHandler.writeCSV(DEPOT_BUS_CSV_FILE_PATH, data);
    }
}
