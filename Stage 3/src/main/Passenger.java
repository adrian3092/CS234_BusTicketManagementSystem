package main;
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

public class Passenger {
    // define attributes
    private String passengerID;
    private String name;
    private String email;
    private String phoneNumber;
    private TicketManager travelHistory;
    private ArrayList<Payment> paymentHistory;
    
    // instantiate constructor
    public Passenger(String name, String email, String phone) {
        // initialize variables
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.passengerID = IdGenerator.generatePassengerId();
        this.travelHistory = new TicketManager();
        this.paymentHistory = new ArrayList<>();
              
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

    public TicketManager getTravelHistory() {
        return travelHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void deletePassenger() {
        // Clearing the passenger's details (simulate deletion)
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
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
    
}
