package model;
import java.awt.*;
import java.io.Serializable;

public abstract class Figura implements Serializable {
    protected Point posicion;
    protected Color color;

    public Figura(Point posicion, Color color) {
        this.posicion = posicion;
        this.color = color;
    }

    public abstract void dibujar(Graphics g);

    public Point getPosicion() { return posicion; }
    public void setPosicion(Point p) { this.posicion = p; }
    public Color getColor() { return color; }
    public void setColor(Color c) { this.color = c; }
}
