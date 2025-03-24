
import java.util.ArrayList;

/**
 *
 * @author George Candal
 */
public class Accounting {
    private ArrayList<Expense> expenses;

    public Accounting() {
        expenses = new ArrayList<>();
    }   
    
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    
    /**
     * Need to add a way to print name and fix output
     */
    public void getReport() {
        for(Expense expense : expenses) {
            System.out.println(expense.getCost());
        }
    }    
}
