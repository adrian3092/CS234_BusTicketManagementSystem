package main;
<<<<<<< HEAD
/**
 *
 * @author Owner
 */
import java.util.ArrayList;
import IdGenerator.IdGenerator;
import payment.Payment;
import ticket.TicketManager;
import ticket.TicketIssuer;
import ticket.Ticket;
=======

import IdGenerator.IdGenerator;
>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0

public class Passenger {
    // define attributes
    private String passengerID;
    private String name;
    private String email;
    private String phoneNumber;
<<<<<<< HEAD
    private TicketManager travelHistory;
    private ArrayList<Payment> paymentHistory;
=======
>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0
    
    // instantiate constructor
    public Passenger(String name, String email, String phone) {
        // initialize variables
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.passengerID = IdGenerator.generatePassengerId();
<<<<<<< HEAD
        this.travelHistory = new TicketManager();
        this.paymentHistory = new ArrayList<>();
              
=======

>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0
    }
    
    public String getPassengerID() {
        return this.passengerID;
    }
    
    public String getPassengerName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

<<<<<<< HEAD
    public TicketManager getTravelHistory() {
        return travelHistory;
=======
    public String getEmail() {
        return email;
>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public void setEmail(String email) {
        this.email = email;
=======
    public void setEmail(String passenger_email) {
        this.email = passenger_email;
>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void deletePassenger() {
        // Clearing the passenger's details (simulate deletion)
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
<<<<<<< HEAD
        this.travelHistory = null;
        this.paymentHistory = null;
        this.passengerID = null;
        System.out.println("Passenger data deleted.");
    }
    
    public void bookTicket(Schedule schedule) {
        TicketIssuer newTicket = new TicketIssuer();
        Ticket newBooking = newTicket.bookTicket(this, schedule);
        this.travelHistory.addSchedule(newBooking);
        makePayment();
        
        System.out.println("Ticket booked for Passenger ID: " + this.passengerID);
    }
    
=======
        this.passengerID = null;
    }
    
>>>>>>> 323d91560e88d17f535c0278d27cb5e0af746ab0
}
