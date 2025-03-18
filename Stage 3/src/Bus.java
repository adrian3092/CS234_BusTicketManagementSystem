
/**
 * @author Adrian Zielinski
 */
public class Bus {
    
    private int busId;
    private int year;
    private String make;
    private String model;
    private int mileage;
    private int capacity;
    private String status;
    private Route route;
    private Driver driver;

    /**
     * constructor that initializes the 
     * year, make, model, and mileage
     */
    public Bus(int year, String make, String model, int mileage) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
    }

    /**
     * gets the id of a bus
     * @return the id of the bus
     */
    public int getBusId() {
        return busId;
    }
    
    /**
     * gets the year of a bus
     * @return the year of the bus
     */
    public int getYear() {
        return year;
    }

    /**
     * gets the make of a bus
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * gets the model of a bus
     * @return the model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * gets the mileage of a bus
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * gets the capacity of a bus
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * get the status of a bus
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * set the mileage for a bus
     * @param mileage 
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * set the status for a bus
     * @param status 
     */
    public void setBusStatus(String status) {
        this.status = status;
    }
}
