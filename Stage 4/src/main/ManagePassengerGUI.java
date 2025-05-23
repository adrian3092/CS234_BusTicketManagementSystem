/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * This is the GUI for managing passengers
 * @author George Candal
 */
public class ManagePassengerGUI extends javax.swing.JFrame {

    private Database database;
    private AdminMenuGUI adminMenuGUI;
    
    /**
     * Creates new form ManagePassengerGUI
     */
    public ManagePassengerGUI() {
        initComponents();
    }
    
    /**
     * Constructor with parameters
     * @param database
     * @param adminMenuGUI 
     */
    public ManagePassengerGUI(Database database, AdminMenuGUI adminMenuGUI) {
        initComponents();
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        
        setLocationRelativeTo(adminMenuGUI);
        
        // style components
        applyTextFieldEffects(txtManagePassenger, "Passenger ID");
        lblManagePassenger.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // add a TableModelListener to watch for edits to the columns
        tblManagePassenger.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                // only handle updates (not inserts or deletes)
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getFirstRow();
                    updatePassenger(row);
                    
                }
            }
        });
        
    }
    
    /**
     * update the passenger name when the row is edited
     * @param row the row that was edited
     */
    public void updatePassenger(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblManagePassenger.getModel();

        // get the ID from the text field
        String PassengerID = txtManagePassenger.getText();

        // get the passenger from the database
        Passenger p = database.getPassengerManager().getPassengerById(PassengerID);

        if (p != null) {
            // get the new  value from the table
            String name = (String) model.getValueAt(row, 1);
            String email = (String) model.getValueAt(row, 2);
            String phone = (String) model.getValueAt(row, 3);

            // update the passenger
            p.setName(name);
            p.setEmail(email);
            p.setPhoneNumber(phone);

            // save changes to CSV
            database.getPassengerManager().savePassengersToCSV();

            // refresh the bus table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populatePassengerTable();
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
        txtManagePassenger = new javax.swing.JTextField();
        lblManagePassenger = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblManagePassenger = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(215, 224, 223));

        txtManagePassenger.setForeground(new java.awt.Color(153, 153, 153));
        txtManagePassenger.setText("Passenger ID");
        txtManagePassenger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtManagePassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManagePassengerActionPerformed(evt);
            }
        });

        lblManagePassenger.setText("Enter a Passenger ID:");

        tblManagePassenger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Passenger ID", "Name", "Email", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblManagePassenger);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblManagePassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManagePassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblManagePassenger)
                .addGap(18, 18, 18)
                .addComponent(txtManagePassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(500, 100, 684, 222);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Changes passenger attributes when enter is hit after changing a value in 
     * the table
     * @param evt 
     */
    private void txtManagePassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManagePassengerActionPerformed
        String passengerID = txtManagePassenger.getText();
        
        Passenger p = database.getPassengerManager().getPassengerById(passengerID);
        
        // create a table model with the appropriate columns
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblManagePassenger.getModel();
        
        // clear existing rows
        model.setRowCount(0);
        
        // add passenger info to the table
        model.addRow(new Object[]{
                p.getPassengerID(),
                p.getPassengerName(),
                p.getEmail(),
                p.getPhoneNumber()                
            });
    }//GEN-LAST:event_txtManagePassengerActionPerformed

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
    private javax.swing.JLabel lblManagePassenger;
    private javax.swing.JTable tblManagePassenger;
    private javax.swing.JTextField txtManagePassenger;
    // End of variables declaration//GEN-END:variables
}
