package figures;
import java.awt.*;

public class Rect extends Figures{

    public Rect (int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        super(x, y, w, h, colorIntern, colorBorder);
    }

    @Override
    public void paint(Graphics g){
        if (colorIntern == null){
            colorIntern = Color.white;
        }
        if (colorBorder == null){
            colorBorder = Color.black;
        }
        g.setColor(this.colorIntern);
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(this.colorBorder);
        g.drawRect(this.x, this.y, this.w, this.h);
    }

}