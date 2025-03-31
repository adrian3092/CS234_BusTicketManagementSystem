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
     * This checks the credentials by searching the login database for a
     * matching username and password.
     * @return True if username and password match
     */
    public boolean checkCredentials() {
        for (Login login : logins) {
            if (login.getUsername().equals(enteredUsername)) {
                if (login.getPassword().equals(enteredPassword)) {
                    return true;
                } else {
                    System.out.println("Invalid password");
                    return false;
                }
            }
        }
    System.out.println("Username not found");
    return false;
    }
    
    /**
     * This displays the login menu and saves the username and password entered
     * by the user.
     */
    public void loginMenu() {
        System.out.print("Username: ");
        enteredUsername = in.next();
        System.out.print("Password: ");
        enteredPassword = in.next();
    }
    
    /**
     * Getter for the logins array list
     * @return logins, the array list that acts as a database of logins
     */
    public ArrayList<Login> getLogins() {
        return logins;
    }
    
    
}
