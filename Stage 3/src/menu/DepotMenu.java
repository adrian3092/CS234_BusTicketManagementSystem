
package menu;
import java.util.Scanner;

import bus.Bus;
import depot.Depot;
import depot.DepotManager;

/**
 * 
 * @author Adrian Zielinski
 */
public class DepotMenu {

    private Scanner in;
    private int menuOption;
    DepotManager depotManager;
    
    /**
     * default constructor
     * @param in, scanner
     */
    public DepotMenu(Scanner in) {
        
        this.in = in;
        menuOption = 0;
        depotManager = new DepotManager();
    }

    /**
     * display the main depot menu
     */
    public void displayMenu() {
        
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Depot Management Menu");
            System.out.println("1. Add a new depot");
            System.out.println("2. Delete an existing depot");
            System.out.println("3. Display all depots");
            System.out.println("4. Return to Employee Menu");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    addDepot();
                }
                case 2 -> {
                    deleteDepot();
                }
                case 3 -> {
                    // display all depots
                    System.out.println("All Depots:");
                    System.out.println(depotManager.getAllDepots());
                }
                case 4 -> {
                    System.out.println("Returning to Employee Menu...");
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
     * display options for adding a new depot
     */
    public void addDepot() {
        in.nextLine(); // consume leftover new line
        System.out.print("Please enter the address of the depot: ");
        String address = in.nextLine();
        Depot newDepot = new Depot(address);
        depotManager.addDepot(newDepot);
        System.out.println("A new depot has been added with ID: " + newDepot.getDepotId());
    }

    /**
     * display options for managing an existing depot
     */
    public void deleteDepot() {
        System.out.print("Please enter the ID of the depot to be deleted: ");
        int depotId = in.nextInt();
        Depot depot = depotManager.findDepotById(depotId);
        depotManager.removeDepot(depot);
        System.out.println("The depot with ID " + depotId + " has been deleted.");
    }
}
