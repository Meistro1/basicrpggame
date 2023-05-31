package my.rpg.com;

import java.util.*;

public class Town {
    private Shop shop;

    public Town() {
        // Initialize the shop with items for sale
        List<Item> itemsForSale = new ArrayList<>();
        itemsForSale.add(new Weapon("Sword", 20, 10, 5, 50));
        itemsForSale.add(new Potion("Health Potion", 10, 5, 10, 10));
        // Add more items to the list

        this.shop = new Shop(itemsForSale);
    }

    public void enterShop() {
        // Implement logic to enter the shop and interact with the shopkeeper
    }
}
