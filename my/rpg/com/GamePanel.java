package my.rpg.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;


public class GamePanel extends JPanel {
    private GameMap gameMap;
    private int heroX, heroY;
    private Character character;
    private int cameraX = 0;
    private int cameraY = 0;
    private int viewWidth = 10;  // set this to your desired values
    private int viewHeight = 10;
    private BufferedImage grassTexture;


    private static final int TILE_SIZE = 20;

    public GamePanel(GameMap gameMap, Character character) {
        this.gameMap = gameMap;
        this.character = character;
        System.out.println(character);
        this.heroX = gameMap.getWidth() / 2;
        this.heroY = gameMap.getHeight() / 2;

try {
    grassTexture = ImageIO.read(getClass().getResource("grass_texture.png"));
    System.out.println("Grass texture loaded successfully");
} catch (IOException e) {
    System.out.println("Error loading grass texture: " + e);
}

        setFocusable(true);
        // Set up key bindings
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newHeroX = character.getX();
                int newHeroY = character.getY() - 1;
                Object newItem = gameMap.getTile(newHeroX, newHeroY);
                moveHero(0, -1, newHeroX, newHeroY, newItem);
                repaint();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newHeroX = character.getX() - 1;
                int newHeroY = character.getY();
                Object newItem = gameMap.getTile(newHeroX, newHeroY);
                moveHero(-1, 0, newHeroX, newHeroY, newItem);
                repaint();
            }
        });
        

        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newHeroX = character.getX();
                int newHeroY = character.getY() + 1;
                Object newItem = gameMap.getTile(newHeroX, newHeroY);
                moveHero(0, 1, newHeroX, newHeroY, newItem);
                repaint();
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newHeroX = character.getX() + 1;
                int newHeroY = character.getY();
                Object newItem = gameMap.getTile(newHeroX, newHeroY);
                moveHero(1, 0, newHeroX, newHeroY, newItem);
                repaint();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("I"), "viewInventory");
        actionMap.put("viewInventory", new AbstractAction() {
         @Override
          public void actionPerformed(ActionEvent e) {
          character.viewInventory();
    }
});
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        int offsetX = Math.max(0, Math.min(character.getX() - gameMap.getWidth() / 2, gameMap.getWidth() - getWidth() / TILE_SIZE));
        int offsetY = Math.max(0, Math.min(character.getY() - gameMap.getHeight() / 2, gameMap.getHeight() - getHeight() / TILE_SIZE));
    
        for (int i = 0; i < gameMap.getHeight(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {
                Terrain terrain = gameMap.getTile(j, i);
    
                switch (terrain.getType()) {
                    case WALL:
                        g.setColor(Color.BLACK);
                        break;
                    case FLOOR:
                        g.drawImage(grassTexture, (j - offsetX) * TILE_SIZE, (i - offsetY) * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                        break;
                    case GRASS:
                    g.drawImage(grassTexture, (j - offsetX) * TILE_SIZE, (i - offsetY) * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                    break;
                    default:
                        g.setColor(Color.WHITE);
                        break;
                }
    
                if (gameMap.getItem(j, i) != null) {
                    Item item = gameMap.getItem(j, i);
                    if (item != null) {
                        switch(item.getType()){
                            case 'P':
                                g.setColor(Color.RED);
                                g.fillOval(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                                break;
                            case 'E':
                                g.setColor(Color.BLUE);
                                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    
        int x = character.getX() * TILE_SIZE;
        int y = character.getY() * TILE_SIZE;
        g.setColor(Color.BLUE);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        drawInventory(g);
    }
    
    
    private void drawInventory(Graphics g) {
        List<Item> inventory = character.getInventory();
        int inventoryOffsetX = 10;
        int inventoryOffsetY = 10;
        int itemSpacing = 20;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            String itemName = item.getName();

            int itemX = inventoryOffsetX;
            int itemY = inventoryOffsetY + i * itemSpacing;

            g.drawString(itemName, itemX, itemY);
        }
    }
    private void moveHero(int dx, int dy, int newHeroX, int newHeroY, Object newItem) {
        // Check if new position is within the world and not a wall
        if (newHeroX >= 0 && newHeroX < gameMap.getWidth() && newHeroY >= 0 && newHeroY < gameMap.getHeight()
                && !(gameMap.getTile(newHeroX, newHeroY).getType() == Terrain.TerrainType.WALL)) {
            heroX = newHeroX;
            heroY = newHeroY;
            character.setX(heroX);  // update character's position
            character.setY(heroY);
    
            // Camera logic
            int cameraPadding = 5; // Keep the character at least this far from the edge of the screen
            if (heroX - cameraX < cameraPadding) {
                cameraX = heroX - cameraPadding;
            } else if (cameraX + viewWidth - heroX < cameraPadding) {
                cameraX = heroX - viewWidth + cameraPadding;
            }
            if (heroY - cameraY < cameraPadding) {
                cameraY = heroY - cameraPadding;
            } else if (cameraY + viewHeight - heroY < cameraPadding) {
                cameraY = heroY - viewHeight + cameraPadding;
            }
    
            // Don't move the camera beyond the edge of the map
            if (cameraX < 0) cameraX = 0;
            if (cameraY < 0) cameraY = 0;
            if (cameraX > gameMap.getWidth() - viewWidth) cameraX = gameMap.getWidth() - viewWidth;
            if (cameraY > gameMap.getHeight() - viewHeight) cameraY = gameMap.getHeight() - viewHeight;
    
            character.pickUpItem(gameMap, newHeroX, newHeroY, newItem); // Call pickUpItem method to handle item pickup
        }
    }
    
    
    public boolean isFocusable() {
        return true;
   

    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}
