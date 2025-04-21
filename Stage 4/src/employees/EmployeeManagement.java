package employees;

import java.util.ArrayList;

/**
 * EmployeeManagement class handles the management of employees and drivers.
 * It provides methods to add, delete, update, and retrieve employees and drivers.
 * 
 * @author Handsome Onojerame
 */
public class EmployeeManagement {
    private ArrayList<Employee> employees; // List to store employees
    private ArrayList<Driver> drivers; // List to store all drivers

    /**
     * Constructor to initialize the employee and driver lists.
     */
    public EmployeeManagement() {
        employees = new ArrayList<>(); // Initialize the list of employees
        drivers = new ArrayList<>(); // Initialize the list of drivers
    }

    /**
     * Adds a new employee to the list.
     * 
     * @param employee The employee to be added.
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Deletes an employee from the list.
     * If the employee is a driver, it is also removed from the drivers list.
     * 
     * @param employee The employee to be deleted.
     */
    public void deleteEmployee(Employee employee) {
        employee.deleteEmployee();
        employees.remove(employee);
        if (employee instanceof Driver) {
            drivers.remove((Driver) employee);
        }
    }

    /**
     * Reassigns a new job title to an employee.
     * If the new job title is "Driver", the employee is added to the drivers list.
     * 
     * @param employee    The employee whose job title is to be reassigned.
     * @param newJobTitle The new job title for the employee.
     */
    public void setJobTitle(Employee employee, String newJobTitle) {
        employee.setJobTitle(newJobTitle);
        if (newJobTitle.equalsIgnoreCase("Driver") && employee instanceof Driver) {
            addDriver((Driver) employee);
        }
    }

    /**
     * Updates the name of an employee.
     * 
     * @param employee   The employee whose name is to be updated.
     * @param firstName  The new first name.
     * @param lastName   The new last name.
     */
    public void updateName(Employee employee, String firstName, String lastName) {
        employee.setName(firstName, lastName);
    }

    /**
     * Updates the email of an employee.
     * 
     * @param employee The employee whose email is to be updated.
     * @param email    The new email address.
     */
    public void updateEmail(Employee employee, String email) {
        employee.setEmail(email);
    }

    /**
     * Updates the phone number of an employee.
     * 
     * @param employee    The employee whose phone number is to be updated.
     * @param phoneNumber The new phone number.
     */
    public void updatePhoneNumber(Employee employee, String phoneNumber) {
        employee.setPhoneNumber(phoneNumber);
    }

    /**
     * Updates the salary of an employee.
     * 
     * @param employee The employee whose salary is to be updated.
     * @param salary   The new salary.
     */
    public void updateSalary(Employee employee, float salary) {
        employee.setSalary(salary);
    }

    /**
     * Retrieves and prints the list of all employees.
     * 
     * @return The list of all employees.
     */
    public ArrayList<Employee> getAllEmployees() {
        System.out.println("List of all employees:");
        System.out.printf("%-20s %-15s %-20s %-30s %-15s %-10s%n", 
                "Name", "Employee ID", "Job Title", "Email", "Phone Number", "Salary");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for (Employee employee : employees) {
            System.out.printf("%-20s %-15s %-20s %-30s %-15s $%-10.2f%n", 
                    employee.getName(), 
                    employee.getEmployeeID(), 
                    employee.getJobTitle(), 
                    employee.getEmail(), 
                    employee.getPhoneNumber(), 
                    employee.getSalary());
        }
        return employees;
    }

    /**
     * Adds a driver to the drivers list.
     * If the driver is not already in the employees list, it is added there as well.
     * 
     * @param driver The driver to be added.
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
        if (!employees.contains(driver)) {
            employees.add(driver);
        }
    }

    /**
     * Retrieves a driver by their employee ID.
     * 
     * @param employeeId The ID of the driver to find.
     * @return The driver with the specified ID, or null if not found.
     */
    public Driver getDriverById(String employeeId) {
        for (Driver driver : drivers) {
            if (driver.getEmployeeID().equals(employeeId)) {
                return driver;
            }
        }
        return null; // Driver not found
    }

    /**
     * Retrieves an employee by their employee ID.
     * 
     * @param employeeId The ID of the employee to find.
     * @return The employee with the specified ID, or null if not found.
     */
    public Employee getEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return employee;
            }
        }
        return null; // Employee not found
    }
}
