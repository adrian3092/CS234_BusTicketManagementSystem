package expenses;


import bus.*;
import csv.CSVHandler;
import depot.*;
import employees.Employee;
import employees.EmployeeManagement;
import java.util.ArrayList;
import java.util.List;

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
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    private static final String EXPENSES_CSV_FILE_PATH = "dist/data/expenses.csv";
    

    /**
     * Constructor for the class
     * @param in Scanner object for input
     * @param busManager Bus database
     * @param depotManager Depot database
     * @param employeeManagement Employee database
     */
    public Accounting(BusManager busManager, 
            DepotManager depotManager, EmployeeManagement employeeManagement) {
        expenses = new ArrayList<>();
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        
    } 
    
    public ArrayList<Expense> getAllExpenses() {
        return expenses;
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
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("%-20s %-20s %-20s %-20s%n", "Expense ID", "Type", "Entity", "Cost"));
        report.append("-------------------------------------------------------------------------\n");
        
        for(Expense expense : expenses) {
            report.append(expense);
            report.append("\n");
        }
        return report.toString();
    }
    
    /**
     * Asks the user for a bus ID and adds either a fuel cost or a maintenance 
     * cost to that bus
     * @param busManager BusManager object 
     * @param type Either "fuel" or "maintenance" for the type of cost to add
     */
//    public void addBusCost(BusManager busManager, String type) {
//        System.out.print("Enter the ID of the bus associated with this cost: ");
//        int busId = in.nextInt();
//        Bus selectedBus = busManager.findBusById(busId);
//        
//        if (selectedBus == null) {
//            System.out.println("Bus with ID " + busId + " not found.");
//            return;
//        } 
//        
//        System.out.print("Enter the amount: ");
//        float cost = in.nextFloat();
//        
//        if (type.equals("fuel")) {
//            new FuelCost(this, cost, selectedBus);  
//        } else if (type.equals("maintenance")) {
//            new MaintenanceCost(this, cost, selectedBus);  
//        }
//    }
    
    /**
     * Asks the user for a depot ID and adds a utility cost associated with that
     * depot.
     * @param depotManager DepotManager
     */
//    public void addUtilityCost(DepotManager depotManager) {
//        System.out.print("Enter the ID of the Depot associated with this cost: ");
//        int depotId = in.nextInt();
//        Depot selectedDepot = depotManager.findDepotById(depotId);
//        
//        if (selectedDepot == null) {
//            System.out.println("Depot with ID " + depotId + " not found.");
//            return;
//        } 
//        
//        System.out.print("Enter the amount: ");
//        float cost = in.nextFloat();
//        
//        new Utility(this, cost, selectedDepot);
//    }
    
    /**
     * Asks the user for an employee ID and a dollar amount. Then it adds a 
     * salary of that amount linked to that employee to the list of expenses
     * @param employeeManagement 
     */
//    public void addSalary(EmployeeManagement employeeManagement) {
//        System.out.print("Enter the ID of the employee: ");
//        in.nextLine();
//        String employeeId = in.next();
//        Employee selectedEmployee = employeeManagement.getEmployeeById(employeeId);
//        
//        if (selectedEmployee == null) {
//            System.out.println("Employee with ID " + employeeId + " not found.");
//            return;
//        } 
//        
//        System.out.print("Enter the amount: ");
//        float cost = in.nextFloat();
//        
//        new Salary(this, cost, selectedEmployee);
//    }
    
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
//    public void removeExpense() {
//        System.out.print("Enter the ID of the expense you want to remove: ");
//        int expenseId = in.nextInt();
//        Expense expenseToRemove = findExpenseById(expenseId);
//        if (expenseToRemove == null) {
//            System.out.println("Expense ID not found");
//        } else {
//        expenses.remove(expenseToRemove);
//        }
//    }  
    
    /**
     * load expenses from CSV file
     * 
     */
    public void loadExpensesFromCSV() {
        List<String[]> data = CSVHandler.readCSV(EXPENSES_CSV_FILE_PATH);
        
        // clear existing expenses
        expenses.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("expenseID");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int expenseID = Integer.parseInt(row[0]);
            String type = row[1];
            String entity = row[2];
            String entityID = row[3];
            float cost = Float.parseFloat(row[4]);
            
            // Get object from entity
            if (type.equals("FuelCost")) {
                Bus bus = busManager.findBusById(Integer.parseInt(entityID));
                new FuelCost(this, cost, bus);
            } else if (type.equals("MaintenanceCost")) {
                Bus bus = busManager.findBusById(Integer.parseInt(entityID));
                new MaintenanceCost(this, cost, bus);
            } else if (type.equals("Salary")) {
                Employee e = employeeManagement.getEmployeeById(entityID);
                new Salary(this, cost, e);
            } else if (type.equals("Utility")) {
                Depot d = depotManager.findDepotById(Integer.parseInt(entityID));
                new Utility(this, cost, d);
            }
        }
    }
    
    /**
     * save expenses to CSV file
     */
    public void saveExpensesToCSV() {
        List<String[]> data = new ArrayList<>();
        String entity = "";
        String entityID = "";
        
        // add data rows
        for (Expense expense : expenses) {
            
            if (expense instanceof FuelCost) {
                entity = "Bus";
                entityID = String.valueOf(((FuelCost) expense).getBus().getBusId());
            } else if (expense instanceof MaintenanceCost) {
                entity = "Bus";
                entityID = String.valueOf(((MaintenanceCost) expense).getBus().getBusId());                
            } else if (expense instanceof Salary) {
                entity = "Employee";
                entityID = ((Salary) expense).getEmployee().getEmployeeID();
            } else if (expense instanceof Utility) {
                entity = "Depot";
                entityID = String.valueOf(((Utility) expense).getDepot().getDepotId());
            }
            
            String[] row = new String[]{
                String.valueOf(expense.getExpenseId()),
                expense.getClass().getSimpleName(),
                entity,
                entityID,
                String.valueOf(expense.getCost())
            };
            data.add(row);
        }
        CSVHandler.writeCSV(EXPENSES_CSV_FILE_PATH, data);
    }
}
