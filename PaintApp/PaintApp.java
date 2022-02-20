import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class paintApp{
    public static void main(String[] args){
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3;
    PaintFrame(){
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
    }

    public void paint(Graphics g){
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.ri, this.gi, this.bi));
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(this.rc, this.gc, this.bc));
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}