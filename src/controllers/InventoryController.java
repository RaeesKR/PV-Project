package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kyojin_gemu.utils.DatabaseConection;
import model.*;

public class InventoryController {

    private final Connection conn = DatabaseConection.getConnection();

    // ==============================
    // 1. SIMPAN ITEM KE DATABASE
    // ==============================
    public boolean addItemToInventory(Player player, Object item) {
        if (player == null || item == null) return false;

        String itemName;
        String itemType;
        int power;

        if (item instanceof Weapon) {
            Weapon w = (Weapon) item;
            itemName = w.getName();
            itemType = "weapon";
            power = w.getAttack();
        } else if (item instanceof Armor) {
            Armor a = (Armor) item;
            itemName = a.getName();
            itemType = "armor";
            power = a.getDefense();
        } else {
            return false;
        }

        String sql = """
            INSERT INTO inventory (id_player, item_name, item_type, power, is_equipped)
            VALUES (?, ?, ?, ?, 0)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, player.getId());
            ps.setString(2, itemName);
            ps.setString(3, itemType);
            ps.setInt(4, power);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal insert inventory: " + e.getMessage());
            return false;
        }
    }

    // ==============================
    // 2. LOAD INVENTORY PLAYER
    // ==============================
  public void loadInventory(Player player) {
        if (player == null) return;

        player.clearInventory(); // ⬅ WAJIB

        String sql = "SELECT * FROM inventory WHERE id_player=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, player.getId());
            ResultSet rs = ps.executeQuery();
            
            System.out.println(rs);

            while (rs.next()) {
                String name = rs.getString("item_name");
                String type = rs.getString("item_type");
                int power = rs.getInt("power");
                boolean equipped = rs.getBoolean("is_equipped");

                Object item = null;

                if ("weapon".equals(type)) {
                    item = new Weapon(name, power, findWeaponImage(name));
                } else if ("armor".equals(type)) {
                    item = new Armor(name, power, findArmorImage(name));
                }

                if (item != null) {
                    player.addItem(item);

                    if (equipped) {
                        player.equipItem(item);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Load inventory gagal: " + e.getMessage());
        }
    }

    // ==============================
    // 3. EQUIP ITEM
    // ==============================
    public void equipItem(Player player, Object item) {
        if (player == null || item == null) return;

        unequipAll(player, item instanceof Weapon ? "weapon" : "armor");

        String sql = """
            UPDATE inventory 
            SET is_equipped = 1 
            WHERE id_player = ? AND item_name = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, player.getId());
            ps.setString(2, getItemName(item));
            ps.executeUpdate();
            player.equipItem(item);
        } catch (SQLException e) {
            System.err.println("Gagal equip item: " + e.getMessage());
        }
    }

    // ==============================
    // 4. UNEQUIP SEMUA ITEM (PER TYPE)
    // ==============================
    private void unequipAll(Player player, String type) {
        String sql = """
            UPDATE inventory 
            SET is_equipped = 0 
            WHERE id_player = ? AND item_type = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, player.getId());
            ps.setString(2, type);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal unequip: " + e.getMessage());
        }
    }

    // ==============================
    // 5. HELPER
    // ==============================
    private String getItemName(Object item) {
        if (item instanceof Weapon) return ((Weapon) item).getName();
        if (item instanceof Armor) return ((Armor) item).getName();
        return "";
    }

    private String findWeaponImage(String name) {
        return ItemCatalog.getWeapons()
                .stream()
                .filter(w -> w.getName().equals(name))
                .map(Weapon::getImagePath)
                .findFirst()
                .orElse(null);
    }

    private String findArmorImage(String name) {
        return ItemCatalog.getArmors()
                .stream()
                .filter(a -> a.getName().equals(name))
                .map(Armor::getImagePath)
                .findFirst()
                .orElse(null);
    }
    
    public void deleteItem(Player player, Object item) {
    if (player == null || item == null) return;

    String sql = """
        DELETE FROM inventory
        WHERE id_player = ?
          AND item_name = ?
          AND item_type = ?
        LIMIT 1
    """;

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, player.getId());
        ps.setString(2, getItemName(item));
        ps.setString(3, item instanceof Weapon ? "weapon" : "armor");
        ps.executeUpdate();

        player.removeItem(item);

    } catch (SQLException e) {
        System.err.println("Delete item gagal: " + e.getMessage());
    }
}

    
}
