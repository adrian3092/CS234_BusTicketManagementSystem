
package menu;
import java.util.Scanner;

/**
 * 
 * @author Adrian Zielinski
 */
public class BusMenu {
    
    private Scanner in;
    private int menuOption;

    // default constructor
    public BusMenu(Scanner in) {

        this.in = in;
        menuOption = 0;
        
    }

    /**
     * display the bus menu
     */
    public void displayMenu() {
        while (menuOption != 3) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Bus Management Menu");
            System.out.println("1. Add a new bus");
            System.out.println("2. Management an existing bus");
            System.out.println("3. Return to Employee Menu");

            menuOption = in.nextInt();

            switch (menuOption) {
                case 1 -> {
                    addBus();
                }
                case 2 -> {
                    manageBus();
                }
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    public void addBus() {
        
        //

    }

    public void manageBus() {

        //

    }

}

