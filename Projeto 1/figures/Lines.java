package figures;
import java.awt.*;

import java.awt.geom.Line2D;

public class Lines extends Figures{

    public Lines(int x, int y, int w, int h, Color colorBorder, Color colorFill){
        super(x, y, w, h, colorBorder, colorFill);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g.create();

        g.setColor(this.colorBorder);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new Line2D.Double(this.x, this.y, this.x + this.w, this.y + this.h));
    }

    public int clicked (int x, int y){
        double Distance = Line2D.ptSegDist(this.x, this.y, this.x + this.w, this.y + this.h, x, y);
        
        if (Distance < 2){
            return 1;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(this.x, this.y, this.x + this.w, this.y + this.h);
    }

    public void resize(int MouseX, int MouseY, int MouseX2, int MouseY2, int storeX, int storeY, int storeW, int storeH, int pos) {
        // TODO Auto-generated method stub
        
    }

}