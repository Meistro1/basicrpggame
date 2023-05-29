package saved;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CharacterSelection characterSelection = new CharacterSelection();
        Character selectedCharacter = characterSelection.selectCharacter();
        System.out.println("You have selected: " + selectedCharacter);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("RPG GAME");
            GameEngine gameEngine = new GameEngine(selectedCharacter, 50);

            frame.add(gameEngine);
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            frame.setVisible(true);

            gameEngine.requestFocusInWindow();
        });
    }
}
