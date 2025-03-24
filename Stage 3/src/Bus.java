
/**
 * @author Adrian Zielinski
 */
public class Bus {

    private final int busId;
    private static int nextBusId = 1000;
    private final int year;
    private final String make;
    private final String model;
    private int mileage;
    private int capacity;
    private String status;


    /**
     * constructor that initializes the
     * year, make, model, and mileage
     * @param year, the year of the bus
     * @param make, the make of the bus
     * @param model, the model of the bus
     * @param mileage, the mileage of the bus
     * @param capacity, the capacity of the bus
     */
    public Bus(int year, String make, String model, int mileage, int capacity) {
        this.busId = nextBusId++;
        this.year = year;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.capacity = capacity;
        this.status = "Available";
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
     * set the mileage for a bus
     * @param mileage, the mileage of the bus
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * gets the seating capacity of a bus
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * sets the seating capacity of a bus
     * @param capacity, the capacity of the bus
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * get the status of a bus
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * set the status for a bus
     * @param status, the status of the bus
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bus ID: " + busId +
                ", Year: " + year +
                ", Make: " + make +
                ", Model: " + model +
                ", Mileage: " + mileage +
                ", Capacity: " + capacity +
                ", Status: " + status;
    }
}
