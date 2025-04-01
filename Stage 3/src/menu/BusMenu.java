
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
     * @param in, scanner
     * @param busManager, the bus manager to use
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

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    addBus();
                }
                case 2 -> {
                    manageBus();
                }
                case 3 -> {
                    // display all buses
                    System.out.println("\n      All Buses      ");
                    ArrayList<Bus> buses = busManager.getAllBuses();
                    if (buses.isEmpty()) {
                        System.out.println("There are no buses available in the system.");
                    } else {
                        for (Bus bus : buses) {
                            System.out.println("\n      Bus Details      ");
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
        
        System.out.print("What is the year of the bus? ");
        int year = in.nextInt();
        System.out.print("What is the make of the bus? ");
        String make = in.next();
        System.out.print("What is the model of the bus? ");
        String model = in.next();
        System.out.print("What is the mileage of the bus? ");
        int mileage = in.nextInt();
        System.out.print("What is the seating capacity of the bus? ");
        int seatingCapacity = in.nextInt();
        Bus newBus = new Bus(year, make, model, mileage, seatingCapacity);
        busManager.addBus(newBus);
        System.out.println("A new bus has been added with ID: " + newBus.getBusId());

    }

    /**
     * display options for managing an existing bus
     */
    public void manageBus() {

        System.out.print("Please enter the id of the bus you would like to manage: ");
        int id = in.nextInt();
        Bus selectedBus = busManager.findBusById(id);
        
        if (selectedBus == null) {
            System.out.println("Bus with ID " + id + " not found.");
            return;
        }
        
        int subMenuOption = 0;

        while (subMenuOption != 3) {
            System.out.println("Bus Management Options for Bus ID: " + id);
            System.out.println("1. Get the mileage");
            System.out.println("2. Update mileage");
            System.out.println("3. Return to Bus Menu");
            
            subMenuOption = in.nextInt();
        
            switch (subMenuOption) {
                case 1 -> {
                    System.out.println("The current mileage is: " + selectedBus.getMileage());
                }
                case 2 -> {
                    System.out.print("Enter new mileage: ");
                    int newMileage = in.nextInt();
                    selectedBus.setMileage(newMileage);
                    System.out.println("The mileage has been updated successfully.");
                }
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
