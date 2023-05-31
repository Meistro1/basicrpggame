package my.rpg.com;

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
    private int width, height;
    private Tile[][] tiles;

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

      
        this.character.setX(5);
        this.character.setY(5);
        this.width = 50;
        this.height = 50;
        this.tiles = new Tile[width][height];
        initializeTiles();
        this.initMap();
        this.setLayout(new BorderLayout());

        this.map[dagger.getX()][dagger.getY()] = dagger;
        this.map[potion.getX()][potion.getY()] = potion;
    
        this.gamePanel = new GamePanel(this.map, this.character, this.tiles);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocusInWindow();

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

    private void handleCommand(String command) {
        if (command.equals("inventory")) {
            character.viewInventory();
        } else {
            System.out.println("Invalid command!");
        }
    }

    public void createRoom(Rect room) {
        for(int x = room.x1 + 1; x < room.x2; x++) {
            for(int y = room.y1 + 1; y < room.y2; y++) {
                tiles[x][y].setBlocked(false);
                tiles[x][y].setBlockSight(false);
            }
        }
    }
    private void initializeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    // Put walls around the edge of the map
                    tiles[x][y] = new Tile(new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"), true);
                } else {
                    // Fill the rest of the map with floor
                    tiles[x][y] = new Tile(new Terrain(Terrain.TerrainType.FLOOR, true, "floor_texture.png"), false);
                }
            }
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
        Rect room1 = new Rect(20, 15, 10, 15);
        Rect room2 = new Rect(35, 15, 10, 15);
        createRoom(room1);
        createRoom(room2);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y].isBlocked()) {
                    tiles[x][y].setTerrain(new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"));
                } else {
                    tiles[x][y].setTerrain(new Terrain(Terrain.TerrainType.FLOOR, true, "floor_texture.png"));
                }
            }
        }
    }
    


@Override
public boolean isFocusable() {
    return true;
}

}


class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}