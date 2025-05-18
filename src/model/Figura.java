package model;

import java.awt.*;

public abstract class Figura {
    protected Point posicion;
    protected Color color;

    public Figura(Point posicion, Color color) {
        this.posicion = posicion;
        this.color = color;
    }

    public abstract void dibujar(Graphics g);
    public abstract boolean estaDentro(int x, int y);

    public void mover(int dx, int dy) {
        posicion.x += dx;
        posicion.y += dy;
    }

    public void setColor(Color nuevo) {
        this.color = nuevo;
    }

    public String getTipo() {
        return getClass().getSimpleName();
    }
}
