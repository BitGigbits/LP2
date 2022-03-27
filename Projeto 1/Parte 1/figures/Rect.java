package figures;
import java.awt.*;

public class Rect extends Figures{
    private int w, h;

    public Rect (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
        super(x, y, rc, gc, bc, ri, gi, bi);
        this.w = w;
        this.h = h;
    }

    public void paint (Graphics g){
        g.setColor(new Color(this.ri, this.gi, this.bi));
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawRect(this.x, this.y, this.w, this.h);
    }
}