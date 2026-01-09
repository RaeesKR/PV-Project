/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.map;

import main.mainFrame;
import ui.menu.*;
import javax.swing.*;
import java.awt.event.*;
import model.Player;
import ui.map.*;

public class mapPanel extends javax.swing.JPanel {

    private Player player;
    private mainFrame mainFrame;
    
    public mapPanel() {
        initComponents();
    }
    
    public mapPanel(mainFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.player = (this.mainFrame != null) ? this.mainFrame.getPlayer() : null;
        updateLevelButtons();
    }
    
        public void setPlayer(Player player) {
        this.player = player;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnLevel1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnLevel2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnLevel3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnLevel4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnLevel5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnLevel6 = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Level 1");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 250, -1, -1));

        btnLevel1.setText("Level 1");
        btnLevel1.setBorderPainted(false);
        btnLevel1.setContentAreaFilled(false);
        btnLevel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel1ActionPerformed(evt);
            }
        });
        add(btnLevel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 238, 10, 10));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Level 2");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 326, -1, -1));

        btnLevel2.setText("Level 2");
        btnLevel2.setBorderPainted(false);
        btnLevel2.setContentAreaFilled(false);
        btnLevel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel2ActionPerformed(evt);
            }
        });
        add(btnLevel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 313, 10, 10));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Level 3");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        btnLevel3.setText("Level 3");
        btnLevel3.setBorderPainted(false);
        btnLevel3.setContentAreaFilled(false);
        btnLevel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel3ActionPerformed(evt);
            }
        });
        add(btnLevel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 244, 10, 10));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Level 4");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 315, -1, -1));

        btnLevel4.setText("Level 4");
        btnLevel4.setBorderPainted(false);
        btnLevel4.setContentAreaFilled(false);
        btnLevel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel4ActionPerformed(evt);
            }
        });
        add(btnLevel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 336, 10, 10));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Level 5");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 271, -1, -1));

        btnLevel5.setText("Level 5");
        btnLevel5.setBorderPainted(false);
        btnLevel5.setContentAreaFilled(false);
        btnLevel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel5ActionPerformed(evt);
            }
        });
        add(btnLevel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 258, 10, 10));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Level 6");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 245, -1, -1));

        btnLevel6.setText("Level 6");
        btnLevel6.setBorderPainted(false);
        btnLevel6.setContentAreaFilled(false);
        btnLevel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLevel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel6ActionPerformed(evt);
            }
        });
        add(btnLevel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 266, 10, 10));

        btnback.setBorderPainted(false);
        btnback.setContentAreaFilled(false);
        btnback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        add(btnback, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 11, 41, 15));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/images/background/MAP.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLevel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel1ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel1(mainFrame));
    }//GEN-LAST:event_btnLevel1ActionPerformed

    private void btnLevel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel2ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel2(mainFrame));
    }//GEN-LAST:event_btnLevel2ActionPerformed

    private void btnLevel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel5ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel5(mainFrame));
    }//GEN-LAST:event_btnLevel5ActionPerformed

    private void btnLevel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel3ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel3(mainFrame));
    }//GEN-LAST:event_btnLevel3ActionPerformed

    private void btnLevel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel4ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel4(mainFrame));
    }//GEN-LAST:event_btnLevel4ActionPerformed

    private void btnLevel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel6ActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new mapLevel6(mainFrame));
    }//GEN-LAST:event_btnLevel6ActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        mainFrame.showPanel(new menuContinuePanel(mainFrame));
    }//GEN-LAST:event_btnbackActionPerformed


    private void updateLevelButtons() {
        int unlocked = 1;
        if (this.mainFrame != null && this.mainFrame.getPlayer() != null) {
            unlocked = this.mainFrame.getPlayer().getHighestUnlockedLevel();
        }
        btnLevel1.setEnabled(unlocked >= 1);
        btnLevel2.setEnabled(unlocked >= 2);
        btnLevel3.setEnabled(unlocked >= 3);
        btnLevel4.setEnabled(unlocked >= 4);
        btnLevel5.setEnabled(unlocked >= 5);
        btnLevel6.setEnabled(unlocked >= 6);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLevel1;
    private javax.swing.JButton btnLevel2;
    private javax.swing.JButton btnLevel3;
    private javax.swing.JButton btnLevel4;
    private javax.swing.JButton btnLevel5;
    private javax.swing.JButton btnLevel6;
    private javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
