
package menu;

import bus.*;
import depot.Depot;
import employees.Driver;
import java.util.ArrayList;
import java.util.Scanner;
import main.*;

/**
 *
 * @author Handsome Onojerame
 */
public class DriverMenu {
    private Scanner in;
    private Dispatcher dispatcher;
    private ScheduleManager scheduleManager;
    private BusManager busManager;

    public DriverMenu(Scanner in, Dispatcher dispatcher, ScheduleManager scheduleManager, BusManager busManager) {
        this.in = in;
        this.dispatcher = dispatcher;
        this.scheduleManager = scheduleManager;
        this.busManager = busManager;
    }

    public void displayMenu() {
        int selection = 0;
        while (selection != 4) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                 DRIVER MENU                    ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║ 1. View Schedule                               ║");
            System.out.println("║ 2. View Bus Information                        ║");
            System.out.println("║ 3. View Route Information                      ║");
            System.out.println("║ 4. Return to Main Menu                         ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-4): ");

            selection = in.nextInt();
            in.nextLine(); // consume the leftover new line character

            switch (selection) {
                case 1:
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║               VIEWING SCHEDULE                 ║");
                    System.out.println("╚════════════════════════════════════════════════╝");
                    System.out.println(scheduleManager);
                    break;
                case 2:
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║             VIEWING BUS INFORMATION            ║");
                    System.out.println("╚════════════════════════════════════════════════╝");
                    displayBusInformation();
                    break;
                case 3:
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║            VIEWING ROUTE INFORMATION           ║");
                    System.out.println("╚════════════════════════════════════════════════╝");
                    displayRouteInformation();
                    break;
                case 4:
                    System.out.println("Exiting Driver Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

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

            System.out.println("══════════════════════════════════════════════════");
            System.out.println("Bus Assignment for " + driver.getName());
            System.out.println("Bus ID: " + bus.getBusId());
            System.out.println("Bus Location: " + depot.getDepotAddress());
            System.out.println("══════════════════════════════════════════════════");
        }
    }

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
            } else {
                System.out.println("A bus is not currently assigned to any route.");
            }
        }
    }
}
