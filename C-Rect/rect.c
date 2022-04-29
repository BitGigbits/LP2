#include "rect.h"
#include <stdio.h>
#include <stdlib.h>

Rect* rect_new(){
    Rect* R  = malloc(sizeof(Rect));
    R->x = 0;
    R->y = 0;
    R->w = 25;
    R->h = 15;
}

void rect_print (Rect* this) {
    printf("Retangulo de tamanho (%d,%d) com coordenadas (%d,%d).\n", this->w, this->h, this->x, this->y);
}

void rect_drag (Rect* this, int dx, int dy){
    this->x += dx;
    this->y += dy;
}