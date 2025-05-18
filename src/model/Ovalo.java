package model;

import java.awt.*;

public class Ovalo extends Figura {
    private int ancho, alto;

    public Ovalo(Point posicion, Color color, int ancho, int alto) {
        super(posicion, color);
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(posicion.x, posicion.y, ancho, alto);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        double dx = x - (posicion.x + ancho / 2.0);
        double dy = y - (posicion.y + alto / 2.0);
        return (dx * dx) / (ancho * ancho / 4.0) + (dy * dy) / (alto * alto / 4.0) <= 1;
    }
}
