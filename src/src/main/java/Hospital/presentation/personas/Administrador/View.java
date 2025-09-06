package Hospital.presentation.personas.Administrador;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;

    Controller controller;
    Model model;

    public JPanel getPanel(){return panel;}
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