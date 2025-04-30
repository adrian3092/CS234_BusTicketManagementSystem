
package ticket;

import java.util.ArrayList;
import java.util.List;

import csv.CSVHandler;
import main.Passenger;
import main.PassengerManager;
import main.Schedule;
import main.ScheduleManager;

/**
 * this class manages all tickets
 * at the bus company
 * @author Adrian Zielinski
 */
public class TicketManager {
    
    private ArrayList<Ticket> allTickets;
    private static final String TICKETS_CSV_FILE_PATH = "data/tickets.csv";

    /**
     * default constructor
     */
    public TicketManager() {
        allTickets = new ArrayList<>();
        loadTicketsFromCSV();
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
        saveTicketsToCSV();
    }

    /**
     * cancel an existing ticket
     * @param ticket the ticket to be cancelled
     */
    public void cancelTicket(Ticket ticket) {
        ticket.setStatus("Cancelled");
        saveTicketsToCSV();
    }
    
    /**
     * load tickets from CSV file
     * @param passengerManager the passenger manager to use for finding passengers by ID
     * @param scheduleManager the schedule manager to use for finding schedules by name
     */
    public void loadTicketsFromCSV(PassengerManager passengerManager, ScheduleManager scheduleManager) {
        List<String[]> data = CSVHandler.readCSV(TICKETS_CSV_FILE_PATH);
        
        // clear existing tickets
        allTickets.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("ticketNumber");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int ticketNumber = Integer.parseInt(row[0]);
            String scheduleName = row[1];
            String passengerID = row[2];
            String status = row[3];
            
            // find the passenger and schedule
            Passenger passenger = passengerManager.getPassengerById(passengerID);
            Schedule schedule = scheduleManager.getScheduleByName(scheduleName);
            
            // create ticket if passenger and schedule exist
            if (passenger != null && schedule != null) {
                Ticket ticket = new Ticket(schedule, passenger, ticketNumber, status);
                allTickets.add(ticket);
                
                // update the last ticket number in TicketIssuer if needed
                if (ticketNumber > TicketIssuer.getLastTicketNumber()) {
                    TicketIssuer.setLastTicketNumber(ticketNumber);
                }
            }
        }
    }
    
    /**
     * load tickets from CSV file
     */
    private void loadTicketsFromCSV() {
        //
    }
    
    /**
     * save tickets to CSV file
     */
    public void saveTicketsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Ticket ticket : allTickets) {
            String scheduleName = ticket.getSchedule() != null ? ticket.getSchedule().getName() : "";
            String passengerID = ticket.getPassenger() != null ? ticket.getPassenger().getPassengerID() : "";
            
            String[] row = new String[]{
                String.valueOf(ticket.getTicketNumber()),
                scheduleName,
                passengerID,
                ticket.getStatus()
            };
            data.add(row);
        }
        CSVHandler.writeCSV(TICKETS_CSV_FILE_PATH, data);
    }
}
