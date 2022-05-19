package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

public class Ellipse extends Figures {
    private int Fx, Fy, Fh, Fw;

    public Ellipse (int x, int y, int w, int h, Color colorFill, Color colorBorder){
        super(x, y, w, h, colorFill, colorBorder);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(this.colorFill);
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }

    public int clicked (int x, int y){
        Ellipse2D e2 = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
        if (e2.contains(x, y)){
            return 1;
        }else if(this.rs1.contains(x,y)){
            return 2;
        }else if(this.rs2.contains(x,y)){
            return 3;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.orange);
        g2d.setStroke(new BasicStroke(3));
        Fx = this.x-3;
        Fy = this.y-3;
        Fh = this.h+6;
        Fw = this.w+6;
        g2d.draw(new Ellipse2D.Double(Fx, Fy, Fw, Fh));

        rs1 = new Rectangle2D.Double(Fx+2, Fy+2, 6, 6);
        g2d.setColor(Color.black);
        g2d.fillRect(Fx+2, Fy+2, 6, 6);
        g2d.draw(rs1);

        rs2 = new Rectangle2D.Double(Fx+Fw-5, Fy+Fh-5, 6, 6);
        g2d.setColor(Color.black);
        g2d.fillRect(Fx+Fw-5, Fy+Fh-5, 6, 6);
        g2d.draw(rs2); 
    }

    public void resize(MouseEvent e, int pos) {
        if (pos == 2){

            int MaximumDecreaseX = this.getX() + this.getW();
            int MaximumDecreaseY = this.getY() + this.getH();

            this.setW((this.getX() - e.getX()) + this.getW());
            if (e.getX() < MaximumDecreaseX){
                this.setX(e.getX());
            }

            this.setH((this.getY() - e.getY()) + this.getH());
            if (e.getY() < MaximumDecreaseY){
                this.setY(e.getY());
            }

        }
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