package Hospital.presentation.personas.Paciente;

import Hospital.presentation.personas.Paciente.Controller;
import Hospital.presentation.personas.Paciente.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JLabel IDLabel;
    private JPanel PacientePanel;
    private JPanel busqPanel;
    private JPanel ListPanel;
    private JTextField IDText;
    private JTextField nomText;
    private JLabel NomLabel;
    private JButton GuardarButton;
    private JButton LimpiarButton;
    private JButton BorrarButton;
    private JLabel NombreLabel;
    private JTextField NombreText;
    private JButton BuscarButton;
    private JButton ReporteButton;
    private JTable ListadoTable;

    Model model;
    Controller controller;

    public View() {
    }

    public JPanel getPanel() { return panel; }

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}
