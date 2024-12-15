/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carsalesapplication.form;

import carsalesapplication.controller.Controller;
import carsalesapplication.domain.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class MainForm extends javax.swing.JFrame {
    private User user;

    /**
     * Creates new form MainForm
     * @param user
     */
    public MainForm(User user) {
        this.user = user;
        initComponents();
        this.setTitle("Car Sales Tracker");
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblMain.setText("Welcome " + user.getFirstName() +" "+ user.getLastName());
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Controller.getInstance().closeCon();
            }
        });
    }
    public MainForm() {
        initComponents();
        this.setTitle("Car Sales Tracker");
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuSeeAllSalesmen = new javax.swing.JMenuItem();
        menuAddNewCar = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuSeeAllCars = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMain.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        lblMain.setText("jLabel1");

        jMenu2.setText("Salesmen");
        jMenu2.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        menuSeeAllSalesmen.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        menuSeeAllSalesmen.setText("See all salesmen");
        menuSeeAllSalesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSeeAllSalesmenActionPerformed(evt);
            }
        });
        jMenu2.add(menuSeeAllSalesmen);

        jMenuBar1.add(jMenu2);

        menuAddNewCar.setText("Cars");
        menuAddNewCar.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jMenuItem2.setText("Add new car");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuAddNewCar.add(jMenuItem2);

        menuSeeAllCars.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        menuSeeAllCars.setText("See all cars");
        menuSeeAllCars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSeeAllCarsActionPerformed(evt);
            }
        });
        menuAddNewCar.add(menuSeeAllCars);

        jMenuBar1.add(menuAddNewCar);

        jMenu1.setText("Customers");
        jMenu1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jMenuItem3.setText("Add new customer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jMenuItem1.setText("See all customers");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Options");
        jMenu3.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jMenu4.setText("Language");
        jMenu4.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jCheckBoxMenuItem1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("English");
        jMenu4.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jCheckBoxMenuItem2.setText("Serbian");
        jMenu4.add(jCheckBoxMenuItem2);

        jMenu3.add(jMenu4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(414, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMain)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSeeAllCarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSeeAllCarsActionPerformed
        try {
            // TODO add your handling code here:
            new CarsTableForm(this, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSeeAllCarsActionPerformed

    private void menuSeeAllSalesmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSeeAllSalesmenActionPerformed
        try {
            // TODO add your handling code here:
            new UsersTableForm(this, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSeeAllSalesmenActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new AddCarForm(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new AddCustomerForm(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // TODO add your handling code here:
            new CustomersTableForm(this, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JLabel lblMain;
    private javax.swing.JMenu menuAddNewCar;
    private javax.swing.JMenuItem menuSeeAllCars;
    private javax.swing.JMenuItem menuSeeAllSalesmen;
    // End of variables declaration//GEN-END:variables
}
