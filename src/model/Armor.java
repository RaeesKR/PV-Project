package model;

public class Armor {
    private String name;
    private int defense;
    private String imagePath;

    public Armor(String name, int defense, String imagePath) {
        this.name = name;
        this.defense = defense;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public int getDefense() { return defense; }
    public String getImagePath() { return imagePath;}
}
