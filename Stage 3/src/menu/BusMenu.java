
package menu;
import java.util.ArrayList;
import java.util.Scanner;

import bus.Bus;
import bus.BusManager;
import depot.DepotManager;

/**
 * 
 * @author Adrian Zielinski
 */
public class BusMenu {
    
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
    public BusMenu(Scanner in, BusManager busManager, DepotManager depotManager) {
        this.in = in;
        menuOption = 0;
        this.busManager = busManager;
        this.depotManager = depotManager;
    }

    /**
     * display the main bus menu
     */
    public void displayMenu() {
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Bus Management Menu");
            System.out.println("1. Add a new bus");
            System.out.println("2. Manage an existing bus");
            System.out.println("3. Display all buses");
            System.out.println("4. Return to Employee Menu");

            if (in.hasNextInt()) {
                menuOption = in.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // consume the invalid input
                continue;
            }

            switch (menuOption) {
                case 1 -> {
                    addBus();
                }
                case 2 -> {
                    manageBus();
                }
                case 3 -> {
                    // display all buses
                    System.out.println("      All Buses      \n");
                    ArrayList<Bus> buses = busManager.getAllBuses();
                    if (buses.isEmpty()) {
                        System.out.println("There are no buses available in the system.");
                    } else {
                        for (Bus bus : buses) {
                            System.out.println("ID: " + bus.getBusId());
                            System.out.println("Year/Make/Model: " + bus.getYear() + " " + bus.getMake() + " " + bus.getModel());
                            System.out.println("Mileage: " + bus.getMileage());
                            System.out.println("Seating Capacity: " + bus.getCapacity());
                            System.out.println("Status: " + bus.getStatus());
                            System.out.println("-----------------------");
                        }
                        System.out.println("\nTotal buses: " + buses.size());
                    }
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
     * display options for adding a new bus
     */
    public void addBus() {
        System.out.print("Enter the year of the bus: ");
        int year;
        if (in.hasNextInt()) {
            year = in.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number for year.");
            in.next(); // consume the invalid input
            return;
        }
        
        System.out.print("Enter the make of the bus: ");
        String make = in.next();
        
        System.out.print("Enter the model of the bus: ");
        String model = in.next();
        
        System.out.print("Enter the mileage of the bus: ");
        int mileage;
        if (in.hasNextInt()) {
            mileage = in.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number for mileage.");
            in.next(); // consume the invalid input
            return;
        }
        
        System.out.print("Enter the seating capacity of the bus: ");
        int seatingCapacity;
        if (in.hasNextInt()) {
            seatingCapacity = in.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number for seating capacity.");
            in.next(); // consume the invalid input
            return;
        }
        
        Bus newBus = new Bus(year, make, model, mileage, seatingCapacity);
        busManager.addBus(newBus);
        System.out.println("A new bus has been added with ID: " + newBus.getBusId());
    }

    /**
     * display options for managing an existing bus
     */
    public void manageBus() {
        System.out.print("Enter the ID of the bus you would like to manage: ");
        int busId;
        if (in.hasNextInt()) {
            busId = in.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            in.next(); // consume the invalid input
            return;
        }
        Bus selectedBus = busManager.findBusById(busId);
        
        if (selectedBus == null) {
            System.out.println("Bus with ID " + busId + " not found.");
            return;
        }
        
        int subMenuOption = 0;

        while (subMenuOption != 6) {
            System.out.println("Bus Management Options for Bus ID: " + busId);
            System.out.println("1. Get the mileage");
            System.out.println("2. Update the mileage");
            System.out.println("3. Get the status");
            System.out.println("4. Update the status");
            System.out.println("5. Delete an existing bus");
            System.out.println("6. Return to Bus Menu");
            
            if (in.hasNextInt()) {
                subMenuOption = in.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // consume the invalid input
                continue;
            }
        
            switch (subMenuOption) {
                case 1 -> {
                    System.out.println("The current mileage is: " + selectedBus.getMileage());
                }
                case 2 -> {
                    System.out.print("Enter new mileage: ");
                    int newMileage;
                    if (in.hasNextInt()) {
                        newMileage = in.nextInt();
                        selectedBus.setMileage(newMileage);
                        System.out.println("The mileage has been updated successfully.");
                    } else {
                        System.out.println("Invalid input. Please enter a number for mileage.");
                        in.next(); // consume the invalid input
                    }
                }
                case 3 -> {
                    System.out.println("The current status is: " + selectedBus.getStatus());
                }
                case 4 -> {
                    System.out.print("Enter new status: ");
                    String newStatus = in.next();
                    selectedBus.setStatus(newStatus);
                    System.out.println("The status has been updated successfully.");
                }
                case 5 -> {
                    busManager.removeBus(selectedBus);
                    System.out.println("The bus with ID " + busId + " has been deleted.");
                }
                case 6 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
