package Hospital.presentation.personas.Administrador;
import Hospital.presentation.personas.Administrador.AdminController;
import Hospital.presentation.personas.Administrador.AdminModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminView implements PropertyChangeListener {
    private JPanel panel;

    AdminController controller;
    AdminModel model;

    public AdminView() {
        panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(AdminController controller) {
        this.controller = controller;
    }

    public void setModel(AdminModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}