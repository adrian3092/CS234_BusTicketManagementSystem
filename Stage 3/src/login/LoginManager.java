package login;

import login.Login;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class manages the login system. It has 4 instance variables.
 * in: Scanner
 * logins: the list of logins for the system
 * enteredUsername: The username entered by the user. Used to check against the 
 * usernames in the system
 * enteredPassword: The password entered by the user. Used to check if it 
 * matches the password for that user in the system.
 * @author George Candal
 */
public class LoginManager {
    
    private Scanner in;
    private ArrayList<Login> logins;
    private String enteredUsername;
    private String enteredPassword;

    /**
     * Constructor for the class. Initializes the database of logins.
     * @param in The Scanner object used for input
     */
    public LoginManager(Scanner in) {
        this.in = in;
        logins = new ArrayList<>();
    }
    
    /**
     * This asks the user to enter a username and password. Then checks the 
     * credentials by searching the login database for a matching username and 
     * password. If a match is found, it will check if the login matches to an 
     * employee. If it does, the user's job title will be returned. The job 
     * title will be used to validate what part of the system the user can 
     * access. If the login does not match to an employee (matches to passenger)
     * it will return the passenger ID. If no correct login is found it will 
     * return "not found"
     * @return A String. Either job title, passenger ID, or "not found"
     */
    public String checkCredentials() {
        System.out.print("Username: ");
        enteredUsername = in.next();
        System.out.print("Password: ");
        enteredPassword = in.next();
        
        String accessLevel = "not found";
        
        for (Login login : logins) {
            if (login.getUsername().equals(enteredUsername)) {
                if (login.getPassword().equals(enteredPassword)) {
                    if (login.getEmployee() != null) { 
                    accessLevel = login.getEmployee().getJobTitle();
                    } else {
                        accessLevel = login.getPassenger().getPassengerID();
                    }
                } else {
                    System.out.println("Invalid password");
                    return "invalid";
                }
            }
        }
        
        if (accessLevel.equals("not found")) {
            System.out.println("Username not found");
        }
        
        return accessLevel;
    }
        
    /**
     * Getter for the logins array list
     * @return logins, the array list that acts as a database of logins
     */
    public ArrayList<Login> getLogins() {
        return logins;
    }   
}
