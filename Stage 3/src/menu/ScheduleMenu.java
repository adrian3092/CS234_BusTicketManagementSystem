package menu;

import depot.Depot;
import java.util.Scanner;
import main.Route;
import main.Schedule;
import main.ScheduleManager;


/**
 *
 * @author Owner
 */
public class ScheduleMenu {
    //this menu needs to add a new schedule, remove an existing schedule and, manage an existing schedule
    private ScheduleManager scheduleManager;
    private int menuOption; 
    private Scanner in;

    public ScheduleMenu(Scanner in, ScheduleManager scheduleManager) {
        this.in = in;
        this.scheduleManager = scheduleManager;
        menuOption = 0;
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
        System.out.print("Enter route name: ");
        String routeName = in.next();
        System.out.print("Enter depot name: ");
        String depotName = in.next();
        System.out.print("Enter start time: ");
        double startTime = in.nextDouble();

        //create new Route and Depot objects based on the input
        Route route = new Route(routeName);
        Depot depot = new Depot(depotName);

        //create a new schedule object
        Schedule newSchedule = new Schedule(route, depot, startTime);

        //set the name of the schedule 
        newSchedule.setName(nameOfSchedule);

        //add the new schedule to the schedule manager
        scheduleManager.addSchedule(newSchedule);
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

            System.out.println("Managing schedule: " + scheduleToManage.getName());
            System.out.println("What would you like to update?");
            System.out.println("1. Update route");
            System.out.println("2. Update depot");
            System.out.println("3. Update start time");
            System.out.println("4. Update schedule name");
            int updateOption = in.nextInt();
            in.nextLine(); // consume newline

            switch (updateOption) {
                case 1 -> {
                    System.out.print("Enter new route name: ");
                    String newRouteName = in.nextLine();
                    Route newRoute = new Route(newRouteName);
                    scheduleToManage.setRoute(newRoute);
                }
                case 2 -> {
                    System.out.print("Enter new depot name: ");
                    String newDepotName = in.nextLine();
                    Depot newDepot = new Depot(newDepotName);
                    scheduleToManage.setDepot(newDepot);
                }
                case 3 -> {
                    System.out.print("Enter new start time: ");
                    double newStartTime = in.nextDouble();
                    scheduleToManage.setStartTime(newStartTime);
                }
                case 4 -> {
                    System.out.print("Enter new schedule name: ");
                    String newScheduleName = in.nextLine();
                    scheduleToManage.setName(newScheduleName);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        } else {
            System.out.println("Schedule not found.");
        }
        System.out.println("Managing an existing schedule...");
        // Example: scheduleManager.manageSchedule(scheduleId);
    }

}
