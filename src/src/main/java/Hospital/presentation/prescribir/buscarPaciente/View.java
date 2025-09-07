package Hospital.presentation.prescribir.buscarPaciente;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

public class View extends JDialog implements PropertyChangeListener {
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

            private void filtrar() {
                if (ordenamientoBusqueda == null) return; // Aún no se ha inicializado

                String texto = busquedaField.getText().trim();
                if (texto.length() == 0) {
                    ordenamientoBusqueda.setRowFilter(null);
                } else {
                    ordenamientoBusqueda.setRowFilter(RowFilter.regexFilter("(?i)" + texto, TableModel.NOMBRE));
                }
            }
        });
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
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
