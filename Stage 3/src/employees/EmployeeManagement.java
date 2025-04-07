package employees;
import java.util.ArrayList;


public class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>(); // List to store employees
    
    public static void addEmployee(Employee employee) {
        // Add a new employee to the list
        employees.add(employee);
    }

    public static void deleteEmployee(Employee employee) {
        employee.deleteEmployee();
        // Remove the employee from the list
        employees.remove(employee);
    }
    
    /**
     * Reassigns a new job title to an employee.
     *
     * @param employee    The employee whose job title is to be reassigned.
     * @param newJobTitle The new job title for the employee.
     */
    public static void setJobTitle(Employee employee, String newJobTitle) {
        employee.setJobTitle(newJobTitle);
    }

    public static void updateName(Employee employee, String firstName, String lastName) {
        employee.setName(firstName, lastName);
    }

    public static void updateEmail(Employee employee, String email) {
        employee.setEmail(email);
    }

    public static void updatePhoneNumber(Employee employee, String phoneNumber) {
        employee.setPhoneNumber(phoneNumber);
    }

    public static void updateSalary(Employee employee, float salary) {
        employee.setSalary(salary);
    }

    public static ArrayList<Employee> getAllEmployees() {
        System.out.println("List of all employees:");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " - " + employee.getEmployeeID() + " - " + employee.getJobTitle() + " - " + employee.getEmail() + " - " + employee.getPhoneNumber() + " - " + "$" + String.format("%.2f", employee.getSalary()));
        }
        return employees; 
    }

    public static Employee getEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return employee;
            }
        }
        return null; // Employee not found
    }
}

