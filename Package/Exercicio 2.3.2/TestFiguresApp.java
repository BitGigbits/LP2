import figures.*;
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
    private Rect r1;
    private Ellipse e1;

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

        this.r1 = new Rect(200, 200, 170, 162, 0, 0, 0, 255, 255, 255);
        this.t1 = new Triangle(x, y, 3);
        this.e1 = new Ellipse(10, 160, 85, 45, 255, 50, 150, 150, 120, 0);
    }

    public void paint(Graphics g){
        this.t1.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
    }
}