import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private String playerName;

    public GameFrame(int rows, int cols, String playerName) {
        this.playerName = playerName;
        setTitle("Memory Matching Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel(rows, cols, this, playerName);
        add(gamePanel, BorderLayout.CENTER);
    }

    public void resetGame(int rows, int cols) {
        remove(gamePanel);
        gamePanel = new GamePanel(rows, cols, this, playerName);
        add(gamePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void navigateToLevels() {
        LevelFrame levelFrame = new LevelFrame(playerName);
        levelFrame.setVisible(true);
        dispose(); // Close the game frame
    }

    public void goToHomePage() {
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setVisible(true);
        dispose(); // Close the game frame
    }

    public void goToHighScores() {
        HighScoresFrame highScoresFrame = new HighScoresFrame();
        highScoresFrame.setVisible(true);
        dispose(); // Close the game frame
    }
}
