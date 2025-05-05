package main;

import employees.*;
import login.LoginManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for adding a new employee to the system
 * @author Adrian Zielinski
 */
public class AddEmployeeGUI extends javax.swing.JFrame {
    
    private Database database;
    private AdminMenuGUI parentFrame;
    
    /**
     * Creates new form AddEmployeeGUI with database and parent frame
     * @param database the database instance
     * @param parentFrame the parent frame
     */
    public AddEmployeeGUI(Database database, AdminMenuGUI parentFrame) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.database = database;
        this.parentFrame = parentFrame;
        
        // set up job title combo box
        cmbJobTitle.addItem("Admin");
        cmbJobTitle.addItem("Driver");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Employee");
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        
        // First Name
        JLabel lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField(20);
        formPanel.add(lblFirstName);
        formPanel.add(txtFirstName);
        
        // Last Name
        JLabel lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField(20);
        formPanel.add(lblLastName);
        formPanel.add(txtLastName);
        
        // Job Title
        JLabel lblJobTitle = new JLabel("Job Title:");
        cmbJobTitle = new JComboBox<>();
        formPanel.add(lblJobTitle);
        formPanel.add(cmbJobTitle);
        
        // Phone Number
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        txtPhoneNumber = new JTextField(20);
        formPanel.add(lblPhoneNumber);
        formPanel.add(txtPhoneNumber);
        
        // Salary
        JLabel lblSalary = new JLabel("Salary:");
        txtSalary = new JTextField(20);
        formPanel.add(lblSalary);
        formPanel.add(txtSalary);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnAdd = new JButton("Add Employee");
        btnCancel = new JButton("Cancel");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnCancel);
        
        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Add panels to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        getContentPane().add(mainPanel);
        pack();
    }
    
    /**
     * adds a new employee based on the form data
     */
    private void addEmployee() {
        
            // get form data
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String jobTitle = (String) cmbJobTitle.getSelectedItem();
            String phoneNumber = txtPhoneNumber.getText().trim();
            float salary = Float.parseFloat(txtSalary.getText().trim());
            
            // get employee management and login manager from database
            EmployeeManagement employeeManagement = database.getEmployeeManagement();
            LoginManager loginManager = database.getLoginManager();
            
            // create employee based on job title
            if (jobTitle.equals("Admin")) {
                new Admin(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
            } else if (jobTitle.equals("Driver")) {
                new Driver(firstName, lastName, jobTitle, phoneNumber, salary, employeeManagement, loginManager);
            }
            
            // show success message
            JOptionPane.showMessageDialog(this,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            
            // refresh employee table in parent frame if available
            if (parentFrame != null) {
                parentFrame.populateEmployeeTable();
            }
            
            // close the form
            dispose();
    }
    
    // Variables declaration
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JComboBox<String> cmbJobTitle;
    private JTextField txtPhoneNumber;
    private JTextField txtSalary;
    private JButton btnAdd;
    private JButton btnCancel;
    // End of variables declaration
}
