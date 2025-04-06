package employees;

/**
 * The Admin class represents an administrator who can manage employees
 * and perform administrative tasks in the system.
 */
public class Admin extends Employee {

    /**
     * Constructor to initialize an Admin object with a name and job title.
     *
     * @param name     The name of the admin.
     * @param jobTitle The job title of the admin.
     */
    public Admin(String firstName, String lastName, String jobTitle, EmployeeManagement employeeManagement) {
        super(firstName, lastName, jobTitle, employeeManagement);
    }

}
