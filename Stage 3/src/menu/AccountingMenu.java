
package menu;
import bus.*;
import java.util.ArrayList;
import java.util.Scanner;

import expenses.*;

/**
 * 
 * @author George Candal
 */
public class AccountingMenu {
    
    private Scanner in;
    private int menuOption;
    private Accounting accounting;
    private BusManager busManager;
    
    public AccountingMenu(Scanner in, Accounting accounting, BusManager busManager) {
        this.in = in;
        this.accounting = accounting;
        this.busManager = busManager;
        menuOption = 0;
    }

    /**
     * display the main accounting menu
     */
    public void displayMenu() {
        
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Accounting Menu");
            System.out.println("1. Generate Report");
            System.out.println("2. Add a new expense");
            System.out.println("3. Manage an existing expense");
            System.out.println("4. Return to previous menu");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    accounting.getReport();
                }
                case 2 -> {
                    addExpenseMenu();
                }
                case 3 -> {
                    // manage an expense
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

    
    public void addExpenseMenu() {
        int menuOption = 0;
        
        while (menuOption != 5) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("What type of expense would you like to add?");
            System.out.println("1. Fuel");
            System.out.println("2. Maintenance");
            System.out.println("3. Salary");
            System.out.println("4. Utilities");
            System.out.println("5. Return to previous menu");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    accounting.addFuelCost(busManager);
                }
                case 2 -> {
                    //maintenance
                }
                case 3 -> {
                    //salary
                }
                case 4 -> {
                    //utility
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
