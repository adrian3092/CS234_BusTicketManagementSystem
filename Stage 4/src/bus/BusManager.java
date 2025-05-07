package bus;

import java.util.ArrayList;
import java.util.List;

import csv.CSVHandler;
import depot.Depot;
import depot.DepotManager;

/**
 * this class manages all buses within the
 * bus company
 * @author Adrian Zielinski
 */
public class BusManager {

    private final ArrayList<Bus> allBuses;
    private final DepotManager depotManager;
    private static final String CSV_FILE_PATH = "dist/data/buses.csv";

    /**
     * default constructor
     * @param depotManager
     */
    public BusManager(DepotManager depotManager) {
        this.depotManager = depotManager;
        this.allBuses = new ArrayList<>();
        loadBusesFromCSV();
    }

    /**
     * add a bus to list of buses
     * @param bus the bus to be added
     */
    public void addBus(Bus bus) {
        allBuses.add(bus);
        saveBusesToCSV();
    }

    /**
     * remove a bus from list of buses
     * @param bus the bus to be removed
     */
    public void removeBus (Bus bus) {
        allBuses.remove(bus);
        saveBusesToCSV();
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
    
    /**
     * load buses from CSV file
     */
    private void loadBusesFromCSV() {
        List<String[]> data = CSVHandler.readCSV(CSV_FILE_PATH);
        
        // clear existing buses
        allBuses.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("busId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);

            int busId = Integer.parseInt(row[0]);
            int year = Integer.parseInt(row[1]);
            String make = row[2];
            String model = row[3];
            int mileage = Integer.parseInt(row[4]);
            int capacity = Integer.parseInt(row[5]);
            String status = row[6];
            
            // create bus with constructor that doesn't auto-generate the ID
            Bus bus = new Bus(year, make, model, mileage, capacity, busId);
            bus.setStatus(status);
            
            allBuses.add(bus);
        }
    }
    
    /**
     * save buses to CSV file
     */
    public void saveBusesToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Bus bus : allBuses) {
            String[] row = new String[]{
                String.valueOf(bus.getBusId()),
                String.valueOf(bus.getYear()),
                bus.getMake(),
                bus.getModel(),
                String.valueOf(bus.getMileage()),
                String.valueOf(bus.getCapacity()),
                bus.getStatus()
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(CSV_FILE_PATH, data);
    }
}
