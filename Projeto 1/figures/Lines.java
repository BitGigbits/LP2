package figures;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Lines extends Figures{

    public Lines(int x, int y, int w, int h, Color colorBorder, Color colorFill){
        super(x, y, w, h, colorBorder, colorFill);
    }

    public void paint(Graphics g, boolean focused){

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(this.colorBorder);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new Line2D.Double(this.x, this.y, this.x + this.w, this.y + this.h));

        if (focused){
            g2d.setColor(Color.orange);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(this.x, this.y, this.x + this.w, this.y + this.h);
        }
    }

    public boolean clicked (int x, int y){
        double Distance = Line2D.ptSegDist(this.x, this.y, this.x + this.w, this.y + this.h, x, y);
        
        if (Distance < 2){
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