import figures.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PressEEllipse {
    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
    }
}

class Frames extends JFrame{
    ArrayList<Ellipse> fig_el = new ArrayList<Ellipse>();
    ArrayList<Rect> fig_re = new ArrayList<Rect>();
    Random rand = new Random();

    Frames(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        for (int i = 0; i < 4; i++){
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
            fig_re.add(new Rect(x, y, w, h, rc, gc, bc, ri, gi, bi));
            repaint();
        }
        
        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed (KeyEvent evt){
                    if(evt.getKeyChar() == 'e'){
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
                        fig_el.add(new Ellipse(x, y, w, h, rc, gc, bc, ri, gi, bi));
                        repaint();
                    }
                }
            }
        );
        this.setTitle("Figuras");
        this.setSize(370,370);
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Ellipse e: this.fig_el){
            e.paint(g);
        }
        for (Rect r: this.fig_re){
            r.paint(g);
        }
    }
}
