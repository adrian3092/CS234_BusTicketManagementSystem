
package main;

import bus.Bus;
import employees.Driver;

/**
 * This class represents a driver assignment by connecting a driver with an
 * assigned bus
 * @author Adrian Zielinski
 */
public class DriverAssignment {
    private Driver driver;
    private Bus bus;
    
    /**
     * default constructor
     * @param driver, the driver to assign
     * @param bus, the bus to assign the driver to
     */
    public DriverAssignment(Driver driver, Bus bus) {
        this.driver = driver;
        this.bus = bus;
    }
    
    /**
     * gets the driver in this assignment
     * @return the assigned driver
     */
    public Driver getDriver() {
        return driver;
    }
    
    /**
     * gets the bus in this assignment.
     * @return the assigned bus
     */
    public Bus getBus() {
        return bus;
    }
    @Override
    public String toString() {
        return "Driver: " + driver.getName() +
             ", Bus: " + bus.getMake() + " " + bus.getModel() +
             " (ID: " + bus.getBusId() + ")";
}
}
