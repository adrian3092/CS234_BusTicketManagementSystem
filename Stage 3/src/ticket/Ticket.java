package ticket;

import main.Schedule;
import main.Passenger;

/**
 *
 * @author Adrian Zielinski
 */
public class Ticket {
    
    private Schedule schedule;
    private String status;
    private Passenger passenger;
    private int ticketNumber;

    public Ticket(Schedule schedule, Passenger passenger, int ticketNumber) {
        this.schedule = schedule;
        this.passenger = passenger;
        this.ticketNumber = ticketNumber;
        status = "Active";
    }
    
    /**
     * 
     * @param ticketNumber
     * @return 
     */
    public String getTicketDetails(int ticketNumber) {
        //
        return null;
        //
    }
    
    /**
     * 
     * @param ticketNumber
     * @param status 
     */
    public void setTicketStatus(int ticketNumber, String status) {
        //
    }
    
    /**
     * 
     * @return 
     */
    public String getPassengerInfo() {
        //
        return null;
        //
    }
    
}
