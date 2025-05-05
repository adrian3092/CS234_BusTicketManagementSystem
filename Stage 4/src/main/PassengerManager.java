package main;

import java.util.ArrayList;
import java.util.List;

import csv.CSVHandler;

/**
 * Manages a list of passengers, providing methods to add, remove, update, and retrieve passengers.
 * 
 * @author Handsome Onojerame
 */
public class PassengerManager {
    private ArrayList<Passenger> passengers; // List of passengers
    private static final String PASSENGERS_CSV_FILE_PATH = "data/passengers.csv";


    /**
     * Constructor to initialize the passenger manager.
     */
    public PassengerManager() {
        this.passengers = new ArrayList<>();
        loadPassengersFromCSV();
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
        savePassengersToCSV();
    }

    /**
     * Removes a passenger from the list.
     * 
     * @param passenger The passenger to remove.
     */
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        savePassengersToCSV();
    }

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
            savePassengersToCSV();
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
    
    /**
     * load passengers from CSV file
     */
    public void loadPassengersFromCSV() {
        List<String[]> data = CSVHandler.readCSV(PASSENGERS_CSV_FILE_PATH);
        
        // clear existing passengers
        passengers.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("passengerID");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String passengerID = row[0];
            String name = row[1];
            String email = row[2];
            String phoneNumber = row[3];
            
            // create passenger with specific ID
            Passenger passenger = new Passenger(name, email, phoneNumber, passengerID);
            passengers.add(passenger);

        }
    }
    
    /**
     * save passengers to CSV file
     */
    public void savePassengersToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Passenger passenger : passengers) {
            String[] row = new String[]{
                passenger.getPassengerID(),
                passenger.getPassengerName(),
                passenger.getEmail(),
                passenger.getPhoneNumber()
            };
            data.add(row);
        }
        CSVHandler.writeCSV(PASSENGERS_CSV_FILE_PATH, data);
    }
    

}
