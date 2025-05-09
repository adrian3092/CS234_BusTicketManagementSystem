package main;

import depot.DepotManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * GUI for viewing all available schedules
 * @author Adrian Zielinski
 */
public class ViewScheduleGUI extends javax.swing.JFrame {

    private ScheduleManager scheduleManager;
    private RouteManager routeManager;
    private DepotManager depotManager;
    
    /**
     * creates new form ViewScheduleGUI with default managers
     */
    public ViewScheduleGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /**
     * creates new form ViewScheduleGUI with specified managers
     * @param scheduleManager the schedule manager
     * @param routeManager the route manager
     * @param depotManager the depot manager
     */
    public ViewScheduleGUI(ScheduleManager scheduleManager, RouteManager routeManager, DepotManager depotManager) {
        initComponents();
        this.scheduleManager = scheduleManager;
        this.routeManager = routeManager;
        this.depotManager = depotManager;
        
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
        new String [] { "Name", "Route", "Start Time", "Departure Time", "Stops" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevents editing
            }
        };
        tableSchedule.setModel(model);
        tableSchedule.setRowSelectionAllowed(true);
        tableSchedule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        styleTable(tableSchedule);
        populateScheduleTable();
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Styling for the table
     * @param table 
     */
    private void styleTable(JTable table) {
    // Header styling
    JTableHeader header = table.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 13));
    header.setBackground(new Color(16, 32, 47)); // Dark gray
    header.setForeground(Color.white);
    header.setReorderingAllowed(false);

    // Row styling
    table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    table.setRowHeight(28);
    table.setGridColor(new Color(230, 230, 230));

    // Zebra striping and selection styling
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                c.setBackground(Color.green); // Light blue
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245)); // Alternating colors
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    });
    }
    /**
     * populates the schedule table with data from the schedule manager
     */
    private void populateScheduleTable() {
        DefaultTableModel model = (DefaultTableModel) tableSchedule.getModel();
        model.setRowCount(0); // clear existing rows
        
        ArrayList<Schedule> schedules = scheduleManager.getSchedules();
        
        for (Schedule schedule : schedules) {
            String name = schedule.getName();
            String routeName = schedule.getRoute() != null ? schedule.getRoute().getName() : "N/A";
            String startTime = formatTime(schedule.getStartTime());
            
            // format departure times as comma-separated list
            String departureTimes = "";
            if (schedule.getDepartureTimes() != null && !schedule.getDepartureTimes().isEmpty()) {
                for (int i = 0; i < schedule.getDepartureTimes().size(); i++) {
                    departureTimes += formatTime(schedule.getDepartureTimes().get(i));
                    if (i < schedule.getDepartureTimes().size() - 1) {
                        departureTimes += ", ";
                    }
                }
            } else {
                departureTimes = "N/A";
            }
            
            // format stops as comma-separated list
            String stops = "";
            if (schedule.getRoute() != null && schedule.getRoute().getStops() != null && 
                !schedule.getRoute().getStops().isEmpty()) {
                for (int i = 0; i < schedule.getRoute().getStops().size(); i++) {
                    stops += schedule.getRoute().getStops().get(i).getName();
                    if (i < schedule.getRoute().getStops().size() - 1) {
                        stops += ", ";
                    }
                }
            } else {
                stops = "N/A";
            }
            
            model.addRow(new Object[]{name, routeName, startTime, departureTimes, stops});
        }
    }
    
    /**
     * formats a double time value to a string in the format HH:MM
     * @param time the time as a double
     * @return the formatted time string
     */
    private String formatTime(double time) {
        int hours = (int) time;
        int minutes = (int) (((time - hours) * 100) + 0.5);
        
        return String.format("%02d:%02d", hours, minutes);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSchedule = new javax.swing.JPanel();
        scrollPaneSchedule = new javax.swing.JScrollPane();
        tableSchedule = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Today's Schedule");

        panelSchedule.setBackground(new java.awt.Color(215, 224, 223));

        tableSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Route", "Start Time", "Departure Times", "Stops"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneSchedule.setViewportView(tableSchedule);

        javax.swing.GroupLayout panelScheduleLayout = new javax.swing.GroupLayout(panelSchedule);
        panelSchedule.setLayout(panelScheduleLayout);
        panelScheduleLayout.setHorizontalGroup(
            panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScheduleLayout.createSequentialGroup()
                .addComponent(scrollPaneSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelScheduleLayout.setVerticalGroup(
            panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScheduleLayout.createSequentialGroup()
                .addComponent(scrollPaneSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSchedule, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
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
            java.util.logging.Logger.getLogger(ViewScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // For testing purposes only
                ScheduleManager scheduleManager = new ScheduleManager();
                RouteManager routeManager = new RouteManager();
                DepotManager depotManager = new DepotManager();
                
                // Load data
                scheduleManager.loadSchedulesFromCSV(routeManager, depotManager);
                
                new ViewScheduleGUI(scheduleManager, routeManager, depotManager).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelSchedule;
    private javax.swing.JScrollPane scrollPaneSchedule;
    private javax.swing.JTable tableSchedule;
    // End of variables declaration//GEN-END:variables
}
