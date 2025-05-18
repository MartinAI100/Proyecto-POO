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
        g.fillOval(posicion.x - radio, posicion.y - radio, radio * 2, radio * 2);
    }
}
