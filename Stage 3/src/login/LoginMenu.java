package login;

import java.util.Scanner;
/**
 *
 * @author George Candal
 */
public class LoginMenu {
    
    private Scanner in;
    private String username;
    private String password;

    public LoginMenu(Scanner in) {
        this.in = in;
        System.out.print("Username: ");
        username = in.nextLine();
        System.out.print("Password: ");
        password = in.nextLine();
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}
    
    