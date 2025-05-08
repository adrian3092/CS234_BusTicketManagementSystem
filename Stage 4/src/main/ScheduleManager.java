
package main;

import java.util.ArrayList;
import java.util.List;

import csv.CSVHandler;
import depot.Depot;
import depot.DepotManager;

/**
 * this class manages all schedules
 * at the bus company
 * @author Adrian Zielinski
 */
public class ScheduleManager {

    private final ArrayList<Schedule> schedules;
    private static final String SCHEDULES_CSV_FILE_PATH = "data/schedules.csv";
    private static final String DEPARTURE_TIMES_CSV_FILE_PATH = "data/departure_times.csv";

    /**
     * default constructor
     */
    public ScheduleManager() {
        this.schedules = new ArrayList<>();
        loadSchedulesFromCSV();
    }

    /**
     * get the list of schedules
     * @return the list of schedules
     */
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * add schedule to list of schedules
     * @param schedule the schedule to be added
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        saveSchedulesToCSV();
    }

    /** 
     * @author Handsome Onojerame
     * remove schedule from list of schedules
     * @param schedule the schedule to be removed
     */
    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        saveSchedulesToCSV();
    }

    /**
     * @author Handsome Onojerame
     * get schedule by name
     * @param name the name of the schedule to be retrieved
     * @return the schedule at the specified name
     */
    public Schedule getScheduleByName(String name) {
        for (Schedule schedule : schedules) {
            if (schedule.getName().equals(name)) {
                return schedule;
            }
        }
        return null; // or throw an exception if not found
    }

    /**
     * Used for printing the object
     * @return A string with schedule information or a string stating "there are
     * no schedules available"
     */
    @Override
    public String toString() {
        if (schedules.isEmpty()) {
            return "There are no schedules available.";
        }
        
        String result = "Available Schedules:\n";
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            result += "#" + (i + 1) + " - " + schedule.getName() + "\n";
            
            // add route information
            if (schedule.getRoute() != null) {
                result += "Route: " + schedule.getRoute().getName() + "\n";
            } 
            else {
                result += "Route: Not assigned, ";
            }

            // add start time
            result += "Start Time: " + formatTime(schedule.getStartTime()) + "\n";
            
            // add departure times if available
            if (schedule.getDepartureTimes() != null && !schedule.getDepartureTimes().isEmpty()) {
                result += "Departure Times: ";
                for (int j = 0; j < schedule.getDepartureTimes().size(); j++) {
                    double time = schedule.getDepartureTimes().get(j);
                    result += formatTime(time);
                    if (j < schedule.getDepartureTimes().size() - 1) {
                        result += ", ";
                    }
                }
                result += "\n";
            } 
            else {
                result += "\n";
            }
            
            // add bus stops if available
            if (schedule.getRoute() != null && schedule.getRoute().getStops() != null && !schedule.getRoute().getStops().isEmpty()) {
                result += "Stops: \n";
                for (int counter = 0; counter < schedule.getRoute().getStops().size(); counter++) {
                    BusStop stop = schedule.getRoute().getStops().get(counter);
                    result += "  - " + stop.getName();
                    result += "\n";
                }
            }
        }
        return result;
    }
    
    /**
     * formats a double time value to a string in the format HH:MM
     * @param time the time as a double
     * @return the formatted time string
     */
    private String formatTime(double time) {
        int hours = (int) time;
        int minutes = (int) (((time - hours) * 100) + 0.5);
        
        return String.format("%02d:%02d", hours, minutes);
    }
    
    /**
     * load schedules from CSV file
     * @param routeManager the route manager to use for finding routes by ID
     * @param depotManager the depot manager to use for finding depots by ID
     */
    public void loadSchedulesFromCSV(RouteManager routeManager, DepotManager depotManager) {
        List<String[]> data = CSVHandler.readCSV(SCHEDULES_CSV_FILE_PATH);
        
        // clear existing schedules
        schedules.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("name");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String name = row[0];
            String routeID = row[1];
            int depotId = Integer.parseInt(row[2]);
            double startTime = Double.parseDouble(row[3]);
            
            // find the route and depot
            Route route = routeManager.getRouteById(routeID);
            Depot depot = depotManager.findDepotById(depotId);
            
            // create schedule if both route and depot exist
            if (route != null && depot != null) {
                Schedule schedule = new Schedule(route, depot, startTime);
                schedule.setName(name);
                schedules.add(schedule);
            }
        }
        
        // load departure times for each schedule
        loadDepartureTimesFromCSV();
    }
    
    /**
     * load schedules from CSV file
     */
    private void loadSchedulesFromCSV() {
        // This is just a placeholder - the actual loading will happen when 
        // loadSchedulesFromCSV(routeManager, depotManager) is called
    }
    
    /**
     * save schedules to CSV file
     */
    public void saveSchedulesToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Schedule schedule : schedules) {
            String routeID = schedule.getRoute() != null ? schedule.getRoute().getRouteID() : "";
            int depotId = schedule.getDepot() != null ? schedule.getDepot().getDepotId() : 0;
            
            String[] row = new String[]{
                schedule.getName(),
                routeID,
                String.valueOf(depotId),
                String.valueOf(schedule.getStartTime())
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(SCHEDULES_CSV_FILE_PATH, data);
        
        // save departure times for each schedule
        saveDepartureTimesToCSV();
    }
    
    /**
     * load departure times from CSV file
     */
    private void loadDepartureTimesFromCSV() {
        List<String[]> data = CSVHandler.readCSV(DEPARTURE_TIMES_CSV_FILE_PATH);
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("scheduleName");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String scheduleName = row[0];
            double departureTime = Double.parseDouble(row[1]);
            
            // find the schedule
            Schedule schedule = getScheduleByName(scheduleName);
            
            // add the departure time to the schedule
            if (schedule != null) {
                schedule.getDepartureTimes().add(departureTime);
            }
        }
    }
    
    /**
     * save departure times to CSV file
     */
    private void saveDepartureTimesToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Schedule schedule : schedules) {
            for (Double departureTime : schedule.getDepartureTimes()) {
                String[] row = new String[]{
                    schedule.getName(),
                    String.valueOf(departureTime)
                };
                data.add(row);
            }
        }
        CSVHandler.writeCSV(DEPARTURE_TIMES_CSV_FILE_PATH, data);
    }
}
