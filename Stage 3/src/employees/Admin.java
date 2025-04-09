package employees;

/**
 * The Admin class represents an administrator who can manage employees
 * and perform administrative tasks in the system.
 * @author Handsome Onojerame
 */
public class Admin extends Employee {

    /**
     * Constructor to initialize an Admin object with a name and job title.
     *
     * @param name     The name of the admin.
     * @param jobTitle The job title of the admin.
     * @param phoneNumber The phone number of the admin.
     * @param salary The salary of the admin.
     * @param employeeManagement The EmployeeManagement object for managing employees.
     * @param employeeManagement The EmployeeManagement object for managing employees.
     */
    public Admin(String firstName, String lastName, String jobTitle, String phoneNumber, float salary, EmployeeManagement employeeManagement) {
        super(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement);
    }

}
