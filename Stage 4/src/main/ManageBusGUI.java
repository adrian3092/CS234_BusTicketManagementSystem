
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Handsome Onojerame
 */
public class ManageBusGUI extends javax.swing.JFrame {
    
    private Database database;
    private AdminMenuGUI adminMenuGUI;
    
    /**
     * Creates new form ManageBusGUI
     */
    public ManageBusGUI(Database database, AdminMenuGUI adminMenuGUI) {
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        initComponents();
        setLocationRelativeTo(adminMenuGUI);
        styleTextField(busIDTxt, "Bus ID");
        styleLabel(busIDlbl);
        
        // add a TableModelListener to watch for edits to the mileage, capacity and status column
        busInfoTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                // only handle updates (not inserts or deletes)
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getFirstRow();
                    int column = event.getColumn();
                    
                    // check if the mileage column was edited
                    if (column == 3) {
                        updateBusMileage(row);
                    }
                    // check if the seating capacity column was edited
                    else if (column == 4) {
                        updateBusCapacity(row);
                    }
                    // check if the status column was edited
                    else if (column == 5) {
                        updateBusStatus(row);
                    }
                }
            }
        });
    }
    
    /**
     * update the bus mileage when the mileage column is edited
     * @param row the row that was edited
     */
    private void updateBusMileage(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) busInfoTable.getModel();

        // get the bus ID from the text field
        int busId = Integer.parseInt(busIDTxt.getText());

        // get the bus from the database
        bus.Bus bus = database.getBusManager().findBusById(busId);

        if (bus != null) {
            // get the new mileage value from the table
            Object value = model.getValueAt(row, 3);
            int mileage;

            // convert the value to an integer
            if (value instanceof Integer) {
                mileage = (Integer) value;
            } else {
                mileage = Integer.parseInt(value.toString());
            }

            // update the bus mileage
            bus.setMileage(mileage);

            // save changes to CSV
            database.getBusManager().saveBusesToCSV();

            // refresh the bus table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateBusTable();
            }
        }
    }
    
    /**
     * update the bus capacity when the capacity column is edited
     * @param row the row that was edited
     */
    private void updateBusCapacity(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) busInfoTable.getModel();

        // get the bus ID from the text field
        int busId = Integer.parseInt(busIDTxt.getText());

        // get the bus from the database
        bus.Bus bus = database.getBusManager().findBusById(busId);

        if (bus != null) {
            // get the new capacity value from the table
            Object value = model.getValueAt(row, 4);
            int capacity;

            // convert the value to an integer
            if (value instanceof Integer) {
                capacity = (Integer) value;
            } else {
                capacity = Integer.parseInt(value.toString());
            }

            // update the bus capacity
            bus.setCapacity(capacity);

            // save changes to CSV
            database.getBusManager().saveBusesToCSV();

            // refresh the bus table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateBusTable();
            }
        }
    }
    
    /**
     * update the bus status when the status column is edited
     * @param row the row that was edited
     */
    private void updateBusStatus(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) busInfoTable.getModel();

        // get the bus ID from the text field
        int busId = Integer.parseInt(busIDTxt.getText());

        // get the bus from the database
        bus.Bus bus = database.getBusManager().findBusById(busId);

        if (bus != null) {
            // get the new status value from the table
            Object value = model.getValueAt(row, 5);
            String status = value.toString();

            // update the bus status
            bus.setStatus(status);

            // save changes to CSV
            database.getBusManager().saveBusesToCSV();

            // refresh the bus table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateBusTable();
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
        busIDTxt = new javax.swing.JTextField();
        busIDlbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        busInfoTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Bus");
        setBackground(new java.awt.Color(215, 224, 223));

        jPanel1.setBackground(new java.awt.Color(215, 224, 223));

        busIDTxt.setText("Bus ID");
        busIDTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busIDTxtActionPerformed(evt);
            }
        });

        busIDlbl.setLabelFor(busIDTxt);
        busIDlbl.setText("Enter a bus Id:");

        busInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Year", "Make", "Model", "Mileage", "Seating Capacity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(busInfoTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(busIDTxt)
                    .addComponent(busIDlbl, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(busIDlbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    /**
     * populate bus info table
     * @param evt 
     */
    private void busIDTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busIDTxtActionPerformed
        int busId = Integer.parseInt(busIDTxt.getText());
        
        // get all buses from the BusManager
        bus.Bus bus = database.getBusManager().findBusById(busId);
        
        // create a table model with the appropriate columns
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) busInfoTable.getModel();
        
        // clear existing rows
        model.setRowCount(0);
        
        // add bus info to the table
        model.addRow(new Object[]{
                bus.getYear(),
                bus.getMake(),
                bus.getModel(),
                bus.getMileage(),
                bus.getCapacity(),
                bus.getStatus()
            });
    }//GEN-LAST:event_busIDTxtActionPerformed

    /**
     * Styling for text fields
     * @param field
     * @param placeholder 
     */
    private void styleTextField(JTextField field, String placeholder) {
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
     * Styling for the labels
     * @param label 
     */
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(Color.DARK_GRAY);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busIDTxt;
    private javax.swing.JLabel busIDlbl;
    private javax.swing.JTable busInfoTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
