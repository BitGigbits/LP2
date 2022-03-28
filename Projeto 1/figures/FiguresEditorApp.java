import figures.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FiguresEditorApp {

    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
        frame.setTitle("Figuras");
        frame.setSize(500,500);
        BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    }
}

class Frames extends JFrame{
    private ArrayList<Figures> figs = new ArrayList<Figures>();
    private Random rand = new Random();
    private Figures focus = null;
    private Point mouse1 = new Point(0,0);

    boolean movimento;
    boolean dentro;

    Frames(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
                focus = null;
                Figures aux;
                int X = evt.getX();
                int Y = evt.getY();
                for (Figures fig: figs){
                    if (X >= fig.x && X <= fig.x+50 && Y >= fig.y && Y <= fig.y+50){
                        focus = fig;
                        movimento = true;
                    }
                }
            }

            public void mouseReleased(MouseEvent evt){
                movimento = false;
            }

            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                dentro = false;
            }

            public void mouseEntered(MouseEvent evt) {
                super.mouseEntered(evt);
                int X = evt.getX();
                int Y = evt.getY();
                for (Figures fig : figs) {
                    if (X >= fig.x && X <= fig.x + 50 && Y >= fig.y && Y <= fig.y + 50) {
                        dentro = true;
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                Point mousept = getMousePosition();
                if (movimento){
                    focus.x = mousept.x-25 ;
                    focus.y = mousept.y-25;
                }
                repaint();
            }
        });

        this.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent evt) {
                super.mouseWheelMoved(evt);
                if (focus != null){
                    if (evt.getWheelRotation() < 0){
                        focus.x = focus.w +2;
                        focus.y = focus.h +2;
                    }else{
                        focus.w = focus.w -2;
                        focus.h = focus.h -2;
                    }
                    repaint();
                }
            }
        });

        this.addKeyListener(
                new KeyAdapter(){
                    public void keyPressed (KeyEvent evt){
                        Point ponto = getMousePosition();
                        switch(evt.getKeyChar()){
                            case 'r':
                                figs.add(new Rect(ponto.x, ponto.y, 50, 50, 0, 0, 0,255, 255, 255 ));
                                break;
                            case 'e':
                                figs.add(new Ellipse(ponto.x, ponto.y, 50, 50, 0, 0, 0,255, 255, 255 ));
                                break;
                            case 't':
                                figs.add(new Triangle(new int[] {ponto.x, ponto.x+25, ponto.x+50}, new int[] {ponto.y+50, ponto.y-50, ponto.y+50}, 3, 0,0,0, 255, 255, 255));
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(focus);
                            case KeyEvent.VK_TAB:
                                break;
                            default:
                                break;
                        }
                        repaint();
                    }
                }
        );
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Figures f: figs){
            f.paint(g);
        }
    }

}
