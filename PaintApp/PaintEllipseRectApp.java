import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;

class PaintEllipseRectApp{
    public static void main(String[] args){
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Rect r1, r2, r3;
    Ellipse e1, e2, e3;
    RectEllipseFrame(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        this.setTitle("Figuras");
        this.setSize(500,500);

        this.r1 = new Rect(50,50,100,30,255,50,150,0 , 255, 0);
        this.r2 = new Rect(100,100,95,45,105,105,40, 255, 66, 99);
        this.r3 = new Rect(200,200,170,162,0,0,0, 255, 255, 255);

        this.e1 = new Ellipse(100,400,50,80,100,45,2,27 , 4, 44);
        this.e2 = new Ellipse(50,250,100,75,0,255,0,160 , 200, 10);
        this.e3 = new Ellipse(10,160,85,45,255,50,150,150 , 120, 0);
    }

    public void paint(Graphics g){
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}

class Rect{
    int x, y;
    int w, h;
    int rc, gc, bc; //c -> Contorno
    int ri, gi, bi; //i -> Interno

    Rect (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
        this.ri = ri;
        this.gi = gi;
        this.bi = bi;
    }

    void paint (Graphics g){
        g.setColor(new Color(this.ri, this.gi, this.bi));
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawRect(this.x, this.y, this.w, this.h);
    }
}

class Ellipse{
    int x, y;
    int w, h;
    int rc, gc, bc; //c -> Contorno
    int ri, gi, bi; //i -> Interno

    Ellipse (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
        this.ri = ri;
        this.gi = gi;
        this.bi = bi;
    }

    void print (){
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.ri, this.gi, this.bi));
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(this.rc, this.gc, this.bc));
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}