package main;
/**
 * This class represents a bus stop. It contains a variable for the name and 
 * one for the distance to the next stop. Used in the route class.
 * @author George Candal
 */
public class BusStop {
    
    private String name;
    private double distanceToNext;

    /**
     * Constructor for the class. Initializes the name.
     * @param name The name of the bus stop
     */
    public BusStop(String name, double distanceToNext) {
        this.name = name;
        this.distanceToNext = distanceToNext;
    }

    /**
     * Returns the name of the bus stop.
     * @return name The name of the bus stop
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bus stop.
     * @param name The name for the bus stop
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the distance to the next stop
     * @return distanceToNext The distance to the next stop
     */
    public double getDistanceToNext() {
        return distanceToNext;
    }

    /**
     * Sets the distance to the next stop
     * @param distanceToNext The distance to the next stop
     */
    public void setDistanceToNext(double distanceToNext) {
        this.distanceToNext = distanceToNext;
    }
            
}
