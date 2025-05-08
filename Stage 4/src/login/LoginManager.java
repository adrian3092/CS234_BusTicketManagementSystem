package login;

import java.util.ArrayList;
import java.util.List;
import csv.CSVHandler;
import employees.Employee;
import employees.EmployeeManagement;
import main.Passenger;
import main.PassengerManager;

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
    
    private ArrayList<Login> logins;
    private String enteredUsername;
    private String enteredPassword;
    private static final String LOGINS_CSV_FILE_PATH = "data/logins.csv";

    /**
     * Constructor for the class. Initializes the database of logins.
     * 
     */
    public LoginManager() {
        logins = new ArrayList<>();
        loadLoginsFromCSV();
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
    public String checkCredentials(String enteredUsername, String enteredPassword) {
        
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
    
    /**
     * load logins from CSV file
     * @param employeeManagement the employee management to use for finding employees by ID
     * @param passengerManager the passenger manager to use for finding passengers by ID
     */
    public void loadLoginsFromCSV(EmployeeManagement employeeManagement, PassengerManager passengerManager) {
        List<String[]> data = CSVHandler.readCSV(LOGINS_CSV_FILE_PATH);
        
        // clear existing logins
        logins.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("username");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            String username = row[0];
            String password = row[1];
            String employeeId = row.length > 2 ? row[2] : "";
            String passengerId = row.length > 3 ? row[3] : "";
            
            // find the employee or passenger
            Employee employee = null;
            Passenger passenger = null;
            
            if (!employeeId.isEmpty()) {
                employee = employeeManagement.getEmployeeById(employeeId);
            }
            
            if (!passengerId.isEmpty()) {
                passenger = passengerManager.getPassengerById(passengerId);
            }
            
            // create login if employee or passenger exists
            if (employee != null) {
                new Login(employee, this, username, password);
            } else if (passenger != null) {
                new Login(passenger, this, username, password);
            }
        }
    }
    
    /**
     * load logins from CSV file
     */
    private void loadLoginsFromCSV() {
        // 
    }
    
    /**
     * save logins to CSV file
     */
    public void saveLoginsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Login login : logins) {
            String employeeId = login.getEmployee() != null ? login.getEmployee().getEmployeeID() : "";
            String passengerId = login.getPassenger() != null ? login.getPassenger().getPassengerID() : "";
            
            String[] row = new String[]{
                login.getUsername(),
                login.getPassword(),
                employeeId,
                passengerId
            };
            data.add(row);
        }
        
        CSVHandler.writeCSV(LOGINS_CSV_FILE_PATH, data);
    }
}
