package figures;
import java.awt.*;

public class Rect extends Figures{

    public Rect (int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        super(x, y, w, h, colorIntern, colorBorder);
    }

    public void paint(Graphics g){
        g.setColor(this.colorIntern);
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(this.colorBorder);
        g.drawRect(this.x, this.y, this.w, this.h);
    }

    public boolean clicked (int x, int y){
        if (x >= this.x && x <= this.x+this.w && y >= this.y && y <= this.y+this.h){
            return true;
        }else{
            return false;
        }
    }

    public void Focus_Paint(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(this.x - 3, this.y - 3, this.w + 6, this.h + 6);
    }
}