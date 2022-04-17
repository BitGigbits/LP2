import figures.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FiguresEditorApp {

    public static void main(String[] args){
        Frames frame = new Frames();
        frame.setVisible(true);
        frame.setTitle("Figures");
        frame.setSize(1000, 800);
        frame.setFocusTraversalKeysEnabled(false);
        frame.createBufferStrategy(2);
    }
}

class Frames extends JFrame{
    private final ArrayList<Figures> figs = new ArrayList<>();
    private int dx, dy, mouseY, mouseX, storeX, storeY, storeH, storeW;
    private Figures focus = null;
    private boolean move = false;
    private boolean resizing = false;

    Frames(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (focus != null){
                    storeX = focus.x;
                    storeY = focus.y;
                    storeH = focus.h;
                    storeW = focus.w;
                }
            }

            public void mousePressed(MouseEvent evt){
                focus = null;
                mouseX = evt.getX();
                mouseY = evt.getY();
                for (Figures fig: figs){
                    if (fig.clicked(mouseX, mouseY)){
                        focus = fig;
                        if (resizing){
                            move = false;
                            storeX = fig.x;
                            storeY = fig.y;
                            storeH = fig.h;
                            storeW = fig.w;
                        }else{
                            move = true;
                        }
                        dx = mouseX - focus.x;
                        dy = mouseY - focus.y;
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
                move = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                Point mousePoint = getMousePosition();
                if (focus != null){
                    if (move && !resizing){
                        focus.x = mousePoint.x - dx;
                        focus.y = mousePoint.y - dy;
                    }else{
                        if (resizing && mousePoint != null){
                            if (mouseX > storeX + storeW - 10 && mouseX < storeX + storeW) {
                                if (mouseX < mousePoint.x) {
                                    focus.w += mousePoint.x - (focus.x + focus.w);
                                }else{
                                    focus.w -= (focus.x + focus.w) - mousePoint.x;
                                }
                                if (focus.w <= 1){
                                    focus.w = 1;
                                }
                            }else if(mouseX < storeX+10 && mouseX > storeX){
                                if (mouseX > mousePoint.x){
                                    focus.w += focus.x - mousePoint.x;
                                }else{
                                    focus.w = focus.w - (mousePoint.x - focus.x);
                                }
                                if (focus.w > 0){
                                    focus.x = mousePoint.x;
                                }
                                if (focus.w <= 1){
                                    focus.w = 1;
                                    focus.x = (storeW + storeX) + focus.w;
                                }
                            }
                            if (mouseY > storeY + storeH - 10 && mouseY < storeY + storeH){
                                if (mouseY < mousePoint.y){
                                    focus.h +=  mousePoint.y - (focus.y + focus.h);
                                }else{
                                    focus.h -= (focus.y + focus.h) - mousePoint.y;
                                }
                                if (focus.h <= 1){
                                    focus.h = 1;
                                }
                            }else if(mouseY < storeY+10 && mouseY > storeY){
                                if (mouseY > mousePoint.y){
                                    focus.h += focus.y - mousePoint.y;
                                }else{
                                    focus.h -= mousePoint.y - focus.y;
                                }
                                if (focus.h > 0){
                                    focus.y = mousePoint.y;
                                }
                                if (focus.h <= 1){
                                    focus.h = 1;
                                    focus.y = (storeH + storeY) + focus.h;
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
                        Point point = getMousePosition();
                        switch(evt.getKeyChar()){
                            case 'r':
                                figs.add(new Rect(point.x-25, point.y-25, 50, 50, Color.white, Color.BLACK));
                                break;
                            case 'e':
                                figs.add(new Ellipse(point.x-25, point.y-25, 50, 50, Color.white, Color.BLACK));
                                break;
                            case 't':
                                figs.add(new Triangle(point.x-25, point.y-25, 50, 50, Color.white, Color.black));
                                break;
                            case 'l':
                                figs.add(new Line(point.x-25, point.y-25, 50, 50, Color.black));
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(focus);
                                focus = null;
                                break;
                            case KeyEvent.VK_TAB:
                                focus = figs.get(0);
                                if (focus != null){
                                    Figures aux = focus;
                                    figs.remove(focus);
                                    figs.add(aux);
                                    focus = aux;
                                }
                                break;
                            case 'i':
                                if (focus != null){
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
                        for (Figures fig : figs) {
                            focus = fig;
                            storeX = focus.x;
                            storeY = focus.y;
                            storeH = focus.h;
                            storeW = focus.w;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.orange);
        if (focus != null){
            focus.Focus_Paint(g2d);
        }
    }
}