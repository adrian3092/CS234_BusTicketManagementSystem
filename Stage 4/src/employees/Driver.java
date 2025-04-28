package employees;

import bus.Bus;
import login.LoginManager;

/**
 * The Driver class represents a bus driver who is an employee.
 * It includes information about the driver's assigned bus and availability.
 * @author Handsome Onojerame
 */
public class Driver extends Employee {
    private Bus bus; // The bus assigned to the driver
    private Boolean availability; // The availability status of the driver

    /**
     * Constructor to initialize a Driver object with a name and job title.
     * The driver is set to available by default.
     * @param firstName The first name of the driver
     * @param lastName The last name of the driver
     * @param jobTitle The job title of the driver
     * @param phoneNumber The phone number of the driver
     * @param salary The salary of the driver
     * @param employeeManagement The EmployeeManagement object for managing employees
     * @param loginManager The LoginManager object for managing logins
     */
    public Driver(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, EmployeeManagement employeeManagement, LoginManager loginManager) {
        super(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
        this.availability = true;
    }
    
    /**
     * constructor to initialize a Driver object with a specific employee ID
     * used for loading from driver employees from CSV
     * @param firstName The first name of the driver
     * @param lastName The last name of the driver
     * @param jobTitle The job title of the driver
     * @param phoneNumber The phone number of the driver
     * @param salary The salary of the driver
     * @param employeeManagement The EmployeeManagement object for managing employees
     * @param loginManager The LoginManager object for managing logins
     * @param employeeID The specific employee ID to use
     */
    public Driver(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, 
                 EmployeeManagement employeeManagement, LoginManager loginManager, String employeeID) {
        super(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager, employeeID);
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
}
