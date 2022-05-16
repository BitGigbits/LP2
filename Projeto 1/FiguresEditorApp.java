import javax.swing.*;
import Button.Button;
import figures.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FiguresEditorApp extends JFrame{
    static ArrayList<Figures> copy;
    public static void main(String[] args) {
        FiguresEditorApp frame = new FiguresEditorApp();
        Panels panel = new Panels();
        panel.setFocusable(true);
        panel.setBackground(Color.white);
        panel.setFocusTraversalKeysEnabled(false);
        copy = getFigs(panel.figs);
        frame.add(panel);
        frame.setVisible(true);
        frame.setTitle("Aplicativo de Figuras");
        frame.setSize(750, 750);
    }

    public static ArrayList<Figures> getFigs(ArrayList<Figures> figs){
        return figs;
    }

    FiguresEditorApp(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                saveFile(copy);
                System.exit(0);
            }
        });
    }

    public void saveFile(ArrayList<Figures> figs){
        try {
            FileOutputStream f = new FileOutputStream("proj.bin");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(figs);
            o.flush();
            o.close();
        } catch (Exception x) {
            System.out.println("Erro ao gravar dados.");
        }
    }
    
}

class Panels extends JPanel{
    public ArrayList<Figures> figs = new ArrayList<>();
    public ArrayList<Button> buts = new ArrayList<>();
    
    protected Figures focus = null;
    protected Button focus_but = null;

    protected int mouseX, mouseY, dx, dy, storeX, storeY, storeH, storeW;
    protected int pos = 0;
    protected boolean move, resizing;

    @SuppressWarnings("unchecked")
    Panels(){

        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figures>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("Erro ao carregar!");
        }

        buts.add(new Button(30, 35, 60, 60, 1, false, new Rect(39, 44, 40, 40, Color.white, Color.black)));
        buts.add(new Button(30, 96, 60, 60, 2, false, new Ellipse(39, 105, 42, 42, Color.white, Color.black)));
        buts.add(new Button(30, 157, 60, 60, 3, false, new Triangle(39, 166, 40, 40, Color.white, Color.black)));
        buts.add(new Button(30, 218, 60, 60, 4, false, new Lines(39, 227, 40, 40, Color.BLACK, null)));

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (focus != null){
                    storeX = focus.getX();
                    storeY = focus.getY();
                    storeH = focus.getH();
                    storeW = focus.getW();
                }
            }

            public void mousePressed(MouseEvent evt){
                focus = null;
                mouseX = evt.getX();
                mouseY = evt.getY();
                boolean figura = false;

                if (focus_but != null && focus_but.focused){
                    if (focus_but.id == 1){
                        figs.add(new Rect(mouseX-25, mouseY-25, 50, 50, Color.white, Color.BLACK));
                    }else if (focus_but.id == 2){
                        figs.add(new Ellipse(mouseX-25, mouseY-25, 50, 50, Color.white, Color.BLACK));
                    }else if (focus_but.id == 3){
                        figs.add(new Triangle(mouseX-25, mouseY-25, 50, 50, Color.white, Color.black));
                    }else if(focus_but.id == 4){
                        figs.add(new Lines(mouseX-25, mouseY-25, 50, 50, Color.black, null));
                    }
                    focus_but.focused = false;
                    focus_but = null;
                }

                //Verificando se clicou em uma figura.
                for (Figures fig: figs){
                    pos = fig.clicked(mouseX, mouseY);
                    if (pos == 1){
                        focus = fig;
                        move = true;
                        resizing = false;
                        dx = mouseX - focus.getX();
                        dy = mouseY - focus.getY();
                        break;
                    }else if(pos > 1){
                        focus = fig;
                        move = false;
                        resizing = true;
                        break;
                    }
                }

                if (focus == null){
                    for (Button but: buts){
                        if (but.clicked(mouseX, mouseY) == 1){
                            focus_but = but;
                            focus_but.focused = true;
                        }else{
                            but.focused = false;
                        }
                    }
                }else{
                    focus_but = null;
                }
                //Colocando o a figura em froco a frente das outras.
                if (focus != null){
                    Figures aux = focus;
                    figs.remove(focus);
                    figs.add(aux);
                    focus = aux;
                }
                repaint();
            }

            public void mouseReleased(MouseEvent evt){
                move = false;
                resizing = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                Point mousePoint = getMousePosition();
                if (focus != null){
                    if (move && !resizing){
                        focus.setX(mousePoint.x - dx);
                        focus.setY(mousePoint.y - dy);
                    }else{
                        focus.resize(mousePoint.x, mousePoint.y, mouseX, mouseY, storeX, storeY, storeW, storeH, pos);
                    }
                    repaint();
                }
            }
        });

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    //Teclas para criar as figuras
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
                            figs.add(new Lines(point.x-25, point.y-25, 50, 50, Color.black, null));
                            break;
                        case KeyEvent.VK_DELETE:
                            if (focus != null){
                                figs.remove(focus);
                                focus = null;
                            }
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
                                focus.set_FillColor(color);
                                break;
                            }
                        case 'b':
                            if (focus != null){
                                Color color = JColorChooser.showDialog(null, "Escolha a cor", Color.black);
                                focus.set_BorderColor(color);
                                break;
                            }
                        default:
                            break;
                    }
                    switch(evt.getKeyCode()){
                        case KeyEvent.VK_UP:
                            if (focus != null){
                                focus.setY(focus.getY() - 4);
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (focus != null){
                                focus.setY(focus.getY() + 4);
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (focus != null){
                                focus.setX(focus.getX() - 4);
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (focus != null) {
                                focus.setX(focus.getX() + 4);
                            }
                            break;
                        default:
                            break;
                    }
                    for (Figures fig : figs) {
                        focus = fig;
                        storeX = focus.getX();
                        storeY = focus.getY();
                        storeH = focus.getH();
                        storeW = focus.getW();
                    }
                    repaint();
                }
            }
        );
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        for (Button but: buts){
            but.paint(g);
        }
        for (Figures f: figs){
            f.paint(g);
        }
        
        if (focus != null){
            g2d.setColor(Color.orange);
            focus.Focus_Paint(g2d);
        }
    }
}
