
package payment;

import main.Passenger;

/**
 * this class represents a payment
 * at the bus company
 * @author Adrian Zielinski
 */
public class Payment {

    private int paymentId;
    private static int nextPaymentId = 1000;
    private String paymentType;
    private double paymentAmount;
    private String paymentStatus;
    private Passenger passenger;
    private String cardNumber;
    private String expirationDate;
    private PaymentManager paymentManager;

    /**
     * default constructor
     * @param paymentType the type of payment being used ( currently only "Credit Card" is accepted)
     * @param paymentAmount the amount to be charged
     * @param paymentDate the date of the payment
     * @param passenger the passenger making the payment
     * @param cardNumber the credit card number
     * @param expirationDate the credit card expiration date
     * @param paymentManager The PaymentManager object that manages the payments
     */
    public Payment(String paymentType, double paymentAmount, Passenger passenger, String cardNumber, String expirationDate, PaymentManager paymentManager) {
        paymentId = nextPaymentId++;      
        this.paymentAmount = paymentAmount;
        this.passenger = passenger;
        this.cardNumber = maskCardNumber(cardNumber);
        this.expirationDate = expirationDate;
        paymentStatus = "Paid";
        paymentManager.addPayment(this);
    }
    
    /**
     * masks the credit card number
     * @param cardNumber the full credit card number
     * @return the masked credit card number (XXXX-XXXX-XXXX-1234)
     */
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "Invalid";
        }
        
        // keep only the last 4 digits visible
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        return "XXXX-XXXX-XXXX-" + lastFourDigits;
    }

    /**
     * get the payment id
     * @return the payment id
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * get the payment type
     * @return the payment type
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * get the payment amount
     * @return the payment amount
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }
    
    /**
     * Sets the payment amount
     * @param amount The payment amount
     */
    public void setPaymentAmount(double amount) {
        this.paymentAmount = amount;
    }

    /**
     * get the status of the payment
     * @return the payment status
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * set the status of the payment
     * @param paymentStatus
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * get the passenger
     * @return Passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * get the card number used for payment
     * @return the masked card number
     */
    public String getCardNumber() {
        return maskCardNumber(cardNumber);
    }

    /**
     * get the expiration date associated with 
     * the credit card used for payment
     * @return the expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }
    
    /**
     * Used when printing the object. 
     * @return a String with the payment ID, passenger name, and cost
     */
    @Override
    public String toString() {
        return String.format("%-15s %-15s $%-10.2f", 
            paymentId, passenger.getPassengerName(), paymentAmount);
    }
}
