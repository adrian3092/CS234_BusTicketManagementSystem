package main;
import IdGenerator.IdGenerator;
import java.util.ArrayList;


/**
 * This class represents a bus route. It contains a name for the route and an
 * array list of BusStop objects. 
 * @author George Candal
 */
public class Route {
    
    private String routeID;
    private String name;
    private ArrayList<BusStop> stops;
    private RouteManager routeManager;

    /**
     * Constructor for the class. Requires a name for the route, it 
     * initializes the array list of stops, and adds the route to the list of
     * routes in routeManager.
     * @param name The name of the route
     * @param routeManager The RouteManager object with the list of routes
     */
    public Route(String name, RouteManager routeManager) {
        this.name = name;
        this.stops = new ArrayList<>();
        this.routeID = IdGenerator.generateRouteId(); //@Handsome Onojerame -- added route ID generation
        routeManager.addRoute(this); //@Handsome Onojerame -- added route to RouteManager
    }

    /**
     * Getter for the name variable
     * @return name the name of the route
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name variable
     * @param name The name of the route
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the routeID variable
     * @return routeID the ID of the route
     */
    public String getRouteID() {
        return routeID;
    }
    
    /**
     * Adds a new BusStop object to the array list of stops
     * @param busStop a BusStop object
     */
    public void addStop(BusStop busStop) {
        stops.add(busStop);
    }
    
    /**
     * Removes a BusStop from the array list of stops
     * @param busStop a BusStop object
     */
    public void removeStop(BusStop busStop) {
        stops.remove(busStop);
    }   
    
    /**
     * getter for the stops ArrayList
     * @return list of all bus stops
     */
    public ArrayList<BusStop> getStops() {
        return stops;
    }
    
    /**
     * Used to print the object
     * @return a string with route ID and route name
     */
    @Override
    public String toString() {
        return "ID: " + routeID + " Name: " + name;
    }
    
    /**
     * Prints the names of all of the bus stops in the route
     */
    public void displayStops() {
        for (BusStop stop : stops)
            System.out.println(stop.getName());
    }
}
