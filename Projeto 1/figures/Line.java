package figures;
import java.awt.*;

public class Line extends Figures{

    public Line (int x, int y, int w, int h, int rc, int gc, int bc){
        super(x, y, w, h, rc, gc, bc);
    }

    public void paint (Graphics g){
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawLine(this.x, this.y, this.w, this.h);
    }

}