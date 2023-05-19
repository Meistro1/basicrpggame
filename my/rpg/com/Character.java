package my.rpg.com;
import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String type;
    private int healthPoints;
    private int strength;
    private int intelligence;
    private int agility;
    private int experience;
    private int level;
    private List<Item> inventory;

    
    public Character(String name, String type, int healthPoints, int strength, int intelligence, int agility) {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.experience = 0;
        this.level = 1;
        this.inventory = new ArrayList<>();
    }

    // Getter and setter methods for all fields
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int exp) {
        this.experience += exp;
        // Assume that every 100 experience points increases the level
        while (this.experience >= 100) {
            this.experience -= 100;
            this.level++;
            System.out.println(this.name + " has reached level " + this.level + "!");
        }
    }

    public int getLevel() {
        return level;
    }

    public int getAgility()
    {
        return this.agility;
    }

    
    public int getStrength()
    {
        return this.strength;
    }

        
    public int getIntelligence()
    {
        return this.intelligence;
    }


    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.add(item);
        System.out.println(this.name + " has picked up a " + item.getName() + "!");
    }

    @Override
    public String toString() {
        return type + " " + name + ", HP: " + healthPoints + ", Level: " + level + ", EXP: " + experience;
    }
}
