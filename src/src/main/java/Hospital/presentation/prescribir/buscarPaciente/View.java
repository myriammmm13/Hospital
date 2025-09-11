package Hospital.presentation.prescribir.buscarPaciente;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

public class View extends JDialog implements PropertyChangeListener{
    private JPanel panel;
    private JComboBox categoriaBox;
    private JTextField busquedaField;
    private JButton cancelButton;
    private JButton okButton;
    private JTable personasBusquedaTable;

    private TableRowSorter<TableModel> ordenamientoBusqueda;

    public View() {
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
        setTitle("Pacientes");
        setSize(400, 250);

        inicializarComboBox();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(personasBusquedaTable.getSelectedRow()>=0){
                    controller.setPaciente(personasBusquedaTable.getSelectedRow());
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.this.setVisible(false);
            }
        });

        busquedaField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });
    }

    private Map<String, Integer> columnaFiltroMap = new HashMap<>();

    private void inicializarComboBox() {
        categoriaBox.addItem("Id");
        categoriaBox.addItem("Nombre");
        categoriaBox.addItem("Telefono");

        columnaFiltroMap.put("Id", TableModel.ID);
        columnaFiltroMap.put("Nombre", TableModel.NOMBRE);
        columnaFiltroMap.put("Telefono", TableModel.TELEFONO);
    }

    private void filtrar() {
        if (ordenamientoBusqueda == null) return;

        String texto = busquedaField.getText().trim();
        String categoriaSeleccionada = (String) categoriaBox.getSelectedItem();
        int columna = columnaFiltroMap.getOrDefault(categoriaSeleccionada, TableModel.NOMBRE);

        if (texto.length() == 0) {
            ordenamientoBusqueda.setRowFilter(null);
        } else {
            ordenamientoBusqueda.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
        personasBusquedaTable.repaint();
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);

        propertyChange(new PropertyChangeEvent(model, Model.PACIENTES, null, null));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PACIENTES:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.TELEFONO, TableModel.FEC_NAC};
                TableModel tableModel = new TableModel(cols, model.getPacientesList());
                personasBusquedaTable.setModel(tableModel);

                ordenamientoBusqueda = new TableRowSorter<>(tableModel);
                personasBusquedaTable.setRowSorter(ordenamientoBusqueda);
                break;
        }
        this.panel.revalidate();
    }
}
