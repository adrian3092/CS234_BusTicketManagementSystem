/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import payment.Payment;

/**
 * GUI to manage payments
 * @author George Candal
 */
public class ManagePaymentGUI extends javax.swing.JFrame {
    
    private Database database;
    private AdminMenuGUI adminMenuGUI;

    /**
     * Creates new form ManagePaymentGUI
     */
    public ManagePaymentGUI() {
        initComponents();
    }
    
    /**
     * Constructor with parameters
     * @param database
     * @param adminMenuGUI 
     */
    public ManagePaymentGUI(Database database, AdminMenuGUI adminMenuGUI) {
        initComponents();
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        
        setLocationRelativeTo(adminMenuGUI);
        
        // style components
        lblPaymentID.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        applyTextFieldEffects(txtPaymentID, "Payment ID");
        
        // add a TableModelListener to watch for edits to the mileage + capacity column
        tblPaymentInfo.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                // only handle updates (not inserts or deletes)
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getFirstRow();
                    int column = event.getColumn();
                    
                    // check if the mileage column was edited
                    if (column == 1) {
                        updatePaymentName(row);
                    }
                    // check if the seating capacity column was edited
                    else if (column == 2) {
                        updatePaymentAmount(row);
                    }
                }
            }
        });
    }
    
    /**
     * update the payment name when the payment column is edited
     * @param row the row that was edited
     */
    private void updatePaymentName(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblPaymentInfo.getModel();

        // get the payment ID from the text field
        int paymentId = 0;
        try {
            paymentId = Integer.parseInt(txtPaymentID.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Payment ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // get the payment from the database
        Payment payment = database.getPaymentManager().findPaymentById(paymentId);
        
        if (payment != null) {
        // get the new values from the table
        Object nameValue = model.getValueAt(row, 1);  
        Object costValue = model.getValueAt(row, 2);  

        String name = "";
        double cost = 0.0;

        // Handle the nameValue
        if (nameValue instanceof String) {
            name = (String) nameValue;
        } else {
            // Handle the case where nameValue is not a String
            JOptionPane.showMessageDialog(this, "Expected text for the passenger name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Handle the costValue
        if (costValue instanceof Double) {
            cost = (double) costValue;
        } else if (costValue instanceof Number) {
            cost = ((Number) costValue).doubleValue();
        } else if (costValue instanceof String) {
            try {
                cost = Double.parseDouble((String) costValue);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid cost format.  Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Handle the case where costValue is not a Double, Number, or String
            JOptionPane.showMessageDialog(this, "Unexpected cost data type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Passenger> passengers = database.getPassengerManager().getPassengers();

        // checks by passenger name if passenger already exists and creates new payment if passenger is found
        for (Passenger passenger : passengers) {
            if (name.equals(passenger.getPassengerName())) {
                payment.setPassenger(passenger);
                adminMenuGUI.populatePaymentsTable();
                return;
            }
        }

        // Creates new passenger if passenger does not exist and creates payment
        Passenger p = new Passenger(name, "N/A", "N/A", database.getPassengerManager());
        payment.setPassenger(p);
        adminMenuGUI.populatePaymentsTable();

    }
    }
    
    /**
     * update the payment amount when the cost column is edited
     * @param row the row that was edited
     */
    private void updatePaymentAmount(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblPaymentInfo.getModel();

        // get the payment ID from the text field
        int paymentId = Integer.parseInt(txtPaymentID.getText());

        // get the payment from the database
        Payment payment = database.getPaymentManager().findPaymentById(paymentId);
        double cost = 0.0;
        
        if (payment != null) {
            // get the new values from the table
            String name = (String) model.getValueAt(row, 1);
            try {
                cost = Double.parseDouble((String) model.getValueAt(row, 2));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Payment Amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            // update the payment cost
            payment.setPaymentAmount(cost);

            // refresh the bus table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populatePaymentsTable();
            }
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
        lblPaymentID = new javax.swing.JLabel();
        txtPaymentID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaymentInfo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(215, 224, 223));

        lblPaymentID.setText("Enter a Payment ID:");

        txtPaymentID.setForeground(new java.awt.Color(153, 153, 153));
        txtPaymentID.setText("Payment ID");
        txtPaymentID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtPaymentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentIDActionPerformed(evt);
            }
        });

        tblPaymentInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Payment ID", "Name", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPaymentInfo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPaymentID)
                    .addComponent(lblPaymentID, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblPaymentID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPaymentID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 250));

        setBounds(500, 100, 595, 258);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPaymentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentIDActionPerformed
        int paymentId = Integer.parseInt(txtPaymentID.getText());
        
        // get payment from PaymentManager
        Payment payment = database.getPaymentManager().findPaymentById(paymentId);
        if (payment == null) {
            return;
        }
        
        // create a table model with the appropriate columns
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblPaymentInfo.getModel();
        
        // clear existing rows
        model.setRowCount(0);
        
        // add bus info to the table
        model.addRow(new Object[]{
                payment.getPaymentId(),
                payment.getPassenger().getPassengerName(),
                payment.getPaymentAmount()
        });
    }//GEN-LAST:event_txtPaymentIDActionPerformed

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaymentID;
    private javax.swing.JTable tblPaymentInfo;
    private javax.swing.JTextField txtPaymentID;
    // End of variables declaration//GEN-END:variables
}
