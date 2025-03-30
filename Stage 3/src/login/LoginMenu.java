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
        System.out.println("Username: ");
        username = in.next();
        System.out.println("Password: ");
        password = in.next();
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}
    
    