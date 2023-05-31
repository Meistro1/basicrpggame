package my.rpg.com;



public class Enemy {
    private int damage;
    private int health;
    private int x, y;

    public Enemy(int damage, int health, int x, int y) {
        this.damage = damage;
        this.health = health;
        this.x = x;
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void attack(Character character) {
        // Implement attack logic
        character.setHealthPoints(character.getHealthPoints() - this.damage);
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
