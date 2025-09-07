package Hospital.presentation.prescribir.buscarMedicamento;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

public class View extends JDialog implements PropertyChangeListener {

    private JPanel panel;
    private JButton cancelButton;
    private JButton okButton;
    private JTable medicamentoBusquedaTable;
    private JTextField busquedaField;
    private JComboBox categoriaBox;

    private TableRowSorter<TableModel> ordenamientoBusqueda;

    public View() {
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
        setTitle("Pacientes");
        setSize(400, 250);

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
                int[] cols = {TableModel.CODIGO, TableModel.NOMBRE, TableModel.PRESENTACION};
                TableModel tableModel = new TableModel(cols, model.getMedicamentosList());
                medicamentoBusquedaTable.setModel(tableModel);

                // Inicializa el sorter
                ordenamientoBusqueda = new TableRowSorter<>(tableModel);
                medicamentoBusquedaTable.setRowSorter(ordenamientoBusqueda);
                break;
        }
        this.panel.revalidate();
    }
}
