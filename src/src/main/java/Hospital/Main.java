package Hospital;

import Hospital.data.Data;
import Hospital.presentation.login.Controller;
import Hospital.presentation.login.Model;
import Hospital.presentation.login.View;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    public static void main(String[] args) {
        Data data = new Data();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {}

        // Mostrar ventana de login
        View loginView = new View();
        Model loginModel = new Model(data);
        Controller loginController = new Controller(loginView, loginModel, (userId, userType) -> {
            SwingUtilities.invokeLater(() -> {
                loginView.dispose();
                launchMainWindow(userId, userType);
            });
        });

        loginView.setVisible(true);
    }

    public static void launchMainWindow(String userId, String userType) {
        JFrame window = new JFrame("Hospital - Usuario: " + userId);
        JTabbedPane tabs = new JTabbedPane();

        switch (userType) {
            case "ADM":
                Hospital.presentation.personas.Administrador.View adminView = new Hospital.presentation.personas.Administrador.View();
                Hospital.presentation.personas.Administrador.Model adminModel = new Hospital.presentation.personas.Administrador.Model();
                Hospital.presentation.personas.Administrador.Controller adminController = new Hospital.presentation.personas.Administrador.Controller(adminModel, adminView);

                tabs.addTab("Medicamentos", adminView.getPanel());
                // agregar más módulos a los que adm tiene acceso
                break;

            case "MED":

                break;

            case "FAR":

                break;

            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido");
                System.exit(0);
        }

        window.setContentPane(tabs);
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}