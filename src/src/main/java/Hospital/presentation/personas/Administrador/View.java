package Hospital.presentation.personas.Administrador;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JTabbedPane tabs;
    private JPanel Medicos;
    private JPanel Farmaceuticos;
    private JPanel Pacientes;
    private JPanel Medicamentos;
    private JPanel Dashboard;
    private JPanel AcercaDe;
    private JPanel Historico;
    private JPanel imagePanel;
    private JLabel imageLabel;

    Controller controller;
    Model model;

    public View() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/hospital.jpg"));
        imageLabel.setIcon(icon);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}