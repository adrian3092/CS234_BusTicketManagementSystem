
package main;

import bus.BusManager;
import depot.DepotManager;
import employees.EmployeeManagement;
import expenses.Accounting;
import login.LoginManager;
import payment.PaymentManager;
import ticket.TicketIssuer;

/**
 *
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

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    public PassengerManager getPassengerManager() {
        return passengerManager;
    }

    public PaymentManager getPaymentManager() {
        return paymentManager;
    }

    public DepotManager getDepotManager() {
        return depotManager;
    }

    public BusManager getBusManager() {
        return busManager;
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    public Accounting getAccounting() {
        return accounting;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public TicketIssuer getTicketIssuer() {
        return ticketIssuer;
    }
    
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
