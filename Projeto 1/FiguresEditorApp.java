
import javax.swing.*;
import Button.Button;
import figures.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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

            FileOutputStream f2 = new FileOutputStream("proj.svg");
            ObjectOutputStream o2 = new ObjectOutputStream(f2);
            
            o2.writeChars("<svg width=\"1024\" height=\"576\" xmlns=\"http://www.w3.org/2000/svg\"");
            o2.flush();
            o2.close();

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

    protected int mouseX, mouseY, dx, dy;
    protected int pos = 0;
    private int ButClick = 0;
    protected boolean move, resizing;
    private Random rand = new Random();

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

        buts.add(new Button(24, 35, 60, 60, 1, false, new Rect(33, 44, 40, 40, Color.white, Color.black)));
        buts.add(new Button(24, 96, 60, 60, 2, false, new Ellipse(33, 105, 42, 42, Color.white, Color.black)));
        buts.add(new Button(24, 157, 60, 60, 3, false, new Triangle(33, 166, 40, 40, Color.white, Color.black)));
        buts.add(new Button(24, 218, 60, 60, 4, false, new Lines(33, 227, 40, 40, Color.BLACK, null)));
        buts.add(new Button(15, 650, 45, 45, 5, false, new Images(20, 655, 35, 35, null, null, "src/Images/Lixeira.png")));
        buts.add(new Button(61, 650, 45, 45, 6, false, new Images(66, 655, 35, 35, null, null, "src/Images/dices.png")));
        buts.add(new Button(107, 650, 45, 45, 7, false, new Images(112, 655, 35, 35, null, null, "src/Images/brush.png")));

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent evt){
                mouseX = evt.getX();
                mouseY = evt.getY();

                for (Button but: buts){
                    ButClick = but.clicked(mouseX, mouseY);
                    if (ButClick == 1){
                        break;
                    }else{
                        ButClick = 0;
                    }
                }

                if (focus_but != null && focus_but.focused && ButClick == 0){
                    if (focus_but.id == 1){
                        CreateFig(0, evt.getX(), evt.getY());
                    }else if (focus_but.id == 2){
                        CreateFig(1, evt.getX(), evt.getY());
                    }else if (focus_but.id == 3){
                        CreateFig(2, evt.getX(), evt.getY());
                    }else if(focus_but.id == 4){
                        CreateFig(3, evt.getX(), evt.getY());
                    }else if(focus_but.id == 5){
                        SearchFig(evt);
                        figs.remove(focus);
                    }else if(focus_but.id == 6){
                        Point point = getMousePosition();
                        Color RC1 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                        Color RC2 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                        CreateFig(rand.nextInt(4), point.x, point.y);
                        focus = figs.get(figs.size()-1);
                        focus.set_BorderColor(RC1);
                        focus.set_FillColor(RC2);
                    }else if(focus_but.id == 7){
                        SearchFig(evt);
                        if (focus != null){
                            String userResponse;
                            userResponse = JOptionPane.showInputDialog("1 - Trocar a cor de fundo.\n2 - Trocar a cor da borda.");
                            ColorChooser(userResponse);
                        }
                    }
                    focus_but.focused = false;
                    focus_but = null;
                }
                focus = null;
                //Verificando se clicou em uma figura.
                SearchFig(evt);
                //Verificando se clicou em um botão
                ButtonSearch();
                
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
                if (focus != null && evt.getX()> 0 && evt.getY()> 0){
                    if (move && !resizing){

                        focus.setX(evt.getX() - dx);
                        focus.setY(evt.getY() - dy);
                    }else{
                        focus.resize(evt, pos);
                    }
                }
                repaint();
            }
        });

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    Point point = getMousePosition();
                    //Teclas para criar as figuras
                    switch(evt.getKeyChar()){
                        case 'r':
                            CreateFig(0, point.x, point.y);
                            break;
                        case 'e':
                            CreateFig(1, point.x, point.y);
                            break;
                        case 't':
                            CreateFig(2, point.x, point.y);
                            break;
                        case 'l':
                            CreateFig(3, point.x, point.y);
                            break;
                        case KeyEvent.VK_DELETE:
                            if (focus != null){
                                figs.remove(focus);
                                focus = null;
                            }
                            repaint();
                            break;
                        case KeyEvent.VK_TAB:
                            if (focus != null){
                                focus = figs.get(0);
                                Figures aux = focus;
                                figs.remove(focus);
                                figs.add(aux);
                                focus = aux;
                            }
                            break;
                        case 'i':
                            ColorChooser("1");
                            break;
                        case 'b':
                            ColorChooser("2");
                        default:
                            break;
                    }
                    if (figs.size() > 0){
                        focus = figs.get(figs.size()-1);
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

                    repaint();
                }
            }
        );
    }

    public void ColorChooser(String key){
        if (focus != null){
            Color color = JColorChooser.showDialog(null, "Escolha a cor", Color.black);
            switch(key){
                case "1":
                    focus.set_FillColor(color);
                    break;
                case "2":
                    focus.set_BorderColor(color);
                    break;
                default:
                    break;
            }
        }
    }

    public void CreateFig(int key, int X, int Y){
        switch(key){
            case 0:
                figs.add(new Rect(X-25, Y-25, 50, 50, Color.white, Color.BLACK));
                break;
            case 1:
                figs.add(new Ellipse(X-25, Y-25, 50, 50, Color.white, Color.BLACK));   
                break;
            case 2:
                figs.add(new Triangle(X-25, Y-25, 50, 50, Color.white, Color.black));
                break;
            case 3:
                figs.add(new Lines(X-25, Y-25, 50, 50, Color.black, null));
                break;
            default:
                break;
        }
    }

    public void SearchFig(MouseEvent e){
        if (focus_but != null && focus_but.focused){
            return;
        }
        for (Figures fig: figs){
            pos = fig.clicked(e.getX(), e.getY());
            if (pos == 1){
                focus = fig;
                move = true;
                resizing = false;
                dx = mouseX - focus.getX();
                dy = mouseY - focus.getY();
            }else if(pos > 1){
                focus = fig;
                move = false;
                resizing = true;
            }
        }
    }

    public void ButtonSearch(){
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
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Button but: buts){
            but.paint(g);
        }
        for (Figures f: figs){
            f.paint(g);
            if (focus == f){
                focus.Focus_Paint(g);
            }
        }
    }
}
