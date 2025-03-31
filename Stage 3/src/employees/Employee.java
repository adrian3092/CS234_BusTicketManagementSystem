package employees;

/**
 * Represents an Employee with details such as ID, name, email, phone number, job title, access level, and salary.
 */
public class Employee {
    private String employeeID; // Unique identifier for the employee
    private String name; // Name of the employee
    private String email; // Email address of the employee
    private String phoneNumber; // Phone number of the employee
    private String jobTitle; // Job title of the employee
    private String accessLevel; // Access level of the employee
    private float salary; // Salary of the employee
   
    /**
     * Constructor to create an Employee with a name and job title.
     * Creates email from employee name
     * @param name Name of the employee.
     * @param jobTitle Job title of the employee.
     */
    public Employee(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.employeeID = generateEmployeeID();
        this.email = name + "@buscompany.com"; //creates email for employee
    }

    /**
     * Constructor to create an Employee with all details.
     * @param name Name of the employee.
     * @param email Email address of the employee.
     * @param phoneNumber Phone number of the employee.
     * @param jobTitle Job title of the employee.
     * @param accessLevel Access level of the employee.
     * @param salary Salary of the employee.
     */
    public Employee(String name, String email, String phoneNumber, String jobTitle, String accessLevel, float salary) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.accessLevel = accessLevel;
        this.salary = salary;
        this.employeeID = generateEmployeeID();
    }

    /**
     * Generates a unique employee ID.
     * @return The generated employee ID.
     */
    public String generateEmployeeID() {
        this.employeeID = "E" + (int)(Math.random() * 10000);
        return this.employeeID;
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public float getSalary() {
        return salary;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * Deletes the employee by resetting all fields to default values.
     */
    public void deleteEmployee() {
        this.employeeID = null;
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
        this.jobTitle = null;
        this.accessLevel = null;
        this.salary = 0;
    }
}
