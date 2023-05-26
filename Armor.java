package saved;

public class Armor extends Item {
    private int defense;

    public Armor(String name, int value, int defense, int x, int y) {
        super(name, value, 'A', x, y); // 'A' for Armor
        this.defense = defense;
    }

    // getters, setters and other methods
    public int getDefense() {
        return defense;
    }
}
