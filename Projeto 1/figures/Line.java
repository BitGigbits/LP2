package figures;
import java.awt.*;

public class Line extends Figures{
    private int v1[] = new int[2];
    private int v2[] = new int[2];

    public Line(int x, int y, int w, int h, Color colorBorder){
        super(x, y, w, h, colorBorder);
    }

    public void paint(Graphics g){
        v1[0] = (2*this.x+2*this.w)/2;
        v1[1] = this.x;
        v2[0] = this.y;
        v2[1] = this.y + this.h;
        g.setColor(this.colorBorder);
        g.drawPolygon(this.v1, this.v2, 2);
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