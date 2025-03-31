
import employees.Admin;
import login.LoginManager;
import login.Login;
import java.util.Scanner;


/**
 *
 * @author George Candal
 */
public class GeorgeTester {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        Admin adminGeorge = new Admin("George", "admin");
        Login loginGeorge = new Login(adminGeorge);
        mainMenu();
    }
    
    public static void employeeLoginMenu() {
        LoginManager loginManager = new LoginManager(in);
        boolean access = loginManager.checkCredentials();
        if (access) {
            //employee menu
        }
    }
    
    public static void mainMenu() {
        
        int input;

        do {
            System.out.println("~~~Menu~~~\n1. Book Ticket\n2. View Schedule"
                    + "\n3. Employee Login\n4. Quit");
            input = in.nextInt();
            in.nextLine();                       

            switch (input) {
                case 1: 
                    //Ticket Menu
                    break;
                case 2:
                    //View Schedule
                    break;
                case 3:
                    employeeLoginMenu();                    
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;    
                default: 
                System.out.println("Invalid option");                
            }

        } while (input != 4);        
    }          
}