
package main;

import java.util.ArrayList;
/**
 *
 * @author Handsome Onojerame
 */
public class RouteManager {
    
    private static ArrayList<Route> routes = new ArrayList<>(); // List of routes
    
    /**
     * Constructor for the class. Initializes the array list of routes.
     */
    
    /**
     * Adds a new route to the array list of routes.
     * @param route a Route object
     */
    public static void addRoute(Route route) {
        routes.add(route);
    }
    
    /**
     * Returns the array list of routes.
     * @return routes the array list of routes
     */
    public static ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * Returns a route by its ID.
     * @param routeID the ID of the route
     * @return the Route object with the specified ID, or null if not found
     */
    public static Route getRouteById(String routeID) {
        for (Route route : routes) {
            if (route.getRouteID().equals(routeID)) {
                return route;
            }
        }
        return null; // Return null if the route is not found   
    }
}
