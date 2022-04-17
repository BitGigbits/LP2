package figures;
import java.awt.*;

import visible.IVisible;

public abstract class Figures implements IVisible{
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

}