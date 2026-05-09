import javax.swing.JFrame;
import javax.tools.Tool;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GameFrame extends JFrame {
    GameFrame() {
        this.setTitle("Gravebound");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        
        this.add(new GamePanel());

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
