package model;

import java.awt.*;

public class Cuadrado extends Figura {
    private int lado;

    public Cuadrado(Point posicion, Color color, int lado) {
        super(posicion, color);
        this.lado = lado;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(posicion.x, posicion.y, lado, lado);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        return x >= posicion.x && x <= posicion.x + lado &&
               y >= posicion.y && y <= posicion.y + lado;
    }
}
