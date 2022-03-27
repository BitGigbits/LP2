package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figures {
    private int w, h;

    public Ellipse (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
        super(x, y, rc, gc, bc, ri, gi, bi);
        this.w = w;
        this.h = h;
    }

    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.ri, this.gi, this.bi));
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(this.rc, this.gc, this.bc));
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}