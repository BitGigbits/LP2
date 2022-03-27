import figures.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FiguresEditorApp {
    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
    }
}

class Frames extends JFrame{
    ArrayList<Figures> figs = new ArrayList<Figures>();
    Random rand = new Random();
    Figures focus = null;

    Frames(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                focus = null;
                for (Figures fig: figs){
                    if(evt.getX() >= fig.x && evt.getX() <= fig.x+50 && evt.getY() >= fig.y && evt.getY() <= fig.y+50){
                        System.out.println("Selecionado.");
                    }
                }
            }
        });

        this.addKeyListener(
                new KeyAdapter(){
                    public void keyPressed (KeyEvent evt){
                        if (evt.getKeyChar() == 'r'){
                            Point ponto = getMousePosition();
                            figs.add(new Rect(ponto.x, ponto.y, 50, 50, 0, 0, 0,255, 255, 255 ));
                        }
                        if (evt.getKeyChar() == 'e'){
                            Point ponto = getMousePosition();
                            figs.add(new Ellipse(ponto.x, ponto.y, 50, 50, 0, 0, 0,255, 255, 255 ));
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
