/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import employees.Employee;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        employeeIdField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Employee Information");

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Job Title", "Email", "Phone Number", "Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        employeeIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeIdFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Enter employee ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(employeeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(employeeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeIdFieldActionPerformed
        // Get the employee ID from the text field
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
    }//GEN-LAST:event_employeeIdFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeIdField;
    private javax.swing.JTable employeeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
