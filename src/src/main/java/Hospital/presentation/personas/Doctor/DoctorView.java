package Hospital.presentation.personas.Doctor;
import Hospital.presentation.personas.Doctor.DoctorController;
import Hospital.presentation.personas.Doctor.DoctorModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DoctorView implements PropertyChangeListener {
    private JPanel panel;

    DoctorController controller;
    DoctorModel model;

    public DoctorView() {
        panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(DoctorController controller) {
        this.controller = controller;
    }

    public void setModel(DoctorModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}