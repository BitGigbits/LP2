#include <stdio.h>

typedef struct{
    int x, y;
    int innerR, outerR;
    int sides;
}Star ;

void print (Star* s){
    printf("Estrela de %d pontas, com raio interior %d e raio exterior %d na posicao (%d, %d).\n", s->sides, s->innerR, s->outerR, s->x, s->y);
}

int main(){
    Star s1 = {2,4,4,8,5};
    print(&s1);
    return 1;
}