package figures;
import java.awt.*;

public class Triangle extends Figures {
    private int v1[] = new int[3];
    private int v2[] = new int[3];

    public Triangle(int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        super(x, y, w , h, colorIntern, colorBorder);
    }

    public void paint(Graphics g){
        this.v1[0] = this.x + this.w;
        this.v1[1] = (2*this.x + this.w)/2;
        this.v1[2] = this.x;
        this.v2[0] = this.y + this.h;
        this.v2[1] = this.y;
        this.v2[2] = this.y + this.h;
        g.setColor(this.colorIntern);
        g.fillPolygon(this.v1, this.v2, 3);
        g.setColor(this.colorBorder);
        g.drawPolygon(this.v1, this.v2, 3);
    }

    public boolean clicked (int x, int y){
        Polygon poly = new Polygon(this.v1, this.v2, 3);
        if (poly.contains(x, y)){
            return true;
        }else{
            return false;
        }
    }

    public void Focus_Paint(Graphics2D g2d, boolean focused){
        int sup1[] = new int[3];
        int sup2[] = new int[3];
        sup1[0] = v1[0] + 3;
        sup1[1] = v1[1];
        sup1[2] = v1[2] - 3;
        sup2[0] = v2[0] + 3;
        sup2[1]  = v2[1] - 4;
        sup2[2]  = v2[2] + 3;
        g2d.setStroke(new BasicStroke(3));
        g2d.drawPolygon(sup1, sup2, 3);
    }
}