package employees;

import IdGenerator.IdGenerator;
import login.*;

/**
 * Represents an Employee with details such as ID, name, email, phone number, 
 * job title, access level, and salary.
 * 
 * Author: Handsome Onojerame
 */
public class Employee {
    private String employeeID; // Unique identifier for the employee
    private String lastName; // Last name of the employee
    private String firstName; // First name of the employee
    private String email; // Email address of the employee
    private String phoneNumber; // Phone number of the employee
    private String jobTitle; // Job title of the employee
    private float salary; // Salary of the employee
    private EmployeeManagement employeeManagement; // Reference to EmployeeManagement class
    private LoginManager loginManager;

    /**
     * Constructor to initialize an Employee object.
     * 
     * @param firstName         The first name of the employee.
     * @param lastName          The last name of the employee.
     * @param jobTitle          The job title of the employee (Driver or Admin).
     * @param phoneNumber       The phone number of the employee.
     * @param salary            The salary of the employee.
     * @param employeeManagement Reference to the EmployeeManagement class.
     * @param loginManager      The LoginManager with the list of logins
     */
    public Employee(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, EmployeeManagement employeeManagement, LoginManager loginManager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.email = firstName + lastName + "@buscompany.com"; // Creates email for employee

        if (jobTitle.equalsIgnoreCase("Admin")) {
            this.employeeID = IdGenerator.generateAdminId(); // Generates a unique ID for Admin
        } else if (jobTitle.equalsIgnoreCase("Driver")) {
            this.employeeID = IdGenerator.generateDriverId(); // Generates a unique ID for Driver
        } else {
            System.out.println("Invalid job title. Employee ID not generated.");
        }
        employeeManagement.addEmployee(this);
        new Login(this, loginManager); //creates new login for employee
    }
    
    /**
     * constructor to initialize an Employee object with a specific employee ID
     * Used for loading employees from CSV
     * 
     * @param firstName         The first name of the employee.
     * @param lastName          The last name of the employee.
     * @param jobTitle          The job title of the employee (Driver or Admin).
     * @param phoneNumber       The phone number of the employee.
     * @param salary            The salary of the employee.
     * @param employeeManagement Reference to the EmployeeManagement class.
     * @param loginManager      The LoginManager with the list of logins
     * @param employeeID        The specific employee ID to use
     */
    public Employee(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, 
                   EmployeeManagement employeeManagement, LoginManager loginManager, String employeeID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.email = firstName + lastName + "@buscompany.com"; // Creates email for employee
        this.employeeID = employeeID;
        
        // Update ID generators if this ID has a higher number
        if (jobTitle.equalsIgnoreCase("Admin") && employeeID.startsWith("A-")) {
            int idNumber = Integer.parseInt(employeeID.substring(2));
            IdGenerator.updateAdminIdIfHigher(idNumber);
        } else if (jobTitle.equalsIgnoreCase("Driver") && employeeID.startsWith("D-")) {
            int idNumber = Integer.parseInt(employeeID.substring(2));
            IdGenerator.updateDriverIdIfHigher(idNumber);
        }
        
        employeeManagement.addEmployee(this);
        new Login(this, loginManager, this.getEmail(), "password"); // Create login for employee loaded from CSV
    }

    // Getters

    /**
     * Gets the employee ID.
     * 
     * @return The employee ID.
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the full name of the employee.
     * 
     * @return The full name of the employee.
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Gets the email address of the employee.
     * 
     * @return The email address of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the employee.
     * 
     * @return The phone number of the employee.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the job title of the employee.
     * 
     * @return The job title of the employee.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Gets the salary of the employee.
     * 
     * @return The salary of the employee.
     */
    public float getSalary() {
        return salary;
    }

    // Setters

    /**
     * Sets the name of the employee.
     * 
     * @param firstName The first name of the employee.
     * @param lastName  The last name of the employee.
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the employee.
     * 
     * @param name The name to generate the email address.
     */
    public void setEmail(String name) {
        this.email = name + "@buscompany.com"; // Creates email for employee
    }

    /**
     * Sets the phone number of the employee.
     * 
     * @param phoneNumber The phone number of the employee.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the job title of the employee.
     * 
     * @param jobTitle The job title of the employee.
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sets the salary of the employee.
     * 
     * @param salary The salary of the employee.
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * Deletes the employee by resetting all fields to default values.
     */
    public void deleteEmployee() {
        this.employeeID = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.phoneNumber = null;
        this.jobTitle = null;
        this.salary = 0;
    }
}
