package main;

import java.awt.Color;
import javax.swing.BorderFactory;
import login.Login;

/**
 * GUI for Passenger Sign up
 * @author Handsome Onojerame
 */
public class PassengerSignUpGUI extends javax.swing.JFrame {
    private Database database;
    
    /**
     * Creates new form PassengerSignUpGUI
     */
    public PassengerSignUpGUI(Database db) {
        this.database = db;
        initComponents();
        setLocationRelativeTo(null);
        setAutoRequestFocus(false);
        signUpBtn.setBackground(Color.white);
        signUpBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        setUpFieldFocus(nameField, "Name", false);
        setUpFieldFocus(phoneField, "Phone Number", false);
        setUpFieldFocus(emailField, "Email", false);
        setUpFieldFocus(passwordField, "Password", true);
        setUpFieldFocus(confirmPasswordField, "Confirm Password", true);

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
        nameField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        signUpBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Passenger Sign Up");

        jPanel1.setBackground(new java.awt.Color(16, 32, 47));

        nameField.setForeground(new java.awt.Color(102, 102, 102));
        nameField.setText("Name");
        nameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        phoneField.setForeground(new java.awt.Color(102, 102, 102));
        phoneField.setText("Phone Number");
        phoneField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        emailField.setForeground(new java.awt.Color(102, 102, 102));
        emailField.setText("Email");
        emailField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        passwordField.setForeground(new java.awt.Color(102, 102, 102));
        passwordField.setText("Password");
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        confirmPasswordField.setForeground(new java.awt.Color(102, 102, 102));
        confirmPasswordField.setText("Confirm Password");
        confirmPasswordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        signUpBtn.setText("Sign Up");
        signUpBtn.setBorder(null);
        signUpBtn.setBorderPainted(false);
        signUpBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                signUpBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                signUpBtnFocusLost(evt);
            }
        });
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailField)
                    .addComponent(phoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(nameField)
                    .addComponent(passwordField)
                    .addComponent(confirmPasswordField)
                    .addComponent(signUpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Focus effect for sign up button
     * @param evt 
     */
    private void signUpBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_signUpBtnFocusGained
        signUpBtn.setBackground(Color.green);
    }//GEN-LAST:event_signUpBtnFocusGained

    /**
     * Focus effect when mouse is not on sign up button
     * @param evt 
     */
    private void signUpBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_signUpBtnFocusLost
        signUpBtn.setBackground(Color.white);
    }//GEN-LAST:event_signUpBtnFocusLost

    /**
     * Creates a new passenger when sign up button is clicked
     * @param evt 
     */
    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (password.equals(confirmPassword)) {
            Passenger passenger = new Passenger(name, email, phone);
            database.getPassengerManager().addPassenger(passenger);
            database.getPassengerManager().savePassengersToCSV();
            Login login = new Login(passenger, database.getLoginManager(), email, password);
            database.getLoginManager().saveLoginsToCSV();
            javax.swing.JOptionPane.showMessageDialog(this, "Sign Up Succesfull!.");
            this.dispose();
        }
        else {
            javax.swing.JOptionPane.showMessageDialog(this, "Passwords do not match, please try again");
        }
    }//GEN-LAST:event_signUpBtnActionPerformed

    /**
     * Sets up focus effect for text fields
     * @param field
     * @param placeholder
     * @param isPassword 
     */
    private void setUpFieldFocus(javax.swing.text.JTextComponent field, String placeholder, boolean isPassword) {
    field.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (field.getText().equals(placeholder)) {
                field.setText("");
                field.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 51), 1));
                field.setForeground(Color.BLACK);

                // Enable masking for password fields
                if (isPassword && field instanceof javax.swing.JPasswordField) {
                    ((javax.swing.JPasswordField) field).setEchoChar('\u2022'); // • bullet character
                }
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
            if (field.getText().isEmpty()) {
                field.setText(placeholder);
                field.setForeground(new Color(102, 102, 102));
                field.setBorder(BorderFactory.createLineBorder(Color.white, 1));

                // Disable masking to show placeholder text
                if (isPassword && field instanceof javax.swing.JPasswordField) {
                    ((javax.swing.JPasswordField) field).setEchoChar((char) 0);
                }
            }
        }
    });

    // Initially remove masking if placeholder is showing
    if (isPassword && field.getText().equals(placeholder)) {
        ((javax.swing.JPasswordField) field).setEchoChar((char) 0);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField emailField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JButton signUpBtn;
    // End of variables declaration//GEN-END:variables
}
