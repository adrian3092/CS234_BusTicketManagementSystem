
package menu;

import java.util.Scanner;

import bus.Bus;
import bus.BusManager;
import main.*;

/**
 * 
 * @author George Candal
 */
public class RouteMenu {
    
    private Scanner in;
    private RouteManager routeManager;
    private int menuOption;
    private Dispatcher dispatcher;
    private BusManager busManager;
    
    /**
     * 
     * @param in
     * @param routeManager
     * @param dispatcher
     * @param busManager
     */
    public RouteMenu(Scanner in, RouteManager routeManager, Dispatcher dispatcher, BusManager busManager) {
        this.in = in;
        menuOption = 0;
        this.routeManager = routeManager;
        this.dispatcher = dispatcher;
        this.busManager = busManager;
    }
    
    /**
     * display the main route menu
     */
    public void displayMenu() {
        
        while (menuOption != 5) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Route Management Menu");
            System.out.println("1. Add a new route");
            System.out.println("2. Manage an existing route");
            System.out.println("3. Display all Routes");
            System.out.println("4. Manage route assignment");
            System.out.println("5. Return to previous menu");

            menuOption = in.nextInt();

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
    
    public void addRoute() {
        System.out.println("What is the name of the route? ");
        in.nextLine(); // consume leftover newline character
        String name = in.nextLine();
        new Route(name, routeManager);
    }
    
    public void manageRoute() {
        System.out.println("Enter the ID of the route you want to manage: ");
        in.nextLine(); // consume leftover newline character
        String routeId = in.nextLine();
        Route route = routeManager.getRouteById(routeId);
        System.out.println(route);
        subMenu(route);
    }
    
    public void subMenu(Route route) {
        int option = 0;
        while (option != 5) {            
            System.out.println("1. Change Name");
            System.out.println("2. Add Bus Stop");
            System.out.println("3. Remove Bus Stop");
            System.out.println("4. Delete route");
            System.out.println("5. Previous Menu");

            option = in.nextInt();

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
//                    route.removeStop(busStop);
                }
                case 4 -> {
                    System.out.println("Route " + route.getName() + " has been deleted.");
                    routeManager.removeRoute(route);
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
    
    public void routeAssignment(Route route) {
        int option = 0;
        while (option != 5) {            
            System.out.println("1. Assign bus to route");
            System.out.println("2. Unassign bus from route");
            System.out.println("3. Assign driver to route");
            System.out.println("4. Unassign driver from route");
            System.out.println("5. Previous Menu");
            
            option = in.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.print("What is the ID of the bus you would like to assign? ");
                    int busId = in.nextInt();
                    
                    // find the bus by ID
                    Bus targetBus = busManager.findBusById(busId);
                    
                    if (targetBus == null) {
                        System.out.println("A bus with ID " + busId + " does not exist");
                        break;
                    }
                    
                    // assign the bus to the route
                    boolean success = dispatcher.assignBusToRoute(targetBus, route);
                    
                    if (success) {
                        System.out.println("Bus " + busId + " has been successfully assigned to route " + route.getName());
                    } 
                    else {
                        System.out.println("Failed to assign bus to route. The bus or route may already be assigned.");
                    }
                }
                case 2 -> {
                    // remove the bus from the route
                    boolean success = dispatcher.removeBusFromRoute(route);
                    
                    if (success) {
                        System.out.println("Bus has been successfully unassigned from route " + route.getName());
                    } 
                    else {
                        System.out.println("Failed to unassign bus from route. There may not be a bus assigned to this route.");
                    }
                }
                case 3 -> {
                    //
                }
                case 4 -> {
                    //
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
