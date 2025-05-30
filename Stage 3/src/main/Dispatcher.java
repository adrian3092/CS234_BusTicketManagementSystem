
package main;

import java.util.ArrayList;

import bus.Bus;
import bus.BusManager;
import employees.Driver;

/**
 * This class is used to assign drivers and routes with buses.
 * @author Adrian Zielinski
 */
public class Dispatcher {
    
    private ArrayList<RouteAssignment> routeAssignments;
    private ArrayList<DriverAssignment> driverAssignments;
    private BusManager busManager;
    
    /**
     * default constructor
     * @param busManager the bus manager to use
     */
    public Dispatcher(BusManager busManager) {
        this.routeAssignments = new ArrayList<>();
        this.driverAssignments = new ArrayList<>();
        this.busManager = busManager;
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
     * Gets the driver assigned to a bus
     * @param bus the bus to check
     * @return The driver assigned to the bus, or null if no driver is assigned
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
     * gets all driver assignments.
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
     * find a route assignment for a given route.
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
     * find a driver assignment for a given bus.
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
}
