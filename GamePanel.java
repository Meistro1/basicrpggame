package my.rpg.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GamePanel extends JPanel {
    private Object[][] map;
    private int heroX, heroY;
    private Character character;
    private static final int  TILE_SIZE = 20;
    private Tile[][] tiles;

    public GamePanel(Object[][] map, Character character, Tile[][] tiles) {
        this.map = map;
        this.tiles = tiles;

        this.character = character;
        this.heroX = map.length / 2;
        this.heroY = map.length / 2;

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
                Object newItem = map[newHeroY][newHeroX];
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
                Object newItem = map[newHeroY][newHeroX];
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
                Object newItem = map[newHeroY][newHeroX];
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
                Object newItem = map[newHeroY][newHeroX];
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
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                Object obj = map[i][j];
                if (obj instanceof Item) {
                    Item item = (Item) obj;
                    if (item != null) {
                        // Draw different items differently, depending on their type
                        switch(item.getType()){
                            case 'W':
                                // Draw wall
                                g.setColor(Color.BLACK);
                                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                                break;
                            case 'P':
                                // Draw potion
                                g.setColor(Color.RED);
                                g.fillOval(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                                break;
                            case 'E':
                                // Draw weapon
                                g.setColor(Color.BLUE);
                                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                                break;
                            default:
                                // Draw floor or empty space
                                g.setColor(Color.LIGHT_GRAY);
                                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
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
        if (newHeroX >= 0 && newHeroX < map.length && newHeroY >= 0 && newHeroY < map.length 
            && !(tiles[newHeroY][newHeroX].getTerrain().getType() == Terrain.TerrainType.WALL)) {
            heroX = newHeroX;
            heroY = newHeroY;
            character.setX(heroX);  // update character's position
            character.setY(heroY);
    
            character.pickUpItem(map, newHeroX, newHeroY, newItem); // Call pickUpItem method to handle item pickup
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
