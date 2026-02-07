import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private int currentScore = 0;
    private boolean shootingActive = false;
    private java.util.List<Rectangle> bigCoins = new java.util.ArrayList<>();
    private java.util.List<Rectangle> smallCoins = new java.util.ArrayList<>();


    private float[][] asteroidsRel = {
            {0.25f, 0.15f}, {0.55f, 0.25f}, {0.35f, 0.75f},
            {0.75f, 0.45f}, {0.15f, 0.55f}, {0.55f, 0.05f},
            {0.8f, 0.15f}, {0.65f, 0.8f}, {0.38f, 0.45f}
    };

    private float[][] starsRel = {
            {0.5f, 0.1f}, {0.1f, 0.8f}, {0.3f, 0.35f},
            {0.4f, 0.05f}, {0.5f, 0.65f}, {0.8f, 0.25f}
    };
    private boolean[] starCollected = new boolean[6];


    private float pirateRelX = 0.5f;
    private float pirateRelY = 0.5f;
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
        setLayout(null);

        // кнопка сброса
        JButton resetBtn = new JButton("назад");
        resetBtn.setBounds(10, 10, 100, 30);
        resetBtn.addActionListener(e -> resetShip());
        resetBtn.setFocusable(false);
        add(resetBtn);

        // кнопка старта
        JButton shootBtn = new JButton("старт");
        shootBtn.setBounds(115, 10, 100, 30);
        shootBtn.addActionListener(e -> shootingActive = true);
        shootBtn.setFocusable(false);
        add(shootBtn);

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

    private void resetShip() {
        shipX = getWidth() * 0.1f;
        shipY = getHeight() * 0.8f;
        shipVx = 0; shipVy = 0;
        gameOver = false;
        win = false;
        hasCargo = true;
        shieldCharges = 0;
        currentScore = 0;
        shootingActive = false;
        bullets.clear();
        for(int i = 0; i < starCollected.length; i++) starCollected[i] = false;
        initCoins(getWidth(), getHeight());
    }

    private void initCoins(int w, int h) {
        bigCoins.clear();
        smallCoins.clear();
        bigCoins.add(new Rectangle((int)(w*0.45f), (int)(h*0.45f), 20, 20));
        bigCoins.add(new Rectangle((int)(w*0.55f), (int)(h*0.55f), 20, 20));
        bigCoins.add(new Rectangle((int)(w*0.25f)+50, (int)(h*0.15f), 20, 20));
        bigCoins.add(new Rectangle((int)(w*0.75f)-50, (int)(h*0.45f), 20, 20));
        bigCoins.add(new Rectangle((int)(w*0.02f), (int)(h*0.95f), 20, 20));
        smallCoins.add(new Rectangle((int)(w*0.2f), (int)(h*0.2f), 10, 10));
        smallCoins.add(new Rectangle((int)(w*0.8f), (int)(h*0.8f), 10, 10));
        smallCoins.add(new Rectangle((int)(w*0.1f), (int)(h*0.4f), 10, 10));
        smallCoins.add(new Rectangle((int)(w*0.9f), (int)(h*0.6f), 10, 10));
        smallCoins.add(new Rectangle((int)(w*0.5f), (int)(h*0.3f), 10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        if (isFirstLaunch && w > 0 && h > 0) {
            shipX = w * 0.1f; shipY = h * 0.8f;
            lastWidth = w; lastHeight = h;
            initCoins(w, h);
            isFirstLaunch = false;
        }


// старт и финиш
        g.setColor(new Color(0,191,255));
        g.fillRect((int)(w * 0.05f), (int)(h * 0.9f), 120, 20);
        g.fillRect((int)(w * 0.85f), (int)(h * 0.1f), 120, 20);

        g.setColor(new Color(192, 192, 192));
        for (Rectangle r : bigCoins) g.fillOval(r.x, r.y, r.width, r.height);
        for (Rectangle r : smallCoins) g.fillOval(r.x, r.y, r.width, r.height);

// вид астероидов
        g.setColor(Color.GRAY);
        for (float[] ast : asteroidsRel) {
            g.fillOval((int)(w * ast[0]), (int)(h * ast[1]), 45, 45);
            g.setColor(new Color(255, 255, 255, 20));
            g.fillArc((int)(w * ast[0]), (int)(h * ast[1]), 45, 45, 90, 180);
            g.setColor(Color.DARK_GRAY);
            g.fillOval((int)(w * ast[0]) + 10, (int)(h * ast[1]) + 8, 12, 12);
            g.fillOval((int)(w * ast[0]) + 26, (int)(h * ast[1]) + 24, 8, 8);
            g.fillOval((int)(w * ast[0]) + 8, (int)(h * ast[1]) + 30, 6, 6);
            g.setColor(Color.GRAY);
        }

// вид пирата
        g.setColor(Color.YELLOW);
        int px = (int)(w * pirateRelX);
        int py = (int)(h * pirateRelY);
        g.fillPolygon(new int[]{px, px-20, px+20}, new int[]{py-20, py+20, py+20}, 3);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(204, 204, 0));
        g2.setStroke(new BasicStroke(8));
        g2.drawPolygon(new int[]{px, px-20, px+20}, new int[]{py-20, py+20, py+20}, 3);
        g2.setStroke(new BasicStroke(1));

// вид пуль
        g.setColor(Color.WHITE);
        for (Bullet b : bullets) {

            g.setColor(new Color(255, 255, 255, 60));
            g.fillOval((int)b.x - 2, (int)b.y - 2, 12, 12);

            g.setColor(Color.WHITE);
            g.fillOval((int)b.x, (int)b.y, 8, 8);
        }

// вид звезд
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


                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString("*" + shieldCharges, (int)shipX + 5, (int)shipY - 10);
            }
            g.setColor(Color.WHITE);
            g.fillOval((int)shipX, (int)shipY, 40, 40);
            g.setColor(Color.BLACK);
            g.drawOval((int)shipX + 5, (int)shipY + 5, 30, 30);
            g.setColor(Color.GREEN);
            g.fillRect((int)shipX + 15, (int)shipY + 15, 10, 10);
        }

        // очки и рекорд
        g.setColor(new Color(210, 105, 30));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Очки: " + currentScore + " | Рекорд: " + SpaceGameLauncher.getBestScore(), 20, h - 20);

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
            initCoins(w, h);
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

        for (int i = 0; i < bigCoins.size(); i++) {
            if (shipRect.intersects(bigCoins.get(i))) {
                bigCoins.remove(i);
                currentScore += 2;
                SpaceGameLauncher.updateScore(currentScore);
            }
        }
        for (int i = 0; i < smallCoins.size(); i++) {
            if (shipRect.intersects(smallCoins.get(i))) {
                smallCoins.remove(i);
                currentScore += 1;
                SpaceGameLauncher.updateScore(currentScore);
            }
        }

        for (float[] ast : asteroidsRel) {
            if (shipRect.intersects(new Rectangle((int)(w * ast[0]), (int)(h * ast[1]), 45, 45))) {
                loseGame();
            }
        }

        if (shootingActive) {
            shootTimer++;
            if (shootTimer > 60) {
                float px = w * pirateRelX;
                float py = h * pirateRelY;
                float dx = (shipX + 20) - px;
                float dy = (shipY + 20) - py;
                float distance = (float)Math.sqrt(dx*dx + dy*dy);
                float speed = 5.0f;
                bullets.add(new Bullet(px, py, (dx/distance)*speed, (dy/distance)*speed));
                shootTimer = 0;
            }
        }


        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.x += b.vx; b.y += b.vy;
            Rectangle bulletRect = new Rectangle((int)b.x, (int)b.y, 8, 8);

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
                    shieldCharges--;
                    bullets.remove(i--);
                } else {
                    loseGame();
                }
            }
        }

        if (shipRect.intersects(new Rectangle((int)(w * 0.85f), (int)(h * 0.1f), 120, 20))) {
            win = true;
            SpaceGameLauncher.updateScore(currentScore);
        }

        repaint();
    }
    private void loseGame() { gameOver = true; hasCargo = false; SpaceGameLauncher.updateScore(currentScore); repaint(); }
}
