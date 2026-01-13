/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.menu;

/**
 *
 * @author Dhenis
 */
import controllers.InventoryController;
import main.mainFrame;
import model.Player;
import model.Weapon;
import model.Armor;
import ui.map.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inventory extends javax.swing.JPanel {

    /**
     * Creates new form Inventory
     */
    private mainFrame mainFrame;
    private Player player;

    private DefaultListModel<Object> listModel;

    public Inventory() {
        initComponents();
    }
    
    public Inventory(mainFrame mainFrame) {
    this.mainFrame = mainFrame;
    this.player = (mainFrame != null) ? mainFrame.getPlayer() : null;

    initComponents();
    initLogic();
     if (player != null) {
         InventoryController ic = new InventoryController();
        ic.loadInventory(player); // AMBIL DARI DB → PLAYER
    }
    refreshAll();
    }
    
    private void initLogic() {
    listModel = new DefaultListModel<>();
    listItems.setModel(listModel);
    listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    listItems.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel lbl = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            

            if (value instanceof Weapon w) {
                lbl.setText(w.getName() + ((player != null && w.equals(player.getEquippedWeapon())) ? "  [EQUIPPED]" : ""));
                java.net.URL url = getClass().getResource(w.getImagePath());
                if (url != null) {
                    ImageIcon raw = new ImageIcon(url);
                    Image scaled = raw.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    lbl.setIcon(new ImageIcon(scaled));
                } else {
                    lbl.setIcon(null);
                }

            } else if (value instanceof Armor a) {
                lbl.setText(a.getName() + ((player != null && a.equals(player.getEquippedArmor())) ? "  [EQUIPPED]" : ""));
                java.net.URL url = getClass().getResource(a.getImagePath());
                if (url != null) {
                    ImageIcon raw = new ImageIcon(url);
                    Image scaled = raw.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    lbl.setIcon(new ImageIcon(scaled));
                } else {
                    lbl.setIcon(null);
                }
            }
            return lbl;
        }
    });

    listItems.addListSelectionListener(e -> updateDetail());

    btnEquip.addActionListener(e -> onEquip());
    btnDrop.addActionListener(e -> onDrop());
    btnBack.addActionListener(e -> onBack());
    }
    
    private void refreshAll() {
        if (player == null) {
            lblStats.setText("Stats: (no player)");
            listModel.clear();
            lblDetail.setText("");
            return;
        }
        lblStats.setText("HP: " + player.getHp() + " / " + player.getMaxHp() + "   Attack: " + player.getAttack());
        listModel.clear();
        for (Object it : player.getInventory()) {
            listModel.addElement(it);
        }
        updateDetail();
    }
    
    private void updateDetail() {
        Object sel = listItems.getSelectedValue();
        if (sel == null) {
            lblDetail.setText("Pilih item untuk melihat detail.");
            lblIcon.setIcon(null);
            return;
        }
        if (sel instanceof Weapon) {
            Weapon w = (Weapon) sel;
            lblDetail.setText("Weapon: " + w.getName() + " ( Attack: +" + w.getAttack() + " )");
            java.net.URL url = getClass().getResource(w.getImagePath());
            if (url != null) {
                ImageIcon raw = new ImageIcon(url);
                Image scaled = raw.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(scaled));
            } else {
                lblIcon.setIcon(null);
            }
        } else if (sel instanceof Armor) {
            Armor a = (Armor) sel;
            lblDetail.setText("Armor: " + a.getName() + " ( HP: +" + a.getDefense() + " )");
            java.net.URL url = getClass().getResource(a.getImagePath());
            if (url != null) {
                ImageIcon raw = new ImageIcon(url);
                Image scaled = raw.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(scaled));
            } else {
                lblIcon.setIcon(null);
            }
        } else {
            lblDetail.setText(sel.toString());
            lblIcon.setIcon(null);
        }
    }

    private void onEquip() {
            Object sel = listItems.getSelectedValue();
         if (player == null || sel == null) return;

         InventoryController ic = new InventoryController();

         boolean wasEquipped =
             (sel instanceof Weapon && sel.equals(player.getEquippedWeapon())) ||
             (sel instanceof Armor && sel.equals(player.getEquippedArmor()));

         if (wasEquipped) {
             player.unequipItem(sel); // optional (DB tidak simpan unequip spesifik)
             JOptionPane.showMessageDialog(this, "Item dilepas.");
         } else {
             ic.equipItem(player, sel); // ⬅ WAJIB
             JOptionPane.showMessageDialog(this, "Item di-equip.");
         }

         refreshAll();
    }

    private void onDrop() {
            Object sel = listItems.getSelectedValue();
        if (player == null || sel == null) return;

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin membuang item ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        InventoryController ic = new InventoryController();
        ic.deleteItem(player, sel); // ⬅ DB + MEMORY

        JOptionPane.showMessageDialog(this, "Item dibuang.");
        refreshAll();
    }

    private void onBack() {
        if (mainFrame != null) {
            mainFrame.showPanel(new mapPanel(mainFrame));
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        refreshAll();
    }

    public void refresh() {
        refreshAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblStats = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JList<>();
        panelRight = new javax.swing.JPanel();
        btnEquip = new javax.swing.JButton();
        btnDrop = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblDetail = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/images/background/UI BACKGROUND.jpg"))); // NOI18N

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStats.setBackground(new java.awt.Color(255, 0, 0));
        lblStats.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        lblStats.setForeground(new java.awt.Color(255, 255, 255));
        lblStats.setText("HP : - / - Attack : -");
        add(lblStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        listItems.setBackground(new java.awt.Color(102, 72, 50));
        listItems.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listItems.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        listItems.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(listItems);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 610, 450));

        panelRight.setBackground(new java.awt.Color(143, 94, 65));
        panelRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnEquip.setBackground(new java.awt.Color(153, 91, 52));
        btnEquip.setForeground(new java.awt.Color(255, 255, 255));
        btnEquip.setText("Equip / Unequip");
        btnEquip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEquip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEquip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEquipMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEquipMouseEntered(evt);
            }
        });
        btnEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquipActionPerformed(evt);
            }
        });

        btnDrop.setBackground(new java.awt.Color(153, 91, 52));
        btnDrop.setForeground(new java.awt.Color(255, 255, 255));
        btnDrop.setText("Delete");
        btnDrop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDrop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDrop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDropMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDropMouseEntered(evt);
            }
        });
        btnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(153, 91, 52));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblDetail.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        lblDetail.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDrop, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDrop)
                    .addComponent(btnEquip))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panelRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 780, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/images/background/UI.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\CLICK SFX.wav");
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnEquipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEquipMouseEntered
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\HOVER SFX.wav");
    }//GEN-LAST:event_btnEquipMouseEntered

    private void btnDropMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDropMouseEntered
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\HOVER SFX.wav");
    }//GEN-LAST:event_btnDropMouseEntered

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\HOVER SFX.wav");
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnEquipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEquipMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEquipMouseClicked

    private void btnDropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDropMouseClicked

    }//GEN-LAST:event_btnDropMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked

    }//GEN-LAST:event_btnBackMouseClicked

    private void btnEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquipActionPerformed
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\CLICK SFX.wav");
    }//GEN-LAST:event_btnEquipActionPerformed

    private void btnDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropActionPerformed
        // TODO add your handling code here:
        mainFrame.playSFX("C:\\Users\\Dhenis\\Documents\\NetBeansProjects\\Kyojin_Gemu\\src\\resource\\sounds\\CLICK SFX.wav");
    }//GEN-LAST:event_btnDropActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDrop;
    private javax.swing.JButton btnEquip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDetail;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblStats;
    private javax.swing.JList<Object> listItems;
    private javax.swing.JPanel panelRight;
    // End of variables declaration//GEN-END:variables
}
