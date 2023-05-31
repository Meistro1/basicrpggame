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
    private int cameraX;
    private int cameraY;

    private Character character;
    private int mapSize;
    private GameMap gameMap;

    private GamePanel gamePanel;

    public GameEngine(Character character, int mapSize) {

        this.character = character;
        this.mapSize = mapSize;
        this.gameMap = new GameMap(mapSize, mapSize); // initialize map here
        initializeTerrain();

        this.character.setX(5);
        this.character.setY(5);
        this.cameraX = this.character.getX();
        this.cameraY = this.character.getY();
        this.width = 50;
        this.height = 50;
        this.setLayout(new BorderLayout());

        this.gamePanel = new GamePanel(this.gameMap, this.character); // Pass the gameMap instance to the GamePanel
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
        for (int x = room.x1 + 1; x < room.x2; x++) {
            for (int y = room.y1 + 1; y < room.y2; y++) {
                gameMap.setTile(x, y, new Terrain(Terrain.TerrainType.FLOOR, true, "floor_texture.png"));
            }
        }
    }


    private void initializeTerrain() {
        // Add some grass
        for (int i = 1; i < mapSize - 1; i++) {
            for (int j = 1; j < mapSize - 1; j++) {
                this.gameMap.setTile(i, j, new Terrain(Terrain.TerrainType.GRASS, true, "grass_texture.png"));
            }
        }

        // Add a border wall
        for (int i = 0; i < mapSize; i++) {
            this.gameMap.setTile(i, 0, new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"));
            this.gameMap.setTile(i, mapSize - 1, new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"));
        }
        for (int i = 1; i < mapSize - 1; i++) {
            this.gameMap.setTile(0, i, new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"));
            this.gameMap.setTile(mapSize - 1, i, new Terrain(Terrain.TerrainType.WALL, false, "wall_texture.png"));
        }
    }




    private void updateStats() {
        this.nameLabel.setText("Class: " + this.character.getName());
        this.healthLabel.setText("HP: " + this.character.getHealthPoints());
        this.strengthLabel.setText("Strength: " + this.character.getStrength());
        this.intelligenceLabel.setText("Intelligence: " + this.character.getIntelligence());
        this.agilityLabel.setText("Agility: " + this.character.getAgility());
        this.experienceLabel.setText("EXP: " + this.character.getExperience());
        this.levelLabel.setText("Level: " + this.character.getLevel());

        this.statsPanel.revalidate();
        this.statsPanel.repaint();
    }

    @Override
    public boolean isFocusable() {
        return true;
    }
}
