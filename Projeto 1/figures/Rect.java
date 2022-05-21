package figures;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

public class Rect extends Figures{ 
    private Rectangle2D rs;

    public Rect (int x, int y, int w, int h, Color colorFill, Color colorBorder){
        super(x, y, w, h, colorFill, colorBorder);
    }

    public void paint(Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(this.colorFill);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Rectangle2D.Double(this.x, this.y, this.w, this.h));

        if (focused){
            rs = new Rectangle2D.Double(this.x-3, this.y-3, this.w+6, this.h+6);
            g2d.setColor(Color.orange);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(rs);
        }
    }

    public boolean clicked (int x, int y){
        Rectangle2D r = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
        if (r.contains(x, y)){
            return true;
        }else{
            return false;
        }
    }

    public void drag(MouseEvent e, int dx, int dy) {
        this.setX(e.getX() - dx);
        this.setY(e.getY() - dy);
        
    }
}