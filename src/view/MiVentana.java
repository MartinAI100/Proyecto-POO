package view;

import control.Sistema;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import model.*;

public class MiVentana extends JFrame {
    private Sistema sistema;
    private JPanel lienzo;

    public MiVentana(Sistema sistema) {
        this.sistema = sistema;
        setTitle("GeoDraw");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFiguras = new JMenu("Añadir Figura");
        JMenuItem itemCirculo = new JMenuItem("Círculo");
        JMenuItem itemCuadrado = new JMenuItem("Cuadrado");
        JMenuItem itemPoligono = new JMenuItem("Polígono");
        menuFiguras.add(itemCirculo);
        menuFiguras.add(itemCuadrado);
        menuFiguras.add(itemPoligono);

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemCargar = new JMenuItem("Cargar");
        JMenuItem itemLimpiar = new JMenuItem("Limpiar Lienzo");
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemCargar);
        menuArchivo.add(itemLimpiar);

        menuBar.add(menuArchivo);
        menuBar.add(menuFiguras);
        setJMenuBar(menuBar);

        // Lienzo
        lienzo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Figura f : sistema.getFiguras()) {
                    f.dibujar(g);
                }
            }
        };
        add(lienzo);

        // Listeners
        itemCirculo.addActionListener(e -> agregarCirculo());
        itemCuadrado.addActionListener(e -> agregarCuadrado());
        itemPoligono.addActionListener(e -> agregarPoligono());
        itemLimpiar.addActionListener(e -> {
            sistema.clearFiguras();
            lienzo.repaint();
        });
        itemGuardar.addActionListener(e -> {
            try {
                sistema.guardar("figuras.dat");
                JOptionPane.showMessageDialog(this, "Guardado con éxito");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });
        itemCargar.addActionListener(e -> {
            try {
                sistema.cargar("figuras.dat");
                lienzo.repaint();
                JOptionPane.showMessageDialog(this, "Cargado con éxito");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage());
            }
        });
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
