
package menu;

import java.util.Scanner;

/**
 * 
 * @author Adrian Zielinski
 */
public class EmployeeMenu {

    private Scanner in;
    private int menuOption;

    /**
     * default constructor
     * 
     * @param in, scanner
     */
    public EmployeeMenu(Scanner in) {

        this.in = in;
        menuOption = 0;
        
    }

    /**
     * display the employee menu
     */
    public void displayMenu() {

        int menuOption = 0;
        
        while (menuOption != 7) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Employee Menu");
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
                    BusMenu busMenu = new BusMenu(in);
                    busMenu.displayMenu();
                }
                case 2 -> {
                    // schedule management
                }
                case 3 -> {
                    // route management
                }
                case 4 -> {
                    // depot management
                }
                case 5 -> {
                    // employee management
                }
                case 6 -> {
                    // expense management
                }
                case 7 -> {
                    System.out.println("Returning to Main Menu...");
                    return; // This will exit the displayMenu method and return to Main.java
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
