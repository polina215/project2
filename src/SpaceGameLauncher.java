import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class RoundedButton extends JButton {
    private int arcWidth = 20;
    private int arcHeight = 20;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        Color btnColor = new Color(25, 25, 50);
        if (getModel().isPressed()) {
            btnColor = new Color(40, 40, 80);
        } else if (getModel().isRollover()) {
            btnColor = new Color(35, 35, 70);
        }

        g2.setColor(btnColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);


        g2.setColor(new Color(0, 210, 255));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arcWidth, arcHeight);

        g2.dispose();
        super.paintComponent(g);
    }


    @Override
    protected void paintBorder(Graphics g) {

    }
}
public class SpaceGameLauncher {


    private static int bestScore = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SpaceGameLauncher().createAndShowGUI();
        });
    }

    public static void updateScore(int currentScore) {
        if (currentScore > bestScore) {
            bestScore = currentScore;
        }
    }

    public static int getBestScore() {
        return bestScore;
    }

    private void createAndShowGUI() {

        JFrame mainFrame = new JFrame("игра");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(10, 10, 20));


        JLabel titleLabel = new JLabel("главный экран", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));


        JPanel levelsPanel = new JPanel();
        levelsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        levelsPanel.setBackground(new Color(10, 10, 20));
        levelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));


        RoundedButton[] levelButtons = new RoundedButton[4];
        String[] levelNames = {"Уровень 1", "Уровень 2", "Уровень 3", "Уровень 4"};
        Color[] buttonColors = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

        for (int i = 0; i < 4; i++) {

            levelButtons[i] = new RoundedButton(levelNames[i]);
            levelButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            levelButtons[i].setBackground(buttonColors[i]);
            levelButtons[i].setForeground(Color.WHITE);
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
        rulesPanel.setBackground(new Color(10, 10, 20));
        rulesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        RoundedButton rulesButton = new RoundedButton("Правила");
        rulesButton.setFont(new Font("Arial", Font.BOLD, 18));
        rulesButton.setBackground(Color.WHITE);
        rulesButton.setForeground(Color.WHITE);
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
        levelFrame.setSize(900, 700);
        levelFrame.setLocationRelativeTo(null);


        if (level == 1) {
            Level1GamePanel gamePanel = new Level1GamePanel();
            levelFrame.add(gamePanel);


            gamePanel.requestFocusInWindow();
        }
        if (level == 2) {
            Level2GamePanel gamePanel = new Level2GamePanel();
            levelFrame.add(gamePanel);


            gamePanel.requestFocusInWindow();
        }
        if (level == 3) {
            Level3GamePanel gamePanel = new Level3GamePanel();
            levelFrame.add(gamePanel);


            gamePanel.requestFocusInWindow();
        }
        if (level == 4) {
            Level4GamePanel gamePanel = new Level4GamePanel();
            levelFrame.add(gamePanel);


            gamePanel.requestFocusInWindow();
        }

        levelFrame.setVisible(true);
    }


    private void showRules() {
        JFrame rulesFrame = new JFrame("Правила");
        rulesFrame.setSize(500, 400);
        rulesFrame.setLocationRelativeTo(null);

        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setBackground(new Color(10,  10,40));


        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));



        String rulesText = "<html><div style='text-align: center;'>"+"<p>1. Космический корабль покидает исходный пункт с некоторым грузом.</p>" +
                "<p>2. Ему нужно добраться до конечного пункта, не потеряв и не повредив груз.</p>" +
                "<p>3. Нажимая на стрелки, управляйте кораблем, увеличивая скорость в разных направлениях.</p>" +
                "<p>4. Угрозы: астероиды и станция космических пиратов </p>" +
                "<p>5. Защиту от пули пиратов можно получить собрав красную звезду</p>";

        JLabel rulesLabel = new JLabel(rulesText);
        rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rulesLabel.setForeground(Color.WHITE);

        RoundedButton closeButton = new RoundedButton("закрыть");
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
        buttonPanel.setBackground(new Color(10,10,40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(closeButton);

        rulesPanel.add(titleLabel, BorderLayout.NORTH);
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesPanel.add(buttonPanel, BorderLayout.SOUTH);

        rulesFrame.add(rulesPanel);
        rulesFrame.setVisible(true);
    }
}

