
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
     * get the list of schedules
     * @return the list of schedules
     */
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * add schedule to list of schedules
     * @param schedule, the schedule to be added
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    /** @author Handsome Onojerame
     * remove schedule from list of schedules
     * @param schedule, the schedule to be removed
     */
    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
    }

    /**@author Handsome Onojerame
     * get schedule by name
     * @param name, the name of the schedule to be retrieved
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

    @Override
    public String toString() {
        if (schedules.isEmpty()) {
            return "No schedules available.";
        }
        
        String result = "Available Schedules:\n";
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            result += "#" + (i + 1) + "\n";
            
            // add route information
            if (schedule.getRoute() != null) {
                result += "Route: " + schedule.getRoute().getName() + "\n";
            } else {
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
            }
            
            result += "\n";
        }
        
        return result;
    }
    
    /**
     * formats a double time value to a string in the format HH:MM
     * @param time, the time as a double
     * @return the formatted time string
     */
    private String formatTime(double time) {
        int hours = (int) time;
        int minutes = (int) ((time - hours) * 60);
        
        return String.format("%02d:%02d", hours, minutes);
    }
}
