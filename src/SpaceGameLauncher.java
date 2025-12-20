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

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.dispose();

        super.paintComponent(g);
    }


    @Override
    protected void paintBorder(Graphics g) {

    }
}
public class SpaceGameLauncher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SpaceGameLauncher().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {

        JFrame mainFrame = new JFrame("игра");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground( new Color(173, 216,230));


        JLabel titleLabel = new JLabel("главный экран", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));


        JPanel levelsPanel = new JPanel();
        levelsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        levelsPanel.setBackground(new Color(173, 216,230));
        levelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));


        RoundedButton[] levelButtons = new RoundedButton[4];
        String[] levelNames = {"Уровень 1", "Уровень 2", "Уровень 3", "Уровень 4"};
        Color[] buttonColors = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

        for (int i = 0; i < 4; i++) {

            levelButtons[i] = new RoundedButton(levelNames[i]);
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
        rulesPanel.setBackground(new Color(173, 216,230));
        rulesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        RoundedButton rulesButton = new RoundedButton("Правила");
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


        String rulesText = "Космический корабль покидает исходный пункт с некоторым грузом." +
                "Ему нужно добраться до конечного пункта, не потеряв и не повредив груз." +
                "Нажимаю на стрелки 'вперед' и 'назад' доставьте груз в нужное место." +
                "Угрозы: астероиды и космические пираты (встретятся на пути)" +
                "Помощь: оружие против пиратов (можно получить при прохождении части пути), возможность отталкнуть один астероид (для получения нужно пролететь через элемент";

        JLabel rulesLabel = new JLabel(rulesText);
        rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);


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
        buttonPanel.setBackground(new Color(173, 216,230));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(closeButton);

        rulesPanel.add(titleLabel, BorderLayout.NORTH);
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesPanel.add(buttonPanel, BorderLayout.SOUTH);

        rulesFrame.add(rulesPanel);
        rulesFrame.setVisible(true);
    }
}