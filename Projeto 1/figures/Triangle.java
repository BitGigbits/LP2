package figures;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Triangle extends Figures {
    private Polygon triangle;

    public Triangle(int x, int y, int w, int h, Color colorFill, Color colorBorder) {
        super(x, y, w, h, colorFill, colorBorder);
        triangle = new Polygon(new int[]{x, x + w/2, x + w}, new int[]{y + h, y, y + h}, 3);
    }

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g.create();

        triangle.xpoints = new int[]{x, x + w/2, x + w};
        triangle.ypoints = new int[]{y + h, y, y + h};

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(colorFill);
        g2d.fill(triangle);

        g2d.setColor(colorBorder);
        g2d.draw(triangle);

        if (focused){
            Polygon p = new Polygon(new int[]{x - 4, x + w/2, x + w + 4}, new int[]{y + h + 3, y - 4, y + h + 3}, 3);

            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.orange);
            g2d.draw(p);
        }
    }

    public boolean clicked(int x, int y) {
        Polygon py = new Polygon(triangle.xpoints, triangle.ypoints, 3);
        if(py.contains(x, y)){
            return true;
        }
        return false;
    }

    public void drag(MouseEvent e, int dx, int dy) {
        for (int i = 0; i < 3; ++i){
            triangle.xpoints[i] += dx;
            triangle.ypoints[i] += dy;
        }
        this.x += dx;
        this.y += dy;
    }
}
