import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame(){
        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        this.setTitle("Hello World!");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.BLACK);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.orange);
        Image img;
        img = new ImageIcon("02-Java2D-Hello\\eletron.png").getImage();
        int w = this.getWidth();
        int h = this.getHeight();
        g2d.drawLine(100,0, 100,350);
        g2d.drawLine(0,h, w,0);
        g2d.drawImage(img, 159, 153, null);
        g2d.drawRect(151, 152, 50, 50);
        g2d.drawString("PEIXE", 157, 146);
    }
}
