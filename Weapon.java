package saved;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, int value, int damage, int x, int y) {
        super(name, value, 'W', x, y); // 'W' is for Weapon
        this.damage = damage;
    }

    // getters, setters and other methods
    public int getDamage() {
        return damage;
    }
}
