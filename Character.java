package saved;
import java.util.ArrayList;
import java.util.*;

public class Character {
    private String name;
    private String type;
    private int healthPoints;
    private int strength;
    private int intelligence;
    private int agility;
    private int experience;
    private int level;
    private Map<String, Item> equippedItems;
    private List<Item> inventory;
    int x, y;

    
    public Character(String name, String type, int healthPoints, int strength, int intelligence, int agility) {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.experience = 0;
        this.level = 1;   
        this.equippedItems = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.inventory.add(new Armor("Rags", 1, 1, 5, 5));
        this.inventory.add(new Weapon("Dagger", 2, 2, 10, 10));
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

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }
    
    public void removeItemFromInventory(Item item) {
        this.inventory.remove(item);
    }
    public void equipItem(String slot, Item item) {
        if (this.inventory.contains(item)) {
            // Check if there's already an item equipped in this slot
            if (this.equippedItems.get(slot) != null) {
                System.out.println("You're already equipped with an item in this slot. You must unequip it first.");
            } else {
                // Add to equipped items and remove from inventory
                this.equippedItems.put(slot, item);
                this.removeItemFromInventory(item);
            }
        }
    }
    
    
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }


    
    public void unequipItem(String slot) {
        // Get the item to unequip
        Item item = this.equippedItems.get(slot);
        
        // Add it back to the inventory
        this.addItemToInventory(item);
        
        // Remove from equipped items
        this.equippedItems.remove(slot);
    }



    @Override
    public String toString() {
        return type + " " + name + ", HP: " + healthPoints + ", Level: " + level + ", EXP: " + experience;
    }
}
