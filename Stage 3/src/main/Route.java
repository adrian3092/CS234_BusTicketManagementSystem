package main;
import idGenerator.IdGenerator;
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

    /**
     * Constructor for the class. Requires a name for the route and it 
     * initializes the array list of stops.
     * @param name 
     */
    public Route(String name) {
        this.name = name;
        this.stops = new ArrayList<>();
        this.routeID = IdGenerator.generateRouteId(); //@Handsome Onojerame -- added route ID generation
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
        
}
