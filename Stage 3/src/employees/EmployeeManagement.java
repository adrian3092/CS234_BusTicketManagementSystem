package employees;
import java.util.ArrayList;


public class EmployeeManagement {
    private ArrayList<Employee> employees; // List to store employees

    public EmployeeManagement() {
        employees = new ArrayList<>(); // Initialize the list of employees
    }
    
    public void addEmployee(Employee employee) {
        // Add a new employee to the list
        employees.add(employee);
    }

    public void deleteEmployee(Employee employee) {
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
    public void setJobTitle(Employee employee, String newJobTitle) {
        employee.setJobTitle(newJobTitle);
    }

    public void updateName(Employee employee, String firstName, String lastName) {
        employee.setName(firstName, lastName);
    }

    public void updateEmail(Employee employee, String email) {
        employee.setEmail(email);
    }

    public void updatePhoneNumber(Employee employee, String phoneNumber) {
        employee.setPhoneNumber(phoneNumber);
    }

    public void updateSalary(Employee employee, float salary) {
        employee.setSalary(salary);
    }

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

    public Employee getEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return employee;
            }
        }
        return null; // Employee not found
    }
}

