package Hospital.presentation.prescribir;

import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener{
    private JLabel controlLabel;
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JLabel recetaLabel;
    private JPanel controlPanel;
    private JPanel ajustarPanel;
    private JPanel RecetaPanel;
    private JPanel recetasConteo;
    private JLabel nombreLabel;
    private JButton fechaButton;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JLabel ajustarLabel;
    private JPanel panel;
    Model model;
    Controller controller;

    public View() {}

    public JPanel getPanel() {return panel;}

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