
package menu;

import java.util.Scanner;

import ticket.*;
import main.*;
import payment.*;

/**
 * this class represents a menu responsible
 * for booking a ticket at the bus company
 * @author Adrian Zielinski
 */
public class TicketMenu {
    
    private Scanner in;
    private TicketIssuer ticketIssuer;
    private ScheduleManager scheduleManager;
    private PaymentManager paymentManager;
    private PassengerManager passengerManager;

    /**
     * default constructor
     * @param in scanner
     * @param scheduleManager the schedule manager containing available schedules
     * @param passsengerManager the PassengerManager database
     */
    public TicketMenu(Scanner in, ScheduleManager scheduleManager, PassengerManager passengerManager) {
        this.in = in;
        ticketIssuer = new TicketIssuer();
        this.scheduleManager = scheduleManager;
        this.paymentManager = new PaymentManager();
        this.passengerManager = passengerManager;
    }

    /**
     * display the ticket booking menu
     */
    public void displayMenu(String passengerID) {
        Schedule selectedSchedule = pickSchedule();
        
        Passenger passenger;
        if (passengerID.equals("guest")) {
            passenger = guestPassenger();
        } else {
            passenger = passengerManager.getPassengerById(passengerID);
        }
        
        // book the ticket
        Ticket ticket = ticketIssuer.bookTicket(passenger, selectedSchedule);
        
        // set ticket price 
        double ticketPrice = 3.00;
        
        // collect payment information
        System.out.println("\nPayment Information:");
        System.out.println("Ticket Price: $" + String.format("%.2f", ticketPrice));
        System.out.print("Please enter your credit card number: ");
        String cardNumber = in.nextLine();
        System.out.print("Please enter your credit card expiration date (MM/YY): ");
        String expirationDate = in.nextLine();
        
        // process payment
        Payment payment = paymentManager.processPayment(passenger, ticketPrice, cardNumber, expirationDate);
        
        // display ticket and payment confirmation
        System.out.println("\nYour ticket has been booked and payment processed successfully!");
        System.out.println("\nPayment Information:");
        System.out.println("Ticket Number: " + ticket.getTicketNumber());
        System.out.println("Schedule: " + selectedSchedule.getName());
        System.out.println("Route: " + selectedSchedule.getRoute().getName());
        System.out.println("Start Time: " + formatTime(selectedSchedule.getStartTime()));
        System.out.println("Passenger: " + passenger.getPassengerName());
        System.out.println("-----------------------");
        System.out.println("Payment Information:");
        System.out.println("Payment ID: " + payment.getPaymentId());
        System.out.println("Amount: $" + String.format("%.2f", payment.getPaymentAmount()));
        System.out.println("Credit Card: " + payment.getCardNumber());
    }
    
    public Schedule pickSchedule() {
        int scheduleChoice;
        
        do {
        // display available schedules
        System.out.println(scheduleManager.toString());
        
        // get user input for schedule selection
        System.out.print("Enter the number of the schedule you would like to purchase a ticket for: ");
        scheduleChoice = in.nextInt();
        
//        scheduleChoice = Integer.parseInt(input);
        
        // validate the schedule choice
        if (scheduleChoice < 1 || scheduleChoice > scheduleManager.getSchedules().size()) {
            System.out.println("Invalid schedule number. Please try again.");
        }
        
        } while ((scheduleChoice < 1 || scheduleChoice > scheduleManager.getSchedules().size()));
        
        // get the selected schedule
        Schedule selectedSchedule = scheduleManager.getSchedules().get(scheduleChoice - 1); 
        return selectedSchedule;
    }
    
    public Passenger guestPassenger() {
        // get passenger information
        System.out.print("Please enter your first and last name: ");
        String name = in.nextLine();
        System.out.print("Please enter your email: ");
        String email = in.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        
        // check is passenger already exists

        // create passenger object
        Passenger passenger = new Passenger(name, email, phoneNumber);
        return passenger;
    }
    
    /**
     * formats a double time value to a string in the format HH:MM
     * @param time the time as a double
     * @return the formatted time string
     */
    private String formatTime(double time) {
        int hours = (int) time;
        int minutes = (int) ((time - hours) * 60);
        
        return String.format("%02d:%02d", hours, minutes);
    }
}
