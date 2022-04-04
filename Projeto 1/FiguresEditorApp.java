import figures.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FiguresEditorApp {

    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
        frame.setTitle("Figuras");
        frame.setSize(500,500);
        frame.setFocusTraversalKeysEnabled(false);
    }
}

class Frames extends JFrame{
    private ArrayList<Figures> figs = new ArrayList<Figures>();
    private int control = 0;
    private int aux;
    private Point poinAux1 = new Point();
    private Figures focus = null;
    private boolean movimento = false;
    private boolean resizing = false;
    private boolean sinal = false;

    Frames(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent evt){
                focus = null;
                poinAux1 = getMousePosition();
                int X = evt.getX();
                int Y = evt.getY();
                for (Figures fig: figs){
                    if (X >= fig.x && X <= fig.x+fig.w && Y >= fig.y && Y <= fig.y+fig.h){
                        focus = fig;
                        movimento = true;
                    }
                }
                if (focus != null){
                    Figures aux = focus;
                    figs.remove(focus);
                    figs.add(aux);
                    focus = aux;
                    repaint();
                }
                repaint();
            }

            public void mouseReleased(MouseEvent evt){
                movimento = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                Point mousept = getMousePosition();
                if (focus != null){
                    if (movimento == true && resizing == false){
                        focus.x = mousept.x - focus.w/2;
                        focus.y = mousept.y - focus.h/2;
                    }else{
                        if (resizing){
                            if (mousept.x > focus.x+focus.w-10) {
                                if (poinAux1.x < mousept.x) {
                                    focus.w += 1;
                                }else {
                                    focus.w -= 1;
                                }
                            }else if(mousept.x < focus.x+10){
                                if (poinAux1.x > mousept.x){
                                    focus.w += 1;
                                    focus.x = mousept.x;
                                }else{
                                    focus.w -= 1;
                                    focus.x = mousept.x;
                                }
                            }
                            if (mousept.y > focus.y+focus.h-10){
                                if (poinAux1.y < mousept.y){
                                    focus.h += 1;
                                }else{
                                    focus.h -= 1;
                                }
                            }else if(mousept.y < focus.y+10){
                                if (poinAux1.y > mousept.y){
                                    focus.h += 1;
                                    focus.y = mousept.y;
                                }else{
                                    focus.h -= 1;
                                    focus.y = mousept.y;
                                }
                            }
                        }
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
                                figs.add(new Rect(ponto.x, ponto.y, 50, 50, Color.white, Color.BLACK));
                                control++;
                                break;
                            case 'e':
                                figs.add(new Ellipse(ponto.x, ponto.y, 50, 50, Color.white, Color.BLACK));
                                control++;
                                break;
                            case 't':
                                figs.add(new Triangle(ponto.x, ponto.y, 50, 50, Color.white, Color.black));
                                control++;
                                break;
                            case 'l':
                                figs.add(new Line(ponto.x, ponto.y, 50, 50, Color.black));
                                control++;
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(focus);
                                focus = null;
                                break;
                            case 'i':
                                if (focus != null){
                                    JColorChooser colorChooser = new JColorChooser();
                                    Color color = JColorChooser.showDialog(null, "Escolha a cor", Color.black);
                                    if (color == null){
                                        break;
                                    }else{
                                        focus.colorIntern = color;
                                    }
                                    break;
                                }
                            case 'b':
                                if (focus != null){
                                    JColorChooser colorChooser = new JColorChooser();
                                    Color color = JColorChooser.showDialog(null, "Escolha a cor", Color.black);
                                    if (color == null){
                                        break;
                                    }else{
                                        focus.colorBorder = color;
                                    }
                                    break;
                                }
                            case 'p':
                                resizing = true;
                                movimento = false;
                                break;
                            case 'o':
                                resizing = false;
                                break;
                            default:
                                break;
                        }

                        switch(evt.getKeyCode()){
                            case KeyEvent.VK_UP:
                                if (focus != null){
                                    focus.y -= 4;
                                }
                                break;
                            case KeyEvent.VK_DOWN:
                                if (focus != null){
                                    focus.y += 4;
                                }
                                break;
                            case KeyEvent.VK_LEFT:
                                if (focus != null){
                                    focus.x -= 4;
                                }
                                break;
                            case KeyEvent.VK_RIGHT:
                                if (focus != null) {
                                    focus.x += 4;
                                }
                                break;
                            default:
                                break;
                        }
                        if (focus != null){
                            for (int i = 0; i < figs.size(); i++){
                                focus = figs.get(i);
                            }
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
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.orange);
        if (focus != null){
            g2d.setStroke(new BasicStroke(4));
            g2d.drawRect(focus.x - 3, focus.y - 3, focus.w + 6, focus.h + 6);
        }
    }
}