
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
 * This is the main file to run the program. Has a static scanner object that is
 * used by the other classes for user input
 * @author Adrian Zielinski
 */
public class Main {
    
    public static Scanner in = new Scanner(System.in);
    
    /**
     * This is the main method. It creates initial data for the program and 
     * displays the main menu.
     * @param args Possible command line arguments
     */
    public static void main(String[] args) {

        //create login database
        LoginManager loginManager = new LoginManager(in);

        //create Route database
        RouteManager routeManager = new RouteManager();
        
        //create employee database
        EmployeeManagement employeeManagement = new EmployeeManagement();

        //create passenger manager
        PassengerManager passengerManager = new PassengerManager();
        
        //create payment manager
        PaymentManager paymentManager = new PaymentManager();
        
        // create employees
        Admin admin1 = new Admin("John", "Doe", "Admin", "5286857246", 40500, employeeManagement, loginManager);
        Driver driver1 = new Driver("Lauren", "Smith", "Driver", "8518534605", 51800, employeeManagement, loginManager);
        employeeManagement.addDriver(driver1);
        
        Driver driver2 = new Driver("Robert", "Brown", "Driver", "4586994350", 55200, employeeManagement, loginManager);
        employeeManagement.addDriver(driver2);

        // create initial depots
        DepotManager depotManager = new DepotManager();
        Depot depot1 = new Depot("1862 Aviation Way, Los Angeles, CA 90071");
        depotManager.addDepot(depot1);
        Depot depot2 = new Depot("2155 Norman Street, Los Angeles, CA 90008");
        depotManager.addDepot(depot2);

        // create initial routes and bus stops, add bus stops to routes
        Route route1 = new Route("Downtown", routeManager);
        Route route2 = new Route("Midtown", routeManager);

        BusStop busStop1 = new BusStop("Main St & 6th St", 0.3);
        BusStop busStop2 = new BusStop("Broadway & 6th St", 0.2);
        BusStop busStop3 = new BusStop("Hill St & 6th St", 0.3);
        BusStop busStop4 = new BusStop("Olive St & 6th St", 0.2);
        BusStop busStop5 = new BusStop("Figueroa St & 6th St", 0.4);
        
        route1.addStop(busStop1);
        route1.addStop(busStop2);
        route1.addStop(busStop3);
        route1.addStop(busStop4);
        route1.addStop(busStop5);

        // create initial schedule
        ScheduleManager scheduleManager = new ScheduleManager();
        Schedule schedule1 = new Schedule(route1, depot1, 08.30);
        scheduleManager.addSchedule(schedule1);
        schedule1.setName("Morning");

        schedule1.getDepartureTimes().add(8.50);
        schedule1.getDepartureTimes().add(9.00);
        schedule1.getDepartureTimes().add(9.50);
        schedule1.getDepartureTimes().add(10.00);
        schedule1.getDepartureTimes().add(10.50);

        // create two initial buses, and add to list of all buses
        BusManager busManager = new BusManager(depotManager);
        Bus bus1 = new Bus(2024, "Volvo", "7900", 3786, 40);
        Bus bus2 = new Bus(2013, "Volvo", "8900", 154965, 60);
        
        busManager.addBus(bus1);
        busManager.addBus(bus2);

        // assign buses to the depot
        depot1.assignBus(bus1);
        depot1.assignBus(bus2);
        
        //create accounting database
        Accounting accounting = new Accounting(in, busManager, depotManager, employeeManagement);
        
        // create dispatcher
        Dispatcher dispatcher = new Dispatcher(busManager);
        dispatcher.assignBusToRoute(bus2, route1);
        dispatcher.assignDriverToBus(driver1, bus2);

        //create admin menu
        AdminMenu adminMenu = new AdminMenu(in, busManager, depotManager, 
                scheduleManager, accounting, employeeManagement, routeManager, 
                dispatcher, loginManager, paymentManager);

        // create driver menu
        DriverMenu driverMenu = new DriverMenu(in, dispatcher, scheduleManager, busManager);
        
        //create expenses
        Salary employee1Salary = new Salary(accounting, 2000, admin1);
        FuelCost bus1Fuel = new FuelCost(accounting, 500, bus1);
        MaintenanceCost bus1Maintenance = new MaintenanceCost(accounting, 650, bus1);  
        
        // create ticket, passenger and payment
        Passenger passenger1 = new Passenger("Robert Smith", "robert.smith@gmail.com", "2824782957");
        Payment payment1 = new Payment("Credit Card", 3, passenger1, "5105105105105100", "12/26", paymentManager);
        TicketIssuer ticketIssuer = new TicketIssuer();
        ticketIssuer.bookTicket(passenger1, schedule1);

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
                    // book ticket
                    in.nextLine(); // consume the leftover new line character
                    PassengerMenu passengerMenu = new PassengerMenu(in, passengerManager, scheduleManager, loginManager, paymentManager, ticketIssuer);
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
