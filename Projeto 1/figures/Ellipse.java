package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figures {

    public Ellipse (int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        super(x, y, w, h, colorIntern, colorBorder);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colorIntern);
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }

}