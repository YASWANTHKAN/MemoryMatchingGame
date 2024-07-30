import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.Arrays;

public class HighScoresFrame extends JFrame {
    private JButton homeButton; // Add a Home button

    public HighScoresFrame() {
        setTitle("High Scores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Initialize and add the home button
        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        homeButton.addActionListener(e -> {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setVisible(true);
            dispose();
        });

        // Create a panel for the home button and add it to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(homeButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Load and display the high scores
        displayHighScores();
    }

    private void displayHighScores() {
        String[] columnNames = {"Player", "Level", "Tries", "IQ"};
        String[][] data = HighScoresManager.getHighScoresData();

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setBorder(new LineBorder(Color.BLACK));
        table.setGridColor(Color.BLACK);

        // Set cell colors
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HighScoresFrame frame = new HighScoresFrame();
            frame.setVisible(true);
        });
    }
}
