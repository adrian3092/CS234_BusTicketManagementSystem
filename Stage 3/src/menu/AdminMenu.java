
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
    public AdminMenu(Scanner in, BusManager busManager, DepotManager depotManager, ScheduleManager scheduleManager, Accounting accounting, EmployeeManagement employeeManagement, RouteManager routeManager) {
        this.in = in;
        menuOption = 0;
        busMenu = new BusMenu(in, busManager, depotManager);
        depotMenu = new DepotMenu(in, busManager, depotManager);
        accountingMenu = new AccountingMenu(in, accounting, busManager, depotManager, employeeManagement);
        employeeManagementMenu = new EmployeeManagementMenu(in, employeeManagement);
        scheduleMenu = new ScheduleMenu(in, scheduleManager, routeManager, depotManager);
        routeMenu = new RouteMenu(in, routeManager);
    }

    /**
     * display the employee menu
     */
    public void displayMenu() {
        while (menuOption != 8) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Admin Menu");
            System.out.println("1. Bus Management");
            System.out.println("2. Schedule Management");
            System.out.println("3. Route Management");
            System.out.println("4. Depot Management");
            System.out.println("5. Driver Management");
            System.out.println("6. Employee Management");
            System.out.println("7. Expense Management");
            System.out.println("8. Return to Main Menu");
            System.out.println("~~~~~~~~~~~~");
            
            menuOption = in.nextInt();

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
                    // driver management
                }
                case 6 -> {
                    employeeManagementMenu.displayMenu(); 
                }
                case 7 -> {
                    accountingMenu.displayMenu();
                }
                case 8 -> {
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
