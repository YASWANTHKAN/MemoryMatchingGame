import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HomeFrame extends JFrame {
    private JTextField nameField;
    private JLabel errorLabel;
    private static Set<String> playerNames; // Static set to store unique player names
    private static HomeFrame instance;

    public HomeFrame() {
        if (playerNames == null) {
            playerNames = new HashSet<>(); // Initialize the set if it's null
        }

        setTitle("Memory Matching Game");
        setSize(600, 500); // Increased the size to accommodate the image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add a colorful background
        getContentPane().setBackground(new Color(114, 217, 218));

        // Create the main content panel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\YASWANTH\\Downloads\\end_Sem_dsa\\backg (1).png"); // Add your image path here
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Welcome to Memory Matching Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel instructionLabel = new JLabel("Enter Your Name:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLACK);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(instructionLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        panel.add(nameField, gbc);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 18));
        playButton.setBackground(new Color(76, 175, 80));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    if (isNameUnique(playerName)) {
                        playerNames.add(playerName); // Add the name to the set
                        LevelFrame levelFrame = new LevelFrame(playerName);
                        levelFrame.setVisible(true);
                        dispose(); // Close the home frame
                    }
                } else {
                    errorLabel.setText("Please enter your name.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(playButton, gbc);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(errorLabel, gbc);

        JLabel moreOptionsLabel = new JLabel("More Options:");
        moreOptionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        moreOptionsLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(moreOptionsLabel, gbc);

        JButton highScoresButton = new JButton("View High Scores");
        highScoresButton.setFont(new Font("Arial", Font.BOLD, 18));
        highScoresButton.setBackground(new Color(33, 150, 243));
        highScoresButton.setForeground(Color.WHITE);
        highScoresButton.setFocusPainted(false);
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoresFrame highScoresFrame = new HighScoresFrame();
                highScoresFrame.setVisible(true);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(highScoresButton, gbc);

        JButton controlsButton = new JButton("Controls");
        controlsButton.setFont(new Font("Arial", Font.BOLD, 18));
        controlsButton.setBackground(new Color(255, 87, 34));
        controlsButton.setForeground(Color.WHITE);
        controlsButton.setFocusPainted(false);
        controlsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Memory Matching Game Controls\n" +
                        "1. Use the mouse to flip cards.\n" +
                        "2. Match pairs to clear them from the board.\n" +
                        "3. Complete all matches to win.\n",
                        "Controls", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(controlsButton, gbc);

        JButton aboutButton = new JButton("About");
        aboutButton.setFont(new Font("Arial", Font.BOLD, 18));
        aboutButton.setBackground(new Color(255, 193, 7));
        aboutButton.setForeground(Color.WHITE);
        aboutButton.setFocusPainted(false);
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "<html><div style='text-align: center;'>Memory Matching Game<br></div>" +
                        "IQ values for Easy is ranging from 65-85<br>" +
                        "IQ values for Medium is ranging from 85-105<br>" +
                        "IQ values for Difficult is ranging from 105-135<br>" +
                        "<div style='text-align: center;'><span style='font-size: 5px;'>Version 1.0</span><br>" +
                        "<span style='font-size: 5px;'>Developed by [RAYS]</span></div></html>",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(aboutButton, gbc);

        add(panel, BorderLayout.CENTER); // Add the panel to the frame
    }

    public static HomeFrame getInstance() {
        if (instance == null) {
            instance = new HomeFrame();
        }
        return instance;
    }

    private boolean isNameUnique(String playerName) {
        if (playerNames.contains(playerName)) {
            int choice = JOptionPane.showConfirmDialog(null, "The name already exists. Is this you?", "Name Exists", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                errorLabel.setText(""); // Clear error message
                return true; // Continue with existing name
            } else if (choice == JOptionPane.NO_OPTION) {
                errorLabel.setText("Please enter a new name."); // Display new error message
                return false; // Change name
            }
        }
        return true; // Name is unique
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomeFrame.getInstance().setVisible(true);
            }
        });
    }
}
