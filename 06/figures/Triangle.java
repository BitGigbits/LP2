package figures;
import java.awt.*;

public class Triangle extends Figures {
    private int x[];
    private int y[];
    private int p;
    private int rc, gc, bc; //c -> Contorno
    private int ri, gi, bi; //i -> Interno

    public Triangle(int x[], int y[], int p, int rc, int gc, int bc, int ri, int gi, int bi){
        this.x = x;
        this.y = y;
        this.p = p;
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
        this.ri = ri;
        this.gi = gi;
        this.bi = bi;
    }

    public void paint(Graphics g){
        g.setColor(new Color(this.ri, this.gi, this.bi));
        g.fillPolygon(x, y, 3);
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawPolygon(this.x, this.y, this.p);
    }

}