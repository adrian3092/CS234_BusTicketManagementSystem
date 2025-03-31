
package ticket;

import main.Passenger;
import main.Schedule;

/**
 *
 * @author Adrian Zielinski
 */
public class TicketIssuer {
    
    private static int lastTicketNumber = 1000;
    
    /**
     * books a ticket for a passenger for a specific schedule
     * @param passenger, the passenger booking the ticket
     * @param schedule, the schedule for which the ticket is being booked
     * @return the newly created ticket
     */
    public Ticket bookTicket(Passenger passenger, Schedule schedule) {

        // generate a unique ticket number
        int ticketNumber = generateTicketNumber();
        
        // create a new ticket
        Ticket ticket = new Ticket(schedule, passenger, ticketNumber);
        
        System.out.println("Ticket #" + ticketNumber + " booked successfully for " + passenger.getPassengerName());
        
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
}
