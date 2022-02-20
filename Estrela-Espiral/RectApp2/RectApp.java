public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,10);
        r1.print();
        System.out.format("Area do retangulo estabelecido: %d.\n", r1.area());
        r1.drag(2, 3);
        System.out.format("\nMudanças após adição de valores às coordenadas: ");
        r1.print();
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void print () {
        System.out.format("\nRetangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }
    int area(){
        int a = this.w * this.h;
        return a;
    }
    void drag(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
}