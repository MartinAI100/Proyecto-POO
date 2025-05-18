package model;

import java.awt.*;

public class Circulo extends Figura {
    private int radio;

    public Circulo(Point posicion, Color color, int radio) {
        super(posicion, color);
        this.radio = radio;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(posicion.x, posicion.y, radio * 2, radio * 2);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        int dx = x - (posicion.x + radio);
        int dy = y - (posicion.y + radio);
        return dx * dx + dy * dy <= radio * radio;
    }
}
