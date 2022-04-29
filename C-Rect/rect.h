#ifndef RECT_H_INCLUDED
#define RECT_H_INCLUDED

typedef struct{
    int x, y, w, h;
} Rect;

Rect* rect_new ();

void rect_print (Rect* this);

void rect_drag (Rect* this, int x, int y);

#endif