
package menu;
import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.*;
import java.util.Scanner;
import payment.Payment;
import payment.PaymentManager;

/**
 * This class is used for the accounting menu. It displays a menu and asks for
 * user input for managing expenses. It has 6 instance variables.
 * in: The Scanner object used for user input
 * menuOption: Used to save the menu option input from the user
 * accounting: The Accounting object used as a database for the expenses
 * busManager: The BusManager object used as a database for buses
 * depotManager: The DepotManager object used as a database for Depots
 * employeeManagement: The EmployeeManagement object used as a database for 
 * employees
 * @author George Candal
 */
public class AccountingMenu {
    
    private Scanner in;
    private int menuOption;
    private Accounting accounting;
    private BusManager busManager;
    private DepotManager depotManager;
    private EmployeeManagement employeeManagement;
    private PaymentManager paymentManager;
    
    /**
     * Constructor for the class. 
     * @param in The Scanner object used for user input
     * @param accounting The Accounting object used as a database for the 
     * expenses
     * @param busManager The BusManager object used as a database for buses
     * @param depotManager The DepotManager object used as a database for Depots
     * @param employeeManagement The EmployeeManagement object used as a 
     * database for employees
     */
    public AccountingMenu(Scanner in, Accounting accounting, 
            BusManager busManager, DepotManager depotManager, 
            EmployeeManagement employeeManagement, PaymentManager paymentManager) {
        this.in = in;
        this.accounting = accounting;
        this.busManager = busManager;
        this.depotManager = depotManager;
        this.employeeManagement = employeeManagement;
        this.paymentManager = paymentManager;
        menuOption = 0;
    }

    /**
     * Displays the main accounting menu
     */
    public void displayMenu() {
        
        while (menuOption != 4) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                 ACCOUNTING MENU                ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Generate Expense Report                    ║");
            System.out.println("║  2. Generate Payment Report                    ║");
            System.out.println("║  3. Manage Payments                            ║");
            System.out.println("║  4. Add a new expense                          ║");
            System.out.println("║  5. Remove an existing expense                 ║");
            System.out.println("║  6. Return to previous menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print(" Please select an option (1-6): ");

            menuOption = in.nextInt();

            System.out.println("══════════════════════════════════════════════════"); 

            switch (menuOption) {
                case 1 -> {
                    accounting.getReport();
                }
                case 2 -> {
                    paymentManager.getReport();
                }
                case 3 -> {
                    Payment payment = findPayment();
                    if (payment == null) {
                        System.out.println("Payment ID not found");
                    } else {
                    managePayment(payment);
                    }
                }
                case 4 -> {
                    expenseSubMenu();
                }
                case 5 -> {
//                    accounting.removeExpense();
                }
                case 6 -> {
                    System.out.println("Returning to previous menu...");
                    menuOption = 0; // reset menuOption before returning
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    /**
     * Displays the sub menu to add a new expense
     */
    public void expenseSubMenu() {
        int menuOption = 0;
        
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║  What type of expense would you like to add?   ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Fuel                                       ║");
            System.out.println("║  2. Maintenance                                ║");
            System.out.println("║  3. Salary                                     ║");
            System.out.println("║  4. Utilities                                  ║");
            System.out.println("║  5. Return to Administrator Menu               ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-5): ");

            menuOption = in.nextInt();
            
            System.out.println("══════════════════════════════════════════════════");
            switch (menuOption) {
                case 1 -> {
//                    accounting.addBusCost(busManager, "fuel");
                }
                case 2 -> {
//                    accounting.addBusCost(busManager, "maintenance");

                }
                case 3 -> {
//                    accounting.addSalary(employeeManagement);
                }
                case 4 -> {
//                    accounting.addUtilityCost(depotManager);
                }
                case 5 -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    } 

    public Payment findPayment() {
        System.out.println("Enter the payment ID of the payment you want to manage: ");
        int paymentID = in.nextInt();
        return paymentManager.findPaymentById(paymentID);       
    }
    
    public void managePayment(Payment payment) {
         int menuOption = 0;
        
        while (menuOption != 5) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║            Manage Payment Menu                 ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Update amount                              ║");
            System.out.println("║  2. Delete payment                             ║");
            System.out.println("║  3. Return to previous menu                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-3): ");

            menuOption = in.nextInt();
            
            System.out.println("══════════════════════════════════════════════════");
            switch (menuOption) {
                case 1 -> {
                    System.out.println("What is the updated payment amount for payment ID: " + payment.getPaymentId());
                    double amount = in.nextDouble();
                    payment.setPaymentAmount(amount);
                    System.out.println("Payment amount has been updated");
                }
                case 2 -> {
                    System.out.println("Payment " + payment.getPaymentId() + " has been removed");
                    paymentManager.removePayment(payment);
                    return;
                    
                }
                case 3 -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
            }
        }
    }
}
