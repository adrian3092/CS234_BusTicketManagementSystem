package expenses;


import employees.Employee;

/**
 *
 * @author George Candal
 */
public class Salary extends Expense {
    private Employee employee;

    public Salary(Accounting accounting, float cost, Employee employee) {
        super(accounting, cost);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    @Override
    public String toString() {
        return super.getExpenseId() + " Salary: " + employee.getName() + " $" + this.getCost();
    }
    
}
