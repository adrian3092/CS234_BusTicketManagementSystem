
package ticket;

import main.Passenger;
import main.Schedule;

/**
 * this class is responsible for issuing
 * tickets at the bus company
 * @author Adrian Zielinski
 */
public class TicketIssuer {
    
    private static int lastTicketNumber = 1000;
    private TicketManager ticketManager;
    
    /**
     * default constructor
     * initializes the ticket manager
     */
    public TicketIssuer() {
        this.ticketManager = new TicketManager();
    }
    
    /**
     * Get the last ticket number used
     * @return the last ticket number
     */
    public static int getLastTicketNumber() {
        return lastTicketNumber;
    }
    
    /**
     * Set the last ticket number
     * @param ticketNumber the new last ticket number
     */
    public static void setLastTicketNumber(int ticketNumber) {
        lastTicketNumber = ticketNumber;
    }
    
    /**
     * books a ticket for a passenger for a specific schedule
     * @param passenger the passenger booking the ticket
     * @param schedule the schedule for which the ticket is being booked
     * @return the newly created ticket
     */
    public Ticket bookTicket(Passenger passenger, Schedule schedule) {

        // generate a unique ticket number
        int ticketNumber = generateTicketNumber();
        
        // create a new ticket
        Ticket ticket = new Ticket(schedule, passenger, ticketNumber);

        ticketManager.addTicket(ticket);

        return ticket;
    }
    
    /**
     * generates a unique ticket number
     * @return a unique ticket number
     */
    private int generateTicketNumber() {

        lastTicketNumber++;
        return lastTicketNumber;

    }

    /**
     * Get the ticket manager
     * @return the ticket manager
     */
    public TicketManager getTicketManager() {
        return ticketManager;
    }
    
    /**
     * Save tickets to CSV file
     */
    public void saveTicketsToCSV() {
        ticketManager.saveTicketsToCSV();
    }
}
