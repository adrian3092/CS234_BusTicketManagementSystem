package employees;

public class EmployeeManagement {
    
    public void deleteEmployee(Employee employee) {
        employee.deleteEmployee();
    }

    /**
     * Reassigns a new job title to an employee.
     *
     * @param employee    The employee whose job title is to be reassigned.
     * @param newJobTitle The new job title for the employee.
     */
    public void setJobTitle(Employee employee, String newJobTitle) {
        employee.setJobTitle(newJobTitle);
    }

    public void updateName(Employee employee, String firstName, String lastName) {
        employee.setName(firstName, lastName);
    }

    public void updateEmail(Employee employee, String email) {
        employee.setEmail(email);
    }

    public void updatePhoneNumber(Employee employee, String phoneNumber) {
        employee.setPhoneNumber(phoneNumber);
    }

    public void updateSalary(Employee employee, float salary) {
        employee.setSalary(salary);
    }
}