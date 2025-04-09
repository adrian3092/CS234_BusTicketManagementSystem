package expenses;


import bus.*;
import depot.*;
import employees.Employee;
import employees.EmployeeManagement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the accounting database of the bus company. It has 5 
 * instance variables. 
 * expenses: An array List to hold all of the expenses
 * in: a Scanner object
 * busManger: BusManager object
 * depotManager: DepotManager object
 * employeeManagement: EmployeeManagement object
 * @author George Candal
 */
public class Accounting {
    private ArrayList<Expense> expenses;
    private Scanner in;
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    

    /**
     * Constructor for the class
     * @param in Scanner object for input
     * @param busManager Bus database
     * @param depotManager Depot database
     * @param employeeManagement Employee database
     */
    public Accounting(Scanner in, BusManager busManager, DepotManager depotManager, EmployeeManagement employeeManagement) {
        this.in = in;
        expenses = new ArrayList<>();
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        
    }   
    
    /**
     * Adds an expense to the list of expenses
     * @param expense 
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    
    /**
     * Prints out an expense report with all of the expenses in the database
     */
    public void getReport() {
        for(Expense expense : expenses) {
            System.out.println(expense);
        }             
    }
    
    /**
     * Asks the user for a bus ID and adds either a fuel cost or a maintenance 
     * cost to that bus
     * @param busManager BusManager object 
     * @param type Either "fuel" or "maintenance" for the type of cost to add
     */
    public void addBusCost(BusManager busManager, String type) {
        System.out.print("Enter the ID of the bus associated with this cost: ");
        int busId = in.nextInt();
        Bus selectedBus = busManager.findBusById(busId);
        
        if (selectedBus == null) {
            System.out.println("Bus with ID " + busId + " not found.");
            return;
        } 
        
        System.out.print("Enter the amount: ");
        float cost = in.nextFloat();
        
        if (type.equals("fuel")) {
            new FuelCost(this, cost, selectedBus);  
        } else if (type.equals("maintenance")) {
            new MaintenanceCost(this, cost, selectedBus);  
        }
    }
    
    /**
     * Asks the user for a depot ID and adds a utility cost associated with that
     * depot.
     * @param depotManager DepotManager
     */
    public void addUtilityCost(DepotManager depotManager) {
        System.out.print("Enter the ID of the Depot associated with this cost: ");
        int depotId = in.nextInt();
        Depot selectedDepot = depotManager.findDepotById(depotId);
        
        if (selectedDepot == null) {
            System.out.println("Depot with ID " + depotId + " not found.");
            return;
        } 
        
        System.out.print("Enter the amount: ");
        float cost = in.nextFloat();
        
        new Utility(this, cost, selectedDepot);
    }
    
    /**
     * Asks the user for an employee ID and a dollar amount. Then it adds a 
     * salary of that amount linked to that employee to the list of expenses
     * @param employeeManagement 
     */
    public void addSalary(EmployeeManagement employeeManagement) {
        System.out.print("Enter the ID of the employee: ");
        in.nextLine();
        String employeeId = in.next();
        Employee selectedEmployee = employeeManagement.getEmployeeById(employeeId);
        
        if (selectedEmployee == null) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        } 
        
        System.out.print("Enter the amount: ");
        float cost = in.nextFloat();
        
        new Salary(this, cost, selectedEmployee);
    }
    
    /**
     * Searches through the expenses by ID and if the ID is found returns the
     * expense with that ID.
     * @param expenseId The ID of the expense
     * @return Expense object with the ID
     */
    public Expense findExpenseById(int expenseId) {
        for (Expense e : expenses) {
            if (e.getExpenseId() == expenseId)
                return e;
        }
        return null;
    }
    
    /**
     * Asks the user for an expense ID and removes that expense from the list
     */
    public void removeExpense() {
        System.out.print("Enter the ID of the expense you want to remove: ");
        int expenseId = in.nextInt();
        Expense expenseToRemove = findExpenseById(expenseId);
        if (expenseToRemove == null) {
            System.out.println("Expense ID not found");
        } else {
        expenses.remove(expenseToRemove);
        }
    }       
}
