
package main;

import java.util.ArrayList;

/**
 *
 * @author Handsome Onojerame
 */
public class PassengerManager {
    private ArrayList<Passenger> passengers; // list of passengers
    private int passengerCount = 0; // number of passengers


    public PassengerManager() {
        this.passengers = new ArrayList<>();
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        passengerCount++;
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        passengerCount--;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public Passenger getPassengerById(String id) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerID().equals(id)) {
                return passenger;
            }
        }
        return null; // or throw an exception if not found
    }

    public void updatePassenger(String id, String name, String email, String phone) {
        Passenger passenger = getPassengerById(id);
        if (passenger != null) {
            passenger.setName(name);
            passenger.setEmail(email);
            passenger.setPhoneNumber(phone);
        } else {
            System.out.println("Passenger not found.");
        }
    }

    public void deletePassenger(String id) {
        Passenger passenger = getPassengerById(id);
        if (passenger != null) {
            passenger.deletePassenger();
            removePassenger(passenger);
        } else {
            System.out.println("Passenger not found.");
        }
    }

    
}
