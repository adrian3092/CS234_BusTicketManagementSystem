package main;

import IdGenerator.IdGenerator;

public class Passenger {
    // define attributes
    private String passengerID;
    private String name;
    private String email;
    private String phoneNumber;
    
    // instantiate constructor
    public Passenger(String name, String email, String phone) {
        // initialize variables
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.passengerID = IdGenerator.generatePassengerId();

    }
    
    public String getPassengerID() {
        return this.passengerID;
    }
    
    public String getPassengerName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String passenger_email) {
        this.email = passenger_email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void deletePassenger() {
        // Clearing the passenger's details (simulate deletion)
        this.name = null;
        this.email = null;
        this.phoneNumber = null;
        this.passengerID = null;
    }
    
}
