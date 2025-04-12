
package payment;

import java.util.ArrayList;
import main.Passenger;

/**
 * this class manages all payments
 * at the bus company
 * @author Adrian Zielinski
 */
public class PaymentManager {
    
    private ArrayList<Payment> payments;
    
    /**
     * default constructor
     */
    public PaymentManager() {
        payments = new ArrayList<>();
    }
    
    /**
     * get all payments
     * @return a list of all payments
     */
    public ArrayList<Payment> getAllPayments() {
        return payments;
    }
    
    /**
     * add a payment to the payment list
     * @param payment, the payment to add
     */
    public void addPayment(Payment payment) {
        payments.add(payment);
    }
    
    /**
     * Removes a payment from the list of payments
     * @param payment The payment to remove
     */
    public void removePayment(Payment payment) {
        payments.remove(payment);
    }
    
    /**
     * process a payment for a ticket
     * @param passenger the passenger making the payment
     * @param paymentAmount the amount to be charged
     * @param cardNumber the credit card number
     * @param expirationDate the credit card expiration date
     * @param paymentManager This PaymentManager list to save the payment
     * @return the payment
     */
    public Payment processPayment(Passenger passenger, double paymentAmount, String cardNumber, String expirationDate, PaymentManager paymentManager) {
        
        // create a new credit card payment
        Payment payment = new Payment("Credit Card", paymentAmount, passenger, cardNumber, expirationDate, paymentManager);
        
        return payment;
    }
    
    /**
     * find a payment by its ID
     * @param paymentId the payment ID to search for
     * @return the payment if found
     */
    public Payment findPaymentById(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null;
    }
    
    /**
     * get all payments for a specific passenger
     * @param passenger the passenger to find payments for
     * @return ArrayList of payments for the passenger
     */
    public ArrayList<Payment> getPaymentsByPassenger(Passenger passenger) {
        ArrayList<Payment> passengerPayments = new ArrayList<>();
        
        for (Payment payment : payments) {
            if (payment.getPassenger().equals(passenger)) {
                passengerPayments.add(payment);
            }
        }
        
        return passengerPayments;
    }
    
    /**
     * cancel a payment
     * @param paymentId the ID of the payment to cancel
     * @return true if the payment was found and canceled, false otherwise
     */
    public boolean cancelPayment(int paymentId) {
        Payment payment = findPaymentById(paymentId);
        
        if (payment != null) {
            payment.setPaymentStatus("Canceled");
            return true;
        }
        
        return false;
    }
    
    /**
     * Prints out an expense report with all of the expenses in the database
     */
    public void getReport() {
        // Print the header for the report
        System.out.printf("%-15s %-15s %-10s%n", "Payment ID", "Name", "Cost");
        System.out.println("--------------------------------------------------");
        for(Payment payment : payments) {
            System.out.println(payment);
        }             
    }
}
