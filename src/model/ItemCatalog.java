package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Central catalog for game items (weapons, armors).
 * Add new items here or at runtime via the addX methods.
 */
public class ItemCatalog {
    private static final List<Weapon> weapons;
    private static final List<Armor> armors;

    static {
        weapons = new ArrayList<>(Arrays.asList(
            new Weapon("Wooden Sword", 2),
            new Weapon("Steel Sword", 4),
            new Weapon("Golden Katana", 6),
            new Weapon("Demonic Blade", 10)
        ));

        armors = new ArrayList<>(Arrays.asList(
            new Armor("Cloth Armor", 1),
            new Armor("Iron Armor", 3),
            new Armor("Dragon Scale", 5),
            new Armor("Shadow Cloak", 8)
        ));
    }

    // Read-only views
    public static List<Weapon> getWeapons() {
        return Collections.unmodifiableList(weapons);
    }

    public static List<Armor> getArmors() {
        return Collections.unmodifiableList(armors);
    }

    // Runtime additions (optional)
    public static void addWeapon(Weapon w) {
        if (w != null) weapons.add(w);
    }

    public static void addArmor(Armor a) {
        if (a != null) armors.add(a);
    }

    // Replace entire lists (useful for loading from file/db)
    public static void setWeapons(List<Weapon> list) {
        weapons.clear();
        if (list != null) weapons.addAll(list);
    }

    public static void setArmors(List<Armor> list) {
        armors.clear();
        if (list != null) armors.addAll(list);
    }
}
