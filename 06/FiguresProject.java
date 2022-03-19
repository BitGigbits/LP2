import figures.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FiguresProject {
    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
    }
}

class Frames extends JFrame{
    ArrayList<Figures> figs = new ArrayList<Figures>();
    Random rand = new Random();

    Frames(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
       
        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed (KeyEvent evt){
                    int x = rand.nextInt(370);
                    int y = rand.nextInt(370);
                    int w = rand.nextInt(220);
                    int h = rand.nextInt(220);
                    int rc = rand.nextInt(255);
                    int gc = rand.nextInt(255);
                    int bc = rand.nextInt(255);
                    int ri = rand.nextInt(255);
                    int gi = rand.nextInt(255);
                    int bi = rand.nextInt(255);
                    if(evt.getKeyChar() == 'e'){
                        figs.add(new Ellipse(x, y, w, h, rc, gc, bc, ri, gi, bi));
                    }else if (evt.getKeyChar() == 'r'){
                        figs.add(new Rect(x, y, w, h, rc, gc, bc, ri, gi, bi));
                    }else if(evt.getKeyChar() == 't'){
                        int x2[] = new int[3];
                        int y2[] = new int[3];
                        for (int k = 0; k < 3; k++){
                            x2[k] = rand.nextInt(370);
                            y2[k] = rand.nextInt(370);
                        }
                        figs.add(new Triangle(x2, y2, 3, rc, gc, bc, ri, gi, bi));
                    }
                    repaint();
                }
            }
        );
        this.setTitle("Figuras");
        this.setSize(370,370);
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Figures f: this.figs){
            f.paint(g);
        }
    }
}
