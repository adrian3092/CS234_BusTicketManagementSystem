
package menu;

import bus.Bus;
import bus.BusManager;
import java.util.Scanner;
import main.*;
import employees.*;

/**
 * This class is used for the route menu. It displays a menu and asks for
 * user input for managing routes. It has 6 instance variables.
 * in: The Scanner object used for user input
 * routeManager: The RouteManager object used as a database for routes
 * menuOption: Used to save the menu option input from the user
 * dispatcher: The Dispatcher object used as a database for driver assignments 
 * and route assignments
 * busManager: The BusManager object used as a database for buses
 * employeeManagement: The EmployeeManagement object used as a database for 
 * employees
 * @author George Candal
 * @author Adrian Zielinski
 */
public class RouteMenu {
    
    private Scanner in;
    private RouteManager routeManager;
    private int menuOption;
    private Dispatcher dispatcher;
    private BusManager busManager;
    private EmployeeManagement employeeManagement;
    
    /**
     * Constructor for the class
     * @param in The Scanner object used for user input
     * @param routeManager The RouteManager object used as a database for routes
     * @param dispatcher The Dispatcher object used as a database for 
     * driver assignments and route assignments
     * @param busManager The BusManager object used as a database for buses
     * @param employeeManagement The EmployeeManagement object used as a 
     * database for employees
     */
    public RouteMenu(Scanner in, RouteManager routeManager, Dispatcher dispatcher, BusManager busManager, EmployeeManagement employeeManagement) {
        this.in = in;
        menuOption = 0;
        this.routeManager = routeManager;
        this.dispatcher = dispatcher;
        this.busManager = busManager;
        this.employeeManagement = employeeManagement;
    }
    
    /**
     * display the main route menu
     */
    public void displayMenu() {
        
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              ROUTE MANAGEMENT MENU             ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Add a new route                            ║");
            System.out.println("║  2. Manage an existing route                   ║");
            System.out.println("║  3. Display all Routes                         ║");
            System.out.println("║  4. Manage route assignment                    ║");
            System.out.println("║  5. Return to previous menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-5): ");

            menuOption = in.nextInt();

            System.out.println("══════════════════════════════════════════════════");

            switch (menuOption) {
                case 1 -> {
                    addRoute();
                }
                case 2 -> {
                    manageRoute();
                }
                case 3 -> {
                    routeManager.displayRoutes();
                }
                case 4 -> {
                    System.out.print("What is the ID of the route? ");
                    in.nextLine(); // consume leftover newline character
                    String routeId = in.nextLine();

                    // find the route by id
                    Route targetRoute = routeManager.getRouteById(routeId);

                    if (targetRoute == null) {
                        System.out.println("A route with ID " + routeId + " does not exist");
                        break;
                    }
                    routeAssignment(targetRoute);
                }
                case 5 -> {
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
    
    /**
     * Creates a new route object
     */
    public void addRoute() {
        System.out.println("What is the name of the route? ");
        in.nextLine(); // consume leftover newline character
        String name = in.nextLine();
        new Route(name, routeManager);
    }
    
    /**
     * Asks for the route ID from the user and calls the route management menu
     * with options to modify that route
     */
    public void manageRoute() {
        System.out.println("Enter the ID of the route you want to manage: ");
        in.nextLine(); // consume leftover newline character
        String routeId = in.nextLine();
        Route route = routeManager.getRouteById(routeId);
        System.out.println(route);
        subMenu(route);
    }
    
    /**
     * A sub menu with different options to manage an existing route
     * @param route The route object to be managed
     */
    public void subMenu(Route route) {
        int option = 0;
        while (option != 6) {            
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                ROUTE MANAGEMENT MENU           ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Change Name                                ║");
            System.out.println("║  2. Add Bus Stop                               ║");
            System.out.println("║  3. Remove Bus Stop                            ║");
            System.out.println("║  4. Delete Route                               ║");
            System.out.println("║  5. Display Bus Stops                          ║");
            System.out.println("║  6. Previous Menu                              ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-6): ");

            option = in.nextInt();

            System.out.println("══════════════════════════════════════════════════");

            switch (option) {
                case 1 -> {
                    System.out.println("Change Route Name");
                    System.out.println("What is the new name of the route?");
                    in.nextLine(); // consume leftover newline character
                    String name = in.nextLine();
                    route.setName(name);
                    System.out.println("Route name has been updated");
                }
                case 2 -> {
                    System.out.println("Add Bus Stop");
                    System.out.println("What is the name of the Bus Stop?");
                    in.nextLine(); // consume leftover newline character
                    String name = in.nextLine();
                    System.out.println("What is the distance from the previous stop?");
                    double distance = in.nextDouble();
                    BusStop newStop = new BusStop(name, distance);
                    route.addStop(newStop);
                }
                case 3 -> {
                    System.out.println("Remove Bus Stop");
                    System.out.println("Current stops on route: ");
                    route.displayStops();
                    System.out.println("What is the name of the Bus Stop to remove?");
                    in.nextLine(); // consume leftover newline character
                    String name = in.nextLine();
                    
                    // find the bus stop with the given name
                    BusStop stopToRemove = null;
                    for (BusStop stop : route.getStops()) {
                        if (stop.getName().equalsIgnoreCase(name)) {
                            stopToRemove = stop;
                            break;
                        }
                    }
                    
                    if (stopToRemove != null) {
                        route.removeStop(stopToRemove);
                        System.out.println("Bus stop " + name 
                                + " has been removed from the route.");
                    } else {
                        System.out.println("Bus stop " + name 
                                + " was not found on this route.");
                    }
                }
                case 4 -> {
                    System.out.println("Route " + route.getName() 
                            + " has been deleted.");
                    routeManager.removeRoute(route);
                    return;
                }
                case 5 -> {
                    route.displayStops();
                }
                case 6 -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    
    /**
     * display menu for route assignment related functions
     * @author Adrian Zielinski
     * @param route the route a bus/driver should be assigned to/unassigned from
     */
    public void routeAssignment(Route route) {
        int option = 0;
        while (option != 3) {            
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              ROUTE ASSIGNMENT MENU             ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Assign bus and driver to route             ║");
            System.out.println("║  2. Unassign bus and driver from route         ║");
            System.out.println("║  3. Previous Menu                              ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-5): ");
            
            option = in.nextInt();

            System.out.println("══════════════════════════════════════════════════"); 

            switch (option) {
                case 1 -> {
                    System.out.print("What is the ID of the bus you would like to assign? ");
                    int busId = in.nextInt();
                    System.out.print("What is the ID of the driver you would like to assign? ");
                    in.nextLine(); // consume leftover newline character
                    String driverId = in.nextLine();

                    // find the bus by ID
                    Bus bus = busManager.findBusById(busId);
                    
                    if (bus == null) {
                        System.out.println("A bus with ID " + busId + " does not exist");
                        break;
                    }
                    
                    // assign the bus to the route
                    boolean busAssignment = dispatcher.assignBusToRoute(bus, route);

                    // find the driver by ID
                    Driver driver = employeeManagement.getDriverById(driverId);

                    if (driver == null) {
                        System.out.println("A driver with ID " + driverId + " does not exist");
                        break;
                    }

                    boolean driverAssignment = dispatcher.assignDriverToBus(driver, bus);
                    
                    if (busAssignment || driverAssignment) {
                        System.out.println("Bus " + busId + " and driver " + driverId + " have been successfully assigned to route " + route.getName());
                    } 
                    else {
                        System.out.println("Failed to assign bus or driver to the route.");
                    }
                }
                case 2 -> {
                    // get the bus assigned to this route
                    Bus bus = dispatcher.getBusForRoute(route);
                    
                    if (bus != null) {
                        // unassign the driver from the bus
                        dispatcher.removeDriverFromBus(bus);
                        
                        // remove the bus from the route
                        boolean busRemoval = dispatcher.removeBusFromRoute(route);
                        
                        if (busRemoval) {
                            System.out.println("Bus and driver have been successfully unassigned from route " + route.getName());
                            return;
                        } 
                        else {
                            System.out.println("Failed to unassign bus from route. There may not be a bus assigned to this route.");
                        }
                    } else {
                        System.out.println("There is no bus assigned to this route.");
                    }
                }
                case 3 -> {
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
