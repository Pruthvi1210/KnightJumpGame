import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel {
    GamePanel() {
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(100, 100, 50, 50);
    }
}