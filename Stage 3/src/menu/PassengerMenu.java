
package menu;
import java.util.Scanner;
import main.Passenger;
import main.PassengerManager;
import main.ScheduleManager;
/**
 *
 * @author Owner
 */
public class PassengerMenu {
    private Scanner in;
    private PassengerManager passengerManager;
    private ScheduleManager scheduleManager;

    public PassengerMenu(Scanner in, PassengerManager passengerManager, ScheduleManager scheduleManager) {
        this.passengerManager = passengerManager;
        this.scheduleManager = scheduleManager;
        this.in = in;
    }

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
                    // Add login logic here
                    break;
                case 2:
                    System.out.println("Redirecting to sign-up...");
                    // Add sign-up logic here
                    signUp();
                    break;
                case 3:
                    System.out.println("Continuing as guest...");
                    TicketMenu ticketMenu = new TicketMenu(in, scheduleManager);
                    ticketMenu.displayMenu();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

    }

    private void login() {
        // Logic for passenger login
        
    }

    private void signUp() {
        System.out.println("Please enter your name: ");
        String name = in.nextLine();
        System.out.println("Please enter your email: ");
        String email = in.nextLine();
        System.out.println("Please enter your phone number: ");
        String phone = in.nextLine();
        System.out.println("Please enter your password: ");
        String password = in.nextLine();
        System.out.println("Please confirm your password: ");
        String confirmPassword = in.nextLine();

        Passenger newPassenger = new Passenger(name, email, phone);
        passengerManager.addPassenger(newPassenger);
        System.out.println("Welcome " + name + "! Your account has been created successfully.");
        System.out.println("Your Passenger ID is: " + newPassenger.getPassengerID());

        //need logic to send email as username and password to login manager class
    }
}
