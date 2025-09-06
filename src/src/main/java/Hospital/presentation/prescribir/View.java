package Hospital.presentation.prescribir;

import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JButton buscarPacienteButton;
    private JButton buscarMedicamentoButton;
    private JTable PrescripcionTable;
    private JButton fechaBiblioButton;

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
