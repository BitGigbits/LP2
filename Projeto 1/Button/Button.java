package Button;
import visible.IVisible;
import figures.*;

import java.awt.*;
import javax.swing.*;

public class Button extends JFrame implements IVisible {

    private Rect but;
    private Figures fig;
    public int id;
    public boolean focused;
    Color c1 = new Color(0,0,0, 20);
    Color c2 = new Color(0,0,0, 100);

    public Button(int x, int y, int h, int w, int id, boolean focused, Figures fig) {
        this.id = id;
        this.fig = fig;
        this.but = new Rect(x, y, h, w, c1, c2);
        this.focused = focused;
    }

    public void paint(Graphics g){
        if (this.focused){
            but.set_FillColor(c2);
        }else{
            but.set_FillColor(c1);
        }
        but.paint(g);
        this.fig.paint(g);
    }

    public int clicked (int x, int y){
        if (but.clicked(x, y) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics2D g2d){
        return;
    }
}
