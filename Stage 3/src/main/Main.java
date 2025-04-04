package main;

import java.util.Scanner;

import bus.*;
import depot.*;
import employees.Employee;
import expenses.*;
import login.*;
import menu.AdminMenu;
import menu.TicketMenu;
import payment.*;
import ticket.*;

/**
 *
 * @author George Candal
 */
public class Main {
    
    public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {

        //create login database
        LoginManager loginManager = new LoginManager(in);
        
        //create accounting database
        Accounting accounting = new Accounting(in);
        
        // create employees
        Employee employee1 = new Employee("John", "Doe", "Admin");
        Login login1 = new Login(employee1, loginManager);

        // create initial depot
        DepotManager depotManager = new DepotManager();
        Depot depot1 = new Depot("1862 Aviation Way, Los Angeles, CA 90071");

        // create initial route and bus stops, add bus stops to route
        Route route1 = new Route("Downtown");

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

        // create two initial buses
        BusManager busManager = new BusManager(depotManager);
        Bus bus1 = new Bus(2024, "Volvo", "7900", 3786, 40);
        Bus bus2 = new Bus(2013, "Volvo", "8900", 154965, 60);
        
        busManager.addBus(bus1);
        busManager.addBus(bus2);

        // Assign buses to the depot
        depot1.assignBus(bus1);
        depot1.assignBus(bus2);
        
        //create employee menu
        AdminMenu employeeMenu = new AdminMenu(in, busManager, depotManager, scheduleManager);
        
        //create expenses
        Salary employee1Salary = new Salary(accounting, 2000, employee1);
        FuelCost bus1Fuel = new FuelCost(accounting, 500, bus1);
        MaintenanceCost bus1Maintenance = new MaintenanceCost(accounting, 650, bus1);  
        
        // create ticket, passenger and payment
        Passenger passenger1 = new Passenger("Robert Smith", "robert.smith@gmail.com", "1112223333");
        Payment payment1 = new Payment("Credit Card", 3, passenger1, "1111222233334444", "12/26");
        PaymentManager paymentManager = new PaymentManager();
        paymentManager.addPayment(payment1);
        TicketIssuer ticketIssuer = new TicketIssuer();
        ticketIssuer.bookTicket(passenger1, schedule1);

        // logic for the main menu
        int menuOption = 0;
        
        while (menuOption != 4) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Main Menu");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Schedule");
            System.out.println("3. Employee Login");
            System.out.println("4. Quit");
            System.out.println("~~~~~~~~~~~~");
            
            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    // book ticket
                    in.nextLine(); // consume the leftover new line character
                    TicketMenu ticketMenu = new TicketMenu(in, scheduleManager);
                    ticketMenu.displayMenu();
                }
                case 2 -> {
                    // view schedule 
                    System.out.println(scheduleManager);
                }
                case 3 -> {
                    // employee login
                    String accessLevel = loginManager.checkCredentials();
                    if (accessLevel.equals("Admin")) {
                        System.out.println("Access Granted"); 
                        employeeMenu.displayMenu();
                    } else if (accessLevel.equals("Driver")) {
                        System.out.println("Access Granted");
                        //insert driver menu here
                    } 
                }
                case 4 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    in.close(); // close scanner when exiting
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
