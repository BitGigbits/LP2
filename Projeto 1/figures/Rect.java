package figures;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect extends Figures{

    private int Fx, Fy, Fh, Fw;

    public Rect (int x, int y, int w, int h, Color colorFill, Color colorBorder){
        super(x, y, w, h, colorFill, colorBorder);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(this.colorFill);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.colorBorder);
        g2d.draw(new Rectangle2D.Double(this.x, this.y, this.w, this.h));
    }

    public int clicked (int x, int y){
        Rectangle2D r = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
        if (r.contains(x, y)){
            return 1;
        }else if(x < this.x && x >= Fx && y >= Fy && y < Fy + Fh){
            return 2;
        }else if(x > this.x+this.w && x <= Fx+Fw && y >= Fy && y <= Fy+Fh){
            return 3;
        }else if(x >= Fx && x <= Fx+Fw && y < this.y && y >= Fy){
            return 4;
        }else if(x >= Fx && x <= Fx+Fw && y > this.y+this.h && y <= Fy+Fh){
            return 5;
        }else{
            return 0;
        }
    }

    public void Focus_Paint(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(3));
        Fx = this.x-3;
        Fy = this.y-3;
        Fh = this.h+6;
        Fw = this.w+6;
        g2d.drawRect(Fx, Fy, Fw, Fh);
    }

    public void resize(int GoingX, int GoingY, int MouseX, int MouseY, int storeX, int storeY, int storeW, int storeH, int pos){
        if (pos == 2){
            if (GoingX < storeX){
                this.setW(storeW + (- GoingX + storeX));
                this.setX(GoingX);
            }else if(GoingX > storeX && GoingX < storeX + storeW){
                this.setW(storeW - (GoingX - storeX));
                this.setX(GoingX);
            }
        }
        if (pos == 3){
            if (GoingX > this.x){
                this.setW(storeW + (GoingX - (storeX+storeW)));
            }
        }
        if (pos == 4){
            if (GoingY < storeY){
                this.setH(storeH + (- GoingY + storeY));
                this.setY(GoingY);
            }else if(GoingY > storeY && GoingY < storeY + storeH){
                this.setH(storeH - (GoingY - storeY));
                this.setY(GoingY);
            }
        }
        if (pos == 5){
            if (GoingY > this.y){
                this.setH(storeH + (GoingY - (storeY+storeH)));
            }
        }
    }
}