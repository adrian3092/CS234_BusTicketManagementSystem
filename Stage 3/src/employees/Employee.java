package employees;

import IdGenerator.IdGenerator;
/**
 * Represents an Employee with details such as ID, name, email, phone number, job title, access level, and salary.
 */
public class Employee {
    private String employeeID; // Unique identifier for the employee
    private String lastName;
    private String firstName; // Name of the employee
    private String email; // Email address of the employee
    private String phoneNumber; // Phone number of the employee
    private String jobTitle; // Job title of the employee
    private float salary; // Salary of the employee
    private EmployeeManagement employeeManagement;

    public Employee(String firstName, String lastName, String jobTitle, EmployeeManagement employeeManagement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.email = firstName + lastName + "@buscompany.com"; //creates email for employee
        

        if (jobTitle.equalsIgnoreCase("Admin")) {
            this.employeeID = IdGenerator.generateAdminId(); // Generates a unique ID for Admin
        } else if (jobTitle.equalsIgnoreCase("Driver")) {
            this.employeeID = IdGenerator.generateDriverId(); // Generates a unique ID for Driver
        }
        else {
            System.out.println("Invalid job title. Employee ID not generated.");
        }
        
        this.employeeManagement = employeeManagement;
        employeeManagement.addEmployee(this);
    }


    public Employee(String firstName, String lastName, String email, String phoneNumber, String jobTitle, float salary, EmployeeManagement employeeManagement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.salary = salary;
        
        if (jobTitle.equalsIgnoreCase("Admin")) {
            this.employeeID = IdGenerator.generateAdminId(); // Generates a unique ID for Admin
        } else if (jobTitle.equalsIgnoreCase("Driver")) {
            this.employeeID = IdGenerator.generateDriverId(); // Generates a unique ID for Driver
        }
        else {
            System.out.println("Invalid job title. Employee ID not generated.");
        }
        
        this.employeeManagement = employeeManagement;
        employeeManagement.addEmployee(this);
    }


    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return firstName + " " + lastName;
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

    public float getSalary() {
        return salary;
    }

    // Setters
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setEmail(String name) {
        this.email = name + "@buscompany.com"; //creates email for employee
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

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