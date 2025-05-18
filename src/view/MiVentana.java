package view;

import control.Sistema;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import model.*;

public class MiVentana extends JFrame {
    private JPanel canvas;
    private Figura figuraSeleccionada = null;
    private Point lastMousePos = null;

    public MiVentana() {
        setTitle("GeoDraw - Proyecto Final");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearMenu();

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Figura f : SistemaConDibujos.figuras) {
                    f.dibujar(g);
                }
            }
        };

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                for (Figura f : SistemaConDibujos.figuras) {
                    if (f.estaDentro(e.getX(), e.getY())) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            figuraSeleccionada = f;
                            lastMousePos = new Point(e.getX(), e.getY());
                        } else if (SwingUtilities.isRightMouseButton(e)) {
                            SistemaConDibujos.figuras.remove(f);
                        }
                        repaint();
                        return;
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                figuraSeleccionada = null;
                lastMousePos = null;
            }

            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (Figura f : SistemaConDibujos.figuras) {
                        if (f.estaDentro(e.getX(), e.getY())) {
                            f.setColor(aleatorioColor());
                            repaint();
                            break;
                        }
                    }
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (figuraSeleccionada != null && lastMousePos != null) {
                    int dx = e.getX() - lastMousePos.x;
                    int dy = e.getY() - lastMousePos.y;
                    figuraSeleccionada.mover(dx, dy);
                    lastMousePos = new Point(e.getX(), e.getY());
                    repaint();
                }
            }
        });

        add(canvas);
        setVisible(true);
    }

    private void agregarCirculo() {
        Random rand = new Random();
        int x = rand.nextInt(lienzo.getWidth() - 100) + 50;
        int y = rand.nextInt(lienzo.getHeight() - 100) + 50;
        int radio = rand.nextInt(30) + 20;
        Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        sistema.addFigura(new Circulo(new model.Point(x, y), color, radio));
        lienzo.repaint();
    }

    private void agregarCuadrado() {
        Random rand = new Random();
        int x = rand.nextInt(lienzo.getWidth() - 100) + 50;
        int y = rand.nextInt(lienzo.getHeight() - 100) + 50;
        int lado = rand.nextInt(40) + 20;
        Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        sistema.addFigura(new Cuadrado(new model.Point(x, y), color, lado));
        lienzo.repaint();
    }

    private void agregarPoligono() {
        Random rand = new Random();
        int numVertices = rand.nextInt(3) + 3; // 3 a 5 vértices
        ArrayList<model.Point> vertices = new ArrayList<>();
        int x0 = rand.nextInt(lienzo.getWidth() - 100) + 50;
        int y0 = rand.nextInt(lienzo.getHeight() - 100) + 50;
        int radio = rand.nextInt(30) + 30;
        double angulo = 2 * Math.PI / numVertices;
        for (int i = 0; i < numVertices; i++) {
            int x = (int) (x0 + radio * Math.cos(i * angulo));
            int y = (int) (y0 + radio * Math.sin(i * angulo));
            vertices.add(new model.Point(x, y));
        }
        Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        sistema.addFigura(new Poligono(color, vertices));
        lienzo.repaint();
    }
}
