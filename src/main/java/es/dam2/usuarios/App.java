package es.dam2.usuarios;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        // Configura el aspecto de la interfaz gráfica al del sistema operativo y lanza la ventana de login
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Se utiliza el aspecto predeterminado si no está disponible el del sistema.
            }
            new LoginFrame().setVisible(true);
        });
    }
}
