package visible;
import java.awt.*;

public interface IVisible {
    boolean clicked (int x, int y);
    void paint (Graphics g, boolean focused);
}
