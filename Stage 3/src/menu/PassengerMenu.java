package menu;

import java.util.Scanner;
import login.Login;
import login.LoginManager;
import main.Passenger;
import main.PassengerManager;
import main.ScheduleManager;

/**
 * PassengerMenu class provides a menu interface for passengers to log in, sign up, 
 * continue as a guest, or return to the main menu.
 * 
 * @author Handsome Onojerame
 */
public class PassengerMenu {
    private Scanner in;
    private PassengerManager passengerManager;
    private ScheduleManager scheduleManager;
    private LoginManager loginManager;
    private TicketMenu ticketMenu;

    /**
     * Constructor to initialize PassengerMenu with required managers and input scanner.
     * 
     * @param in               Scanner object for user input
     * @param passengerManager Manager to handle passenger-related operations
     * @param scheduleManager  Manager to handle schedule-related operations
     * @param loginManager     Manager to handle login-related operations
     */
    public PassengerMenu(Scanner in, PassengerManager passengerManager, ScheduleManager scheduleManager, LoginManager loginManager) {
        this.passengerManager = passengerManager;
        this.scheduleManager = scheduleManager;
        this.in = in;
        this.loginManager = loginManager;
        this.ticketMenu = new TicketMenu(in, scheduleManager, passengerManager);
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
                    String passengerID = loginManager.checkCredentials();
                    in.nextLine(); // Consume the newline character left by nextInt()
                    if (passengerID.equals("not found")) {
                        System.out.println("Passenger ID not found. Please try again.");
                    } else if (passengerID.equals("invalid")) {
                        System.out.println("Invalid credentials. Please try again.");
                        break;
                    } else if (passengerID.equals("error")) {
                        System.out.println("An error occurred during login. Please try again later.");
                    } else if (passengerID.equals("empty")) {
                        System.out.println("No passengers found. Please sign up first.");
                    }
                    if (passengerID.equals("not found")) {
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
                    {ticketMenu.displayMenu(passengerID);}
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
        System.out.println("Please enter your email: ");
        String email = in.nextLine();
        System.out.println("Please enter your phone number: ");
        String phone = in.nextLine();
        
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

        Passenger newPassenger = new Passenger(name, email, phone);
        passengerManager.addPassenger(newPassenger);
        System.out.println("Welcome " + name + "! Your account has been created successfully.");
        System.out.println("Your Passenger ID is: " + newPassenger.getPassengerID());

        new Login(newPassenger, loginManager, email, password);
    }
}
