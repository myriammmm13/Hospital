package Hospital.presentation.dashboard;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener{
    private JPanel panel;
    private JTable MedicamentosTable;
    private JComboBox medicamentoSelect;
    private JButton confirmarButton;
    private JButton dobleConfirmarButton;
    private JButton button4;
    private JButton button5;
    private JComboBox hastaAnnioSelect;
    private JComboBox despuesMesSelect;
    private JComboBox desdeMesSelect;
    private JComboBox desdeAnnioSelect;
    private JPanel datosPanel;
    private JLabel desdeLabel;
    private JLabel hastaLabel;
    private JLabel medicamentosLabel;

    Controller controller;
    Model model;

    public View(){}
    public JPanel getPanel() {return panel;}
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //llenar
    }

}
