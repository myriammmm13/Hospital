package Hospital.frontend.presentation.AcercaDe;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panelPrincipal;
    private JPanel imagePanel;
    private JLabel imageHospital;
    private JLabel nomHospital;
    private JLabel ContacNumber;
    private JLabel direc;
    Controller controller;
    Model model;

    public View() {

    }

    public JPanel getPanel() {
        System.out.println("Panel de Acerca de: " + panelPrincipal);
        return panelPrincipal;
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