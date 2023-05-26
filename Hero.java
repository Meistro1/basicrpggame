package saved;

public class Hero {

    private String name;
    private int healthPoints;
    private int attackPower;
    public int heroX, heroY;
    // ... other properties ...
    
    public Hero(String name, int healthPoints, int attackPower /*, ... */) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        // ... initialize other properties ...
    }
    
    public int getX() {
        return this.heroX;
    }

    public void setX(int heroX) {
        this.heroX = heroX;
    }

    public int getY() {
        return this.heroY;
    }

    public void setY(int heroY) {
        this.heroY = heroY;
    }
    
}
