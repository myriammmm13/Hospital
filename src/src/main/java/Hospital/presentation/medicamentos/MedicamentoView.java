package Hospital.presentation.medicamentos;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MedicamentoView implements PropertyChangeListener {
    private JPanel panel;

    MedicamentoController controller;
    MedicamentoModel model;

    public MedicamentoView() {
        panel = new JPanel(); // modificar con el GUI Builder
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(MedicamentoController controller) {
        this.controller = controller;
    }

    public void setModel(MedicamentoModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}