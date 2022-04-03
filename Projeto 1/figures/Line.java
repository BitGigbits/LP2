package figures;
import java.awt.*;

public class Line extends Figures{
    private int v1[] = new int[2];
    private int v2[] = new int[2];

    public Line(int x, int y, int w, int h, Color colorIntern){
        super(x, y, w, h, colorIntern);
    }

    public void paint(Graphics g){
        v1[0] = (2*this.x+2*this.w)/2;
        v1[1] = this.x;
        v2[0] = this.y;
        v2[1] = this.y + this.h;
        g.setColor(this.colorIntern);
        g.drawPolygon(this.v1, this.v2, 2);
    }
}