package Hospital.presentation.historico;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.despacho.TableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class View implements PropertyChangeListener {
    private JPanel listaPanel;
    private JTable respuestaBusquedaPanel;
    private JTable table1;
    private JPanel panel;
    private JComboBox categoriaBox;
    private JTextField busquedaField;

    Controller controller;
    Model model;

    private TableRowSorter<TableModel> ordenamientoBusqueda;
    private Map<String, Integer> columnaFiltroMap = new HashMap<>();
    private Hospital.presentation.despacho.detalleMed.View detalleMedView;

    public JPanel getPanel() { return panel; }

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public View(){
        inicializarComboBox();

        respuestaBusquedaPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int filaVista = respuestaBusquedaPanel.getSelectedRow();
                    if (filaVista != -1) {
                        int filaModelo = respuestaBusquedaPanel.convertRowIndexToModel(filaVista);
                        TableModel tableModel = (TableModel) respuestaBusquedaPanel.getModel();
                        Receta recetaSeleccionada = tableModel.getRecetaAt(filaModelo);
                        detalleMedView = new Hospital.presentation.despacho.detalleMed.View(recetaSeleccionada);
                        //detalleMedView.setController(controller);
                        //detalleMedView.setModel(model);
                        //controller.addRecetaReciente(recetaSeleccionada);
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

    private void inicializarComboBox() {
        categoriaBox.addItem("Paciente");
        categoriaBox.addItem("Doctor");

        columnaFiltroMap.put("Paciente", TableModel.PACIENTE);
        columnaFiltroMap.put("Doctor", TableModel.DOCTOR);
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
                int[] colsRec = {TableModel.PACIENTE, TableModel.DOCTOR, TableModel.PRESCRIPCIONES,
                        TableModel.FECHA_CONFECCION, TableModel.ESTADO};

                TableModel tableModelRecientes = new TableModel(colsRec, model.getRecetasRecientes());
                respuestaBusquedaPanel.setModel(tableModelRecientes);
                break;
        }
        this.panel.revalidate();
    }

    private void limpiarCampos() {
        respuestaBusquedaPanel.clearSelection();
        respuestaBusquedaPanel.repaint();
        table1.repaint();
    }
}
