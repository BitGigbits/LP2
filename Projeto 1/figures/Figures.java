package figures;
import java.awt.*;
import visible.IVisible;

public abstract class Figures implements IVisible{
    public int x, y;
    public int w, h;
    protected Color colorIntern;
    protected Color colorBorder;

    protected Figures(int x, int y, int w, int h, Color colorIntern, Color colorBorder){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorIntern = colorIntern;
        this.colorBorder = colorBorder;
    }

    protected Figures(int x, int y, int w, int h, Color colorBorder){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorBorder = colorBorder;
    }

    public void set_FillColor(Color c){
        if (c != null){
            this.colorIntern = c;
        }
    }

    public void set_BorderColor(Color c){
        if (c != null){
            this.colorBorder = c;
        }
    }

}