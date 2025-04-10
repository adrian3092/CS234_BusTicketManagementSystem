package login;

import employees.Employee;
import main.Passenger;


/**
 * This class represents a login used to log in to the employee side of the 
 * program. It has 4 instance variables.
 * username: The username used to log in
 * password: The password associated with the username
 * employee: The Employee who the login belongs to
 * loginManager: The LoginManager object used as a database to save all of the
 * login objects
 * @author George Candal
 */
public class Login {
    
    private String username;
    private String password;
    private Employee employee;
    private LoginManager loginManager;
    private Passenger passenger;

    /**
     * Constructor for the class. Sets the username to the employee's email 
     * address. Sets the default password to "password". Adds the login to the 
     * list of logins in loginManager.
     * @param employee The Employee who the login belongs to
     * @param loginManager The LoginManager database
     */
    public Login(Employee employee, LoginManager loginManager) {
        this.employee = employee;
        this.username = employee.getEmail();
        this.password = "password"; //default password
        loginManager.getLogins().add(this);
    }
    
    public Login(Passenger passenger, LoginManager loginManager, String email, String password) {
        this.passenger = passenger;
        this.username = email;
        this.password = password;
        loginManager.getLogins().add(this);
    }

    /**
     * Getter for the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the password
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the username
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for the password
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the employee
     * @return The Employee associated with the login
     */
    public Employee getEmployee() {
        return employee;
    }    
}
