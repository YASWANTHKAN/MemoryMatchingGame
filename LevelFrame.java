import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelFrame extends JFrame {
    private String playerName;

    public LevelFrame(String playerName) {
        this.playerName = playerName;

        setTitle("Select Level");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\YASWANTH\\Downloads\\end_Sem_dsa\\backg (1).png"); // Corrected path with double backslashes
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setOpaque(false); // Make the panel transparent

        JButton backButton = createButton("Back", new Color(255, 69, 0), Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeFrame homeFrame = HomeFrame.getInstance();
                homeFrame.setVisible(true);
                dispose();
            }
        });
        controlPanel.add(backButton);

        JPanel levelPanel = new JPanel(new GridBagLayout());
        levelPanel.setOpaque(false); // Make the panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton easyButton = createButton("Easy (4x2)", new Color(144, 238, 144), Color.BLACK);
        easyButton.setPreferredSize(new Dimension(300, 60)); // Increased button size
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(4, 2, playerName);
                gameFrame.setVisible(true);
                dispose();
            }
        });

        JButton mediumButton = createButton("Medium (4x4)", new Color(255, 255, 102), Color.BLACK);
        mediumButton.setPreferredSize(new Dimension(300, 60)); // Increased button size
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(4, 4, playerName);
                gameFrame.setVisible(true);
                dispose();
            }
        });

        JButton difficultButton = createButton("Difficult (6x6)", new Color(255, 99, 71), Color.BLACK);
        difficultButton.setPreferredSize(new Dimension(300, 60)); // Increased button size
        difficultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(6, 6, playerName);
                gameFrame.setVisible(true);
                dispose();
            }
        });

        levelPanel.add(easyButton, gbc);
        gbc.gridy++;
        levelPanel.add(mediumButton, gbc);
        gbc.gridy++;
        levelPanel.add(difficultButton, gbc);

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(levelPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border
        button.setOpaque(true);

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LevelFrame levelFrame = new LevelFrame("Player");
                levelFrame.setVisible(true);
            }
        });
    }
}
