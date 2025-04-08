
package main;

import java.util.ArrayList;
/**
 *
 * @author Handsome Onojerame
 * @author George Candal
 */
public class RouteManager {
    
    private ArrayList<Route> routes;// List of routes
    
    /**
     * Constructor for the class. Initializes the array list of routes.
     */
    public RouteManager() {
        this.routes = new ArrayList<>();
    }
    
    /**
     * Adds a new route to the array list of routes.
     * @param route a Route object
     */
    public void addRoute(Route route) {
        routes.add(route);
    }
    
    public void removeRoute(Route route) {
        routes.remove(route);
    }
    
    /**
     * Returns the array list of routes.
     * @return routes the array list of routes
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * Returns a route by its ID.
     * @param routeID the ID of the route
     * @return the Route object with the specified ID, or null if not found
     */
    public Route getRouteById(String routeID) {
        for (Route route : routes) {
            if (route.getRouteID().equals(routeID)) {
                return route;
            }
        }
        return null; // Return null if the route is not found   
    }
    
    public void displayRoutes() {
        for (Route r : routes) {
            System.out.println(r);
        }
    }
}
