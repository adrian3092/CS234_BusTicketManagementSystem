package Employee;

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
    public Admin(String name, String jobTitle) {
        super(name, jobTitle);
    }

    /**
     * Deletes an employee from the system.
     *
     * @param employee The employee to be deleted.
     */
    public void deleteEmployee(Employee employee) {
        employee.deleteEmployee();
    }

    /**
     * Reassigns a new job title to an employee.
     *
     * @param employee    The employee whose job title is to be reassigned.
     * @param newJobTitle The new job title for the employee.
     */
    public void reasignJobTitle(Employee employee, String newJobTitle) {
        employee.setJobTitle(newJobTitle);
    }

    /**
     * Generates a report of the system.
     */
    public void generateReport() {
        // Generate a report of the system
    }
}
