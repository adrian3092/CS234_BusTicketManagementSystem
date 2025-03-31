package employees;

/**
 * The Driver class represents a bus driver who is an employee.
 * It includes information about the driver's assigned bus and availability.
 */
public class Driver extends Employee {
    private Bus bus; // The bus assigned to the driver
    private Boolean availability; // The availability status of the driver

    /**
     * Constructor to initialize a Driver object with a name and job title.
     * The driver is set to available by default.
     *
     * @param name     The name of the driver
     * @param jobTitle The job title of the driver
     */
    public Driver(String name, String jobTitle) {
        super(name, jobTitle);
        this.availability = true;
    }

    /**
     * Gets the availability status of the driver.
     *
     * @return The availability status
     */
    public Boolean getAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the driver.
     *
     * @param availability The new availability status
     */
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    /**
     * Gets the bus assigned to the driver.
     *
     * @return The assigned bus
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * Sets the bus assigned to the driver.
     *
     * @param bus The bus to assign
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * Displays the schedule for the bus assigned to the driver.
     */
    public void viewSchedule() {
        // View the schedule for the bus
    }
}
