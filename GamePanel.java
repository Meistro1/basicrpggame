package saved;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {
    private Object[][] map;
    private int heroX, heroY;
    private Character character;
    private static final int  TILE_SIZE = 20;

    public GamePanel(Object[][] map, Character character) {
        this.map = map;
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
                GamePanel.this.moveHero(0, -1);
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHero(-1, 0);
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHero(0, 1);
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHero(1, 0);
                repaint();
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
    }
    private void moveHero(int dx, int dy) {
        int newHeroX = character.getX() + dx;
        int newHeroY = character.getY() + dy;
    
        // Check if new position is within the world and not a wall
        if (newHeroX >= 0 && newHeroX < map.length && newHeroY >= 0 && newHeroY < map.length && !(map[newHeroY][newHeroX] instanceof Wall)) {
            heroX = newHeroX;
            heroY = newHeroY;
            character.setX(heroX);  // update character's position
            character.setY(heroY);
        }
    }

    @Override
    public boolean isFocusable() {
        return true;
   

    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}
