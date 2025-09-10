package Hospital.presentation.historico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel listaPanel;
    private JPanel busquedaPanel;
    private JLabel codigoLabel;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable respuestaBusquedaPanel;
    private JTable table1;
    private JPanel panel;

    Controller controller;
    Model model;

    public View(){}
    public JPanel getPanel() {return panel;}
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void setModel(Model model) {
        this.model = model;
       // model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //llenar
    }
}
