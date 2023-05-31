package my.rpg.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharacterSelection {
    private List<Character> availableCharacters;
    
    public CharacterSelection() {
        this.availableCharacters = new ArrayList<>();
        this.availableCharacters.add(new Character("Warrior", "Human", 30, 20, 15, 17));
        this.availableCharacters.add(new Character("Rogue", "Elf", 20, 15, 22, 25));
        this.availableCharacters.add(new Character("Mage", "Human", 20, 10, 25, 25));
    }

    public Character selectCharacter() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose your character:");
            for (int i = 0; i < this.availableCharacters.size(); i++) {
                System.out.printf("%d. %s\n", i+1, this.availableCharacters.get(i));
            }
            String input = scanner.nextLine();
            try {
                int selectedIndex = Integer.parseInt(input) - 1;
                if (selectedIndex >= 0 && selectedIndex < this.availableCharacters.size()) {
                    return this.availableCharacters.get(selectedIndex);
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
