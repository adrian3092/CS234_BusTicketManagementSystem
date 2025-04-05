
package ticket;

import java.util.ArrayList;

/**
 *
 * @author Adrian Zielinski
 */
public class TicketManager {
    
    private ArrayList<Ticket> allTickets;

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
     * @param ticket, the ticket to be added
     */
    public void addTicket(Ticket ticket) {

        allTickets.add(ticket);
        
    }

    /**
     * 
     */
    public void cancelTicket (Ticket ticket) {
        //
    }
}
