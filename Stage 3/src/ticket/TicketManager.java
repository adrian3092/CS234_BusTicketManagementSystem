
package ticket;

import java.util.ArrayList;

/**
 * this class manages all tickets
 * at the bus company
 * @author Adrian Zielinski
 */
public class TicketManager {
    
    private ArrayList<Ticket> allTickets;

    /**
     * default constructor
     * @param allTickets 
     */
    public TicketManager() {
        allTickets = new ArrayList<>();
    }

    /**
     * get the list of all tickets
     * @return the list of tickets
     */
    public ArrayList<Ticket> getTickets() {
        return allTickets;
    }

    /**
     * add ticket to list of tickets
     * @param ticket the ticket to be added
     */
    public void addTicket(Ticket ticket) {
        allTickets.add(ticket);
    }

    /**
     * cancel an existing ticket
     * @param ticket the ticket to be cancelled
     */
    public void cancelTicket (Ticket ticket) {
        //
    }
}
