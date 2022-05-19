package figures;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

public class Lines extends Figures{

    private int Fx, Fy, Fw, Fh;

    public Lines(int x, int y, int w, int h, Color colorBorder, Color colorFill){
        super(x, y, w, h, colorBorder, colorFill);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(this.colorBorder);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new Line2D.Double(this.x, this.y, this.x + this.w, this.y + this.h));
    }

    public int clicked (int x, int y){
        double Distance = Line2D.ptSegDist(this.x, this.y, this.x + this.w, this.y + this.h, x, y);
        
        if (Distance < 2){
            return 1;
        }else if (this.rs1.contains(x, y)){
            return 2;
        }else if (this.rs2.contains(x, y)){
            return 3;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.orange);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(this.x, this.y, this.x + this.w, this.y + this.h);

        Fx = this.x-3;
        Fy = this.y-3;
        Fh = this.h+6;
        Fw = this.w+6;

        rs1 = new Rectangle2D.Double(Fx, Fy, 6, 6);
        g2d.setColor(Color.black);
        g2d.fillRect(Fx, Fy, 6, 6);
        g2d.draw(rs1);

        rs2 = new Rectangle2D.Double(Fx+Fw, Fy+Fh, 6, 6);
        g2d.setColor(Color.black);
        g2d.fillRect(Fx+Fw, Fy+Fh, 6, 6);
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