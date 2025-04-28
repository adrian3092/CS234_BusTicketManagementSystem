package employees;

import login.LoginManager;

/**
 * The Admin class represents an administrator who can manage employees
 * and perform administrative tasks in the system.
 * @author Handsome Onojerame
 */
public class Admin extends Employee {

    /**
     * Constructor to initialize an Admin object with a name and job title.
     *
     * @param firstName The first name of the admin.
     * @param lastName The last name of the admin.
     * @param jobTitle The job title of the admin.
     * @param phoneNumber The phone number of the admin.
     * @param salary The salary of the admin.
     * @param employeeManagement The EmployeeManagement object for managing employees.
     * @param loginManager The LoginManager object for managing logins.
     */
    public Admin(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, EmployeeManagement employeeManagement, LoginManager loginManager) {
        super(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
    }
    
    /**
     * constructor to initialize an Admin object with a specific employee ID
     * U=used for loading admin employees from CSV
     *
     * @param firstName The first name of the admin.
     * @param lastName The last name of the admin.
     * @param jobTitle The job title of the admin.
     * @param phoneNumber The phone number of the admin.
     * @param salary The salary of the admin.
     * @param employeeManagement The EmployeeManagement object for managing employees.
     * @param loginManager The LoginManager object for managing logins.
     * @param employeeID The specific employee ID to use.
     */
    public Admin(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, 
                EmployeeManagement employeeManagement, LoginManager loginManager, String employeeID) {
        super(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager, employeeID);
    }

}
