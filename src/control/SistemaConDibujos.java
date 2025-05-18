package control;

import model.*;
import view.MiVentana;
import java.awt.*;
import java.io.*;
import java.util.*;

public class SistemaConDibujos {
    public static ArrayList<Figura> figuras = new ArrayList<>();

    public static void main(String[] args) {
        cargarEjemplo();
        new view.MiVentana();
    }

    public static void cargarEjemplo() {
        figuras.clear();
        figuras.add(new Circulo(new Point(50, 60), Color.RED, 30));
        figuras.add(new Cuadrado(new Point(150, 100), Color.BLUE, 60));
        figuras.add(new Poligono(new Point(250, 150), Color.GREEN,
                new int[]{0, 40, 20}, new int[]{0, 0, 50}));
    }

    public static void guardar(String nombreArchivo) {
        try (PrintWriter pw = new PrintWriter(nombreArchivo)) {
            for (Figura f : figuras) {
                pw.println(f.getTipo());
            }
            System.out.println("Guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static void cargarDesdeArchivo(String archivo) {
        figuras.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String tipo;
            while ((tipo = br.readLine()) != null) {
                switch (tipo) {
                    case "Circulo":
                        figuras.add(new Circulo(new Point(100, 100), Color.MAGENTA, 25)); break;
                    case "Cuadrado":
                        figuras.add(new Cuadrado(new Point(200, 200), Color.CYAN, 50)); break;
                    case "Poligono":
                        figuras.add(new Poligono(new Point(300, 250), Color.ORANGE,
                                new int[]{0, 40, 20}, new int[]{0, 0, 50})); break;
                    case "Ovalo":
                        figuras.add(new Ovalo(new Point(350, 200), Color.GRAY, 60, 40)); break;
                    case "Estrella":
                        figuras.add(new Estrella(new Point(400, 100), Color.YELLOW, 30)); break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    public static void mostrarEstadisticas() {
        System.out.println("Total figuras: " + figuras.size());
        Map<String, Integer> conteo = new HashMap<>();
        for (Figura f : figuras) {
            conteo.put(f.getTipo(), conteo.getOrDefault(f.getTipo(), 0) + 1);
        }
        String masComun = Collections.max(conteo.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Figura más común: " + masComun);
    }
}
