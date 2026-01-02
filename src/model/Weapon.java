package model;

public class Weapon {
    private String name;
    private int attack;
    private String imagePath;

    public Weapon(String name, int attack, String imagePath) {
        this.name = name;
        this.attack = attack;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public int getAttack() { return attack; }
    public String getImagePath() { return imagePath; }
}
