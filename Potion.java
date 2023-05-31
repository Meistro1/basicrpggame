package my.rpg.com;

// Potion class
public class Potion extends Item {
    private int healingPower;

    public Potion(String name, int value, int healingPower, int x, int y) {
        super(name, value, 'P', x, y);
        this.healingPower = healingPower;
    }

    public int getHealingPower() {
        return healingPower;
    }
}