package Hospital.presentation.dashboard;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
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
    private JPanel datosPanel;
    private JLabel desdeLabel;
    private JLabel hastaLabel;
    private JLabel medicamentosLabel;
    private JPanel annioDesdePanel;
    private JPanel annioHastaPanel;
    private DatePicker annioHasta;
    private DatePicker annioDesde;
    Controller controller;
    Model model;

    public View(){
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("yyyy-MM-dd");
        annioDesde = new DatePicker(settings);
        annioDesdePanel.setLayout(new BorderLayout());
        annioDesdePanel.add(annioDesde, BorderLayout.CENTER);

        DatePickerSettings a = new DatePickerSettings();
        a.setFormatForDatesCommonEra("yyyy-MM-dd");
        annioHasta = new DatePicker(a);
        annioHastaPanel.setLayout(new BorderLayout());
        annioHastaPanel.add(annioHasta, BorderLayout.CENTER);

    }
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
