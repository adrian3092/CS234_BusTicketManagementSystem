package main;

import java.util.ArrayList;

/**
 * 
 * @author Adrian Zielinski
 */
public class ScheduleManager {

    private final ArrayList<Schedule> schedules;

    /**
     * default constructor
     */
    public ScheduleManager() {
        this.schedules = new ArrayList<>();
    }

    /**
     * add schedule to list of schedules
     * @param schedule, the schedule to be added
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    @Override
    public String toString() {
        if (schedules.isEmpty()) {
            return "No schedules available.";
        }
        
        String result = "Available Schedules:\n";
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            result += (i + 1) + ". ";
            // add schedule name if available
            if (schedule.getName() != null && !schedule.getName().isEmpty()) {
                result += "Name: " + schedule.getName() + "\n";
            }
            
            // add route information
            if (schedule.getRoute() != null) {
                result += "Route: " + schedule.getRoute().getName() + "\n";
            } else {
                result += "Route: Not assigned, ";
            }

            

            // add start time
            result += "Start Time: " + schedule.getStartTime() + "\n";
            
            // add departure times if available
            if (schedule.getDepartureTimes() != null && !schedule.getDepartureTimes().isEmpty()) {
                result += "Departure Times: " + schedule.getDepartureTimes().toString();
            }
            
            result += "\n";
        }
        
        return result;
    }
}
