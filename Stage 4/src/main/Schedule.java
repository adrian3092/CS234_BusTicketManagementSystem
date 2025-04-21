package main;

import depot.Depot;
import java.util.ArrayList;

/**
 * This class represents a schedule. It is associated with a route and has 
 * different scheduled times for the bus stops. A route can have multiple 
 * schedules. For example, a different schedule for each day. It has 5 instance 
 * variables.
 * name: The name of the schedule
 * route: The associated route of the schedule
 * depot: The depot that the route is associated with. This is the starting 
 * point for the buses on that route.
 * startTime: The time that the bus leaves the depot
 * departureTimes: A list of times that the bus leaves each scheduled bus stop 
 * @author George Candal
 */
public class Schedule {
    
    private String name;
    private Route route;
    private Depot depot;
    private double startTime;
    private ArrayList<Double> departureTimes;

    /**
     * Constructor for the class. Instantiates the array list of departure times
     * @param route The associated Route 
     * @param depot The associated Depot
     * @param startTime The time the bus leaves the depot
     */
    public Schedule(Route route, Depot depot, double startTime) {
        this.name = "";
        this.route = route;
        this.depot = depot;
        this.startTime = startTime;
        this.departureTimes = new ArrayList<>();
    }

    /**
     * Getter for the schedule's name
     * @return The name of the schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name The name of the schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the associated route
     * @return The route associated with the schedule
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Setter for the associated route
     * @param route The route associated with the schedule
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * Getter for the depot
     * @return The associated depot
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Setter for the depot
     * @param depot THe associated depot
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * Getter for the schedule start time
     * @return The time the bus leaves the depot
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time
     * @param startTime The time the bus leaves the depot
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the list of departure times
     * @return THe list of departure times
     */
    public ArrayList<Double> getDepartureTimes() {
        return departureTimes;
    }      
}
