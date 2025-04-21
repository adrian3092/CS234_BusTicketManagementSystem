package expenses;


import employees.Employee;

/**
 * This class represents a salary expense. It inherits from Expense. It has one
 * instance variable.
 * employee: The Employee object associated with the expense
 * @author George Candal
 */
public class Salary extends Expense {
    private Employee employee;

    /**
     * Constructor for the class
     * @param accounting The Accounting object used as a database for the 
     * expenses
     * @param cost The cost of the salary
     * @param employee The Employee associated with the salary
     */
    public Salary(Accounting accounting, float cost, Employee employee) {
        super(accounting, cost);
        this.employee = employee;
    }

    /**
     * Getter for Employee
     * @return The EMployee associated with the salary
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /**
     * Setter for Employee
     * @param employee The Employee to associate with this salary
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    /**
     * Used when printing the object
     * @return a String with Expense ID, employee name, and cost
     */
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s $%-10.2f", 
                super.getExpenseId(), "Salary", employee.getName(), this.getCost());
    }
    
}
