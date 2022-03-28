package figures;
import java.awt.*;

public abstract class Figures {
    public int x, y;
    public int rc, gc, bc;
    public int ri, gi, bi;
    public int w, h;

    public Point oi;

    public Figures(int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
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

    public Figures(int rc, int gc, int bc, int ri, int gi, int bi){
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
        this.ri = ri;
        this.gi = gi;
        this.bi = bi;
    }

    public Figures(int x, int y, int w, int h, int rc, int gc, int bc){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
    }

    public abstract void paint (Graphics g);

}