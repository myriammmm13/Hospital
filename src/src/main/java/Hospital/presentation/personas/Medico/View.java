package Hospital.presentation.personas.Medico;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JPanel MedicoPanel;
    private JPanel ListadoPanel;
    private JPanel BusquedaPanel;
    private JLabel IDLabel;
    private JTextField IDText;
    private JLabel NomLabel;
    private JTextField NomText;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JLabel EspLabel;
    private JTextField EspText;
    private JButton borrarButton;
    private JLabel NombreLabelBusq;
    private JTextField NomTextBusq;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable ListaTable;

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public View() {

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