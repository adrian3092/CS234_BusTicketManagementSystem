package menu;

import employees.Employee;
import employees.EmployeeManagement;
import java.util.Scanner;
/**
 *
 * @author Owner
 */
public class EmployeeManagementMenu {
    private Scanner in;
    private int menuOption;
    private EmployeeManagement employeeManagement;
    

    public EmployeeManagementMenu(Scanner in, EmployeeManagement employeeManagement) {
        this.in = in;
        this.employeeManagement = employeeManagement;
        menuOption = 0;
    }

    public void displayMenu() {
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║            EMPLOYEE MANAGEMENT MENU            ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Add a New Employee                         ║");
            System.out.println("║  2. Delete an Existing Employee                ║");
            System.out.println("║  3. Display All Employees                      ║");
            System.out.println("║  4. Update Employee Information                ║");
            System.out.println("║  5. Return to Main Menu                        ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-5): ");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    addEmployee(); // I commented out this line because its breaking the code (George)
                }
                case 2 -> {
                    deleteEmployee();
                }
                case 3 -> {
                    // display all employees
                    this.employeeManagement.getAllEmployees();
                }
                case 4 -> {
                    updateEmployee();
                }
                case 5 -> {
                    System.out.println("Returning to Employee Menu...");
                    menuOption = 0; // reset menuOption before returning
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void updateEmployee() {
        System.out.println("Enter the ID of the employee to update:");
        String employeeId = in.next();
        Employee employee = this.employeeManagement.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Update failed.");
            return;
        }
        in.nextLine(); // consume newline

        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Job Title");
        System.out.println("3. Email");
        System.out.println("4. Phone Number");
        System.out.println("5. Salary");
        int updateOption = in.nextInt();
        in.nextLine(); // consume newline


        switch (updateOption) {
            case 1 -> {
                System.out.println("Enter the new first name of the employee: ");
                String newName = in.nextLine();
                System.out.println("Enter the new last name of the employee: ");
                String newLastName = in.nextLine();
                this.employeeManagement.updateName(employee, newName, newLastName); 
                System.out.println("Employee name updated successfully.");
            }
            case 2 -> {
                System.out.println("Enter the new job title of the employee:");
                String newJobTitle = in.nextLine();
                this.employeeManagement.setJobTitle(employee, newJobTitle);
                System.out.println("Employee job title updated successfully.");
            }
            case 3 -> {
                System.out.println("Enter the new email of the employee:");
                String newEmail = in.nextLine();
                this.employeeManagement.updateEmail(employee, newEmail);
                System.out.println("Employee email updated successfully.");
            }
            case 4 -> {
                System.out.println("Enter the new phone number of the employee:");
                String newPhoneNumber = in.nextLine();
                this.employeeManagement.updatePhoneNumber(employee, newPhoneNumber);
                System.out.println("Employee phone number updated successfully.");
            }
            case 5 -> {
                System.out.println("Enter the new salary of the employee:");
                float newSalary = in.nextFloat();
                this.employeeManagement.updateSalary(employee, newSalary);
                System.out.println("Salary has been updated successfully.");
            }
            default -> {
                System.out.println("Invalid option. Update canceled.");
                return;
            }
        }

    }

    private void deleteEmployee() {
        System.out.println("Enter the ID of the employee to delete:");
        String employeeId = in.next();
        Employee employee = this.employeeManagement.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Deletion failed.");
            return;
        }
        this.employeeManagement.deleteEmployee(employee);
        System.out.println("Employee deleted successfully.");
    }


    private void addEmployee() {

        in.nextLine(); // consume leftover new line
        System.out.print("Please enter the first name of the employee: ");
        String firstName = in.nextLine();
        System.out.print("Please enter the last name of the employee: ");
        String lastName = in.nextLine();
        System.out.print("Please enter the job title of the employee: ");
        String jobTitle = in.nextLine();
        boolean jTest = true;
        
        while(jTest) {
        
        if (jobTitle.equalsIgnoreCase("Admin") || jobTitle.equalsIgnoreCase("Driver")){
            jTest = false;
        }
        else {
            System.out.println("Please enter a valid jobTitle: (Driver) or (Admin): ");
            jobTitle = in.nextLine();
        }}
          //change
        System.out.print("Please enter the email of the employee: ");
        String email = in.nextLine();
        System.out.print("Please enter the phone number of the employee: ");
        String phoneNumber = in.nextLine();
        System.out.print("Please enter the salary of the employee: ");
        float salary = in.nextFloat();
        in.nextLine(); // consume leftover new line

        Employee newEmployee = new Employee(firstName, lastName, jobTitle, phoneNumber, salary, this.employeeManagement);
        System.out.println("A new employee has been added with ID: " + newEmployee.getEmployeeID());
    }    
    
    }


