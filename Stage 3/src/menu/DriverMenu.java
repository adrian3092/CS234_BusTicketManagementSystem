
package menu;

import java.util.Scanner;
import java.util.ArrayList;
import main.*;
import employees.Driver;
import bus.*;
import depot.Depot;

/**
 *
 * @author Handsome Onojerame
 */
public class DriverMenu {
    //This menu needs to show driver schedule, bus, and route information
    private Scanner in;
    private String menuTitle;
    private String[] menuOptions;
    private String menuPrompt;
    private String menuExit;
    private Dispatcher dispatcher;
    private ScheduleManager scheduleManager;
    private BusManager busManager;

    /**
     * 
     * @param in
     * @param dispatcher
     * @param scheduleManager
     * @param depotManager
     */
    public DriverMenu(Scanner in, Dispatcher dispatcher, ScheduleManager scheduleManager, BusManager busManager) {
        this.in = in;
        this.menuTitle = "Driver Menu";
        this.menuOptions = new String[] {
            "View Schedule",
            "View Bus Information",
            "View Route Information",
            "Return to Main Menu"
        };
        this.menuPrompt = "Please select an option: ";
        this.menuExit = "Exiting Driver Menu...";
        this.dispatcher = dispatcher;
        this.scheduleManager = scheduleManager;
        this.busManager = busManager;
    }
    public void displayMenu() {
        System.out.println(menuTitle);
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
        System.out.print(menuPrompt);
    }

    public void handleMenuSelection(int selection) {
        switch (selection) {
            case 1:
                System.out.println("\nViewing Schedule...");
                System.out.println(scheduleManager);
                break;
            case 2:
                System.out.println("\nViewing Bus Information...");
                displayBusInformation();
                break;
            case 3:
                System.out.println("\nViewing Route Information...");
                displayRouteInformation();
                break;
            case 4:
                System.out.println(menuExit);
                return;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }
    public void run() {
        int selection = 0;
        while (selection != 4) {
            displayMenu();
            selection = in.nextInt();
            in.nextLine(); // consume the leftover new line character
            handleMenuSelection(selection);
        }
    }
    
    /**
     * displays information about all buses assigned to drivers
     */
    private void displayBusInformation() {
        ArrayList<DriverAssignment> assignments = dispatcher.getAllDriverAssignments();
        
        if (assignments.isEmpty()) {
            System.out.println("There are currently no routes assigned to any drivers.");
            return;
        }
        
        for (DriverAssignment assignment : assignments) {
            Driver driver = assignment.getDriver();
            Bus bus = assignment.getBus();
            Depot depot = busManager.findDepotByBusId(bus.getBusId());
            
            System.out.println("-----------------------");
            System.out.println("Bus Assignment for " + driver.getName());
            System.out.println("Bus ID: " + bus.getBusId());
            System.out.println("Bus Location: " + depot.getDepotAddress());
            System.out.println("-----------------------\n");
        }
    }
    
    /**
     * displays information about routes assigned to buses
     */
    private void displayRouteInformation() {
        ArrayList<DriverAssignment> driverAssignments = dispatcher.getAllDriverAssignments();
        
        if (driverAssignments.isEmpty()) {
            System.out.println("There are currently no drivers assigned to any buses.");
            return;
        }
        
        for (DriverAssignment driverAssignment : driverAssignments) {
            Driver driver = driverAssignment.getDriver();
            Bus bus = driverAssignment.getBus();
            Route route = dispatcher.getRouteForBus(bus);
            
            if (route != null) {
                System.out.println("-----------------------");
                System.out.println("Route Assignment for " + driver.getName());
                System.out.println("Route ID: " + route.getRouteID());
                System.out.println("Route Name: " + route.getName());
                System.out.println("Number of Stops: " + route.getStops().size());
                System.out.println("-----------------------\n");
            } 
            else {
                System.out.println("A bus is not currently assigned to any route.");
            }
        }
    }
}
