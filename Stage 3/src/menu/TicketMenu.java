package menu;

import java.util.Scanner;

import ticket.Ticket;
import main.Passenger;

public class TicketMenu {
    
    private Scanner in;
    private int menuOption;

    public TicketMenu(Scanner in) {

        this.in = in;
        menuOption = 0;

    }

    /**
     * display the ticket menu
     */
    public void displayMenu() {
        System.out.print("Which schedule would you like to purchase a ticket for? ");
        String schedule = in.nextLine();
        System.out.print("Please enter your first and last name: ");
        String name = in.nextLine();
        System.out.print("Please enter your email: ");
        String email = in.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        Passenger passenger = new Passenger(name, email, phoneNumber);
        
    }
}
