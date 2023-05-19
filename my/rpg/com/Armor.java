package my.rpg.com;
// Armor class


public class Armor extends Item {
    private int defense;

    public Armor(String name, int value, int defense) {
        super(name, value);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }
}
