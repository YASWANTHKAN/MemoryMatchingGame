import javax.swing.*;

public class MemoryGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeFrame homeFrame = HomeFrame.getInstance();
            homeFrame.setVisible(true);
        });
    }
}
