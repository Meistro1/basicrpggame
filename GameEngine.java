package saved;
import javax.swing.*;
import java.awt.*;

public class GameEngine extends JPanel {


    private JPanel statsPanel;
    private JLabel nameLabel;
    private JLabel healthLabel;
    private JLabel strengthLabel;
    private JLabel intelligenceLabel;
    private JLabel agilityLabel;
    private JLabel experienceLabel;
    private JLabel levelLabel;

    private Character character;
    private int mapSize;
    private Item[][] map; // declare Item[][] map as a class variable

    private GamePanel gamePanel;

    public GameEngine(Character character, int mapSize) {

        this.character = character;
        this.mapSize = mapSize;
        this.map = new Item[this.mapSize][this.mapSize]; // initialize map here

        Weapon dagger = new Weapon("Dagger", 10, 5, 10, 20);
        Potion potion = new Potion("Potion", 7, 10, 5, 5); 

        this.initMap();

        this.setLayout(new BorderLayout());

        this.map[dagger.getX()][dagger.getY()] = dagger;
        this.map[potion.getX()][potion.getY()] = potion;
    
        this.gamePanel = new GamePanel(this.map, this.character);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocusInWindow();

        this.gamePanel.addKeyListener(new GameKeyListener());
    
        // Initialize stats panel and labels
        this.statsPanel = new JPanel(new GridLayout(7, 1));
        this.nameLabel = new JLabel();
        this.healthLabel = new JLabel();
        this.strengthLabel = new JLabel();
        this.intelligenceLabel = new JLabel();
        this.agilityLabel = new JLabel();
        this.experienceLabel = new JLabel();
        this.levelLabel = new JLabel();

        
        
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(statsPanel, BorderLayout.EAST);

        // Add labels to the panel
        this.statsPanel.add(this.nameLabel);
        this.statsPanel.add(this.healthLabel);
        this.statsPanel.add(this.strengthLabel);
        this.statsPanel.add(this.intelligenceLabel);
        this.statsPanel.add(this.agilityLabel);
        this.statsPanel.add(this.experienceLabel);
        this.statsPanel.add(this.levelLabel);
    
        this.updateStats();
        // Create world and place hero

        this.gamePanel.requestFocusInWindow();


    }


    private void createRoom(int startX, int startY, int width, int height) {
        // create horizontal walls
        for(int x = startX; x < startX + width; x++) {
            this.map[startY][x] = new Wall(x, startY); // top wall
            this.map[startY + height - 1][x] = new Wall(x, startY + height - 1); // bottom wall
        }
    
        // create vertical walls
        for(int y = startY; y < startY + height; y++) {
            this.map[y][startX] = new Wall(startX, y); // left wall
            this.map[y][startX + width - 1] = new Wall(startX + width - 1, y); // right wall
        }
    
        // create passageway in top wall
        if(height > 1) {
            this.map[startY][startX + width/2] = null; // a passageway
        }
    }

    private void updateStats() {
        this.nameLabel.setText("Name: " + this.character.getName());
        this.healthLabel.setText("HP: " + this.character.getHealthPoints());
        this.strengthLabel.setText("Strength: " + this.character.getStrength());
        this.intelligenceLabel.setText("Intelligence: " + this.character.getIntelligence());
        this.agilityLabel.setText("Agility: " + this.character.getAgility());
        this.experienceLabel.setText("EXP: " + this.character.getExperience());
        this.levelLabel.setText("Level: " + this.character.getLevel());
        
    this.statsPanel.revalidate();
    this.statsPanel.repaint();

    }
    


    private void initMap() {
        this.createRoom(5, 5, 10, 10); // creates a 10x10 room at (5,5)
        this.createRoom(20, 20, 20, 20); // creates a 20x20 room at (20,20)
        this.createRoom(14, 10, 2, 10); // creates a 2x10 passageway at (14,10)
}

@Override
public boolean isFocusable() {
    return true;
}

}
