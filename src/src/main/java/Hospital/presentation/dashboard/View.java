package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.presentation.dashboard.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class View implements PropertyChangeListener {
    private JLabel Desde;
    private JPanel fechaParaDesde;
    private JLabel Hasta;
    private JComboBox listaMedicamentos;
    private JButton aceptarButton;
    private JTable table1;
    private JPanel panelDatos;

    Controller controller;
    Model model;

    public View() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para consultar indicadores, si querés agregarla después
            }
        });

        listaMedicamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = listaMedicamentos.getSelectedItem().toString();
                Medicamento m = buscarMedicamentoPorNombre(seleccionado);
                if (m != null) {
                    TableModel modeloTabla = (TableModel) table1.getModel();
                    modeloTabla.getRows().add(m); // ← agrega sin reemplazar
                    table1.revalidate();
                    table1.repaint();
                }
            }
        });
    }

    public JPanel getPanel() {
        return panelDatos;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);

        for (Medicamento m : model.getMedicamentos()) {
            listaMedicamentos.addItem(m.getNombre());
        }

        int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.PRESENTACION};
        table1.setModel(new TableModel(cols, new java.util.ArrayList<>()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.PRESENTACION};
                table1.setModel(new TableModel(cols, model.getMedicamentos()));
                break;
            case Model.CURRENT:
                // Si más adelante querés mostrar detalles del medicamento actual, lo agregás acá
                break;
        }
        panelDatos.revalidate();
    }

    private Medicamento buscarMedicamentoPorNombre(String nombre) {
        for (Medicamento m : model.getMedicamentos()) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }
}