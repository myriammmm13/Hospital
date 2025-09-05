package Hospital.presentation.personas.Administrador;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Hospital.presentation.medicamentos.MedicamentosView;

public class View implements PropertyChangeListener {
    private JPanel panel;
//    private JPanel medicoPanel;
//    private JPanel farmaceutaPanel;
//    private JPanel pacientePanel;
//    private JPanel medicamentoPanel;
//    private JPanel dashboardPanel;
//    private JPanel historicoPanel;
//    private JPanel acercaDePanel;
    Controller controller;
    Model model;

    public View() {
    }

//    public JPanel getPanel() {
//        return panel;
//    }
//    public JPanel getMedicoPanel() {return medicoPanel;}
//    public JPanel getFarmaceutaPanel() {return farmaceutaPanel;}
//    public JPanel getPacientePanel() {return pacientePanel;}
//    public JPanel getMedicamentoPanel() {return medicamentosView.getPanel();} //el get de medicamentos view para ligar
//    public JPanel getDashboardPanel() {return dashboardPanel;}
//    public JPanel getHistoricoPanel() {return historicoPanel;}
//    public JPanel getAcercaDePanel() {return acercaDePanel;}

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