
package ticket;

import main.Passenger;
import main.Schedule;

/**
 * this class represents a ticket
 * at the bus company
 * @author Adrian Zielinski
 */
public class Ticket {
    
    private Schedule schedule;
    private String status;
    private Passenger passenger;
    private int ticketNumber;

    /**
     * default constructor
     * @param schedule the schedule associated with the ticket
     * @param passenger the passenger associated with the ticket
     * @param ticketNumber the ticket number of the ticket
     */
    public Ticket(Schedule schedule, Passenger passenger, int ticketNumber) {
        this.schedule = schedule;
        this.passenger = passenger;
        this.ticketNumber = ticketNumber;
        status = "Active";
    }

    /**
     * get the ticket number
     * @return the ticket number
     */
    public int getTicketNumber() {
        return ticketNumber;
    }
    
    /**
     * get the schedule associated with the ticket
     * @return the schedule associated with the ticket
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * get the passenger associated with the ticket
     * @return
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Used for printing a ticket
     * @return A String consisting of a ticket number, the passenger, the 
     * schedule, and the status of the ticket.
     */
    @Override
    public String toString() {       
        return  "\nTicket Number: " + ticketNumber +
                "\nPassenger Name: " + passenger.getPassengerName() +
                "\nSchedule: " + schedule.getName() +
                "\nStatus: " + status;
    }
}
