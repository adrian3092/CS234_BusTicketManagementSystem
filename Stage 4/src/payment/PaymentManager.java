
package payment;

import java.util.ArrayList;
import java.util.List;

import csv.CSVHandler;
import main.Passenger;
import main.PassengerManager;

/**
 * this class manages all payments
 * at the bus company
 * @author Adrian Zielinski
 */
public class PaymentManager {
    
    private ArrayList<Payment> payments;
    private static final String PAYMENTS_CSV_FILE_PATH = "data/payments.csv";
    
    /**
     * default constructor
     */
    public PaymentManager() {
        payments = new ArrayList<>();
        loadPaymentsFromCSV();
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
        savePaymentsToCSV();
    }
    
    /**
     * Removes a payment from the list of payments
     * @param payment The payment to remove
     */
    public void removePayment(Payment payment) {
        payments.remove(payment);
        savePaymentsToCSV();
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
            savePaymentsToCSV();
            return true;
        }
        
        return false;
    }
    
    /**
     * Prints out an expense report with all of the expenses in the database
     */
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("%-15s %-15s %-10s%n", "Payment ID", "Name", "Cost"));
        report.append("--------------------------------------------------\n");
        
        for(Payment payment : payments) {
            report.append(payment);
            report.append("\n");
        }
        
        return report.toString();
    }
    
    /**
     * load payments from CSV file
     * @param passengerManager the passenger manager to use for finding passengers by ID
     */
    public void loadPaymentsFromCSV(PassengerManager passengerManager) {
        List<String[]> data = CSVHandler.readCSV(PAYMENTS_CSV_FILE_PATH);
        
        // clear existing payments
        payments.clear();
        
        // skip header row if it exists
        boolean hasHeader = data.get(0)[0].equals("paymentId");
        int startRow = hasHeader ? 1 : 0;
        
        // process each row
        for (int i = startRow; i < data.size(); i++) {
            String[] row = data.get(i);
            
            int paymentId = Integer.parseInt(row[0]);
            String paymentType = row[1];
            double paymentAmount = Double.parseDouble(row[2]);
            String paymentStatus = row[3];
            String passengerID = row[4];
            String cardNumber = row[5];
            String expirationDate = row[6];
            
            // find the passenger
            Passenger passenger = passengerManager.getPassengerById(passengerID);
            
            // create payment if passenger exists
            if (passenger != null) {
                Payment payment = new Payment(paymentId, paymentType, paymentAmount, paymentStatus, 
                                                passenger, cardNumber, expirationDate, this);
            }
        }
    }
    
    /**
     * load payments from CSV file
     */
    private void loadPaymentsFromCSV() {
        //
    }
    
    /**
     * save payments to CSV file
     */
    public void savePaymentsToCSV() {
        List<String[]> data = new ArrayList<>();
        
        // add data rows
        for (Payment payment : payments) {
            String passengerID = payment.getPassenger() != null ? payment.getPassenger().getPassengerID() : "";
            
            String[] row = new String[]{
                String.valueOf(payment.getPaymentId()),
                payment.getPaymentType(),
                String.valueOf(payment.getPaymentAmount()),
                payment.getPaymentStatus(),
                passengerID,
                payment.getCardNumber(),
                payment.getExpirationDate()
            };
            data.add(row);
        }
        CSVHandler.writeCSV(PAYMENTS_CSV_FILE_PATH, data);
    }
}
