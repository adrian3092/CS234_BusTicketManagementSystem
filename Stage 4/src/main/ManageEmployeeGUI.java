/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import employees.Employee;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 * Creates the Manage Employee GUI
 * @author Adrian Zielinski
 */
public class ManageEmployeeGUI extends javax.swing.JFrame {

    private Database database;
    private AdminMenuGUI adminMenuGUI;
    private Employee currentEmployee;
    
    /**
     * Creates new form manageEmployeeGUI
     */
    public ManageEmployeeGUI(Database database, AdminMenuGUI adminMenuGUI) {
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        initComponents();
        setLocationRelativeTo(adminMenuGUI);
        
        // style components
        applyTextFieldEffects(employeeIdField, "Employee ID");
        styleButton(saveButton);
        employeeLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));

        // add a TableModelListener to watch for edits
        employeeTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getFirstRow();
                    int column = event.getColumn();

                    // check which column was edited
                    if (column == 0) {
                        updateEmployeeName(row);
                    } else if (column == 1) {
                        updateEmployeeJobTitle(row);
                    } else if (column == 2) {
                        updateEmployeeEmail(row);
                    } else if (column == 3) {
                        updateEmployeePhoneNumber(row);
                    } else if (column == 4) {
                        updateEmployeeSalary(row);
                    }
                }
            }
        });
    }
    
    /**
     * update the employee name when the name column is edited
     * @param row the row that was edited
     */
    private void updateEmployeeName(int row) {
        // get the table model
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (currentEmployee != null) {
            // get the new name value from the table
            String name = (String) model.getValueAt(row, 0);
            
            // split the name into first and last name
            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";
            
            // update the employee name
            database.getEmployeeManagement().updateName(currentEmployee, firstName, lastName);
            
            // save changes to CSV
            database.getEmployeeManagement().saveEmployeesToCSV();
            
            // refresh the employee table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateEmployeeTable();
            }
        }
    }
    
    /**
     * update the employee job title when the job title column is edited
     * @param row the row that was edited
     */
    private void updateEmployeeJobTitle(int row) {
        // get the table model
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (currentEmployee != null) {
            // get the new job title value from the table
            String jobTitle = (String) model.getValueAt(row, 1);
            
            // update the employee job title
            database.getEmployeeManagement().setJobTitle(currentEmployee, jobTitle);
            
            // save changes to CSV
            database.getEmployeeManagement().saveEmployeesToCSV();
            
            // refresh the employee table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateEmployeeTable();
            }
        }
    }
    
    /**
     * update the employee email when the email column is edited
     * @param row the row that was edited
     */
    private void updateEmployeeEmail(int row) {
        // get the table model
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (currentEmployee != null) {
            // get the new email value from the table
            String email = (String) model.getValueAt(row, 2);
            
            // update the employee email
            database.getEmployeeManagement().updateEmail(currentEmployee, email);
            
            // save changes to CSV
            database.getEmployeeManagement().saveEmployeesToCSV();
            
            // refresh the employee table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateEmployeeTable();
            }
        }
    }
    
    /**
     * update the employee phone number when the phone number column is edited
     * @param row the row that was edited
     */
    private void updateEmployeePhoneNumber(int row) {
        // get the table model
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (currentEmployee != null) {
            // get the new phone number value from the table
            String phoneNumber = (String) model.getValueAt(row, 3);
            
            // update the employee phone number
            database.getEmployeeManagement().updatePhoneNumber(currentEmployee, phoneNumber);
            
            // save changes to CSV
            database.getEmployeeManagement().saveEmployeesToCSV();
            
            // refresh the employee table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateEmployeeTable();
            }
        }
    }
    
    /**
     * update the employee salary when the salary column is edited
     * @param row the row that was edited
     */
    private void updateEmployeeSalary(int row) {
        // get the table model
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (currentEmployee != null) {
            // get the new salary value from the table
            Object value = model.getValueAt(row, 4);
            String salaryStr = value.toString().replace("$", "").trim();
            float salary;
            
            try {
                // convert the value to a float
                salary = Float.parseFloat(salaryStr);
                
                // update the employee salary
                database.getEmployeeManagement().updateSalary(currentEmployee, salary);
                
                // save changes to CSV
                database.getEmployeeManagement().saveEmployeesToCSV();
                
                // refresh the employee table in AdminMenuGUI
                if (adminMenuGUI != null) {
                    adminMenuGUI.populateEmployeeTable();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid salary format. Please enter a valid number.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                
                // revert to the original value
                displayEmployeeInfo();
            }
        }
    }
    
    /**
     * find and display employee information based on the entered employee ID
     */
    private void findEmployee() {
        String employeeId = employeeIdField.getText().trim();
        
        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter an employee ID", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // find the employee by ID
        currentEmployee = database.getEmployeeManagement().getEmployeeById(employeeId);
        
        if (currentEmployee == null) {
            JOptionPane.showMessageDialog(this, 
                "No employee found with ID: " + employeeId, 
                "Employee Not Found", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // display the employee information in the table
        displayEmployeeInfo();
        
        // enable the save button now that we have an employee to update
        saveButton.setEnabled(true);
    }
    
    /**
     * display the current employee's information in the table
     */
    private void displayEmployeeInfo() {
        if (currentEmployee == null) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setRowCount(0); // clear existing rows
        
        // add the employee information to the table
        model.addRow(new Object[]{
            currentEmployee.getName(),
            currentEmployee.getJobTitle(),
            currentEmployee.getEmail(),
            currentEmployee.getPhoneNumber(),
            String.format("%.2f", currentEmployee.getSalary())
        });
    }
    
    /**
     * save the updated employee information
     */
    private void saveEmployeeInfo() {
        if (currentEmployee == null) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        
        if (model.getRowCount() > 0) {
            // get the updated values from the table
            String name = (String) model.getValueAt(0, 0);
            String jobTitle = (String) model.getValueAt(0, 1);
            String email = (String) model.getValueAt(0, 2);
            String phoneNumber = (String) model.getValueAt(0, 3);
            String salaryStr = (String) model.getValueAt(0, 4);
            
            // parse the salary
            salaryStr = salaryStr.replace("$", "").trim();
            float salary = Float.parseFloat(salaryStr);
            
            // update the employee information
            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";
            
            // update employee information using EmployeeManagement methods
            database.getEmployeeManagement().updateName(currentEmployee, firstName, lastName);
            database.getEmployeeManagement().setJobTitle(currentEmployee, jobTitle);
            database.getEmployeeManagement().updateEmail(currentEmployee, email);
            database.getEmployeeManagement().updatePhoneNumber(currentEmployee, phoneNumber);
            database.getEmployeeManagement().updateSalary(currentEmployee, salary);
            
            // save changes to CSV
            database.getEmployeeManagement().saveEmployeesToCSV();
            
            // refresh the employee table in AdminMenuGUI
            adminMenuGUI.populateEmployeeTable();
            
            JOptionPane.showMessageDialog(this, 
                "Employee information updated successfully!", 
                "Update Successful", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // clear the form for next use
            employeeIdField.setText("");
            currentEmployee = null;
            DefaultTableModel tableModel = (DefaultTableModel) employeeTable.getModel();
            tableModel.setRowCount(0);
            saveButton.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        employeeLbl = new javax.swing.JLabel();
        employeeIdField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Employee Information");

        jPanel1.setBackground(new java.awt.Color(215, 224, 223));

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Job Title", "Email", "Phone Number", "Salary"
            }
        ));
        jScrollPane1.setViewportView(employeeTable);

        employeeLbl.setText("Enter employee ID:");

        employeeIdField.setForeground(new java.awt.Color(153, 153, 153));
        employeeIdField.setText("Employee ID");
        employeeIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeIdFieldActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(employeeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(employeeLbl))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(employeeLbl)
                .addGap(18, 18, 18)
                .addComponent(employeeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Calls findEmployee() when enter is hit in the Employee ID field
     * @param evt 
     */
    private void employeeIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeIdFieldActionPerformed
        findEmployee();
    }//GEN-LAST:event_employeeIdFieldActionPerformed
    
   
    /**
     * Calls saveEmployeeInfo when the save button is clicked
     * @param evt 
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveEmployeeInfo();
    }//GEN-LAST:event_saveButtonActionPerformed
    
    /**
     * applies text field effects
     * @param field
     * @param placeholder 
     */
    private void applyTextFieldEffects(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.setText(placeholder);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
                field.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
                field.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
        });
    }
    
    /**
     * Styles any buttons passed as a parameter
     * @param button 
     */
    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(160, 23));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Color hover = Color.green;

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeIdField;
    private javax.swing.JLabel employeeLbl;
    private javax.swing.JTable employeeTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
