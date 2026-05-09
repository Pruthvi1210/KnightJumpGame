import java.awt.Color;
import java.awt.Graphics;
public class Platform extends GameObject
{
public Platform(int x, int y, int width, int height)
{
    super(x, y, width, height);
}
@Override
public void draw(Graphics g)
{
    g.setColor(Color.GRAY);
g.fillRect(x, y, width, height);
}
 
}