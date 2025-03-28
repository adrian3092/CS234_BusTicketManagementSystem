package login;

import Employee.Employee;


/**
 *
 * @author George Candal
 */
public class Login {
    
    private String username;
    private String password;
    private Employee employee;

    public Login(Employee employee) {
        this.employee = employee;
        this.username = employee.getEmail();
        this.password = "password";               
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
