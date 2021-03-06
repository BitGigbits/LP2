package Button;
import visible.IVisible;
import figures.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class Button implements IVisible {

    private int x, y, w, h;
    private Rectangle2D but;
    private Figures fig;
    public int id;
    public boolean focused;
    Color c1 = new Color(0,0,0, 20);
    Color c2 = new Color(0,0,0, 100);

    public Button(int x, int y, int h, int w, int id, boolean focused, Figures fig) {
        this.id = id;
        this.fig = fig;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.but = new Rectangle2D.Double(x, y, h, w);
    }

    public void paint(Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g.create();

        if (focused){
            g2d.setColor(Color.gray);
        }else{
            g2d.setColor(Color.white);
        }
        g2d.fillRect(x, y, w, h);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(but);
        if (fig != null){
            this.fig.paint(g, false);
        }
    }

    public boolean clicked (int x, int y){
        if (but.contains(x, y)){
            return true;
        }else{
            return false;
        }
    }
}
