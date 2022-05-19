package figures;
import java.awt.*;
import java.io.Serializable;
import visible.IVisible;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

public abstract class Figures implements IVisible, Serializable{
    protected int x, y;
    protected int w, h;
    protected Rectangle2D rs1, rs2;
    protected Color colorFill;
    protected Color colorBorder;
    protected Color Neutral = new Color(0, 0, 0, 150);

    protected Figures(int x, int y, int w, int h, Color colorFill, Color colorBorder){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorFill = colorFill;
        this.colorBorder = colorBorder;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x > 0){
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y > 0){
            this.y = y;
        }
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        if (w > 0){
            this.w = w;
        }
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        if (h > 0){
            this.h = h;
        }
    }

    public void set_FillColor(Color c){
        if (c != null){
            this.colorFill = c;
        }
    }

    public void set_BorderColor(Color c){
        if (c != null){
            this.colorBorder = c;
        }
    }

    public abstract void resize(MouseEvent e, int pos);
}