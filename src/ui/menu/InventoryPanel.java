package ui.menu;

import main.mainFrame;
import model.Player;
import model.Weapon;
import model.Armor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryPanel extends javax.swing.JPanel {
    private mainFrame mainFrame;
    private Player player;

    private JLabel lblStats;
    private JList<Object> listItems;
    private DefaultListModel<Object> listModel;
    private JButton btnEquip;
    private JButton btnDrop;
    private JButton btnBack;
    private JLabel lblDetail;

    public InventoryPanel(mainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.player = (mainFrame != null) ? mainFrame.getPlayer() : null;
        initComponents();
        refreshAll();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblStats = new JLabel("Stats: -");
        add(lblStats, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        listItems = new JList<>(listModel);
        listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listItems.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Weapon) {
                    Weapon w = (Weapon) value;
                    lbl.setText("Weapon: " + w.getName() + " (+" + w.getAttack() + " atk)" + (w.equals(player.getEquippedWeapon()) ? "  [EQUIPPED]" : ""));
                } else if (value instanceof Armor) {
                    Armor a = (Armor) value;
                    lbl.setText("Armor: " + a.getName() + " (+" + a.getDefense() + " HP)" + (a.equals(player.getEquippedArmor()) ? "  [EQUIPPED]" : ""));
                } else {
                    lbl.setText(value.toString());
                }
                return lbl;
            }
        });

        add(new JScrollPane(listItems), BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3,1,5,5));
        btnEquip = new JButton("Equip/Unequip");
        btnDrop = new JButton("Drop");
        btnBack = new JButton("Back");
        btnPanel.add(btnEquip);
        btnPanel.add(btnDrop);
        btnPanel.add(btnBack);

        right.add(btnPanel, BorderLayout.NORTH);

        lblDetail = new JLabel(" ");
        lblDetail.setVerticalAlignment(SwingConstants.TOP);
        right.add(lblDetail, BorderLayout.CENTER);

        add(right, BorderLayout.EAST);

        // Actions
        btnEquip.addActionListener(e -> onEquip());
        btnDrop.addActionListener(e -> onDrop());
        btnBack.addActionListener(e -> onBack());

        listItems.addListSelectionListener(e -> updateDetail());
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
            return;
        }
        if (sel instanceof Weapon) {
            Weapon w = (Weapon) sel;
            lblDetail.setText("Weapon: " + w.getName() + " - Attack: +" + w.getAttack());
        } else if (sel instanceof Armor) {
            Armor a = (Armor) sel;
            lblDetail.setText("Armor: " + a.getName() + " - HP Bonus: +" + a.getDefense());
        } else {
            lblDetail.setText(sel.toString());
        }
    }

    private void onEquip() {
        Object sel = listItems.getSelectedValue();
        if (player == null) return;
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Pilih item terlebih dahulu.");
            return;
        }
        if (sel instanceof Weapon || sel instanceof Armor) {
            boolean wasEquipped = (sel instanceof Weapon && sel.equals(player.getEquippedWeapon())) || (sel instanceof Armor && sel.equals(player.getEquippedArmor()));
            if (wasEquipped) {
                player.unequipItem(sel);
                JOptionPane.showMessageDialog(this, "Item dilepas dari equip.");
            } else {
                player.equipItem(sel);
                JOptionPane.showMessageDialog(this, "Item di-equip.");
            }
            refreshAll();
        } else {
            JOptionPane.showMessageDialog(this, "Item ini tidak dapat di-equip.");
        }
    }

    private void onDrop() {
        Object sel = listItems.getSelectedValue();
        if (player == null) return;
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Pilih item terlebih dahulu.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin membuang item ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        boolean removed = player.removeItem(sel);
        if (removed) {
            JOptionPane.showMessageDialog(this, "Item dibuang dari inventory.");
            refreshAll();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal membuang item.");
        }
    }

    private void onBack() {
        if (mainFrame != null) {
            mainFrame.showPanel(new menuPanel(mainFrame));
        }
    }
}
