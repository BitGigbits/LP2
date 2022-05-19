package figures;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

public class Triangle extends Figures {
    int v1[] = new int[3];
    int v2[] = new int[3];
    private int Fx, Fy, Fw, Fh;

    public Triangle(int x, int y, int w, int h, Color colorFill, Color colorBorder) {
        super(x, y, w, h, colorFill, colorBorder);
        this.v1[0] = this.x + this.w;
        this.v1[1] = (2*this.x + this.w)/2;
        this.v1[2] = this.x;
        this.v2[0] = this.y + this.h;
        this.v2[1] = this.y;
        this.v2[2] = this.y + this.h;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        this.v1[0] = this.x + this.w;
        this.v1[1] = (2*this.x + this.w)/2;
        this.v1[2] = this.x;
        this.v2[0] = this.y + this.h;
        this.v2[1] = this.y;
        this.v2[2] = this.y + this.h;

        Polygon poly = new Polygon(v1, v2, 3);

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(colorFill);
        g2d.fill(poly);
        g2d.setColor(colorBorder);
        g2d.draw(poly);
    }

    public void Focus_Paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int sup1[] = new int[3];
        int sup2[] = new int[3];
        sup1[0] = v1[0] + 3;
        sup1[1] = v1[1];
        sup1[2] = v1[2] - 3;
        sup2[0] = v2[0] + 3;
        sup2[1]  = v2[1] - 4;
        sup2[2]  = v2[2] + 3;

        Polygon p = new Polygon(sup1, sup2, 3);

        Fx = this.x-3;
        Fy = this.y-3;
        Fh = this.h+6;
        Fw = this.w+6;

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.orange);
        g2d.draw(p);

        rs2 = new Rectangle2D.Double(Fx+Fw, Fy+Fh, 6, 6);
        g2d.setColor(Color.black);
        g2d.fillRect(Fx+Fw-1, Fy+Fh-1, 6, 6);
        g2d.draw(rs2);

    }

    public int clicked(int x, int y) {
        Polygon py = new Polygon(v1, v2, 3);
        if(py.contains(x, y)){
            return 1;
        }else if(this.rs2.contains(x, y)){
            return 3;
        }
        return 0;
    }

    public void resize(MouseEvent e, int pos) {
        if (pos == 3){
            this.setW(e.getX() - this.getX() - 3);

            if (this.getW() <= 3){
                this.setW(3);
            }

            this.setH(e.getY() - this.getY() - 3);

            if (this.getH() <= 3){
                this.setH(3);
            }
        }
        
    }
    
}
