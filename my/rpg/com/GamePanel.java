package my.rpg.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {
    private char[][] world;
    private int heroX, heroY;
    private Character character;

    public GamePanel(char[][] map, Character character) {
        this.world = map;
        this.character = character;
        this.heroX = map.length / 2;
        this.heroY = map.length / 2;
        this.world[heroY][heroX] = '@';

        setFocusable(true);

        // Set up key bindings
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHero(0, -1);
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                g.drawChars(new char[] {world[i][j]}, 0, 1, j * 20, i * 20);
            }
        }
    }

    private void moveHero(int dx, int dy) {
        int newHeroX = heroX + dx;
        int newHeroY = heroY + dy;
    
        // Check if new position is within the world and not a wall
        if (newHeroX >= 0 && newHeroX < world.length && newHeroY >= 0 && newHeroY < world.length && world[newHeroY][newHeroX] != 'W') {
            world[heroY][heroX] = '.';  // remove hero from old position
            heroX = newHeroX;
            heroY = newHeroY;
            world[heroY][heroX] = '@';  // place hero at new position
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
