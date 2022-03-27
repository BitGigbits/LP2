package figures;
import java.awt.*;

public class Line extends Figures{
    private int x2, y2;

    public Line (int x, int y, int x2, int y2, int rc, int gc, int bc){
        super(x, y, rc, gc, bc);
        this.x2 = x2;
        this.y2 = y2;
    }

    public void paint (Graphics g){
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawLine(this.x, this.y, this.x2, this.y2);
    }
}