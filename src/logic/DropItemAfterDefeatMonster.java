package logic;

import java.util.Random;
import model.Player;
import model.Armor;
import model.Weapon;
import model.ItemCatalog;

public class DropItemAfterDefeatMonster {

    public static java.util.List<Object> dropItems(Player player) {
        Random rand = new Random();
        java.util.List<Object> drops = new java.util.ArrayList<>();

        java.util.List<Weapon> weapons = ItemCatalog.getWeapons();
        java.util.List<Armor> armors = ItemCatalog.getArmors();

        if ((weapons == null || weapons.isEmpty()) && (armors == null || armors.isEmpty())) {
            return drops;
        }

        // Pilih 2 item acak
        for (int i = 0; i < 2; i++) {
            boolean chooseWeapon = true;
            if (weapons.isEmpty()) chooseWeapon = false;
            else if (armors.isEmpty()) chooseWeapon = true;
            else chooseWeapon = rand.nextBoolean();

            if (chooseWeapon) {
                Weapon w = weapons.get(rand.nextInt(weapons.size()));
                drops.add(new Weapon(w.getName(), w.getAttack(), w.getImagePath()));
            } else {
                Armor a = armors.get(rand.nextInt(armors.size()));
                drops.add(new Armor(a.getName(), a.getDefense(), a.getImagePath()));
            }
        }

        return drops;
    }
}
