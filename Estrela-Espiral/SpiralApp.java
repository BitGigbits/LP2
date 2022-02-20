public class SpiralApp {
    public static void main (String[] args){
        Spiral sp1 = new Spiral(2,2,5,6);
        sp1.print();
    }
}

class Spiral{
    int x, y; //Posição
    int radius; //Define o espaçamento entre cada volta.
    int turns; //Quantidade de voltas.
    Spiral (int x, int y, int radius, int turns){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.turns = turns;
    }
    void print(){
        System.out.format("Espiral de espaçamento %d, fazendo %d voltas e na posição (%d,%d).\n", this.radius, this.turns, this.x, this.y);
    }
}
