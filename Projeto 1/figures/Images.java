package figures;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Images extends Figures{

    BufferedImage img;

    public Images(int x, int y, int w, int h, Color colorFill, Color colorBorder, String URL) {
        super(x, y, w, h, colorFill, colorBorder);
        setImage(URL);
    }

    public void setImage(String URL){
        try {
            img = ImageIO.read(new File(URL));
        } catch (IOException e) {
            System.out.println("Erro: " + URL + " não encontrado! " + e);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, this.x, this.y, this.w, this.h, null);
    }

    //Não utilizados abaixo.
    public void Focus_Paint(Graphics g) {
        return;
    }

    public void resize(MouseEvent e, int pos) {
        return;
    }

    public int clicked(int x, int y) {
        return 0;
    }
    
}
