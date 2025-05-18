package model;

import java.awt.*;

public class Poligono extends Figura {
    private int[] xPoints, yPoints;

    public Poligono(Point posicion, Color color, int[] dx, int[] dy) {
        super(posicion, color);
        xPoints = new int[dx.length];
        yPoints = new int[dy.length];
        for (int i = 0; i < dx.length; i++) {
            xPoints[i] = posicion.x + dx[i];
            yPoints[i] = posicion.y + dy[i];
        }
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        Polygon p = new Polygon(xPoints, yPoints, xPoints.length);
        return p.contains(x, y);
    }
}
