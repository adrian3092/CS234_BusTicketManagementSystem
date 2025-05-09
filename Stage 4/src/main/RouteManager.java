
package main;

import java.util.ArrayList;
import java.util.List;
import csv.CSVHandler;

/**
 * This class acts as a database for the routes. It has one instance variable.
 * routes: an array list of all of the routes for the bus company.
 * @author Handsome Onojerame
 */
public class RouteManager {
    
    private ArrayList<Route> routes;// list of routes
    private static final String ROUTES_CSV_FILE_PATH = "data/routes.csv";
    private static final String BUS_STOPS_CSV_FILE_PATH = "data/bus_stops.csv";
    
    /**
     * Constructor for the class. Initializes the array list of routes.
     */
    public RouteManager() {
        this.routes = new ArrayList<>();
        loadRoutesFromCSV();
    }
    
    /**
     * Adds a new route to the array list of routes.
     * @param route a Route object
     */
    public void addRoute(Route route) {
        routes.add(route);
        saveRoutesToCSV();
    }
    
    /**
     * Removes a route from the list of routes
     * @param route The Route object to remove
     */
    public void removeRoute(Route route) {
        routes.remove(route);
        saveRoutesToCSV();
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
    
    /**
     * Prints out all of the routes in the list of routes
     */
    public void displayRoutes() {
        for (Route r : routes) {
            System.out.println(r);
        }
    }
    
    /**
     * load routes from CSV file
     */
    private void loadRoutesFromCSV() {
        List<String[]> data = CSVHandler.readCSV(ROUTES_CSV_FILE_PATH);
        
        // clear existing routes
        routes.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("routeID");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String routeID = row[0];
            String name = row[1];
            
            // create route without adding it to the manager
            Route route = new Route(name, routeID, this);
            routes.add(route);
        }
        
        // load bus stops for each route
        loadBusStopsFromCSV();
    }
    
    /**
     * save routes to CSV file
     */
    public void saveRoutesToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Route route : routes) {
            String[] row = new String[]{
                route.getRouteID(),
                route.getName()
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(ROUTES_CSV_FILE_PATH, data);
        
        // save bus stops for each route
        saveBusStopsToCSV();
    }
    
    /**
     * load bus stops from CSV file
     */
    private void loadBusStopsFromCSV() {
        List<String[]> data = CSVHandler.readCSV(BUS_STOPS_CSV_FILE_PATH);
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("routeID");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String routeID = row[0];
            String stopName = row[1];
            double distanceToNext = Double.parseDouble(row[2]);
            
            // find the route
            Route route = getRouteById(routeID);
            
            // add the bus stop to the route
            if (route != null) {
                BusStop busStop = new BusStop(stopName, distanceToNext);
                route.addStop(busStop);
            }
        }
    }
    
    /**
     * save bus stops to CSV file
     */
    private void saveBusStopsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Route route : routes) {
            for (BusStop stop : route.getStops()) {
                String[] row = new String[]{
                    route.getRouteID(),
                    stop.getName(),
                    String.valueOf(stop.getDistanceToNext())
                };
                data.add(row);
            }
        }
        CSVHandler.writeCSV(BUS_STOPS_CSV_FILE_PATH, data);
    }
}
