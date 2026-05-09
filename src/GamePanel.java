import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel implements KeyListener {
    int playerX = 50;
    int playerY = 400;
    int playerWidth = 80;
    int playerHeight = 80;
    int velocityY = 0;
    int gravity = 1;
    boolean onGround = false;
   
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    GamePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        platforms.add(new Platform(200, 450, 200, 20));
        platforms.add(new Platform(500, 350, 200, 20));
        platforms.add(new Platform(100, 250, 200, 20));
        
        enemies.add(new Enemy(300, 400, 40, 40, 2, 200, 400)); 
        
        Timer timer = new Timer(16, e -> {
            playerY += velocityY;
            velocityY += gravity;
            onGround = false;
            
            Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);

            for (Platform p : platforms) {
                Rectangle platformRect = new Rectangle(p.x, p.y, p.width, p.height);

                if (playerRect.intersects(platformRect)) {
                    if (velocityY > 0 && playerY + playerHeight - velocityY <= p.y) {
                        playerY = p.y - playerHeight;
                        velocityY= 0;
                        onGround= true;     
                    }
                    
                }

                if (playerY >= 420) {
                    playerY = 420;
                    velocityY = 0;
                    onGround = true;
                }

                for (Enemy enemy : enemies) {
                    enemy.update();

                    if (playerRect.intersects(enemy.getBounds())) {
                        playerX = 350;
                        playerY = 250;
                        velocityY = 0;   
                    }
                }
            }
            if (playerX < 0) {
                playerX = 0;  
            }
            if (playerX + playerWidth > getWidth()) {
                playerX = getWidth() - playerWidth;  
            }
            if (playerY < 0) {
                playerY = 0;
            }
            if (playerY + playerHeight > getHeight()) {
                playerY = getHeight() - playerHeight;
            }
            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);
        g.setColor(Color.GRAY);
        for (Platform p : platforms) {
            g.fillRect(p.x, p.y, p.width, p.height);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerX -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playerX += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && onGround) {
            velocityY = -15;
            onGround = false;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}