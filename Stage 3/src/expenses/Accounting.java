package expenses;


import bus.*;
import depot.Depot;
import depot.DepotManager;
import employees.Employee;
import employees.EmployeeManagement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author George Candal
 */
public class Accounting {
    private ArrayList<Expense> expenses;
    private Scanner in;
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    

    public Accounting(Scanner in, BusManager busManager, DepotManager depotManager, EmployeeManagement employeeManagement) {
        this.in = in;
        expenses = new ArrayList<>();
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        
    }   
    
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    
    public void getReport() {
        for(Expense expense : expenses) {
            System.out.println(expense);
        }             
    }
    
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
    
    public Expense findExpenseById(int expenseId) {
        for (Expense e : expenses) {
            if (e.getExpenseId() == expenseId)
                return e;
        }
        return null;
    }
     
    public void removeExpense() {
        System.out.print("Enter the ID of the expense you want to remove: ");
        int expenseId = in.nextInt();
        Expense expenseToRemove = findExpenseById(expenseId);
        expenses.remove(expenseToRemove);        
    }
    
    
    
}
