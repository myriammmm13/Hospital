package Hospital.presentation.recetas;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecetaView implements PropertyChangeListener {
    private JPanel panel;

    RecetaController controller;
    RecetaModel model;

    public RecetaView() {
        panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(RecetaController controller) {
        this.controller = controller;
    }

    public void setModel(RecetaModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}
