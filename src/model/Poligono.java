package model;
import java.awt.*;
import java.util.ArrayList;

public class Poligono extends Figura {
    private ArrayList<Point> vertices;

    public Poligono(Color color, ArrayList<Point> vertices) {
        super(vertices.get(0), color);
        this.vertices = vertices;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        int n = vertices.size();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = vertices.get(i).x;
            ys[i] = vertices.get(i).y;
        }
        g.fillPolygon(xs, ys, n);
    }
}
