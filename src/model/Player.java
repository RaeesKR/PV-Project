package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private int id;
    private String name;
    private int hp;
    private int attack;
    private int maxHp;
    private final List<Object> inventory = new ArrayList<>();
    private Weapon equippedWeapon = null;
    private Armor equippedArmor = null;

    public Player(int id, String name, int hp, int attack) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.maxHp = hp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        // clamp hp between 0 and maxHp
        if (this.maxHp <= 0) {
            this.hp = Math.max(0, hp);
        } else {
            this.hp = Math.max(0, Math.min(hp, this.maxHp));
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        // ensure current hp does not exceed new max
        if (this.hp > this.maxHp) this.hp = this.maxHp;
    }

    public void restoreFullHp() {
        this.hp = this.maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void addAttack(int delta) {
        this.attack += delta;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    // Max HP helper
    public void addMaxHp(int delta) {
        this.maxHp += delta;
        if (delta > 0) {
            // grant immediate HP gain when max increases
            this.hp += delta;
        } else {
            // ensure current HP does not exceed new max
            if (this.hp > this.maxHp) this.hp = this.maxHp;
        }
        if (this.maxHp < 1) this.maxHp = 1;
    }
    
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Inventory operations
    public void addItem(Object item) {
        if (item == null) return;
        inventory.add(item);
        // auto-equip if weapon/armor
        if (item instanceof Weapon) {
            equipWeapon((Weapon) item);
        } else if (item instanceof Armor) {
            equipArmor((Armor) item);
        }
    }

    public List<Object> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    private void equipWeapon(Weapon w) {
        if (w == null) return;
        // remove bonus from previous weapon
        if (equippedWeapon != null) {
            addAttack(-equippedWeapon.getAttack());
        }
        equippedWeapon = w;
        addAttack(w.getAttack());
    }

    private void equipArmor(Armor a) {
        if (a == null) return;
        // remove HP bonus from previous armor
        if (equippedArmor != null) {
            addMaxHp(-equippedArmor.getDefense());
        }
        equippedArmor = a;
        addMaxHp(a.getDefense());
    }

    public boolean equipItem(Object item) {
        if (item == null) return false;
        if (!inventory.contains(item)) return false;
        if (item instanceof Weapon) {
            equipWeapon((Weapon) item);
            return true;
        } else if (item instanceof Armor) {
            equipArmor((Armor) item);
            return true;
        }
        return false;
    }

    public boolean unequipItem(Object item) {
        if (item == null) return false;
        if (item instanceof Weapon && item.equals(equippedWeapon)) {
            addAttack(-equippedWeapon.getAttack());
            equippedWeapon = null;
            return true;
        } else if (item instanceof Armor && item.equals(equippedArmor)) {
            addMaxHp(-equippedArmor.getDefense());
            equippedArmor = null;
            return true;
        }
        return false;
    }

    public boolean removeItem(Object item) {
        if (item == null) return false;
        if (item.equals(equippedWeapon)) {
            unequipItem(item);
        }
        if (item.equals(equippedArmor)) {
            unequipItem(item);
        }
        return inventory.remove(item);
    }

    public String getInventoryDescription() {
        if (inventory.isEmpty()) return "(kosong)";
        StringBuilder sb = new StringBuilder();
        for (Object it : inventory) {
            if (it instanceof Weapon) {
                Weapon w = (Weapon) it;
                sb.append("Weapon: ").append(w.getName()).append(" (+").append(w.getAttack()).append(" atk)");
                if (w.equals(equippedWeapon)) sb.append("  [EQUIPPED]");
            } else if (it instanceof Armor) {
                Armor a = (Armor) it;
                sb.append("Armor: ").append(a.getName()).append(" (+").append(a.getDefense()).append(" HP)");
                if (a.equals(equippedArmor)) sb.append("  [EQUIPPED]");
            } else {
                sb.append(it.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
