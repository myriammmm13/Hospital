package Hospital.presentation.historico;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.despacho.TableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private TableRowSorter<TableModel> ordenamientoBusqueda;
    private Map<String, Integer> columnaFiltroMap = new HashMap<>();
    private Hospital.presentation.despacho.detalleMed.View detalleMedView;

    public View(){
        respuestaBusquedaPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int filaVista = respuestaBusquedaPanel.getSelectedRow();
                    if (filaVista != -1) {
                        int filaModelo = respuestaBusquedaPanel.convertRowIndexToModel(filaVista);
                        TableModel tableModel = (TableModel) respuestaBusquedaPanel.getModel();
                        Receta recetaSeleccionada = tableModel.getRecetaAt(filaModelo);
                        detalleMedView = new Hospital.presentation.despacho.detalleMed.View(recetaSeleccionada);
                        detalleMedView.setVisible(true);
                    }
                }
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
    public JPanel getPanel() { return panel; }

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    private void inicializarComboBox() {
        categoriaBox.addItem("Paciente");
        categoriaBox.addItem("Doctor");
        categoriaBox.addItem("Estado");

        columnaFiltroMap.put("Paciente", TableModel.PACIENTE);
        columnaFiltroMap.put("Doctor", TableModel.DOCTOR);
        columnaFiltroMap.put("Estado", TableModel.ESTADO);
    }

    private void filtrar() {
        if (ordenamientoBusqueda == null) return;

        String texto = busquedaField.getText().trim();
        String categoriaSeleccionada = (String) categoriaBox.getSelectedItem();
        int columna = columnaFiltroMap.getOrDefault(categoriaSeleccionada, TableModel.PACIENTE);

        if (texto.length() == 0) {
            ordenamientoBusqueda.setRowFilter(null);
        } else {
            ordenamientoBusqueda.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.RECETAS:
                int[] cols = {TableModel.PACIENTE, TableModel.DOCTOR, TableModel.PRESCRIPCIONES,
                        TableModel.FECHA_CONFECCION, TableModel.ESTADO};

                TableModel tableModel = new TableModel(cols, model.getRecetasList());
                respuestaBusquedaPanel.setModel(tableModel);


                ordenamientoBusqueda = new TableRowSorter<>(tableModel);
                respuestaBusquedaPanel.setRowSorter(ordenamientoBusqueda);
                break;
            case Model.RECETASRECIENTES:
                int[] cols = {TableModel.PACIENTE, TableModel.DOCTOR, TableModel.PRESCRIPCIONES,
                        TableModel.FECHA_CONFECCION, TableModel.ESTADO};

                TableModel tableModel = new TableModel(cols, model.getRecetasList());
                respuestaBusquedaPanel.setModel(tableModel);
                break;
        }
        this.panel.revalidate();
    }

    private void limpiarCampos() {
        respuestaBusquedaPanel.clearSelection();
        respuestaBusquedaPanel.revalidate();
        table1.revalidate();
    }
}
