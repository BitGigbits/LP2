package figures;
import java.awt.*;

public abstract class Figures {
    public int x, y;
    public int rc, gc, bc;
    public int ri, gi, bi;

    public Figures(int x, int y, int rc, int gc, int bc, int ri, int gi, int bi){
        this.x = x;
        this.y = y;
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
    public abstract void paint (Graphics g);
}