package main;

import java.util.ArrayList;

/**
 * Manages a list of passengers, providing methods to add, remove, update, and retrieve passengers.
 * 
 * @author Handsome Onojerame
 */
public class PassengerManager {
    private ArrayList<Passenger> passengers; // List of passengers

    /**
     * Constructor to initialize the passenger manager.
     */
    public PassengerManager() {
        this.passengers = new ArrayList<>();
    }

    /**
     * Retrieves the list of passengers.
     * 
     * @return ArrayList of passengers.
     */
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Adds a new passenger to the list.
     * 
     * @param passenger The passenger to add.
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * Removes a passenger from the list.
     * 
     * @param passenger The passenger to remove.
     */
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    /**
     * Retrieves the total number of passengers.
     * 
     * @return The number of passengers.
     */

    /**
     * Retrieves a passenger by their ID.
     * 
     * @param id The ID of the passenger.
     * @return The passenger with the given ID, or null if not found.
     */
    public Passenger getPassengerById(String id) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerID().equals(id)) {
                return passenger;
            }
        }
        return null; // Return null if passenger not found
    }   

    /**
     * Updates the details of a passenger.
     * 
     * @param id    The ID of the passenger to update.
     * @param name  The new name of the passenger.
     * @param email The new email of the passenger.
     * @param phone The new phone number of the passenger.
     */
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

    /**
     * Deletes a passenger by their ID.
     * 
     * @param id The ID of the passenger to delete.
     */
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
