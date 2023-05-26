package saved;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                // handle up movement
                break;
            case KeyEvent.VK_A:
                // handle left movement
                break;
            case KeyEvent.VK_S:
                // handle down movement
                break;
            case KeyEvent.VK_D:
                // handle right movement
                break;
        }
    }
}
