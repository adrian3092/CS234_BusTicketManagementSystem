
package menu;

import java.util.Scanner;
/**
 *
 * @author Owner
 */
public class DriverMenu {
    //This menu needs to show driver schedule, bus, and route information
    private Scanner in;
    private String menuTitle;
    private String[] menuOptions;
    private String menuPrompt;
    private String menuExit;

    public DriverMenu(Scanner in) {
        this.in = in;
        this.menuTitle = "Driver Menu";
        this.menuOptions = new String[] {
            "View Schedule",
            "View Bus Information",
            "View Route Information",
            "Exit"
        };
        this.menuPrompt = "Please select an option: ";
        this.menuExit = "Exiting Driver Menu...";
    }
    public void displayMenu() {
        System.out.println(menuTitle);
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
        System.out.print(menuPrompt);
    }

    public void handleMenuSelection(int selection) {
        switch (selection) {
            case 1:
                System.out.println("Viewing Schedule...");
                
                // Add code to view schedule
                break;
            case 2:
                System.out.println("Viewing Bus Information...");
                // Add code to view bus information
                break;
            case 3:
                System.out.println("Viewing Route Information...");
                // Add code to view route information
                break;
            case 4:
                System.out.println(menuExit);
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }
    public void run() {
        int selection = 0;
        while (selection != 4) {
            displayMenu();
            selection = in.nextInt();
            handleMenuSelection(selection);
        }
    }
}
