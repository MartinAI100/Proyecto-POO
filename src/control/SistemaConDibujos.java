package control;
import model.*;
import view.*;

import javax.swing.SwingUtilities;

public class SistemaConDibujos {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        SwingUtilities.invokeLater(() -> {
            MiVentana ventana = new MiVentana(sistema);
            ventana.setVisible(true);
        });
    }
}
