
package menu;
import bus.Bus;
import bus.BusManager;
import depot.Depot;
import depot.DepotManager;
import java.util.Scanner;

/**
 * 
 * @author Adrian Zielinski
 */
public class DepotMenu {

    private Scanner in;
    private int menuOption;
    private DepotManager depotManager;
    private BusManager busManager;
    
    /**
     * default constructor
     * @param in scanner
     * @param busManager the bus manager to use
     * @param depotManager the depot manager to use
     */
    public DepotMenu(Scanner in, BusManager busManager, DepotManager depotManager) {
        this.in = in;
        menuOption = 0;
        this.depotManager = depotManager;
        this.busManager = busManager;
    }

    /**
     * display the main depot menu
     */
    public void displayMenu() {
        while (menuOption != 6) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              DEPOT MANAGEMENT MENU             ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Add a new depot                            ║");
            System.out.println("║  2. Delete an existing depot                   ║");
            System.out.println("║  3. Assign a bus to a depot                    ║");
            System.out.println("║  4. Unassign a bus from a depot                ║");
            System.out.println("║  5. Display all depots                         ║");
            System.out.println("║  6. Return to Employee Menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-6): ");

            menuOption = in.nextInt();

            System.out.println("══════════════════════════════════════════════════"); 

            switch (menuOption) {
                case 1 -> {
                    // add a depot
                    addDepot();
                }
                case 2 -> {
                    // delete a depot
                    deleteDepot();
                }
                case 3 -> {
                    // assign a bus to a depot
                    assignDepot();
                }
                case 4 -> {
                    // unassign a bus from a depot
                    unassignDepot();
                }
                case 5 -> {
                    // display all depots
                    System.out.println("All Depots:");
                    if (depotManager.getAllDepots().isEmpty()) {
                        System.out.println("No depots found...");
                    }
                    else {
                        for (Depot depot: depotManager.getAllDepots()) {
                            System.out.println("Depot ID: " + depot.getDepotId());
                            System.out.println("Address: " + depot.getDepotAddress());
                        }
                    }
                }
                case 6 -> {
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
        in.nextLine(); // consume the leftover new line character
        System.out.print("Enter the address of the depot: ");
        String address = in.nextLine();
        Depot newDepot = new Depot(address);
        depotManager.addDepot(newDepot);
        System.out.println("A new depot has been added with ID: " + newDepot.getDepotId());
    }

    /**
     * display options for managing an existing depot
     */
    public void deleteDepot() {
        System.out.print("Enter the ID of the depot to be deleted: ");
        int depotId = in.nextInt();
        Depot depot = depotManager.findDepotById(depotId);
        if (depot == null) {
            System.out.println("A depot with ID " + depotId + " does not exist.");
        }
        else {
            depotManager.removeDepot(depot);
            System.out.println("The depot with ID " + depotId + " has been deleted.");
        }
    }

    /**
     * display options for assigning a bus to a depot
     */
    public void assignDepot() {
        System.out.print("Enter the ID of the bus to be assigned: ");
        int busId = in.nextInt();
        Bus bus = busManager.findBusById(busId);
        System.out.print("Enter the ID of the depot you would like to assign the bus to: ");
        int depotId = in.nextInt();
        Depot depot = depotManager.findDepotById(depotId);
        depotManager.assignBusToDepot(depotId, bus);
        System.out.println("The bus with ID " + busId + " has been assigned to depot " + depot.getDepotId());
    }

    /**
     * display options for unassigning a bus from a depot
     */
    public void unassignDepot() {
        System.out.print("Enter the ID of the bus to be unassigned: ");
        int busId = in.nextInt();
        Bus bus = busManager.findBusById(busId);
        System.out.print("Enter the ID of the depot you would like to unassign the bus from: ");
        int depotId = in.nextInt();
        Depot depot = depotManager.findDepotById(depotId);
        depotManager.removeBusFromDepot(depotId, bus);
        System.out.println("The bus with ID " + busId + " has been unassigned from depot " + depot.getDepotId());
    }
}
