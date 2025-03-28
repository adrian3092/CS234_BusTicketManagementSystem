package login;

import login.Login;
import login.LoginMenu;
import java.util.Scanner;
import java.util.ArrayList;




/**
 *
 * @author George Candal
 */
public class LoginManager {
    
    private Scanner in;
    private ArrayList<Login> logins;
    private LoginMenu loginMenu;
    private String userName;
    private String password;

    public LoginManager(Scanner in) {
        this.in = in;
        logins = new ArrayList<>();
        this.loginMenu = new LoginMenu(in);
        userName = loginMenu.getUsername();
        password = loginMenu.getPassword();
        
    }
    
    public boolean checkCredentials() {
        for (Login login : logins) {
            if (login.getUsername().equals(userName)) {
                if (login.getPassword().equals(password)) {
                    return true;
                } else {
                    System.out.println("Invalid password");
                    return false;
                }
            }
        }
    System.out.println("Username not found");
    return false;
    }
    
    
}
