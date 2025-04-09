
package menu;

import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.Accounting;
import java.util.Scanner;
import main.*;

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
    private EmployeeManagementMenu employeeManagementMenu;
    private ScheduleMenu scheduleMenu;
    private RouteMenu routeMenu;
    private Dispatcher dispatcher;

    /**
     * default constructor
     * @param in
     * @param busManager
     * @param depotManager
     * @param scheduleManager
     * @param accounting
     * @param employeeManagement
     * @param routeManager
     */
    public AdminMenu(Scanner in, BusManager busManager, DepotManager depotManager, ScheduleManager scheduleManager, Accounting accounting, EmployeeManagement employeeManagement, RouteManager routeManager, Dispatcher dispatcher) {
        this.in = in;
        menuOption = 0;
        busMenu = new BusMenu(in, busManager, depotManager);
        depotMenu = new DepotMenu(in, busManager, depotManager);
        accountingMenu = new AccountingMenu(in, accounting, busManager, depotManager, employeeManagement);
        employeeManagementMenu = new EmployeeManagementMenu(in, employeeManagement);
        scheduleMenu = new ScheduleMenu(in, scheduleManager, routeManager, depotManager);
        routeMenu = new RouteMenu(in, routeManager, dispatcher, busManager);
    }

    /**
     * display the employee menu
     */
    public void displayMenu() {
        while (menuOption != 7) {
                            System.out.println("╔════════════════════════════════════════════════╗");
                            System.out.println("║              ADMINISTRATOR MENU                ║");
                            System.out.println("╠════════════════════════════════════════════════╣");
                            System.out.println("║  1. Bus Management                             ║");
                            System.out.println("║  2. Schedule Management                        ║");
                            System.out.println("║  3. Route Management                           ║");
                            System.out.println("║  4. Depot Management                           ║");
                            System.out.println("║  5. Employee Management                        ║");
                            System.out.println("║  6. Expense Management                         ║");
                            System.out.println("║  7. Return to Main Menu                        ║");
                            System.out.println("╚════════════════════════════════════════════════╝");
                            System.out.print("Please select an option (1-7): ");
            
            menuOption = in.nextInt();
            System.out.println("════════════════════════════════════════════════"); 

            switch (menuOption) {
                case 1 -> {
                    busMenu.displayMenu();
                }
                case 2 -> {
                    scheduleMenu.displayMenu(); 
                }
                case 3 -> {
                    routeMenu.displayMenu();
                }
                case 4 -> {
                    depotMenu.displayMenu();
                }
                case 5 -> {
                    employeeManagementMenu.displayMenu(); 
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
