
import java.util.ArrayList;

/**
 *
 * @author George Candal
 */
public class Schedule {
    
    private String name;
    private Route route;
    private Depot depot;
    private double startTime;
    private ArrayList<Double> departureTimes;

    public Schedule(Route route, Depot depot, double startTime) {
        name = "";
        this.route = route;
        this.depot = depot;
        this.startTime = startTime;
        this.departureTimes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Double> getDepartureTimes() {
        return departureTimes;
    }  
    
}
