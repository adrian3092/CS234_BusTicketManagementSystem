
package menu;
import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.*;
import java.util.Scanner;

/**
 * 
 * @author George Candal
 */
public class AccountingMenu {
    
    private Scanner in;
    private int menuOption;
    private Accounting accounting;
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    
    public AccountingMenu(Scanner in, Accounting accounting, BusManager busManager, DepotManager depotManager, EmployeeManagement employeeManagement) {
        this.in = in;
        this.accounting = accounting;
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        menuOption = 0;
    }

    /**
     * display the main accounting menu
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
            System.out.print(" Please select an option (1-5): ");

            menuOption = in.nextInt();

            System.out.println("========================================");

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
