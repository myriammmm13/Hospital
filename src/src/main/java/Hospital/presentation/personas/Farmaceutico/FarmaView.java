package Hospital.presentation.personas.Farmaceutico;
import Hospital.presentation.personas.Farmaceutico.FarmaController;
import Hospital.presentation.personas.Farmaceutico.FarmaModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FarmaView implements PropertyChangeListener {
    private JPanel panel;

    FarmaController controller;
    FarmaModel model;

    public FarmaView() {
        panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(FarmaController controller) {
        this.controller = controller;
    }

    public void setModel(FarmaModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}