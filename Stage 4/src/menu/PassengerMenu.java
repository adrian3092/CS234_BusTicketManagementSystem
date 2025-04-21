package menu;

import java.util.ArrayList;
import java.util.Scanner;
import login.Login;
import login.LoginManager;
import main.Passenger;
import main.PassengerManager;
import main.ScheduleManager;
import payment.PaymentManager;
import ticket.*;

/**
 * PassengerMenu class provides a menu interface for passengers to log in, sign up, 
 * continue as a guest, or return to the main menu.
 * 
 * @author Handsome Onojerame
 */
public class PassengerMenu {
    private Scanner in;
    private Passenger passenger;
    private PassengerManager passengerManager;
    private LoginManager loginManager;
    private TicketMenu ticketMenu;
    private String passengerID;
    private TicketIssuer ticketIssuer;
    private TicketManager ticketManager;
    private Login login;

    /**
     * Constructor to initialize PassengerMenu with required managers and input scanner.
     * 
     * @param in               Scanner object for user input
     * @param passengerManager Manager to handle passenger-related operations
     * @param scheduleManager  Manager to handle schedule-related operations
     * @param loginManager     Manager to handle login-related operations
     * @param paymentManager   Manager to handle payment-related operations
     * @param ticketIssuer    Manager to handle ticket issuance
     */
    public PassengerMenu(Scanner in, PassengerManager passengerManager, ScheduleManager scheduleManager, LoginManager loginManager, PaymentManager paymentManager, TicketIssuer ticketIssuer) {
        this.passengerManager = passengerManager;
        this.in = in;
        this.loginManager = loginManager;
        this.ticketMenu = new TicketMenu(in, scheduleManager, passengerManager, paymentManager, ticketIssuer);
        this.ticketIssuer = ticketIssuer;
    }

    /**
     * Displays the passenger menu and handles user input for various options.
     */
    public void displayMenu() {
        int choice;

        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              PASSENGER LOGIN MENU              ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Already have an account? Login.            ║");
            System.out.println("║  2. New to bus company? Sign up.               ║");
            System.out.println("║  3. Don't need an account? Continue as guest.  ║");
            System.out.println("║  4. Return to main menu.                       ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-4): ");

            choice = in.nextInt();
            System.out.println("══════════════════════════════════════════════════");

            in.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.println("Redirecting to login...");
                    this.passengerID = loginManager.checkCredentials();
                    in.nextLine(); // Consume the newline character left by nextInt()
                    if (this.passengerID.equals("not found")) {
                        System.out.println("Passenger ID not found. Please try again.");                    
                        System.out.println("Want to continue as guest? (yes/no)");
                        String response = in.nextLine();
                        if (response.equalsIgnoreCase("yes")) {
                            ticketMenu.displayMenu("guest");
                        } else {
                            System.out.println("Returning to main menu...");
                            choice = 4; // Set choice to 4 to exit the loop
                        }
                    }                    
                    else
                    {passengerDashboard();}
                    break;
                case 2:
                    System.out.println("Redirecting to sign-up...");
                    signUp();
                    break;
                case 3:
                    System.out.println("Continuing as guest...");
                    ticketMenu.displayMenu("guest");
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    /**
     * Handles the sign-up process for new passengers.
     */
    private void signUp() {
        System.out.println("Please enter your name: ");
        String name = in.nextLine();
        System.out.println("Please enter your phone number: ");
        String phone = in.nextLine();
        System.out.println("Please enter your email: ");
        String email = in.nextLine();  
        String password;
        String confirmPassword;
        do{
        System.out.println("Please enter your password: ");
        password = in.nextLine();
        System.out.println("Please confirm your password: ");
        confirmPassword = in.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
        }
        } while (!password.equals(confirmPassword)); 

        this.passenger = new Passenger(name, email, phone);
        passengerManager.addPassenger(this.passenger);
        this.passengerID = this.passenger.getPassengerID();
        System.out.println("══════════════════════════════════════════════════"); 
        System.out.println("Welcome " + name + "! Your account has been created successfully.");
        System.out.println("Your Passenger ID is: " + this.passenger.getPassengerID());

        this.login = new Login(this.passenger, loginManager, email, password);
        passengerDashboard();
    }

    /**
     * Displays the passenger dashboard menu and handles user input for various options.
     */
    private void passengerDashboard() {

        int choice;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║               PASSENGER DASHBOARD              ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Book Ticket                                ║");
            System.out.println("║  2. View Ticket History                        ║");
            System.out.println("║  3. Update Profile                             ║");
            System.out.println("║  4. Logout                                     ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-4): ");
            choice = in.nextInt();
            in.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
            case 1:
                this.ticketMenu.displayMenu(this.passengerID);
                break;
            case 2:
                System.out.println("Viewing ticket history...");
                this.ticketManager = this.ticketIssuer.getTicketManager();
                ArrayList<Ticket> tickets = this.ticketManager.getTickets();
                System.out.println("══════════════════════════════════════════════════"); 
                for (Ticket ticket : tickets) {
                    if (ticket.getPassenger().getPassengerID().equals(this.passengerID)) {
                        System.out.println(ticket);}
                }
                System.out.println("══════════════════════════════════════════════════"); 
                break;
            case 3:
                System.out.println("Updating profile...");
                updateProfile();
                break;
            case 4:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

    }

    /**
     * Handles passenger profile update.
     */
    private void updateProfile() {
        int choice;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║              UPDATE PROFILE MENU               ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Update Name                                ║");
            System.out.println("║  2. Update Email                               ║");
            System.out.println("║  3. Update Phone Number                        ║");
            System.out.println("║  4. Delete Account                             ║");
            System.out.println("║  5. Back to Dashboard                          ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Please select an option (1-5): ");
            choice = in.nextInt();
            in.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
            case 1:                
                Passenger passenger = passengerManager.getPassengerById(this.passengerID);
                System.out.println("Enter new name: ");
                String newName = in.nextLine();
                passenger.setName(newName);
                System.out.println("Name updated successfully.");
                break;
            case 2:
                Passenger passenger2 = passengerManager.getPassengerById(this.passengerID);
                System.out.println("Enter new email: ");
                String newEmail = in.nextLine();
                passenger2.setEmail(newEmail);
                System.out.println("Email updated successfully.");
                break;
            case 3:
                Passenger passenger3 = passengerManager.getPassengerById(this.passengerID);
                System.out.println("Enter new phone number: ");
                String newPhone = in.nextLine();
                passenger3.setPhoneNumber(newPhone);
                System.out.println("Phone number updated successfully.");
                break;
            case 4:
                System.out.println("Are you sure you want to delete your account? (yes/no)");
                String confirm = in.nextLine();
                if(confirm.equalsIgnoreCase("yes")) {
                this.passengerManager.deletePassenger(this.passengerID);
                loginManager.getLogins().remove(this.login);
                System.out.println("Account deleted successfully.");
                displayMenu();
                return; // Exit the method after account deletion
                }
                else {
                System.out.println("Account deletion cancelled.");
                }
                break;
            case 5:
                System.out.println("Returning to dashboard...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        
    }
}

