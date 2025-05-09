
package main;

import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.Accounting;
import login.LoginManager;
import payment.PaymentManager;
import ticket.TicketIssuer;

/**
 * This class represents a database that holds an instance of all of the 
 * manager classes
 * @author George Candal
 */
public class Database {
    
    private LoginManager loginManager;
    private RouteManager routeManager;
    private EmployeeManagement employeeManagement;
    private PassengerManager passengerManager;
    private PaymentManager paymentManager;
    private DepotManager depotManager;
    private BusManager busManager;
    private ScheduleManager scheduleManager;
    private Accounting accounting;
    private Dispatcher dispatcher;
    private TicketIssuer ticketIssuer;
    
    /**
     * Constructor for the class loads all of the data from CSV files
     */
    public Database() {
        // Create all manager objects
        this.loginManager = new LoginManager();
        this.routeManager = new RouteManager();
        this.employeeManagement = new EmployeeManagement();
        this.passengerManager = new PassengerManager();
        this.paymentManager = new PaymentManager();
        this.depotManager = new DepotManager();
        this.busManager = new BusManager(depotManager);
        this.scheduleManager = new ScheduleManager();
        this.accounting = new Accounting(busManager, depotManager, employeeManagement);
        this.ticketIssuer = new TicketIssuer();
        
        // load data from CSV
        passengerManager.loadPassengersFromCSV();
        paymentManager.loadPaymentsFromCSV(passengerManager);
        employeeManagement.loadEmployeesFromCSV(loginManager);
        loginManager.loadLoginsFromCSV(employeeManagement, passengerManager);
        depotManager.loadDepotBusAssignmentsFromCSV(busManager);
        scheduleManager.loadSchedulesFromCSV(routeManager, depotManager);
        ticketIssuer.getTicketManager().loadTicketsFromCSV(passengerManager, scheduleManager);
        accounting.loadExpensesFromCSV();
        
        // Create dispatcher after loading employees
        this.dispatcher = new Dispatcher(busManager, routeManager, employeeManagement);
        
        // Create main menu gui
        new MainMenuGUI(this).setVisible(true);
        
    }

    /**
     * Getter for LoginManager
     * @return LoginManager
     */
    public LoginManager getLoginManager() {
        return loginManager;
    }

    /**
     * Getter for RouteManager
     * @return RouteManager
     */
    public RouteManager getRouteManager() {
        return routeManager;
    }

    /**
     * Getter for EmployeeManagement
     * @return EmployeeManagement
     */
    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    /**
     * Getter for PassengerManager
     * @return PassengerManager
     */
    public PassengerManager getPassengerManager() {
        return passengerManager;
    }

    /**
     * Getter for PaymentManager
     * @return PaymentManager
     */
    public PaymentManager getPaymentManager() {
        return paymentManager;
    }

    /**
     * Getter for DepotManager
     * @return DepotManager
     */
    public DepotManager getDepotManager() {
        return depotManager;
    }

    /**
     * Getter for BusManager
     * @return BusManager
     */
    public BusManager getBusManager() {
        return busManager;
    }

    /**
     * Getter for ScheduleManager
     * @return ScheduleManager
     */
    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    /**
     * Getter for Accounting
     * @return Accounting
     */
    public Accounting getAccounting() {
        return accounting;
    }

    /**
     * Getter for Dispatcher
     * @return Dispatcher
     */
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * Getter for TicketIssuer
     * @return TicketIssuer
     */
    public TicketIssuer getTicketIssuer() {
        return ticketIssuer;
    }
    
    /**
     * Saves the data from all of the manager classes to csv files
     */
    public void saveData() {
        busManager.saveBusesToCSV();
        depotManager.saveDepotsToCSV();
        routeManager.saveRoutesToCSV();
        scheduleManager.saveSchedulesToCSV();
        passengerManager.savePassengersToCSV();
        paymentManager.savePaymentsToCSV();
        employeeManagement.saveEmployeesToCSV();
        loginManager.saveLoginsToCSV();
        ticketIssuer.saveTicketsToCSV();
        accounting.saveExpensesToCSV();
        dispatcher.saveDriverAssignmentsToCSV();
        dispatcher.saveRouteAssignmentsToCSV();
    }
}
