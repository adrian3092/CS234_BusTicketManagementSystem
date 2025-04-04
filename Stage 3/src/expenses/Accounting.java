package expenses;


import bus.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author George Candal
 */
public class Accounting {
    private ArrayList<Expense> expenses;
    private Scanner in;
    private int expenseID;

    public Accounting(Scanner in) {
        this.in = in;
        expenses = new ArrayList<>();
        expenseID = 1;
    }   
    
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    
    public void getReport() {
        for(Expense expense : expenses) {
            System.out.println(expense);
        }             
    }
    
    public void addFuelCost(BusManager busManager) {
        System.out.print("Enter the ID of the bus associated with this cost: ");
        int busId = in.nextInt();
        Bus selectedBus = busManager.findBusById(busId);
        
        if (selectedBus == null) {
            System.out.println("Bus with ID " + busId + " not found.");
            return;
        } 
        
        System.out.print("Enter the amount: ");
        float cost = in.nextFloat();
        
        FuelCost expenseID = new FuelCost(this, cost, selectedBus);
        
        
        
    }
    
}
