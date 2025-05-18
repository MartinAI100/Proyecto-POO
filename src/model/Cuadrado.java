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
        g.fillRect(posicion.x - lado/2, posicion.y - lado/2, lado, lado);
    }
}
