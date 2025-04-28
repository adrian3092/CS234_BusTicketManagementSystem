
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
     * constructor with status parameter for loading from CSV
     * @param schedule the schedule associated with the ticket
     * @param passenger the passenger associated with the ticket
     * @param ticketNumber the ticket number of the ticket
     * @param status the status of the ticket
     */
    public Ticket(Schedule schedule, Passenger passenger, int ticketNumber, String status) {
        this.schedule = schedule;
        this.passenger = passenger;
        this.ticketNumber = ticketNumber;
        this.status = status;
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
     * @return the passenger associated with the ticket
     */
    public Passenger getPassenger() {
        return passenger;
    }
    
    /**
     * get the status of the ticket
     * @return the status of the ticket
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * set the status of the ticket
     * @param status the new status of the ticket
     */
    public void setStatus(String status) {
        this.status = status;
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
