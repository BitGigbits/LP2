package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

public class Ellipse extends Figures {
    private int Fx, Fy, Fh, Fw;

    public Ellipse (int x, int y, int w, int h, Color colorFill, Color colorBorder){
        super(x, y, w, h, colorFill, colorBorder);
    }

    public void paint(Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(this.colorFill);
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        if (focused){
            g2d.setColor(Color.orange);
            g2d.setStroke(new BasicStroke(3));
            Fx = this.x-3;
            Fy = this.y-3;
            Fh = this.h+6;
            Fw = this.w+6;
            g2d.draw(new Ellipse2D.Double(Fx, Fy, Fw, Fh));
        }
    }

    public boolean clicked (int x, int y){
        Ellipse2D e2 = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
        if (e2.contains(x, y)){
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