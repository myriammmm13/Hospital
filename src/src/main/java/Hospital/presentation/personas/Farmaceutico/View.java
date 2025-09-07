package Hospital.presentation.personas.Farmaceutico;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel FarmaceuticoPanel;
    private JPanel panel;
    private JPanel listadoPanel;
    private JPanel busquedaPanel;
    private JPanel farmaceuticoPanel;
    private JLabel idLabel;
    private JTextField idText;
    private JLabel nombreLabel;
    private JTextField nombreText;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JLabel nomLabel;
    private JTextField nomText;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable listadoTable;

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