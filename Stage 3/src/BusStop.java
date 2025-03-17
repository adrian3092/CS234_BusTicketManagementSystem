/**
 * This class represents a bus stop. It contains one variable for the name.
 * @author George Candal
 */
public class BusStop {
    
    private String name;

    /**
     * Constructor for the class. Initializes the name.
     * @param name The name of the bus stop
     */
    public BusStop(String name) {
        this.name = name;
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
    
}
