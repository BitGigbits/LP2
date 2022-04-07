package figures;
import java.awt.*;

public abstract class Figures {
    public int x, y;
    public int w, h;
    public Color colorIntern;
    public Color colorBorder;

    public Figures(int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorIntern = colorIntern;
        this.colorBorder = colorBorder;
    }

    public Figures(int x, int y, int w, int h, Color colorBorder){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorBorder = colorBorder;
    }

    public abstract void paint(Graphics g);

}