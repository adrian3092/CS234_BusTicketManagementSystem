
package menu;

import java.util.Scanner;
import main.RouteManager;
import main.*;

/**
 * 
 * @author George Candal
 */
public class RouteMenu {
    
    private Scanner in;
    private RouteManager routeManager;
    private int menuOption;
    
    public RouteMenu(Scanner in, RouteManager routeManager) {
        this.in = in;
        menuOption = 0;
        this.routeManager = routeManager;        
    }
    
    /**
     * display the main accounting menu
     */
    public void displayMenu() {
        
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Route Management Menu");
            System.out.println("1. Add a new route");
            System.out.println("2. Manage an existing route");
            System.out.println("3. Display all Routes");
            System.out.println("4. Return to previous menu");

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
        String name = in.nextLine();
        new Route(name, routeManager);
    }
    
    public void manageRoute() {
        System.out.println("Enter the ID of the route you want to manage: ");
        String routeId = in.nextLine();
        Route route = routeManager.getRouteById(routeId);
        System.out.println(route);
        subMenu(route);
    }
    
    public void subMenu(Route route) {
        int option = 0;
        while (option != 4) {            
            System.out.println("1. Change Name");
            System.out.println("2. Add Bus Stop");
            System.out.println("3. Remove Bus Stop");
            System.out.println("4. Delete route");
            System.out.println("5. Previous Menu");

            menuOption = in.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Change Route Name");
                    System.out.println("What is the new name of the route?");
                    String name = in.nextLine();
                    route.setName(name);
                    System.out.println("Route name has been updated");
                }
                case 2 -> {
                    System.out.println("Add Bus Stop");
                    System.out.println("What is the name of the Bus Stop?");
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
}
    
    

