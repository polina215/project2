import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpaceGameLauncher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SpaceGameLauncher().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {

        JFrame mainFrame = new JFrame("Космическая Игра - главный экран");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLUE);


        JLabel titleLabel = new JLabel("КОСМИЧЕСКАЯ ИГРА", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));


        JPanel levelsPanel = new JPanel();
        levelsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        levelsPanel.setBackground(Color.BLUE);
        levelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));


        JButton[] levelButtons = new JButton[4];
        String[] levelNames = {"Уровень 1", "Уровень 2",
                "Уровень 3", "Уровень 4"};
        Color[] buttonColors = {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE};

        for (int i = 0; i < 4; i++) {
            levelButtons[i] = new JButton(levelNames[i]);
            levelButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            levelButtons[i].setBackground(buttonColors[i]);
            levelButtons[i].setForeground(Color.BLACK);
            levelButtons[i].setFocusPainted(false);


            final int levelNumber = i + 1;
            levelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openLevelWindow(levelNumber);
                }
            });

            levelsPanel.add(levelButtons[i]);
        }


        JPanel rulesPanel = new JPanel();
        rulesPanel.setBackground(Color.BLUE);
        rulesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton rulesButton = new JButton("Правила");
        rulesButton.setFont(new Font("Arial", Font.BOLD, 18));
        rulesButton.setBackground(Color.WHITE);
        rulesButton.setForeground(Color.BLACK);
        rulesButton.setFocusPainted(false);

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRules();
            }
        });

        rulesPanel.add(rulesButton);


        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(levelsPanel, BorderLayout.CENTER);
        mainPanel.add(rulesPanel, BorderLayout.SOUTH);


        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
   private void openLevelWindow(int level) {
        JFrame levelFrame = new JFrame("Уровень " + level);
        levelFrame.setSize(500, 400);

        levelFrame.setLocationRelativeTo(null);

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new BorderLayout());
        levelPanel.setBackground(new Color(10, 10, 40));
        JLabel levelLabel = new JLabel();


        JLabel infoLabel = new JLabel();


        levelPanel.add(levelLabel, BorderLayout.NORTH);
        levelPanel.add(infoLabel, BorderLayout.CENTER);

        levelFrame.add(levelPanel);
        levelFrame.setVisible(true);
    }


    private void showRules() {
        JFrame rulesFrame = new JFrame("Правила");
        rulesFrame.setSize(500, 400);
        rulesFrame.setLocationRelativeTo(null);

        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        String rulesText = "правила";

        JLabel rulesLabel = new JLabel(rulesText);
        rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JButton closeButton = new JButton("закрыть");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton button = (JButton) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(button);
                window.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(closeButton);

        rulesPanel.add(titleLabel, BorderLayout.NORTH);
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesPanel.add(buttonPanel, BorderLayout.SOUTH);

        rulesFrame.add(rulesPanel);
        rulesFrame.setVisible(true);
    }
}
