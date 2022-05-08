package figures;
import visible.IVisible;
import java.awt.*;
import javax.swing.*;

public class Button extends JFrame implements IVisible {

    public int id;
    private Figures fig;
    public boolean focused;
    Color c1 = new Color(0,0,0, 20);
    Color c2 = new Color(0,0,0, 100);

    public Button(Figures fig, int id){
        this.fig = fig;
        this.id = id;
    }

    public void paint(Graphics g){
        this.fig.paint(g);
        if (focused){
            g.setColor(c2);
        }else{
            g.setColor(c1);
        }
        g.fillRect(this.fig.x-5, this.fig.y-5, this.fig.h+10, this.fig.w+10);
        g.drawRect(this.fig.x-5, this.fig.y-5, this.fig.h+10, this.fig.w+10);
    }

    public boolean clicked (int x, int y){
        if (x >= this.fig.x-5 && x <= this.fig.x-5 + this.fig.w+10 && y >= this.fig.y-5 && y <= this.fig.y-5 + this.fig.h+10){
            return true;
        }else{
            return false;
        }
    }

    public void Focus_Paint(Graphics2D g2d, boolean focused){
        return;
    }
}
