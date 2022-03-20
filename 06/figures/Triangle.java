package figures;
import java.awt.*;

public class Triangle extends Figures {
    private int v1[];
    private int v2[];

    public Triangle(int v1[], int v2[], int rc, int gc, int bc, int ri, int gi, int bi){
        super(rc, gc, bc, ri, gi, bi);
        this.v1 = v1;
        this.v2 = v2;
    }

    public void paint(Graphics g){
        g.setColor(new Color(this.ri, this.gi, this.bi));
        g.fillPolygon(v1, v2, 3);
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawPolygon(this.v1, this.v2, 3);
    }

}