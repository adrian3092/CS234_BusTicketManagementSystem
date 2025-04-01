
package menu;

import java.util.Scanner;

import ticket.Ticket;
import ticket.TicketIssuer;
import main.Passenger;
import main.Schedule;
import main.ScheduleManager;

/**
 *
 * @author Adrian Zielinski
 */
public class TicketMenu {
    
    private Scanner in;
    private TicketIssuer ticketIssuer;
    private ScheduleManager scheduleManager;

    /**
     * default constructor
     * @param in, scanner
     * @param scheduleManager, the schedule manager containing available schedules
     */
    public TicketMenu(Scanner in, ScheduleManager scheduleManager) {
        this.in = in;
        ticketIssuer = new TicketIssuer();
        this.scheduleManager = scheduleManager;
    }

    /**
     * display the ticket booking menu
     */
    public void displayMenu() {
        // display available schedules
        System.out.println(scheduleManager.toString());
        
        // get user input for schedule selection
        System.out.print("Enter the number of the schedule you would like to purchase a ticket for: ");
        String input = in.nextLine();
        
        int scheduleChoice = Integer.parseInt(input);
        
        // validate the schedule choice
        if (scheduleChoice < 1 || scheduleChoice > scheduleManager.getSchedules().size()) {
            System.out.println("Invalid schedule number. Please try again.");
            return;
        }
        
        // get the selected schedule
        Schedule selectedSchedule = scheduleManager.getSchedules().get(scheduleChoice - 1);
        
        // get passenger information
        System.out.print("Please enter your first and last name: ");
        String name = in.nextLine();
        System.out.print("Please enter your email: ");
        String email = in.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        
        // create passenger object
        Passenger passenger = new Passenger(name, email, phoneNumber);
        
        // book the ticket
        Ticket ticket = ticketIssuer.bookTicket(passenger, selectedSchedule);
        
        // display ticket confirmation
        System.out.println("Your ticket has been booked successfully!");
        System.out.println("Ticket Number: " + ticket.getTicketNumber());
        System.out.println("Schedule: " + selectedSchedule.getName());
        System.out.println("Route: " + selectedSchedule.getRoute().getName());
        System.out.println("Start Time: " + formatTime(selectedSchedule.getStartTime()));
        System.out.println("Passenger: " + passenger.getPassengerName());
    }
    
    /**
     * formats a double time value to a string in the format HH:MM
     * @param time, the time as a double
     * @return the formatted time string
     */
    private String formatTime(double time) {
        int hours = (int) time;
        int minutes = (int) ((time - hours) * 60);
        
        return String.format("%02d:%02d", hours, minutes);
    }
}
