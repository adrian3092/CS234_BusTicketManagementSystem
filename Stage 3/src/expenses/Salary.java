package expenses;


import Employee.Employee;

/**
 *
 * @author George Candal
 */
public class Salary extends Expense {
    private Employee employee;

    public Salary(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    } 
}
