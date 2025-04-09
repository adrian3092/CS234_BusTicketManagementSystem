package menu;

import depot.Depot;
import depot.DepotManager;
import java.util.Scanner;
import main.Route;
import main.RouteManager;
import main.Schedule;
import main.ScheduleManager;

/**
 * ScheduleMenu class provides options to manage schedules, including adding,
 * removing, and updating schedules.
 */
public class ScheduleMenu {
    private ScheduleManager scheduleManager;
    private RouteManager routeManager;
    private DepotManager depotManager;
    private int menuOption;
    private Scanner in;

    // Constructor to initialize the menu with required managers and scanner
    public ScheduleMenu(Scanner in, ScheduleManager scheduleManager, RouteManager routeManager, DepotManager depotManager) {
        this.in = in;
        this.scheduleManager = scheduleManager;
        this.routeManager = routeManager;
        this.depotManager = depotManager;
        this.menuOption = 0;
    }

    // Displays the main menu and handles user input
    public void displayMenu() {
        while (menuOption != 5) {
            System.out.println("========================================");
            System.out.println("|         Schedule Management Menu     |");
            System.out.println("========================================");
            System.out.println("|  1. Add a new schedule               |");
            System.out.println("|  2. Remove an existing schedule      |");
            System.out.println("|  3. Manage an existing schedule      |");
            System.out.println("|  4. Display all schedules            |");
            System.out.println("|  5. Return to Main Menu              |");
            System.out.println("========================================");
            System.out.print("Please select an option (1-5): ");
            System.out.println();

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    System.out.println("========================================");
                    System.out.println("|          Add a New Schedule          |");
                    System.out.println("========================================");
                    addSchedule();
                }
                case 2 -> {
                    System.out.println("========================================");
                    System.out.println("|       Remove an Existing Schedule    |");
                    System.out.println("========================================");
                    removeSchedule();
                }
                case 3 -> {
                    System.out.println("========================================");
                    System.out.println("|      Manage an Existing Schedule     |");
                    System.out.println("========================================");
                    manageSchedule();
                }
                case 4 -> {
                    System.out.println("==========================================");
                    System.out.println("|         Displaying All Schedules        |");
                    System.out.println("==========================================");
                    System.out.println(scheduleManager);
                }
                case 5 -> {
                    System.out.println("========================================");
                    System.out.println("|        Returning to Main Menu        |");
                    System.out.println("========================================");
                    return;
                }
                default -> {
                    System.out.println("========================================");
                    System.out.println("|          Invalid Option!             |");
                    System.out.println("|       Please try again (1-5).        |");
                    System.out.println("========================================");
                }
            }
        }
    }

    // Adds a new schedule
    private void addSchedule() {

        System.out.print("Enter schedule name: ");
        String nameOfSchedule = in.next();

        System.out.print("Enter route ID: ");
        String routeID = in.next();
        Route route = routeManager.getRouteById(routeID);

        // Validate route
        while (route == null) {
            System.out.println("Route not found. Please try again.");
            System.out.print("Enter route ID: ");
            routeID = in.next();
            route = routeManager.getRouteById(routeID);
        }
        System.out.println("Route found: " + route.getName());

        System.out.print("Enter depot ID: ");
        Integer depotId = validateIntegerInput();

        Depot depot = depotManager.findDepotById(depotId);

        // Validate depot
        while (depot == null) {
            System.out.println("Depot not found. Please try again.");
            System.out.print("Enter depot ID: ");
            depotId = validateIntegerInput();
            depot = depotManager.findDepotById(depotId);
        }
        System.out.println("Depot found: " + depot.getDepotAddress());

        System.out.print("Enter start time (HH.MM): ");
        double startTime = validateStartTimeInput();

        // Create and add the new schedule
        Schedule newSchedule = new Schedule(route, depot, startTime);
        newSchedule.setName(nameOfSchedule);
        scheduleManager.addSchedule(newSchedule);

        System.out.println("Schedule added successfully.");
    }

    // Removes an existing schedule
    private void removeSchedule() {
        System.out.println("Enter the name of the schedule to remove:");
        String scheduleName = in.next();
        Schedule scheduleToRemove = scheduleManager.getScheduleByName(scheduleName);

        if (scheduleToRemove != null) {
            scheduleManager.removeSchedule(scheduleToRemove);
            System.out.println("Schedule removed successfully.");
        } else {
            System.out.println("Schedule not found.");
        }
    }

    // Manages an existing schedule
    private void manageSchedule() {
        System.out.println("Enter the name of the schedule to manage below (Case sensitive).");
        System.out.print("Schedule name: ");
        String scheduleName = in.next();
        Schedule scheduleToManage = scheduleManager.getScheduleByName(scheduleName);

        if (scheduleToManage != null) {
            Integer updateOption = 0;

            while (updateOption != 5) {
                System.out.println("========================================");
                System.out.println("|         Manage Schedule Menu         |");
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║              MANAGE SCHEDULE MENU              ║");
                System.out.println("╠════════════════════════════════════════════════╣");
                System.out.println("  ║  Managing: "+ scheduleToManage.getName() +"    ║");
                System.out.println("╠════════════════════════════════════════════════╣");
                System.out.println("║  1. Update route                               ║");
                System.out.println("║  2. Update depot                               ║");
                System.out.println("║  3. Update start time                          ║");
                System.out.println("║  4. Update schedule name                       ║");
                System.out.println("║  5. Return to Schedule Management              ║");
                System.out.println("╚════════════════════════════════════════════════╝");
                System.out.print(" Please select an option (1-5): ");

                updateOption = in.nextInt();

                System.out.println("========================================");
                in.nextLine(); // Consume newline

                switch (updateOption) {
                    case 1 -> updateRoute(scheduleToManage);
                    case 2 -> updateDepot(scheduleToManage);
                    case 3 -> updateStartTime(scheduleToManage);
                    case 4 -> updateScheduleName(scheduleToManage);
                    case 5 -> System.out.println("Returning to Schedule Management Menu...");
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Oops! Schedule not found.");
            System.out.println("Would you like to create a new schedule? (yes/no)");
            String response = in.next();
            if (response.equalsIgnoreCase("yes")) {
                addSchedule();
            } else {
                System.out.println("Returning to Schedule Management Menu...");
            }
        }
    }

    // Helper method to update the route of a schedule
    private void updateRoute(Schedule schedule) {
        System.out.print("Enter the route ID: ");
        String newRouteId = in.nextLine();
        Route newRoute = routeManager.getRouteById(newRouteId);

        if (newRoute != null) {
            schedule.setRoute(newRoute);
            System.out.println("Route updated successfully.");
        } else {
            System.out.println("Route not found. Please try again.");
        }
    }

    // Helper method to update the depot of a schedule
    private void updateDepot(Schedule schedule) {
        System.out.print("Enter new depot ID: ");
        Integer newDepotId = validateIntegerInput();
        Depot newDepot = depotManager.findDepotById(newDepotId);

        if (newDepot != null) {
            schedule.setDepot(newDepot);
            System.out.println("Depot updated successfully.");
        } else {
            System.out.println("Depot not found. Please try again.");
        }
    }

    private double validateStartTimeInput() {
        double startTime = in.nextDouble();
        while (startTime < 0 || startTime > 24) {
            System.out.println("Invalid time. Please enter a valid time between 0 and 24:");
            startTime = in.nextDouble();
        }
        return startTime;
    }
    // Helper method to update the start time of a schedule
    private void updateStartTime(Schedule schedule) {
        System.out.print("Enter new start time (HH.MM): ");
        double newStartTime = in.nextDouble();

        while (newStartTime < 0 || newStartTime > 24) {
            System.out.println("Invalid time. Please enter a valid time between 0 and 24:");
            newStartTime = validateStartTimeInput();
        }
        schedule.setStartTime(newStartTime);
        System.out.println("Start time updated successfully.");
    }

    // Helper method to update the name of a schedule
    private void updateScheduleName(Schedule schedule) {
        System.out.print("Enter new schedule name: ");
        String newScheduleName = in.nextLine();
        schedule.setName(newScheduleName);
        System.out.println("Schedule name updated successfully.");
    }

    // Validates integer input from the user
    private Integer validateIntegerInput() {
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer:");
            in.next(); // Clear invalid input
        }
        return in.nextInt();
    }
}
