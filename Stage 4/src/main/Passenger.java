package main;

import IdGenerator.IdGenerator;

/**
 * Represents a Passenger in the Bus Ticket Management System.
 * This class manages passenger details such as name, email, phone number, 
 * and a unique passenger ID.
 * 
 * Author: Handsome Onojerame
 */
public class Passenger {
    // Attributes
    private String passengerID; // Unique ID for the passenger
    private String name;        // Passenger's name
    private String email;       // Passenger's email address
    private String phoneNumber; // Passenger's phone number

    /**
     * Constructor to initialize a Passenger object.
     * 
     * @param name  The name of the passenger.
     * @param email The email address of the passenger.
     * @param phone The phone number of the passenger.
     */
    public Passenger(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.passengerID = IdGenerator.generatePassengerId(); // Generate unique ID
    }
    
    /**
     * constructor to initialize a Passenger object with a specific ID
     * used for loading passengers from CSV
     * 
     * @param name        The name of the passenger.
     * @param email       The email address of the passenger.
     * @param phone       The phone number of the passenger.
     * @param passengerID The specific passenger ID to use.
     */
    public Passenger(String name, String email, String phone, String passengerID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.passengerID = passengerID;
        
        // update the ID generator if this ID has a higher number
        if (passengerID.startsWith("P-")) {
            int idNumber = Integer.parseInt(passengerID.substring(2));
            IdGenerator.updatePassengerIdIfHigher(idNumber);
        }
    }

    /**
     * Gets the passenger's unique ID.
     * 
     * @return The passenger's ID.
     */
    public String getPassengerID() {
        return this.passengerID;
    }

    /**
     * Gets the passenger's name.
     * 
     * @return The passenger's name.
     */
    public String getPassengerName() {
        return this.name;
    }

    /**
     * Gets the passenger's phone number.
     * 
     * @return The passenger's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the passenger's email address.
     * 
     * @return The passenger's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the passenger's name.
     * 
     * @param name The new name for the passenger.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the passenger's email address.
     * 
     * @param passenger_email The new email address for the passenger.
     */
    public void setEmail(String passenger_email) {
        this.email = passenger_email;
    }

    /**
     * Sets the passenger's phone number.
     * 
     * @param phoneNumber The new phone number for the passenger.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Deletes the passenger's details by clearing all attributes.
     * Simulates the deletion of a passenger.
     */
    public void deletePassenger() {
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
        this.passengerID = null;
    }
}
