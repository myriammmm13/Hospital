package Hospital.presentation.personas.Administrador;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel administradorPanel;
    public JPanel getPanel() {
        return administradorPanel;
    }

    Controller controller;
    Model model;

    public View() {
        //medicamentosView = new Hospital.presentation.medicamentos.View(); //para ligar el tap
    }

    //Para ligar con el tap de medicamentos
    //private Hospital.presentation.medicamentos.View medicamentosView;

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