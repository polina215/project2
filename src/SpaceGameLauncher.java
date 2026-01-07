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
        levelFrame.setSize(600, 450); // Чуть больше для игры
        levelFrame.setLocationRelativeTo(null);


        if (level == 1) {
            Level1GamePanel gamePanel = new Level1GamePanel();
            levelFrame.add(gamePanel);


            gamePanel.requestFocusInWindow();
        }
//        if (level == 2) {
//            Level2GamePanel gamePanel = new Level2GamePanel();
//            levelFrame.add(gamePanel);
//
//
//            gamePanel.requestFocusInWindow();
//        }
//        if (level == 3) {
//            Level3GamePanel gamePanel = new Level3GamePanel();
//            levelFrame.add(gamePanel);
//
//
//            gamePanel.requestFocusInWindow();
//        }
//        if (level == 4) {
//            Level4GamePanel gamePanel = new Level4GamePanel();
//            levelFrame.add(gamePanel);
//
//
//            gamePanel.requestFocusInWindow();
//        }
        else  {

            JPanel levelPanel = new JPanel();
            levelPanel.setLayout(new BorderLayout());
            levelPanel.setBackground(new Color(10, 10, 40));


            levelFrame.add(levelPanel);
        }

        levelFrame.setVisible(true);
    }


    private void showRules() {
        JFrame rulesFrame = new JFrame("Правила");
        rulesFrame.setSize(500, 400);
        rulesFrame.setLocationRelativeTo(null);

        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setBackground(new Color(173, 216,230));

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
class Level1GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private float shipX = 0, shipY = 0;
    private float shipVx = 0, shipVy = 0;
    private boolean gameOver = false;
    private boolean win = false;
    private boolean hasCargo = true;
    private boolean isFirstLaunch = true;
    private int lastWidth = 0;
    private int lastHeight = 0;


    // количество и асположение астероидов
    private float[][] asteroidsRel = {
            {0.3f, 0.2f}, {0.4f, 0.5f}, {0.35f, 0.8f},
            {0.5f, 0.3f}, {0.6f, 0.7f}, {0.55f, 0.1f},
            {0.7f, 0.4f}, {0.8f, 0.2f}, {0.6f, 0.5f}
    };

    // количество и расположение звезд
    private float[][] starsRel = {
            {0.5f, 0.2f}, {0.2f, 0.7f}, {0.3f, 0.4f},
            {0.4f, 0.15f}, {0.5f, 0.6f}, {0.75f, 0.3f}
    };
    private boolean[] starCollected = new boolean[6];



    private float pirateRelX = 0.9f;
    private float pirateRelY = 0.85f;
    private int shootTimer = 0;


    class Bullet {
        float x, y;
        float vx, vy;
        public Bullet(float x, float y, float vx, float vy) {
            this.x = x; this.y = y;
            this.vx = vx; this.vy = vy;
        }
    }

    private java.util.ArrayList<Bullet> bullets = new java.util.ArrayList<>();



    private int shieldCharges = 0;


    public Level1GamePanel() {
        setFocusable(true);
        setBackground(new Color(10, 10, 50));

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (gameOver || win) return;
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP: shipVy -= 1; break;
                    case java.awt.event.KeyEvent.VK_DOWN: shipVy += 1; break;
                    case java.awt.event.KeyEvent.VK_LEFT: shipVx -= 1; break;
                    case java.awt.event.KeyEvent.VK_RIGHT: shipVx += 1; break;
                }
            }
        });

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        if (isFirstLaunch && w > 0 && h > 0) {
            shipX = w * 0.1f; shipY = h * 0.8f;
            lastWidth = w; lastHeight = h;
            isFirstLaunch = false;
        }





        g.setColor(new Color(0,191,255));
        g.fillRect((int)(w * 0.05f), (int)(h * 0.9f), 120, 20); // Старт
        g.fillRect((int)(w * 0.85f), (int)(h * 0.1f), 120, 20); // Финиш


        g.setColor(Color.GRAY);
        for (float[] ast : asteroidsRel) {
            g.fillOval((int)(w * ast[0]), (int)(h * ast[1]), 45, 45);
        }


        g.setColor(Color.YELLOW);
        int px = (int)(w * pirateRelX);
        int py = (int)(h * pirateRelY);
        g.fillPolygon(new int[]{px, px-20, px+20}, new int[]{py-20, py+20, py+20}, 3);


        for (Bullet b : bullets) g.fillOval((int)b.x, (int)b.y, 8, 8);


        g.setColor(new Color(196, 84, 58));
        for (int i = 0; i < starsRel.length; i++) {
            if (!starCollected[i]) {
                int sx = (int)(w * starsRel[i][0]);
                int sy = (int)(h * starsRel[i][1]);
                g.fillPolygon(new int[]{sx, sx+7, sx, sx-7}, new int[]{sy-7, sy, sy+7, sy}, 4);
            }
        }

        if (!gameOver || hasCargo) {
            if (shieldCharges > 0) {
                g.setColor(Color.CYAN);
                g.drawOval((int)shipX - 10, (int)shipY - 10, 60, 60);
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString("x" + shieldCharges, (int)shipX + 40, (int)shipY);
            }
            g.setColor(Color.WHITE);
            g.fillOval((int)shipX, (int)shipY, 40, 40);
            g.setColor(Color.BLACK);
            g.drawOval((int)shipX + 5, (int)shipY + 5, 30, 30);
            g.setColor(Color.GREEN);
            g.fillRect((int)shipX + 15, (int)shipY + 15, 10, 10);
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("ГРУЗ ПОТЕРЯН!", w/2 - 120, h/2);
        } else if (win) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("ГРУЗ ДОСТАВЛЕН!", w/2 - 150, h/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver || win) return;
        int w = getWidth();
        int h = getHeight();
        if (w <= 0) return;


        if (lastWidth > 0 && (w != lastWidth || h != lastHeight)) {
            shipX *= (float)w/lastWidth; shipY *= (float)h/lastHeight;
        }
        lastWidth = w; lastHeight = h;

        shipX += shipVx; shipY += shipVy;
        Rectangle shipRect = new Rectangle((int)shipX, (int)shipY, 40, 40);


        if (shipX < 0 || shipX > w-40 || shipY < 0 || shipY > h-40) loseGame();


        for (int i = 0; i < starsRel.length; i++) {
            if (!starCollected[i]) {
                Rectangle starRect = new Rectangle((int)(w * starsRel[i][0])-10, (int)(h * starsRel[i][1])-10, 20, 20);
                if (shipRect.intersects(starRect)) {
                    starCollected[i] = true;
                    shieldCharges++;
                }
            }
        }


        for (float[] ast : asteroidsRel) {
            if (shipRect.intersects(new Rectangle((int)(w * ast[0]), (int)(h * ast[1]), 45, 45))) {
                loseGame();
            }
        }

        // частота стрельбы пиратов
        shootTimer++;
        if (shootTimer > 40) { // Было 60, стало 80 (реже)
            float px = w * pirateRelX;
            float py = h * pirateRelY;

            float dx = shipX - px;
            float dy = shipY - py;
            float distance = (float)Math.sqrt(dx*dx + dy*dy);
            float speed = 6.0f;
            bullets.add(new Bullet(px, py, (dx/distance)*speed, (dy/distance)*speed));
            shootTimer = 0;
        }


        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.x += b.vx; b.y += b.vy;
            Rectangle bulletRect = new Rectangle((int)b.x, (int)b.y, 8, 8);

            // исчезновение пули при касании астероида
            boolean hitAsteroid = false;
            for (float[] ast : asteroidsRel) {
                if (bulletRect.intersects(new Rectangle((int)(w * ast[0]), (int)(h * ast[1]), 45, 45))) {
                    hitAsteroid = true; break;
                }
            }

            if (hitAsteroid || b.x < 0||b.x > w || b.y < 0 || b.y > h) {
                bullets.remove(i--); continue;
            }





            if (bulletRect.intersects(shipRect)) {
                if (shieldCharges > 0) {
                    shieldCharges--; // Щит сработал
                    bullets.remove(i--);
                } else {
                    loseGame();
                }
            }
        }


        if (shipRect.intersects(new Rectangle((int)(w * 0.85f), (int)(h * 0.1f), 120, 20))) win = true;

        repaint();
    }
    private void loseGame() { gameOver = true; hasCargo = false; repaint(); }
}


