
package menu;

import java.util.Scanner;
import bus.BusManager;
import depot.DepotManager;
import expenses.Accounting;
import main.ScheduleManager;

/**
 * 
 * @author Adrian Zielinski
 */
public class AdminMenu {

    private Scanner in;
    private int menuOption;
    private BusMenu busMenu;
    private DepotMenu depotMenu;
    private AccountingMenu accountingMenu;
    private Accounting accounting;
    private ScheduleManager scheduleManager;

    /**
     * default constructor
     * 
     * @param in, scanner
     * @param busManager, the bus manager to use
     */
    public AdminMenu(Scanner in, BusManager busManager, DepotManager depotManager, ScheduleManager scheduleManager, Accounting accounting) {
        this.in = in;
        menuOption = 0;
        busMenu = new BusMenu(in, busManager, depotManager);
        depotMenu = new DepotMenu(in, busManager, depotManager);
        accountingMenu = new AccountingMenu(in, accounting, busManager);
        this.scheduleManager = scheduleManager;
    }

    /**
     * display the employee menu
     */
    public void displayMenu() {
        
        while (menuOption != 7) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Admin Menu");
            System.out.println("1. Bus Management");
            System.out.println("2. Schedule Management");
            System.out.println("3. Route Management");
            System.out.println("4. Depot Management");
            System.out.println("5. Employee Management");
            System.out.println("6. Expense Management");
            System.out.println("7. Return to Main Menu");
            System.out.println("~~~~~~~~~~~~");
            
            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    busMenu.displayMenu();
                }
                case 2 -> {
                    // schedule management
                }
                case 3 -> {
                    // route management
                }
                case 4 -> {
                    depotMenu.displayMenu();
                }
                case 5 -> {
                    // employee management
                }
                case 6 -> {
                    accountingMenu.displayMenu();
                }
                case 7 -> {
                    System.out.println("Returning to Main Menu...");
                    menuOption = 0; // reset menuOption before returning
                    return; 
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
