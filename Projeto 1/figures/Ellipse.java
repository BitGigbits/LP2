package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figures {
    private Ellipse2D el;

    public Ellipse (int x, int y, int w, int h, Color colorFill, Color colorBorder){
        super(x, y, w, h, colorFill, colorBorder);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(this.colorFill);
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }

    public int clicked (int x, int y){
        el = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
        if (el.contains(x, y)){
            return 1;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.x - 3, this.y - 3, this.w + 6, this.h + 6));
    }

    public void resize(int MouseX, int MouseY, int MouseX2, int MouseY2, int storeX, int storeY, int storeW, int storeH, int pos) {
        // TODO Auto-generated method stub
        
    }
}