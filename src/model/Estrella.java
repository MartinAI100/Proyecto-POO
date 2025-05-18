package model;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Estrella extends Figura {
    private int tamaño;

    public Estrella(Point posicion, Color color, int tamaño) {
        super(posicion, color);
        this.tamaño = tamaño;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        GeneralPath estrella = new GeneralPath();
        double angle = Math.PI / 5;
        int cx = posicion.x, cy = posicion.y;
        estrella.moveTo(cx, cy - tamaño);
        for (int i = 1; i < 10; i++) {
            double a = i * angle;
            int r = (i % 2 == 0) ? tamaño : tamaño / 2;
            estrella.lineTo(cx + (int)(r * Math.sin(a)), cy - (int)(r * Math.cos(a)));
        }
        estrella.closePath();
        g2.fill(estrella);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        return new Rectangle(posicion.x - tamaño, posicion.y - tamaño, tamaño * 2, tamaño * 2).contains(x, y);
    }
}
