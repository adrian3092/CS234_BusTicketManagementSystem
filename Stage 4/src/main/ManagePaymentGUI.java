/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import payment.Payment;

/**
 *
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
    
    public ManagePaymentGUI(Database database, AdminMenuGUI adminMenuGUI) {
        initComponents();
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaymentInfo = new javax.swing.JTable();
        lblPaymentID = new javax.swing.JLabel();
        txtPaymentID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        lblPaymentID.setText("Enter a Payment ID:");

        txtPaymentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPaymentID)
                    .addComponent(lblPaymentID, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(lblPaymentID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPaymentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        setBounds(500, 100, 804, 225);
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
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagePaymentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePaymentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePaymentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePaymentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePaymentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaymentID;
    private javax.swing.JTable tblPaymentInfo;
    private javax.swing.JTextField txtPaymentID;
    // End of variables declaration//GEN-END:variables
}
