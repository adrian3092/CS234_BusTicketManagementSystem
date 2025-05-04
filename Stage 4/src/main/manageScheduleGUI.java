/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Adrian Zielinski
 */
public class manageScheduleGUI extends javax.swing.JFrame {

    private Database database;
    private AdminMenuGUI adminMenuGUI;
    
    /**
     * Creates new form manageScheduleGUI
     */
    public manageScheduleGUI(Database database, AdminMenuGUI adminMenuGUI) {
        this.database = database;
        this.adminMenuGUI = adminMenuGUI;
        initComponents();
        
        // add action listener to the text field
        scheduleNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleNameTxtActionPerformed(evt);
            }
        });
        
        // add a TableModelListener to watch for edits to the schedule table
        scheduleTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                // only handle updates (not inserts or deletes)
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getFirstRow();
                    int column = event.getColumn();
                    
                    // check if the name column was edited
                    if (column == 0) {
                        updateScheduleName(row);
                    }
                    // check if the route ID column was edited
                    else if (column == 1) {
                        updateScheduleRoute(row);
                    }
                    // check if the depot ID column was edited
                    else if (column == 2) {
                        updateScheduleDepot(row);
                    }
                    // check if the start time column was edited
                    else if (column == 3) {
                        updateScheduleStartTime(row);
                    }
                }
            }
        });
    }
    
    /**
     * update the schedule route when the route ID column is edited
     * @param row the row that was edited
     */
    private void updateScheduleRoute(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) scheduleTable.getModel();

        // get the schedule name from the text field
        String scheduleName = scheduleNameTxt.getText();

        // get the schedule from the database
        Schedule schedule = database.getScheduleManager().getScheduleByName(scheduleName);

        if (schedule != null) {
            // get the new route ID value from the table
            Object value = model.getValueAt(row, 1);
            String routeId = value.toString();

            // find the route by ID
            Route route = database.getRouteManager().getRouteById(routeId);
            
            if (route != null) {
                // update the schedule route
                schedule.setRoute(route);

                // save changes to CSV
                database.getScheduleManager().saveSchedulesToCSV();

                // refresh the schedule table in AdminMenuGUI
                if (adminMenuGUI != null) {
                    adminMenuGUI.populateScheduleTable();
                }
            }
        }
    }
    
    /**
     * update the schedule depot when the depot ID column is edited
     * @param row the row that was edited
     */
    private void updateScheduleDepot(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) scheduleTable.getModel();

        // get the schedule name from the text field
        String scheduleName = scheduleNameTxt.getText();

        // get the schedule from the database
        Schedule schedule = database.getScheduleManager().getScheduleByName(scheduleName);

        if (schedule != null) {
            // get the new depot ID value from the table
            Object value = model.getValueAt(row, 2);
            int depotId;

            // convert the value to an integer
            if (value instanceof Integer) {
                depotId = (Integer) value;
            } else {
                depotId = Integer.parseInt(value.toString());
            }

            // find the depot by ID
            depot.Depot depot = database.getDepotManager().findDepotById(depotId);
            
            if (depot != null) {
                // update the schedule depot
                schedule.setDepot(depot);

                // save changes to CSV
                database.getScheduleManager().saveSchedulesToCSV();

                // refresh the schedule table in AdminMenuGUI
                if (adminMenuGUI != null) {
                    adminMenuGUI.populateScheduleTable();
                }
            }
        }
    }
    
    /**
     * update the schedule name when the name column is edited
     * @param row the row that was edited
     */
    private void updateScheduleName(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) scheduleTable.getModel();

        // get the old schedule name from the text field
        String oldScheduleName = scheduleNameTxt.getText();

        // get the schedule from the database
        Schedule schedule = database.getScheduleManager().getScheduleByName(oldScheduleName);

        if (schedule != null) {
            // get the new name value from the table
            Object value = model.getValueAt(row, 0);
            String newName = value.toString();

            // update the schedule name
            schedule.setName(newName);
            
            // update the text field with the new name
            scheduleNameTxt.setText(newName);

            // save changes to CSV
            database.getScheduleManager().saveSchedulesToCSV();

            // refresh the schedule table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateScheduleTable();
            }
        }
    }
    
    /**
     * update the schedule start time when the start time column is edited
     * @param row the row that was edited
     */
    private void updateScheduleStartTime(int row) {
        // get the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) scheduleTable.getModel();

        // get the schedule name from the text field
        String scheduleName = scheduleNameTxt.getText();

        // get the schedule from the database
        Schedule schedule = database.getScheduleManager().getScheduleByName(scheduleName);

        if (schedule != null) {
            // get the new start time value from the table
            Object value = model.getValueAt(row, 3);
            double startTime;

            // convert the value to a double
            if (value instanceof Double) {
                startTime = (Double) value;
            } else {
                startTime = Double.parseDouble(value.toString());
            }

            // update the schedule start time
            schedule.setStartTime(startTime);

            // save changes to CSV
            database.getScheduleManager().saveSchedulesToCSV();

            // refresh the schedule table in AdminMenuGUI
            if (adminMenuGUI != null) {
                adminMenuGUI.populateScheduleTable();
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

        manageSchedulePanel = new javax.swing.JPanel();
        scheduleNameLbl = new javax.swing.JLabel();
        scheduleNameTxt = new javax.swing.JTextField();
        scheduleTablePanel = new javax.swing.JPanel();
        scheduleTablePane = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(215, 224, 223));

        manageSchedulePanel.setBackground(new java.awt.Color(215, 224, 223));

        scheduleNameLbl.setText("Enter Schedule Name:");

        scheduleNameTxt.setText("Schedule Name");

        javax.swing.GroupLayout manageSchedulePanelLayout = new javax.swing.GroupLayout(manageSchedulePanel);
        manageSchedulePanel.setLayout(manageSchedulePanelLayout);
        manageSchedulePanelLayout.setHorizontalGroup(
            manageSchedulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageSchedulePanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(manageSchedulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scheduleNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(scheduleNameTxt))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        manageSchedulePanelLayout.setVerticalGroup(
            manageSchedulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageSchedulePanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(scheduleNameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scheduleNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        scheduleTablePanel.setBackground(new java.awt.Color(215, 224, 223));

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Route ID", "Depot ID", "Start Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scheduleTablePane.setViewportView(scheduleTable);

        javax.swing.GroupLayout scheduleTablePanelLayout = new javax.swing.GroupLayout(scheduleTablePanel);
        scheduleTablePanel.setLayout(scheduleTablePanelLayout);
        scheduleTablePanelLayout.setHorizontalGroup(
            scheduleTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, scheduleTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scheduleTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );
        scheduleTablePanelLayout.setVerticalGroup(
            scheduleTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scheduleTablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageSchedulePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scheduleTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageSchedulePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scheduleTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * populate schedule info table
     * @param evt 
     */
    private void scheduleNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleNameTxtActionPerformed
        String scheduleName = scheduleNameTxt.getText();
        
        // get the schedule from the ScheduleManager
        Schedule schedule = database.getScheduleManager().getScheduleByName(scheduleName);
        
        if (schedule != null) {
            // create a table model with the appropriate columns
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) scheduleTable.getModel();
            
            // clear existing rows
            model.setRowCount(0);
            
            // add schedule info to the table
            String routeId = schedule.getRoute() != null ? schedule.getRoute().getRouteID() : "";
            int depotId = schedule.getDepot() != null ? schedule.getDepot().getDepotId() : 0;
            
            model.addRow(new Object[]{
                schedule.getName(),
                routeId,
                depotId,
                schedule.getStartTime()
            });
        }
    }//GEN-LAST:event_scheduleNameTxtActionPerformed

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
            java.util.logging.Logger.getLogger(manageScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new manageScheduleGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel manageSchedulePanel;
    private javax.swing.JLabel scheduleNameLbl;
    private javax.swing.JTextField scheduleNameTxt;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JScrollPane scheduleTablePane;
    private javax.swing.JPanel scheduleTablePanel;
    // End of variables declaration//GEN-END:variables
}
