import java.util.ArrayList;

/**
 * Represents a passenger in the bus ticket management system.
 */
public class Passenger {
    private String passengerID; // Unique ID for the passenger
    private String name; // Passenger's name
    private String email; // Passenger's email
    private String phoneNumber; // Passenger's phone number
    private ArrayList travelHistory; // List of tickets booked by the passenger
    private Ticket ticket; // Current ticket of the passenger
    private ArrayList<Payment> paymentHistory; // List of payments made by the passenger

    /**
     * Constructor to initialize a passenger with name, email, and phone number.
     * @param name Passenger's name
     * @param email Passenger's email
     * @param phoneNumber Passenger's phone number
     */
    public Passenger(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passengerID = generatePassengerID();
        this.travelHistory = new ArrayList();
    }

    /**
     * @return Passenger's unique ID
     */
    public String getPassengerID() {
        return passengerID;
    }

    /**
     * @return Passenger's name
     */
    public String getPassengerName() {
        return name;
    }

    /**
     * @return Passenger's email
     */
    public String getPassengerEmail() {
        return email;
    }

    /**
     * @return Passenger's phone number
     */
    public String getPassengerPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return List of tickets booked by the passenger
     */
    public ArrayList getTravelHistory() {
        return travelHistory;
    }

    /**
     * Generates a unique passenger ID.
     */
    public String generatePassengerID() {
        // Generate a unique passenger ID
        this.passengerID = "P" + (int)(Math.random() * 10000);
        return this.passengerID;
    }

    /**
     * Sets the passenger's name.
     * @param name New name of the passenger
     */
    public void setPassengerName(String name) {
        this.name = name;
    }

    /**
     * Sets the passenger's email.
     * @param email New email of the passenger
     */
    public void setPassengerEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the passenger's phone number.
     * @param phoneNumber New phone number of the passenger
     */
    public void setPassengerPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Deletes the passenger's information.
     */
    public void deletePassenger() {
        this.passengerID = null;
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
        this.travelHistory = null;
    }

    /**
     * Books a ticket for the passenger.
     * @param schedule The schedule for which the ticket is booked
     * @return The booked ticket
     */
    public Ticket bookTicket(Schedule schedule) {
        Ticket ticket = new Ticket(schedule, "Booked", this, generateTicketNumber()); // Suggest generateTicketNumber() method
        travelHistory.add(ticket);
        return ticket;
    }

    /**
     * Cancels a ticket for the passenger.
     * @param ticket The ticket to be canceled
     */
    public void cancelTicket(Ticket ticket) {
        this.ticket.setTicketStatus(this.ticket.getTicketNumber(), "Cancelled"); // Suggest creating a cancelTicket method in Ticket class
        travelHistory.remove(ticket);
    }

    /**
     * Processes a payment for the passenger.
     * @param payment The payment to be processed
     */
    public void makePayment(Payment payment) {
        payment.processPayment();
        paymentHistory.add(payment);
    }

    /**
     * Displays the payment history of the passenger.
     */
    public void viewPaymentHistory() {
        for (Payment payment : paymentHistory) {
            System.out.println(payment);
        }
    }
}
