
package main;

import java.util.ArrayList;
import java.util.List;

import bus.Bus;
import bus.BusManager;
import employees.Driver;
import csv.CSVHandler;

/**
 * this class is used to assign drivers and routes with buses
 * @author Adrian Zielinski
 */
public class Dispatcher {
    
    private ArrayList<RouteAssignment> routeAssignments;
    private ArrayList<DriverAssignment> driverAssignments;
    private BusManager busManager;
    private RouteManager routeManager;
    private employees.EmployeeManagement employeeManagement;
    private static final String ROUTE_ASSIGNMENTS_CSV_FILE_PATH = "data/route_assignments.csv";
    private static final String DRIVER_ASSIGNMENTS_CSV_FILE_PATH = "data/driver_assignments.csv";
    
    /**
     * default constructor
     * @param busManager the bus manager to use
     */
    public Dispatcher(BusManager busManager) {
        this.routeAssignments = new ArrayList<>();
        this.driverAssignments = new ArrayList<>();
        this.busManager = busManager;
        this.routeManager = null;
        this.employeeManagement = null;
    }
    
    /**
     * constructor with route manager
     * @param busManager the bus manager to use
     * @param routeManager the route manager to use
     */
    public Dispatcher(BusManager busManager, RouteManager routeManager) {
        this.routeAssignments = new ArrayList<>();
        this.driverAssignments = new ArrayList<>();
        this.busManager = busManager;
        this.routeManager = routeManager;
        this.employeeManagement = null;
        loadRouteAssignmentsFromCSV();
        loadDriverAssignmentsFromCSV();
    }
    
    /**
     * constructor with route manager and employee management
     * @param busManager the bus manager to use
     * @param routeManager the route manager to use
     * @param employeeManagement the employee management to use
     */
    public Dispatcher(BusManager busManager, RouteManager routeManager, employees.EmployeeManagement employeeManagement) {
        this.routeAssignments = new ArrayList<>();
        this.driverAssignments = new ArrayList<>();
        this.busManager = busManager;
        this.routeManager = routeManager;
        this.employeeManagement = employeeManagement;
        loadRouteAssignmentsFromCSV();
        loadDriverAssignmentsFromCSV();
    }
    
    /**
     * set the route manager
     * @param routeManager the route manager to use
     */
    public void setRouteManager(RouteManager routeManager) {
        this.routeManager = routeManager;
        if (routeManager != null) {
            loadRouteAssignmentsFromCSV();
        }
    }
    
    /**
     * set the employee management
     * @param employeeManagement the employee management to use
     */
    public void setEmployeeManagement(employees.EmployeeManagement employeeManagement) {
        this.employeeManagement = employeeManagement;
        if (employeeManagement != null) {
            loadDriverAssignmentsFromCSV();
        }
    }
    
    /**
     * assigns a bus to a route
     * @param bus the bus to assign
     * @param route the route to assign the bus to
     * @return true if the assignment was successful, otherwise false
     */
    public boolean assignBusToRoute(Bus bus, Route route) {
        // check if the bus is already assigned to a route
        if (getBusRouteAssignment(bus) != null) {
            return false;
        }
        
        // check if the route already has a bus assigned
        if (getRouteBusAssignment(route) != null) {
            return false;
        }
        
        // create a new assignment
        RouteAssignment assignment = new RouteAssignment(bus, route);
        routeAssignments.add(assignment);
        
        // update the bus status
        bus.setStatus("In Use");
        
        // save the assignments to CSV
        saveRouteAssignmentsToCSV();
        
        return true;
    }
    
    /**
     * assigns a driver to a bus
     * @param driver the driver to assign
     * @param bus the bus to assign the driver to
     * @return true if the assignment was successful, otherwise false
     */
    public boolean assignDriverToBus(Driver driver, Bus bus) {
        // check if the driver is available
        if (!driver.getAvailability()) {
            return false;
        }
        
        // check if the bus already has a driver
        if (getBusDriverAssignment(bus) != null) {
            return false;
        }
        
        // create a new assignment
        DriverAssignment assignment = new DriverAssignment(driver, bus);
        driverAssignments.add(assignment);
        
        // update the driver
        driver.setBus(bus);
        driver.setAvailability(false);
        
        // save the assignments to CSV
        saveDriverAssignmentsToCSV();
        
        return true;
    }
    
    /**
     * gets the bus assigned to a route
     * @param route the route to check
     * @return the bus assigned to the route, or null if no bus is assigned to the route
     */
    public Bus getBusForRoute(Route route) {
        RouteAssignment assignment = getRouteBusAssignment(route);
        return assignment != null ? assignment.getBus() : null;
    }
    
    /**
     * gets the route assigned to a bus
     * @param bus the bus to check
     * @return the route assigned to the bus, or null if the bus is not assigned to a route
     */
    public Route getRouteForBus(Bus bus) {
        RouteAssignment assignment = getBusRouteAssignment(bus);
        return assignment != null ? assignment.getRoute() : null;
    }
    
    /**
     * gets the driver assigned to a bus
     * @param bus the bus to check
     * @return the driver assigned to the bus, or null if no driver is assigned
     */
    public Driver getDriverForBus(Bus bus) {
        DriverAssignment assignment = getBusDriverAssignment(bus);
        return assignment != null ? assignment.getDriver() : null;
    }
    
    /**
     * removes the assignment of a bus from a route
     * @param route the route to remove the bus from
     * @return true if the assignment was removed, false if there was no assignment
     */
    public boolean removeBusFromRoute(Route route) {
        RouteAssignment assignment = getRouteBusAssignment(route);
        if (assignment == null) {
            return false;
        }
        
        // update the bus status
        assignment.getBus().setStatus("Available");
        
        // remove the assignment
        routeAssignments.remove(assignment);
        
        // save the assignments to CSV
        saveRouteAssignmentsToCSV();
        
        return true;
    }
    
    /**
     * removes the assignment of a driver from a bus
     * @param bus the bus to remove the driver from
     * @return true if the assignment was removed, false if there was no assignment
     */
    public boolean removeDriverFromBus(Bus bus) {
        DriverAssignment assignment = getBusDriverAssignment(bus);
        if (assignment == null) {
            return false;
        }
        
        // update the driver
        Driver driver = assignment.getDriver();
        driver.setBus(null);
        driver.setAvailability(true);
        
        // remove the assignment
        driverAssignments.remove(assignment);
        
        // save the assignments to CSV
        saveDriverAssignmentsToCSV();
        
        return true;
    }
    
    /**
     * gets all route assignments
     * @return a list of all route assignments
     */
    public ArrayList<RouteAssignment> getAllRouteAssignments() {
        return new ArrayList<>(routeAssignments);
    }
    
    /**
     * gets all driver assignments
     * @return a list of all driver assignments
     */
    public ArrayList<DriverAssignment> getAllDriverAssignments() {
        return new ArrayList<>(driverAssignments);
    }
    
    /**
     * find a route assignment for a given bus
     * @param bus the bus to find an assignment for
     * @return the route assignment, or null if not found
     */
    private RouteAssignment getBusRouteAssignment(Bus bus) {
        for (RouteAssignment assignment : routeAssignments) {
            if (assignment.getBus().getBusId() == bus.getBusId()) {
                return assignment;
            }
        }
        return null;
    }
    
    /**
     * find a route assignment for a given route
     * @param route the route to find an assignment for
     * @return the route assignment, or null if not found
     */
    private RouteAssignment getRouteBusAssignment(Route route) {
        for (RouteAssignment assignment : routeAssignments) {
            if (assignment.getRoute().getRouteID().equals(route.getRouteID())) {
                return assignment;
            }
        }
        return null;
    }
    
    /**
     * find a driver assignment for a given bus
     * @param bus the bus to find an assignment for
     * @return the driver assignment, or null if not found
     */
    private DriverAssignment getBusDriverAssignment(Bus bus) {
        for (DriverAssignment assignment : driverAssignments) {
            if (assignment.getBus().getBusId() == bus.getBusId()) {
                return assignment;
            }
        }
        return null;
    }
    
    /**
     * save route assignments to CSV file
     */
    public void saveRouteAssignmentsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add header row
        data.add(new String[]{"busId", "routeId"});
        
        // add data rows
        for (RouteAssignment assignment : routeAssignments) {
            String[] row = new String[]{
                String.valueOf(assignment.getBus().getBusId()),
                assignment.getRoute().getRouteID()
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(ROUTE_ASSIGNMENTS_CSV_FILE_PATH, data);
    }
    
    /**
     * load route assignments from CSV file
     */
    private void loadRouteAssignmentsFromCSV() {
        if (routeManager == null) {
            return; // can't load without route manager
        }
        
        List<String[]> data = CSVHandler.readCSV(ROUTE_ASSIGNMENTS_CSV_FILE_PATH);
        
        // clear existing assignments
        routeAssignments.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.size() > 0 && data.get(0)[0].equals("busId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int busId = Integer.parseInt(row[0]);
            String routeId = row[1];
            
            // find the bus and route
            Bus bus = busManager.findBusById(busId);
            Route route = routeManager.getRouteById(routeId);
            
            if (bus != null && route != null) {
                // create the assignment
                RouteAssignment assignment = new RouteAssignment(bus, route);
                routeAssignments.add(assignment);
                
                // update the bus status
                bus.setStatus("In Use");
            }
        }
    }
    
    /**
     * save driver assignments to CSV file
     */
    public void saveDriverAssignmentsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add header row
        data.add(new String[]{"driverId", "busId"});
        
        // add data rows
        for (DriverAssignment assignment : driverAssignments) {
            String[] row = new String[]{
                String.valueOf(assignment.getDriver().getEmployeeID()),
                String.valueOf(assignment.getBus().getBusId())
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(DRIVER_ASSIGNMENTS_CSV_FILE_PATH, data);
    }
    
    /**
     * load driver assignments from CSV file
     */
    private void loadDriverAssignmentsFromCSV() {
        List<String[]> data = CSVHandler.readCSV(DRIVER_ASSIGNMENTS_CSV_FILE_PATH);
        
        // clear existing assignments
        driverAssignments.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.size() > 0 && data.get(0)[0].equals("driverId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int driverId = Integer.parseInt(row[0]);
            int busId = Integer.parseInt(row[1]);
            
            // find the driver and bus
            Driver driver = findDriverById(driverId);
            Bus bus = busManager.findBusById(busId);
            
            if (driver != null && bus != null) {
                // create the assignment
                DriverAssignment assignment = new DriverAssignment(driver, bus);
                driverAssignments.add(assignment);
                
                // update the driver
                driver.setBus(bus);
                driver.setAvailability(false);
            }
        }
    }
    
    /**
     * helper method to find a driver by ID
     * @param driverId the ID of the driver to find
     * @return the driver with the given ID, or null if not found
     */
    private Driver findDriverById(int driverId) {
        if (employeeManagement == null) {
            return null;
        }
        
        // convert the numeric ID to the format used in the system (e.g., "D-123")
        String driverIdStr = "D-" + driverId;
        
        // use the employee management to find the driver
        return employeeManagement.getDriverById(driverIdStr);
    }
}
