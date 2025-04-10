package menu;

import employees.Driver;
import employees.Employee;
import employees.EmployeeManagement;
import java.util.Scanner;
import login.LoginManager;

/**
 * EmployeeManagementMenu provides a menu-driven interface for managing employees.
 * It allows adding, deleting, updating, and displaying employee information.
 * 
 * @author Handsome Onojerame
 */
public class EmployeeManagementMenu {
    private Scanner in;
    private int menuOption;
    private EmployeeManagement employeeManagement;
    private LoginManager loginManager;

    /**
     * Constructor to initialize the EmployeeManagementMenu.
     * 
     * @param in Scanner object for user input.
     * @param employeeManagement EmployeeManagement object for managing employees.
     * @param loginManager LoginManager object to manage logins
     */
    public EmployeeManagementMenu(Scanner in, EmployeeManagement employeeManagement, LoginManager loginManager) {
        this.in = in;
        this.employeeManagement = employeeManagement;
        this.loginManager = loginManager;
        menuOption = 0;
    }

    /**
     * Displays the employee management menu and handles user input.
     */
    public void displayMenu() {
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║            EMPLOYEE MANAGEMENT MENU            ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Add a New Employee                         ║");
            System.out.println("║  2. Delete an Existing Employee                ║");
            System.out.println("║  3. Display All Employees                      ║");
            System.out.println("║  4. Update Employee Information                ║");
            System.out.println("║  5. Return to Administrator Menu               ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-5): ");

            menuOption = in.nextInt();
            System.out.println("══════════════════════════════════════════════════");

            switch (menuOption) {
                case 1 -> addEmployee();
                case 2 -> deleteEmployee();
                case 3 -> employeeManagement.getAllEmployees();
                case 4 -> updateEmployee();
                case 5 -> {
                    System.out.println("Returning to Employee Menu...");
                    menuOption = 0; // Reset menuOption before returning
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Updates the information of an existing employee.
     */
    private void updateEmployee() {
        System.out.println("Enter the ID of the employee to update:");
        String employeeId = in.next();
        Employee employee = employeeManagement.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Update failed.");
            return;
        }
        in.nextLine(); // Consume newline

        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Job Title");
        System.out.println("3. Email");
        System.out.println("4. Phone Number");
        System.out.println("5. Salary");
        int updateOption = in.nextInt();
        in.nextLine(); // Consume newline

        switch (updateOption) {
            case 1 -> {
                System.out.println("Enter the new first name of the employee: ");
                String newName = in.nextLine();
                System.out.println("Enter the new last name of the employee: ");
                String newLastName = in.nextLine();
                employeeManagement.updateName(employee, newName, newLastName);
                System.out.println("Employee name updated successfully.");
            }
            case 2 -> {
                System.out.println("Enter the new job title of the employee:");
                String newJobTitle = in.nextLine();
                employeeManagement.setJobTitle(employee, newJobTitle);
                System.out.println("Employee job title updated successfully.");
            }
            case 3 -> {
                System.out.println("Enter the new email of the employee:");
                String newEmail = in.nextLine();
                employeeManagement.updateEmail(employee, newEmail);
                System.out.println("Employee email updated successfully.");
            }
            case 4 -> {
                System.out.println("Enter the new phone number of the employee:");
                String newPhoneNumber = in.nextLine();
                employeeManagement.updatePhoneNumber(employee, newPhoneNumber);
                System.out.println("Employee phone number updated successfully.");
            }
            case 5 -> {
                System.out.println("Enter the new salary of the employee:");
                float newSalary = in.nextFloat();
                employeeManagement.updateSalary(employee, newSalary);
                System.out.println("Salary has been updated successfully.");
            }
            default -> System.out.println("Invalid option. Update canceled.");
        }
    }

    /**
     * Deletes an existing employee based on their ID.
     */
    private void deleteEmployee() {
        System.out.println("Enter the ID of the employee to delete:");
        String employeeId = in.next();
        Employee employee = employeeManagement.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Deletion failed.");
            return;
        }
        employeeManagement.deleteEmployee(employee);
        System.out.println("Employee deleted successfully.");
    }

    /**
     * Adds a new employee to the system.
     */
    private void addEmployee() {
        in.nextLine(); // Consume leftover new line
        System.out.print("Please enter the first name of the employee: ");
        String firstName = in.nextLine();
        System.out.print("Please enter the last name of the employee: ");
        String lastName = in.nextLine();
        System.out.print("Please enter the job title of the employee: ");
        String jobTitle = in.nextLine();
        boolean jTest = true;

        while (jTest) {
            if (jobTitle.equalsIgnoreCase("Admin") || jobTitle.equalsIgnoreCase("Driver")) {
                jTest = false;
            } else {
                System.out.println("Please enter a valid jobTitle: (Driver) or (Admin): ");
                jobTitle = in.nextLine();
            }
        }

        System.out.print("Please enter the email of the employee: ");
        String email = in.nextLine();
        System.out.print("Please enter the phone number of the employee: ");
        String phoneNumber = in.nextLine();
        System.out.print("Please enter the salary of the employee: ");
        float salary = in.nextFloat();
        in.nextLine(); // Consume leftover new line

        Employee newEmployee;
        if (jobTitle.equalsIgnoreCase("Driver")) {
            // Create a Driver object for driver employees
            Driver newDriver = new Driver(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
            employeeManagement.addDriver(newDriver);
            newEmployee = newDriver;
        } else {
            // Create a regular Employee object for non-driver employees
            newEmployee = new Employee(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
        }
        System.out.println("A new employee has been added with ID: " + newEmployee.getEmployeeID());
    }
}
