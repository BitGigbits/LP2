package figures;
import java.awt.*;

public class Triangle {
    private int x[];
    private int y[];
    private int p;

    public Triangle(int x[], int y[], int p){
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public void paint(Graphics g){
        g.drawPolygon(this.x, this.y, this.p);
    }

}
