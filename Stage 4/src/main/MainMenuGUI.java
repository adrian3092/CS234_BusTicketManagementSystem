/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import depot.DepotManager;
import java.awt.Color;
import javax.swing.JButton;
import login.LoginManager;

/**
 * Creates the Main Menu GUI
 * @author Handsome Onojerame
 */
public class MainMenuGUI extends javax.swing.JFrame {
    
    private Database database;
    private LoginManager loginManager;
    private ScheduleManager scheduleManager;
    private RouteManager routeManager;
    private DepotManager depotManager;

    /**
     * Creates new form MainMenuGUI with all managers
     * @param loginManager the login manager
     * @param scheduleManager the schedule manager
     * @param routeManager the route manager
     * @param depotManager the depot manager
     */
    public MainMenuGUI(Database initialDatabase) {
        initComponents();
        this.database = initialDatabase;
        this.loginManager = database.getLoginManager();
        this.scheduleManager = database.getScheduleManager();
        this.routeManager = database.getRouteManager();
        this.depotManager = database.getDepotManager();
        setAutoRequestFocus(false);
        
        // add hover effect for buttons
        addHoverEffect(bookTicketMainMenuBtn, Color.WHITE, Color.GREEN);
        addHoverEffect(ViewSchedulesMainMenuBtn, Color.WHITE, Color.GREEN);
        addHoverEffect(EmployeeLoginMainMenuBtn, Color.WHITE, Color.GREEN);
        
        // set consistent size for all buttons
        int buttonWidth = 160;
        int buttonHeight = 23;
        
        // set size for all buttons
        bookTicketMainMenuBtn.setPreferredSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        bookTicketMainMenuBtn.setMinimumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        bookTicketMainMenuBtn.setMaximumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        
        ViewSchedulesMainMenuBtn.setPreferredSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        ViewSchedulesMainMenuBtn.setMinimumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        ViewSchedulesMainMenuBtn.setMaximumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        
        EmployeeLoginMainMenuBtn.setPreferredSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        EmployeeLoginMainMenuBtn.setMinimumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        EmployeeLoginMainMenuBtn.setMaximumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
        
        // add font setting for buttons
        java.awt.Font buttonFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        bookTicketMainMenuBtn.setFont(buttonFont);
        ViewSchedulesMainMenuBtn.setFont(buttonFont);
        EmployeeLoginMainMenuBtn.setFont(buttonFont);
        
        // set consistent text alignment and padding
        bookTicketMainMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ViewSchedulesMainMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeLoginMainMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        // set consistent margin for all buttons
        java.awt.Insets buttonMargin = new java.awt.Insets(5, 10, 5, 10);
        bookTicketMainMenuBtn.setMargin(buttonMargin);
        ViewSchedulesMainMenuBtn.setMargin(buttonMargin);
        EmployeeLoginMainMenuBtn.setMargin(buttonMargin);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuContentPanel = new javax.swing.JPanel();
        bookTicketMainMenuBtn = new javax.swing.JButton();
        ViewSchedulesMainMenuBtn = new javax.swing.JButton();
        EmployeeLoginMainMenuBtn = new javax.swing.JButton();
        welcomeHeader1 = new javax.swing.JLabel();
        welcomeHeader2 = new javax.swing.JLabel();
        backGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setLocation(new java.awt.Point(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        mainMenuContentPanel.setOpaque(false);

        bookTicketMainMenuBtn.setText("Book Ticket");
        bookTicketMainMenuBtn.setBorderPainted(false);
        bookTicketMainMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookTicketMainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTicketMainMenuBtnActionPerformed(evt);
            }
        });

        ViewSchedulesMainMenuBtn.setText("View Schedules");
        ViewSchedulesMainMenuBtn.setBorderPainted(false);
        ViewSchedulesMainMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewSchedulesMainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewSchedulesMainMenuBtnActionPerformed(evt);
            }
        });

        EmployeeLoginMainMenuBtn.setText("Employee Login");
        EmployeeLoginMainMenuBtn.setBorderPainted(false);
        EmployeeLoginMainMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EmployeeLoginMainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeLoginMainMenuBtnActionPerformed(evt);
            }
        });

        welcomeHeader1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        welcomeHeader1.setText("WELCOME TO BUS ");

        welcomeHeader2.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        welcomeHeader2.setForeground(new java.awt.Color(0, 153, 0));
        welcomeHeader2.setText("COMPANY!");

        javax.swing.GroupLayout mainMenuContentPanelLayout = new javax.swing.GroupLayout(mainMenuContentPanel);
        mainMenuContentPanel.setLayout(mainMenuContentPanelLayout);
        mainMenuContentPanelLayout.setHorizontalGroup(
            mainMenuContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuContentPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(mainMenuContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainMenuContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(EmployeeLoginMainMenuBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(ViewSchedulesMainMenuBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookTicketMainMenuBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(welcomeHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        mainMenuContentPanelLayout.setVerticalGroup(
            mainMenuContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuContentPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(welcomeHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeHeader2)
                .addGap(64, 64, 64)
                .addComponent(bookTicketMainMenuBtn)
                .addGap(18, 18, 18)
                .addComponent(ViewSchedulesMainMenuBtn)
                .addGap(18, 18, 18)
                .addComponent(EmployeeLoginMainMenuBtn)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        getContentPane().add(mainMenuContentPanel);
        mainMenuContentPanel.setBounds(0, 0, 720, 460);

        backGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/background.png"))); // NOI18N
        backGround.setLabelFor(this);
        backGround.setText("background");
        getContentPane().add(backGround);
        backGround.setBounds(0, 0, 715, 460);

        setSize(new java.awt.Dimension(725, 470));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Creates a new PassengerLoginGUI when book ticket is clicked
     * @param evt 
     */
    private void bookTicketMainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTicketMainMenuBtnActionPerformed

        new PassengerLoginGUI(database).setVisible(true);

    }//GEN-LAST:event_bookTicketMainMenuBtnActionPerformed

    /**
     * Creates a new ViewScheduleGUI when the View Schedules button is clicked
     * @param evt 
     */
    private void ViewSchedulesMainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewSchedulesMainMenuBtnActionPerformed

        if (scheduleManager != null && routeManager != null && depotManager != null) {
            new ViewScheduleGUI(scheduleManager, routeManager, depotManager).setVisible(true);
        } else {
            // Create new instances if not provided
            ScheduleManager sm = new ScheduleManager();
            RouteManager rm = new RouteManager();
            DepotManager dm = new DepotManager();
            
            // Load data
            sm.loadSchedulesFromCSV(rm, dm);
            
            new ViewScheduleGUI(sm, rm, dm).setVisible(true);
        }
    }//GEN-LAST:event_ViewSchedulesMainMenuBtnActionPerformed

    /**
     * Creates a LoginGUI when Employee Login is clicked
     * @param evt 
     */
    private void EmployeeLoginMainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeLoginMainMenuBtnActionPerformed

        new LoginGUI(database).setVisible(true);

    }//GEN-LAST:event_EmployeeLoginMainMenuBtnActionPerformed

    /**
     * Saves all data to CSV and exits program when window is closed
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Save data to CSV when window is closed
        database.saveData();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing
    
    /**
     * gets the database instance
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }
    
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EmployeeLoginMainMenuBtn;
    private javax.swing.JButton ViewSchedulesMainMenuBtn;
    private javax.swing.JLabel backGround;
    private javax.swing.JButton bookTicketMainMenuBtn;
    private javax.swing.JPanel mainMenuContentPanel;
    private javax.swing.JLabel welcomeHeader1;
    private javax.swing.JLabel welcomeHeader2;
    // End of variables declaration//GEN-END:variables
}
