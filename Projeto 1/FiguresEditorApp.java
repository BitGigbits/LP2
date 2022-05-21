
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
    private boolean click = false;
    protected boolean resizing = false;
    protected boolean move = false, canSearch = false;
    private final Random rand = new Random();

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

        buts.add(new Button(15, 650, 45, 45, 5, false, new Images(20, 655, 35, 35, null, null, "src/Images/cursor.png")));
        buts.add(new Button(61, 650, 45, 45, 6, false, new Images(66, 655, 35, 35, null, null, "src/Images/resize.png")));
        buts.add(new Button(107, 650, 45, 45, 7, false, new Images(112, 655, 35, 35, null, null, "src/Images/Lixeira.png")));
        buts.add(new Button(153, 650, 45, 45, 8, false, new Images(158, 655, 35, 35, null, null, "src/Images/dices.png")));
        buts.add(new Button(199, 650, 45, 45, 9, false, new Images(204, 655, 35, 35, null, null, "src/Images/brush.png")));

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent evt){
                mouseX = evt.getX();
                mouseY = evt.getY();
                Button lastFocus = focus_but;
                boolean foundFig = false;

                if (focus_but != null && focus_but.id != 8 && focus_but.id != 6){
                    SearchFig(evt);
                    if (focus != null){
                        foundFig = true;
                    }
                    focus = null;
                }

                if (focus_but != null && !foundFig){
                    for (Button but: buts){
                        if (but.clicked(mouseX, mouseY)){
                            if (lastFocus == but){
                                if (but.id == 5 || but.id == 8 || but.id == 9){
                                    canSearch = false;
                                    focus = null;
                                }else if (but.id == 6){
                                    resizing = false;
                                    canSearch = false;
                                    focus = null;
                                }
                                focus_but = null;
                                click = true;
                            }else{
                                focus_but = but;
                                focus = null;
                                click = true;
                                resizing = false;
                            }
                            move = false;
                        }
                    }
                }

                if (focus_but != null && !click){
                    canSearch = false;
                    switch (focus_but.id) {
                        case 1:
                            CreateFig(0, mouseX, mouseY);
                            break;
                        case 2:
                            CreateFig(1, mouseX, mouseY);
                            break;
                        case 3:
                            CreateFig(2, mouseX, mouseY);
                            break;
                        case 4:
                            CreateFig(3, mouseX, mouseY);
                            break;
                        case 5:
                            canSearch = true;
                            click = true;
                            break;
                        case 6:
                            resizing = true;
                            canSearch = true;
                            click = true;
                            break;
                        case 7:
                            SearchFig(evt);
                            figs.remove(focus);
                            focus = null;
                            break;
                        case 8:
                            Point point = getMousePosition();
                            Color RC1 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                            Color RC2 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                            CreateFig(rand.nextInt(4), point.x, point.y);
                            focus = figs.get(figs.size() - 1);
                            focus.set_BorderColor(RC1);
                            focus.set_FillColor(RC2);
                            click = true;
                            break;
                        case 9:
                            SearchFig(evt);
                            if (focus != null) {
                                String userResponse;
                                userResponse = JOptionPane.showInputDialog("1 - Trocar a cor de fundo.\n2 - Trocar a cor da borda.");
                                if (userResponse != null) {
                                    ColorChooser(userResponse);
                                }
                            }
                            focus = null;
                            break;
                        default:
                            break;
                    }

                }

                if (canSearch){
                    SearchFig(evt);
                }

                if(!click && !move && !foundFig){
                    ButtonSearch();

                }else if (!move){
                    click = false;
                }

                if (focus != null){
                    figs.remove(focus);
                    figs.add(focus);
                    storeX = focus.getX();
                    storeY = focus.getY();
                    storeH = focus.getH();
                    storeW = focus.getW();
                }

                if (focus_but != null && focus_but.id == 8){
                    click = false;
                }

                repaint();
            }

            public void mouseReleased(MouseEvent evt){
                move = false;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                if (focus != null && evt.getX() > 0 && evt.getY() > 0){
                    if (move && !resizing){
                        if (focus instanceof Triangle){
                            int xNew, yNew;

                            xNew = (evt.getX() - mouseX);
                            yNew = (evt.getY() - mouseY);
                            
                            focus.drag(evt, xNew, yNew);

                            mouseX = evt.getX();
                            mouseY = evt.getY();
                        }else{
                            focus.drag(evt, dx, dy);
                        }
                    }else if(!move && resizing){
                        if (mouseX > storeX + storeW + 8 - 3 && mouseX < storeX + storeW + 8 + 3){
                            if (mouseX < evt.getX()){
                                focus.setW(focus.getW() + (evt.getX() - (focus.getX() + focus.getW())));
                            }else{
                                focus.setW(focus.getW() - ((focus.getX() + focus.getW()) - evt.getX()));
                            }
                            if (focus.getW() <= 1){
                                focus.setW(1);
                            }
                        }else if(mouseX < storeX - 8 + 3 && mouseX > storeX - 8 - 3){
                            if (mouseX > evt.getX()){
                                focus.setW(focus.getW() + (focus.getX() - evt.getX()));
                            }else{
                                focus.setW(focus.getW() - (evt.getX() - focus.getX()));
                            }
                            if (focus.getW() <= 1){
                                focus.setW(1);
                                focus.setX((storeW + storeX) + focus.getW());
                            }
                            if (focus.getW() > 1){
                                focus.setX(evt.getX());
                            }
                        }
                        if (mouseY > storeY + storeH + 8 - 3 && mouseY < storeY + storeH + 8 + 3){
                            if (mouseY < evt.getY()){
                                focus.setH(focus.getH() + (evt.getY() - (focus.getY() + focus.getH())));
                            }else{
                                focus.setH(focus.getH() - ((focus.getY() + focus.getH()) - evt.getY()));
                            }
                            if (focus.getH() <= 1){
                                focus.setH(1);
                            }
                        }else if(mouseY < storeY - 8 + 3 && mouseY > storeY - 8 - 3){
                            if (mouseY > evt.getY()){
                                focus.setH(focus.getH() + (focus.getY() - evt.getY()));
                            }else{
                                focus.setH(focus.getH() - (evt.getY() - focus.getY()));
                            }
                            if (focus.getH() <= 1){
                                focus.setH(1);
                                focus.setY((storeH + storeY) + focus.getH());
                            }
                            if (focus.getH() > 1){
                                focus.setY(evt.getY());
                            }
                        }
                    }
                }
                repaint();
            }
        });

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    Point point = getMousePosition();
                    ButtonSearch_ForKeys(point.x, point.y);
                    //Teclas para criar as figuras
                    if (!ButtonSearch_ForKeys(point.x, point.y)){
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
                                }
                                focus = null;
                                repaint();
                                break;
                            case KeyEvent.VK_TAB:
                                if (focus != null){
                                    focus = figs.get(0);
                                    figs.remove(focus);
                                    figs.add(focus);
                                }else if(figs.size() > 0){
                                    focus = figs.get(0);
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
                    }
                    
                    if (figs.size() > 0){
                        Figures aux = figs.get(figs.size()-1);
                        storeX = aux.getX();
                        storeY = aux.getY();
                        storeH = aux.getH();
                        storeW = aux.getW();
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
        Figures aux = figs.get(figs.size()-1);
        storeX = aux.getX();
        storeY = aux.getY();
        storeH = aux.getH();
        storeW = aux.getW();
    }

    public void SearchFig(MouseEvent e){
        for (Figures fig: figs){
            if (fig.clicked(e.getX(), e.getY())){
                focus = fig;
                dx = mouseX - focus.getX();
                dy = mouseY - focus.getY();
                storeX = focus.getX();
                storeY = focus.getY();
                storeH = focus.getH();
                storeW = focus.getW();
                move = !resizing;
            }
        }
    }

    public void ButtonSearch(){
        if (focus == null){
            for (Button but: buts){
                if (but.clicked(mouseX, mouseY)){
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

    public boolean ButtonSearch_ForKeys(int mx, int my){
        for (Button but: buts){
            if (but.clicked(mx, my)){
                return true;
            }
        }
        return false;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (resizing){
            for (Figures f: figs){
                f.ResizeFocus_Paint(g);
            }
        }
        for (Figures f: figs){
            f.paint(g, f == focus);
        }
        for (Button but: buts){
            but.paint(g, but == focus_but);
        }
    }
}
