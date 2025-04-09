
package menu;
import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.*;
import java.util.Scanner;

/**
 * This class is used for the accounting menu. It displays a menu and asks for
 * user input for managing expenses. It has 6 instance variables.
 * in: The Scanner object used for user input
 * menuOption: Used to save the menu option input from the user
 * accounting: The Accounting object used as a database for the expenses
 * busManager: The BusManager object used as a database for buses
 * depotManager: The DepotManager object used as a database for Depots
 * employeeManagement: The EmployeeManagement object used as a database for 
 * employees
 * @author George Candal
 */
public class AccountingMenu {
    
    private Scanner in;
    private int menuOption;
    private Accounting accounting;
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    
    /**
     * Constructor for the class. 
     * @param in The Scanner object used for user input
     * @param accounting The Accounting object used as a database for the 
     * expenses
     * @param busManager The BusManager object used as a database for buses
     * @param depotManager The DepotManager object used as a database for Depots
     * @param employeeManagement The EmployeeManagement object used as a 
     * database for employees
     */
    public AccountingMenu(Scanner in, Accounting accounting, 
            BusManager busManager, DepotManager depotManager, 
            EmployeeManagement employeeManagement) {
        this.in = in;
        this.accounting = accounting;
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        menuOption = 0;
    }

    /**
     * Displays the main accounting menu
     */
    public void displayMenu() {
        
        while (menuOption != 4) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                 ACCOUNTING MENU                ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Generate Report                            ║");
            System.out.println("║  2. Add a new expense                          ║");
            System.out.println("║  3. Remove an existing expense                 ║");
            System.out.println("║  4. Return to previous menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-4): ");

            menuOption = in.nextInt();

            System.out.println("══════════════════════════════════════════════════"); 

            switch (menuOption) {
                case 1 -> {
                    accounting.getReport();
                }
                case 2 -> {
                    expenseSubMenu();
                }
                case 3 -> {
                    accounting.removeExpense();
                }
                case 4 -> {
                    System.out.println("Returning to previous menu...");
                    menuOption = 0; // reset menuOption before returning
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    /**
     * Displays the sub menu to add a new expense
     */
    public void expenseSubMenu() {
        int menuOption = 0;
        
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║  What type of expense would you like to add?   ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Fuel                                       ║");
            System.out.println("║  2. Maintenance                                ║");
            System.out.println("║  3. Salary                                     ║");
            System.out.println("║  4. Utilities                                  ║");
            System.out.println("║  5. Return to previous menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-5): ");

            menuOption = in.nextInt();
            
            System.out.println("══════════════════════════════════════════════════");
            switch (menuOption) {
                case 1 -> {
                    accounting.addBusCost(busManager, "fuel");
                }
                case 2 -> {
                    accounting.addBusCost(busManager, "maintenance");

                }
                case 3 -> {
                    accounting.addSalary(employeeManagement);
                }
                case 4 -> {
                    accounting.addUtilityCost(depotManager);
                }
                case 5 -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }     
}
