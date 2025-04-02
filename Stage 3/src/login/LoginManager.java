package login;

import login.Login;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class manages the login system. 
 * @author George Candal
 */
public class LoginManager {
    
    private Scanner in;
    private ArrayList<Login> logins;
    private String enteredUsername;
    private String enteredPassword;

    /**
     * Constructor for the class. Initializes the database of logins.
     * @param in 
     */
    public LoginManager(Scanner in) {
        this.in = in;
        logins = new ArrayList<>();
    }
    
    /**
     * This asks the user to enter a username and password. Then checks the 
     * credentials by searching the login database for a matching username and 
     * password. If a match is found the user's job title will be returned. The
     * job title will be used to validate what part of the system the user can 
     * access. If no matching password is found, "not found" will be returned.
     * @return The job title of the user
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
                    accessLevel = login.getEmployee().getJobTitle();
                } else {
                    System.out.println("Invalid password");
                    break;
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
