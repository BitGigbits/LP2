import figures.Triangle;
import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFiguresApp{
    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
    }
}

class Frames extends JFrame{
    private Triangle t1;

    int x[] = {20, 125, 200};
    int y[] = {95, 230, 95};

    Frames(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        this.setTitle("Figuras");
        this.setSize(370,370);

        this.t1 = new Triangle(x, y, 3);
    }

    public void paint(Graphics g){
        this.t1.paint(g);
    }
}