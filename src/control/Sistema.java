package control;
import model.*;
import java.util.ArrayList;
import java.io.*;

public class Sistema implements Serializable {
    private ArrayList<Figura> figuras;

    public Sistema() {
        figuras = new ArrayList<>();
    }

    public ArrayList<Figura> getFiguras() { return figuras; }

    public void addFigura(Figura f) { figuras.add(f); }

    public void clearFiguras() { figuras.clear(); }

    public void guardar(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(figuras);
        }
    }

    @SuppressWarnings("unchecked")
    public void cargar(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            figuras = (ArrayList<Figura>) ois.readObject();
        }
    }
}
