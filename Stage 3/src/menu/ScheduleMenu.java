package menu;

import depot.Depot;
import depot.DepotManager;
import java.util.Scanner;
import main.Route;
import main.RouteManager;
import main.Schedule;
import main.ScheduleManager;


/**
 *
 * @author Owner
 */
public class ScheduleMenu {
    //this menu needs to add a new schedule, remove an existing schedule and, manage an existing schedule
    private ScheduleManager scheduleManager;
    private RouteManager routeManager; // not used in this class, but needed for the constructor
    private int menuOption; 
    private Scanner in;

    public ScheduleMenu(Scanner in, ScheduleManager scheduleManager, RouteManager routeManager) {
        this.in = in;
        this.scheduleManager = scheduleManager;
        this.routeManager = routeManager; // not used in this class, but needed for the constructor
        this.menuOption = 0;
    }
    public void displayMenu() {
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Schedule Management Menu");
            System.out.println("1. Add a new schedule");
            System.out.println("2. Remove an existing schedule");
            System.out.println("3. Manage an existing schedule");
            System.out.println("4. Return to Main Menu");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    addSchedule();
                }
                case 2 -> {
                    removeSchedule();
                }
                case 3 -> {
                    manageSchedule();
                }
                case 4 -> {
                    System.out.println("Returning to Main Menu...");
                    menuOption = 0; // reset menuOption before returning
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void addSchedule() {
        System.out.println("Adding a new schedule...");

        System.out.print("Enter schedule name: ");
        String nameOfSchedule = in.next();
        System.out.print("Enter route ID: ");
        String routeID = in.next();
        Route route = RouteManager.getRouteById(routeID);
        boolean routeFound = false;

        while (!routeFound) {
            if (route != null) {
                routeFound = true;
                System.out.println("Route found: " + route.getName());
            } else {
                System.out.println("Route not found. Please try again.");
                System.out.print("Enter route ID: ");
                routeID = in.next();
                route = RouteManager.getRouteById(routeID);
            }
        }

        System.out.print("Enter depot ID: ");
        Integer depotID = in.nextInt();
        DepotManager depotManager = new DepotManager(); // create an instance of DepotManager - but this will cause issues. need to make depot methods static
        Depot depot = depotManager.findDepotById(depotID); // static method error

        System.out.print("Enter start time: ");
        double startTime = in.nextDouble();
    
        //create a new schedule object
        Schedule newSchedule = new Schedule(route, depot, startTime);

        //set the name of the schedule 
        newSchedule.setName(nameOfSchedule);

        //add the new schedule to the schedule manager
        scheduleManager.addSchedule(newSchedule);
        System.out.println("Schedule added successfully.");
    }

    private void removeSchedule() {
        // Add code to remove an existing schedule
        System.out.println("Enter the name of the schedule to remove:");
        String scheduleName = in.next();
        Schedule scheduleToRemove = scheduleManager.getScheduleByName(scheduleName);
        if (scheduleToRemove != null) {
            scheduleManager.removeSchedule(scheduleToRemove);
            System.out.println("Schedule removed successfully.");
        } else {
            System.out.println("Schedule not found.");
        }
        System.out.println("Removing an existing schedule...");
        // Example: scheduleManager.removeSchedule(scheduleId);
    }

    private void manageSchedule() {
        // Add code to manage an existing schedule
        System.out.println("Enter the name of the schedule to manage:");
        String scheduleName = in.next();
        Schedule scheduleToManage = scheduleManager.getScheduleByName(scheduleName);
        if (scheduleToManage != null) {
            // Implement management options here (e.g., update schedule details)
            Integer updateOption = 0;
            

            while (updateOption != 5) {
                System.out.println("Managing schedule: " + scheduleToManage.getName());
                System.out.println("What would you like to update?");
                System.out.println("1. Update route");
                System.out.println("2. Update depot");
                System.out.println("3. Update start time");
                System.out.println("4. Update schedule name");
                System.out.println("5. Return to Schedule Management Menu");
                updateOption = in.nextInt();
                in.nextLine(); // consume newline
                
                switch (updateOption) {
                    case 1 -> {
                        System.out.print("Enter the route ID: ");
                        String newRouteId = in.nextLine();
                        Route newRoute = RouteManager.getRouteById(newRouteId);
                        if (newRoute != null) {
                            scheduleToManage.setRoute(newRoute);
                            System.out.println("Route updated successfully.");
                        } else {
                            System.out.println("Route not found. Please try again.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter new depot ID: ");
                        Integer newDepotId = in.nextInt();
                        DepotManager depotManager = new DepotManager(); // create an instance of DepotManager - but this will cause issues. need to make depot methods static
                        Depot newDepot = depotManager.findDepotById(newDepotId); // static method error
                        if (newDepot != null) {
                            scheduleToManage.setDepot(newDepot);
                            System.out.println("Depot updated successfully.");
                        } else {
                            System.out.println("Depot not found. Please try again.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter new start time: ");
                        double newStartTime = in.nextDouble();
                        scheduleToManage.setStartTime(newStartTime);
                        System.out.println("Start time updated successfully.");
                    }
                    case 4 -> {
                        System.out.print("Enter new schedule name: ");
                        String newScheduleName = in.nextLine();
                        scheduleToManage.setName(newScheduleName);
                        System.out.println("Schedule name updated successfully.");
                    }
                    case 5 -> {
                        System.out.println("Returning to Schedule Management Menu...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
                

            }
        } else {
            System.out.println("Schedule not found.");
        }
    }

}
