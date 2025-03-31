package main;
/**
 *
 * @author Adrian Zielinski
 */
public class Payment {

    private int paymentId;
    private static int nextPaymentId = 1000;
    private String paymentType;
    private double paymentAmount;
    private String paymentDate;
    private String paymentStatus;
    private Passenger passenger;

    /**
     *
     * @param paymentType, the type of payment being used
     * @param paymentAmount, the amount to be charged
     * @param paymentDate, the date of the payment
     * @param passenger, the passenger making the payment
     */
    public Payment(String paymentType, double paymentAmount, String paymentDate, Passenger passenger) {
        paymentId = nextPaymentId++;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.passenger = passenger;
        paymentStatus = "Paid";
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
     * get the payment date
     * @return the payment date
     */
    public String getPaymentDate() {
        return paymentDate;
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

    @Override
    public String toString() {
        return "Payment ID: " + paymentId +
                ", Type: " + paymentType +
                ", Amount: $" + paymentAmount +
                ", Date: " + paymentDate +
                ", Status: " + paymentStatus;
    }
}
