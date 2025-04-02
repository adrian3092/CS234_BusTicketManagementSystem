package login;

import employees.Employee;


/**
 *
 * @author George Candal
 */
public class Login {
    
    private String username;
    private String password;
    private Employee employee;
    private LoginManager loginManager;

    public Login(Employee employee, LoginManager loginManager) {
        this.employee = employee;
        this.username = employee.getEmail();
        this.password = "password"; //default password
        loginManager.getLogins().add(this);
        
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

    public Employee getEmployee() {
        return employee;
    }
    
    
}
