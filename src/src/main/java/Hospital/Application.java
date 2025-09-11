package Hospital;

import Hospital.data.Data;
import Hospital.presentation.login.Controller;
import Hospital.presentation.login.Model;
import Hospital.presentation.login.View;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Application {

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    public static void main(String[] args) {
        Data data = new Data();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {}

        // Mostrar ventana de login
        Hospital.presentation.login.View loginView = new Hospital.presentation.login.View();
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

        // Todos los tríos
        var medicoView = new Hospital.presentation.personas.Medico.View();
        var medicoModel = new Hospital.presentation.personas.Medico.Model();
        var medicoController = new Hospital.presentation.personas.Medico.Controller(medicoModel, medicoView);

        var farmaceuticoView = new Hospital.presentation.personas.Farmaceutico.View();
        var farmaceuticoModel = new Hospital.presentation.personas.Farmaceutico.Model();
        var farmaceuticoController = new Hospital.presentation.personas.Farmaceutico.Controller(farmaceuticoModel, farmaceuticoView);

        var medicamentoView = new Hospital.presentation.medicamentos.View();
        var medicamentoModel = new Hospital.presentation.medicamentos.Model();
        var medicamentoController = new Hospital.presentation.medicamentos.Controller(medicamentoModel, medicamentoView);

        var prescribirView = new Hospital.presentation.prescribir.View();
        var prescribirModel = new Hospital.presentation.prescribir.Model();
        var prescribirController = new  Hospital.presentation.prescribir.Controller(prescribirModel, prescribirView);


        var pacienteView = new Hospital.presentation.personas.Paciente.View();
        var pacienteModel = new Hospital.presentation.personas.Paciente.Model();
        var pacienteController = new Hospital.presentation.personas.Paciente.Controller(pacienteModel, pacienteView);

        var dashboardView = new Hospital.presentation.dashboard.View();
        var dashboardModel = new Hospital.presentation.dashboard.Model();
        var dashboardController = new Hospital.presentation.dashboard.Controller(dashboardModel, dashboardView);

        var historicoView = new Hospital.presentation.historico.View();
        var historicoModel = new Hospital.presentation.historico.Model();
        var historicoController = new Hospital.presentation.historico.Controller(historicoModel, historicoView);

        var acercaDeView = new Hospital.presentation.AcercaDe.View();
        var acercaDeModel = new Hospital.presentation.AcercaDe.Model();
        var acercaDeController = new Hospital.presentation.AcercaDe.Controller(acercaDeModel, acercaDeView);

        switch (userType) {
            case "ADM":
                tabs.addTab("Medicos", cargarIcono("/images/medico1.png"), medicoView.getPanel());
                  tabs.addTab("Farmaceutas", cargarIcono("/images/farmaceuta.png"), farmaceuticoView.getPanel());
                  tabs.addTab("Pacientes", cargarIcono("/images/paciente.png"), pacienteView.getPanel());
                  tabs.addTab("Medicamentos", cargarIcono("/images/medicamento1.png"), medicamentoView.getPanel());
                  tabs.addTab("Dashboard", cargarIcono("/images/dashboard1.png"), dashboardView.getPanel());
                  tabs.addTab("Histórico", cargarIcono("/images/historico1.png"), historicoView.getPanel());
                  tabs.addTab("Acerca de...", cargarIcono("/images/acercade1.png"), acercaDeView.getPanel());
                break;

            case "MED":
                tabs.addTab("Prescribir", cargarIcono("/images/prescipcion1.png"), prescribirView.getPanel());
                tabs.addTab("Dashboard", cargarIcono("/images/dashboard1.png"), dashboardView.getPanel());
                tabs.addTab("Histórico", cargarIcono("/images/historico1.png"), historicoView.getPanel());
                tabs.addTab("Acerca de...", cargarIcono("/images/acercade1.png"), acercaDeView.getPanel());

                break;

            case "FAR":
                tabs.addTab("Prescribir", cargarIcono("/images/prescipcion1.png"), prescribirView.getPanel());
                tabs.addTab("Dashboard", cargarIcono("/images/dashboard1.png"), dashboardView.getPanel());
                tabs.addTab("Acerca de...", cargarIcono("/images/acercade1.png"), acercaDeView.getPanel());
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

    private static ImageIcon cargarIcono(String ruta) {
        URL url = Application.class.getResource(ruta);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image scaled = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return null;
    }
}