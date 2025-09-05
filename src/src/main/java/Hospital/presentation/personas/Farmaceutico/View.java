package Hospital.presentation.personas.Farmaceutico;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel FarmaceuticoPanel;
    private JPanel farmaceuticoPanel;

    public JPanel getPanel() {
        return FarmaceuticoPanel;
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