
package payment;

import java.util.ArrayList;
import main.Passenger;

/**
 * 
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
     * process a payment for a ticket
     * @param passenger, the passenger making the payment
     * @param paymentAmount, the amount to be charged
     * @param cardNumber, the credit card number
     * @param expirationDate, the credit card expiration date
     * @return the payment
     */
    public Payment processPayment(Passenger passenger, double paymentAmount, String cardNumber, String expirationDate) {
        
        // create a new credit card payment
        Payment payment = new Payment("Credit Card", paymentAmount, passenger, cardNumber, expirationDate);
        
        // add payment to the list
        addPayment(payment);
        
        return payment;
    }
    
    /**
     * find a payment by its ID
     * @param paymentId, the payment ID to search for
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
     * @param passenger, the passenger to find payments for
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
     * @param paymentId, the ID of the payment to cancel
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
}
