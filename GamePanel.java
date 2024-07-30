import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private List<JButton> buttons;
    private List<Integer> cardValues;
    private List<JButton> flippedCards;
    private int pairsFound;
    private int tries;
    private JLabel triesLabel;
    private JButton resetButton;
    private JButton backButton;
    private JButton homeButton; // Added Home button
    private JButton highScoresButton; // Added High Scores button
    private GameFrame gameFrame;
    private int rows;
    private int cols;
    private String playerName;

    private static final Color[] CARD_COLORS = {
        new Color(144, 238, 144), // Light Green
        new Color(255, 182, 193), // Light Pink
        new Color(173, 216, 230), // Light Blue
        new Color(255, 255, 153), // Light Yellow
        new Color(255, 204, 153), // Light Orange
        new Color(204, 153, 255), // Light Purple
        new Color(255, 228, 181), // Light Peach
        new Color(255, 218, 185), // Light Salmon
        new Color(152, 251, 152), // Pale Green
        new Color(176, 224, 230)  // Powder Blue
    };

    public GamePanel(int rows, int cols, GameFrame gameFrame, String playerName) {
        this.rows = rows;
        this.cols = cols;
        this.gameFrame = gameFrame;
        this.playerName = playerName;

        setLayout(new BorderLayout());
        buttons = new ArrayList<>();
        cardValues = new ArrayList<>();
        flippedCards = new ArrayList<>();
        pairsFound = 0;
        tries = 0;

        int pairs = (rows * cols) / 2;
        initializeCards(pairs);
        initializeButtons(rows, cols);
        initializeControlPanel();
    }

    private void initializeCards(int pairs) {
        for (int i = 1; i <= pairs; i++) {
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);
    }

    private void initializeButtons(int rows, int cols) {
        JPanel gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\YASWANTH\\Downloads\\end_Sem_dsa\\backg (1).png"); // Corrected path with double backslashes
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        gridPanel.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < cardValues.size(); i++) {
            JButton button = createCardButton(i);
            buttons.add(button);
            gridPanel.add(button);
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        resetButton = createControlButton("Reset", new Color(255, 127, 127), Color.BLACK);
        backButton = createControlButton("Back", new Color(173, 216, 230), Color.BLACK);
        homeButton = createControlButton("Home", new Color(253, 250, 114), Color.BLACK); // Initialize Home button
        highScoresButton = createControlButton("High Scores", new Color(144, 238, 144), Color.BLACK); // Initialize High Scores button

        resetButton.addActionListener(e -> gameFrame.resetGame(rows, cols));
        backButton.addActionListener(e -> gameFrame.navigateToLevels());
        homeButton.addActionListener(e -> gameFrame.goToHomePage()); // Home button action
        highScoresButton.addActionListener(e -> gameFrame.goToHighScores()); // High Scores button action

        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);
        buttonPanel.add(homeButton); // Add Home button to button panel
        buttonPanel.add(highScoresButton); // Add High Scores button to button panel

        triesLabel = new JLabel("Tries: 0");
        triesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        triesLabel.setForeground(Color.BLACK); // Make the text BLACK to stand out

        controlPanel.add(triesLabel, BorderLayout.WEST);
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        add(controlPanel, BorderLayout.NORTH);
    }

    private JButton createCardButton(int index) {
        JButton button = new JButton("?");
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(CARD_COLORS[index % CARD_COLORS.length]);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border
        button.setOpaque(true);

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CARD_COLORS[index % CARD_COLORS.length]);
            }
        });

        button.addActionListener(new CardClickListener(index));
        return button;
    }

    private JButton createControlButton(String text, Color backgroundColor, Color foregroundColor) {
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

    private class CardClickListener implements ActionListener {
        private int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flippedCards.size() < 2 && !flippedCards.contains(buttons.get(index))) {
                buttons.get(index).setText(String.valueOf(cardValues.get(index)));
                flippedCards.add(buttons.get(index));

                if (flippedCards.size() == 2) {
                    tries++;
                    triesLabel.setText("Tries: " + tries);
                    checkMatch();
                }
            }
        }

        private void checkMatch() {
            JButton firstCard = flippedCards.get(0);
            JButton secondCard = flippedCards.get(1);

            if (firstCard.getText().equals(secondCard.getText())) {
                pairsFound++;
                flippedCards.clear();

                if (pairsFound == cardValues.size() / 2) {
                    calculateAndShowIQ();
                }
            } else {
                Timer timer = new Timer(1000, e -> {
                    firstCard.setText("?");
                    secondCard.setText("?");
                    flippedCards.clear();
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

        private void calculateAndShowIQ() {
            int level = cardValues.size() / 2;
            int iq = 0;
            int minTriesEasy = 4;
            int minTriesMedium = 8;
            int minTriesDifficult = 16;

            if (level == 4) {
                iq = 85 - ((tries - minTriesEasy) * 2);
                if (iq <= 65) {
                    JOptionPane.showMessageDialog(null, "Your IQ is low. Take more tests and practice well.");
                }
            } else if (level == 8) {
                iq = 105 - ((tries - minTriesMedium) * 2);
                if (iq <= 85) {
                    JOptionPane.showMessageDialog(null, "Your IQ is low. Try the Easy Level.");
                }
            } else if (level == 18) {
                iq = 135 - ((tries - minTriesDifficult) * 2);
                if (iq <= 105) {
                    JOptionPane.showMessageDialog(null, "Your IQ is low. Take the Medium Level test.");
                }
            }

            HighScoresManager.addScore(playerName, iq, level, tries);

            Object[] options = {"Home", "High Scores"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "You've found all pairs! Your IQ is estimated to be: " + iq,
                    "Game Over",
                    JOptionPane.YES_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == JOptionPane.YES_OPTION) {
                HomeFrame homeFrame = new HomeFrame();
                homeFrame.setVisible(true);
                gameFrame.dispose();
            } else if (choice == JOptionPane.NO_OPTION) {
                HighScoresFrame highScoresFrame = new HighScoresFrame();
                highScoresFrame.setVisible(true);
                gameFrame.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame gameFrame = new GameFrame(4, 4, "Player");
                gameFrame.setVisible(true);
            }
        });
    }
}

