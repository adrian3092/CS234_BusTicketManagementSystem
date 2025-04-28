package main;

import bus.*;
import depot.*;
import employees.*;
import expenses.*;
import java.util.Scanner;
import login.*;
import menu.*;
import payment.*;
import ticket.*;

/**
 * this is the main file to run the program. it has a static scanner object that is
 * used by the other classes for user input
 * @author Adrian Zielinski
 */
public class Main {
    
    public static Scanner in = new Scanner(System.in);
    
    /**
     * this is the main method. it loads all data from CSV files and displays the main menu
     * @param args possible command line arguments
     */
    public static void main(String[] args) {

        // create login database
        LoginManager loginManager = new LoginManager(in);

        // create Route database and load routes
        RouteManager routeManager = new RouteManager();
        
        // create employee database
        EmployeeManagement employeeManagement = new EmployeeManagement();

        // create passenger manager and load passengers
        PassengerManager passengerManager = new PassengerManager();
        
        // create payment manager and load payments
        PaymentManager paymentManager = new PaymentManager();
        paymentManager.loadPaymentsFromCSV(passengerManager);
        
        // load employees after login manager is initialized
        employeeManagement.loadEmployeesFromCSV(loginManager);
        
        // load logins after employees and passengers are loaded
        loginManager.loadLoginsFromCSV(employeeManagement, passengerManager);
    
        // create depot and bus managers and load data
        DepotManager depotManager = new DepotManager();
        BusManager busManager = new BusManager(depotManager);
        
        // load bus-depot assignments after both buses and depots are loaded
        depotManager.loadDepotBusAssignmentsFromCSV(busManager);
        
        // create schedule manager and load schedules
        ScheduleManager scheduleManager = new ScheduleManager();
        scheduleManager.loadSchedulesFromCSV(routeManager, depotManager);
        
        // create accounting database
        Accounting accounting = new Accounting(in, busManager, depotManager, employeeManagement);
        
        // create dispatcher
        Dispatcher dispatcher = new Dispatcher(busManager);
        
        // create ticket issuer and load tickets
        TicketIssuer ticketIssuer = new TicketIssuer();
        ticketIssuer.getTicketManager().loadTicketsFromCSV(passengerManager, scheduleManager);

        // create admin menu
        AdminMenu adminMenu = new AdminMenu(in, busManager, depotManager, 
                scheduleManager, accounting, employeeManagement, routeManager, 
                dispatcher, loginManager, paymentManager);

        // create driver menu
        DriverMenu driverMenu = new DriverMenu(in, dispatcher, scheduleManager, busManager);
        
        // reate passenger menu
        PassengerMenu passengerMenu = new PassengerMenu(in, passengerManager, scheduleManager, loginManager, paymentManager, ticketIssuer);

        // logic for the main menu
        int menuOption = 0;
        while (menuOption != 4) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              WELCOME TO BUS COMPANY            ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║                    MAIN MENU                   ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Book Ticket                                ║");
            System.out.println("║  2. View Schedule                              ║");
            System.out.println("║  3. Employee Login                             ║");
            System.out.println("║  4. Quit                                       ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-4): ");

            menuOption = in.nextInt();

            System.out.println("══════════════════════════════════════════════════"); 

            switch (menuOption) {
                case 1 -> {
                    in.nextLine(); // consume the leftover new line character
                    passengerMenu.displayMenu();
                }
                case 2 -> {
                    // view schedule
                    System.out.println(scheduleManager);
                }
                case 3 -> {
                    // employee login
                    System.out.println("Employee Login");
                    String accessLevel = loginManager.checkCredentials();
                    if (accessLevel.equals("Admin")) {
                        System.out.println("Access Granted: Admin Menu");
                        adminMenu.displayMenu();
                    } else if (accessLevel.equals("Driver")) {
                        System.out.println("Access Granted: Driver Menu");
                        driverMenu.displayMenu();
                    } else {
                        System.out.println("Access Denied: Invalid Credentials");
                    }
                }
                case 4 -> {
                    System.out.println("Thank you for using the Bus Company App!");
                    System.out.println("Saving data...");
                    
                    // save all data before exiting the program
                    busManager.saveBusesToCSV();
                    depotManager.saveDepotsToCSV();
                    routeManager.saveRoutesToCSV();
                    scheduleManager.saveSchedulesToCSV();
                    passengerManager.savePassengersToCSV();
                    paymentManager.savePaymentsToCSV();
                    employeeManagement.saveEmployeesToCSV();
                    loginManager.saveLoginsToCSV();
                    ticketIssuer.saveTicketsToCSV();
                    
                    System.out.println("Exiting the program. Goodbye!");
                    in.close(); // close scanner when exiting
                }
                default -> {
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                }
            }
            System.out.println();
        }
    }
}
