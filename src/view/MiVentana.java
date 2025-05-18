package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.SistemaConDibujos;
import model.*;

import java.util.Random;

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

    private void crearMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("Añadir");
        JMenuItem c1 = new JMenuItem("Círculo");
        JMenuItem c2 = new JMenuItem("Cuadrado");
        JMenuItem c3 = new JMenuItem("Triángulo");
        JMenuItem c4 = new JMenuItem("Ovalo");
        JMenuItem c5 = new JMenuItem("Estrella");
        JMenuItem c6 = new JMenuItem("Casa (Figura compuesta)");

        c1.addActionListener(e -> {
            SistemaConDibujos.figuras.add(new Circulo(new Point(100, 100), aleatorioColor(), 30));
            repaint();
        });
        c2.addActionListener(e -> {
            SistemaConDibujos.figuras.add(new Cuadrado(new Point(200, 100), aleatorioColor(), 50));
            repaint();
        });
        c3.addActionListener(e -> {
            SistemaConDibujos.figuras.add(new Poligono(new Point(300, 100), aleatorioColor(),
                    new int[]{0, 40, 20}, new int[]{0, 0, 50}));
            repaint();
        });
        c4.addActionListener(e -> {
            SistemaConDibujos.figuras.add(new Ovalo(new Point(400, 100), aleatorioColor(), 70, 40));
            repaint();
        });
        c5.addActionListener(e -> {
            SistemaConDibujos.figuras.add(new Estrella(new Point(500, 100), aleatorioColor(), 30));
            repaint();
        });
        c6.addActionListener(e -> {
            FiguraCompuesta casa = new FiguraCompuesta(new Point(600, 300), Color.BLACK);
            casa.agregar(new Cuadrado(new Point(600, 300), Color.ORANGE, 60));
            casa.agregar(new Poligono(new Point(600, 300), Color.RED,
                    new int[]{0, 60, 30}, new int[]{0, 0, -40}));
            SistemaConDibujos.figuras.add(casa);
            repaint();
        });

        menu.add(c1); menu.add(c2); menu.add(c3); menu.add(c4); menu.add(c5); menu.add(c6);
        bar.add(menu);

        JMenu archivo = new JMenu("Archivo");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem cargar = new JMenuItem("Cargar");
        JMenuItem stats = new JMenuItem("Estadísticas");

        guardar.addActionListener(e -> SistemaConDibujos.guardar("dibujos.txt"));
        cargar.addActionListener(e -> { SistemaConDibujos.cargarDesdeArchivo("dibujos.txt"); repaint(); });
        stats.addActionListener(e -> SistemaConDibujos.mostrarEstadisticas());

        archivo.add(guardar); archivo.add(cargar); archivo.add(stats);
        bar.add(archivo);

        setJMenuBar(bar);
    }

    private Color aleatorioColor() {
        Random r = new Random();
        return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }
}
