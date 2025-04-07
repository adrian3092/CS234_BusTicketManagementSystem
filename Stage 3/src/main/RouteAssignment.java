
package main;

import bus.Bus;

/**
 * 
 * @author Adrian Zielinski
 */
public class RouteAssignment {
    private Bus bus;
    private Route route;
    
    /**
     * default constructor
     * @param bus, the bus to assign
     * @param route, the route to assign the bus to
     */
    public RouteAssignment(Bus bus, Route route) {
        this.bus = bus;
        this.route = route;
    }
    
    /**
     * gets the bus in this assignment
     * @return the assigned bus
     */
    public Bus getBus() {
        return bus;
    }
    
    /**
     * gets the route in this assignment
     * @return the assigned route
     */
    public Route getRoute() {
        return route;
    }
}
