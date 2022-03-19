package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figures {
    private int x, y;
    private int w, h;
    private int rc, gc, bc; //c -> Contorno
    private int ri, gi, bi; //i -> Interno

    public Ellipse (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
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

    public void print (){
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.ri, this.gi, this.bi));
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(this.rc, this.gc, this.bc));
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}