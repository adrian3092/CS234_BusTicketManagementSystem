
package main;

import depot.DepotManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * GUI for the passenger dashboard
 * @author Handsome Onojerame
 */
public class passengerDashboardGUI extends javax.swing.JFrame {
    private ScheduleManager scheduleManager;
    private RouteManager routeManager;
    private DepotManager depotManager;
    private Passenger passenger;
    private PassengerManager passengerManager;
    private Database db;
    
    /**
     * Creates new form passengerDashboardGUI
     */
    public passengerDashboardGUI() {
        initComponents();
    }

    /**
     * Constructor with parameters
     * @param database
     * @param passengerID 
     */
    public passengerDashboardGUI(Database database, String passengerID) {
        initComponents();
        this.db = database;
        this.scheduleManager = database.getScheduleManager();
        this.routeManager = database.getRouteManager();
        this.depotManager = database.getDepotManager();
        this.passengerManager = database.getPassengerManager();
        this.passenger = passengerManager.getPassengerById(passengerID);
        welcomelbl.setText("Welcome " + this.passenger.getPassengerName());
        
        // color buttons
        addHoverEffect(ticketHistoryBtn, Color.WHITE, Color.GREEN);
        addHoverEffect(updateProfileBtn, Color.WHITE, Color.GREEN);
        addHoverEffect(logoutBtn, Color.WHITE, Color.GREEN);
        addHoverEffect(bookTicketBtn, Color.WHITE, Color.GREEN);
        // set fonts
        ticketHistoryBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        ticketHistoryBtn.setPreferredSize(new Dimension(132, 23));
        updateProfileBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        updateProfileBtn.setPreferredSize(new Dimension(132, 23));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        logoutBtn.setPreferredSize(new Dimension(132, 23));
        bookTicketBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        bookTicketBtn.setPreferredSize(new Dimension(132, 23));
        welcomelbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        schedulelbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
        new String [] { "Name", "Route", "Start Time", "Departure Time", "Stops" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevents editing
            }
        };
        passengerScheduleTable.setModel(model);
        passengerScheduleTable.setRowSelectionAllowed(true);
        passengerScheduleTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        styleTable(passengerScheduleTable);
        populateScheduleTable();
        
        
        setLocationRelativeTo(null);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        passengerScheduleTable = new javax.swing.JTable();
        bookTicketBtn = new javax.swing.JButton();
        ticketHistoryBtn = new javax.swing.JButton();
        updateProfileBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        welcomelbl = new javax.swing.JLabel();
        schedulelbl = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Passenger Dashboard");

        jPanel3.setBackground(new java.awt.Color(16, 32, 47));

        jScrollPane1.setBackground(new java.awt.Color(215, 224, 223));

        passengerScheduleTable.setBackground(new java.awt.Color(16, 32, 47));
        passengerScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Route", "Start Time", "Departure Time", "Stops"
            }
        ));
        jScrollPane1.setViewportView(passengerScheduleTable);

        bookTicketBtn.setText("Book Ticket");
        bookTicketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTicketBtnActionPerformed(evt);
            }
        });

        ticketHistoryBtn.setText("View Ticket History");
        ticketHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketHistoryBtnActionPerformed(evt);
            }
        });

        updateProfileBtn.setText("Update Profile");
        updateProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileBtnActionPerformed(evt);
            }
        });

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        welcomelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        welcomelbl.setForeground(new java.awt.Color(255, 255, 255));
        welcomelbl.setText("jLabel1");

        schedulelbl.setForeground(new java.awt.Color(255, 255, 255));
        schedulelbl.setText("Select a schedule to book a ticket:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(welcomelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ticketHistoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bookTicketBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schedulelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(welcomelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(schedulelbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ticketHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateProfileBtn)
                        .addGap(18, 18, 18)
                        .addComponent(bookTicketBtn)
                        .addGap(18, 18, 18)
                        .addComponent(logoutBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(890, 525));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bookTicketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTicketBtnActionPerformed
        int selectedRow = passengerScheduleTable.getSelectedRow();
        if (selectedRow != -1) {
        String scheduleName = (String) passengerScheduleTable.getValueAt(selectedRow, 0);
        new TicketMenuGUI(db, passenger, scheduleName).setVisible(true);
        // You can now look it up in scheduleManager and book it
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a schedule to book.");
        }

    }//GEN-LAST:event_bookTicketBtnActionPerformed

    /**
     * Closes the window when log out button is clicked
     * @param evt 
     */
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    /**
     * Creates PassengerProfileGUI when update profile is clicked
     * @param evt 
     */
    private void updateProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileBtnActionPerformed
        new UpdatePassengerProfileGUI(this.passenger, this.passengerManager, this).setVisible(true);
    }//GEN-LAST:event_updateProfileBtnActionPerformed

    /**
     * Creates TicketHistoryGUI when ticket history button is clicked
     * @param evt 
     */
    private void ticketHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketHistoryBtnActionPerformed
        new TicketHistoryGUI(db, passenger).setVisible(true);
    }//GEN-LAST:event_ticketHistoryBtnActionPerformed

    /**
     * Adds hover effect to buttons
     * @param button
     * @param normal
     * @param hover 
     */
    private void addHoverEffect(final JButton button, final Color normal, final Color hover) {
    button.setOpaque(true);
    button.setBackground(normal);
    button.setBorderPainted(false);

    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(hover);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(normal);
            
        }
    });
    }   
    
    /**
     * Sets the welcome label to have the passengers name
     */
    void updateWelcomelbl() {
        welcomelbl.setText("Welcome " + this.passenger.getPassengerName());
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
     * populates the schedule table
     */
    private void populateScheduleTable() {
        DefaultTableModel model = (DefaultTableModel) passengerScheduleTable.getModel();
        
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bookTicketBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTable passengerScheduleTable;
    private javax.swing.JLabel schedulelbl;
    private javax.swing.JButton ticketHistoryBtn;
    private javax.swing.JButton updateProfileBtn;
    private javax.swing.JLabel welcomelbl;
    // End of variables declaration//GEN-END:variables
}
