package figures;
import java.awt.*;

public class Rect extends Figures{
    private int x, y;
    private int w, h;
    private int rc, gc, bc; //c -> Contorno
    private int ri, gi, bi; //i -> Interno

    public Rect (int x, int y, int w, int h, int rc, int gc, int bc, int ri, int gi, int bi){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;
        this.ri = ri;
        this.gi = gi;
        this.bi = bi;
    }

    public void print (){
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g){
        g.setColor(new Color(this.ri, this.gi, this.bi));
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(new Color(this.rc, this.gc, this.bc));
        g.drawRect(this.x, this.y, this.w, this.h);
    }
}