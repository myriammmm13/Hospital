package Hospital.presentation.despacho;

import Hospital.logic.recetas.Receta;

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

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JLabel buscarPorField;
    private JButton estadoRecetaButton;
    private JButton limpiarButton;
    private JButton descartarRecetaButton;
    private JButton detallesPrescripcionButton;
    private JTable recetaTable;
    private JComboBox categoriaBox;
    private JTextField busquedaField;

    private TableRowSorter<TableModel> ordenamientoBusqueda;
    private Map<String, Integer> columnaFiltroMap = new HashMap<>();

    Model model;
    Controller controller;

    View(){
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        descartarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recetaTable.getSelectedRow()>=0){
                    try{
                        controller.descartarReceta(recetaTable.getSelectedRow());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Error al actualizar: " + ex.getMessage());
                    }
                }
            }
        });

        recetaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && recetaTable.getSelectedRow() >= 0) {
                String estado = model.getRecetasList().get(recetaTable.getSelectedRow()).getEstado();
                estado.toLowerCase();

                switch (estado) {
                    case "confeccionada":
                        estadoRecetaButton.setText("Procesar");
                        break;
                    case "en proceso":
                        estadoRecetaButton.setText("Preparar");
                        break;
                    case "lista":
                        estadoRecetaButton.setText("Despachar");
                        break;
                    case "entregada":
                        estadoRecetaButton.setText("—");
                        estadoRecetaButton.setEnabled(false);
                        break;
                    default:
                        estadoRecetaButton.setText("Procesar");
                        break;
                }

                if (!estado.equalsIgnoreCase("entregada")) {
                    estadoRecetaButton.setEnabled(true);
                }
            }
        });

        estadoRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recetaTable.getSelectedRow()>=0){
                    try{
                        switch(estadoRecetaButton.getText()){
                            case "Procesar":
                                controller.procesarReceta(recetaTable.getSelectedRow());
                                limpiarCampos();
                                break;
                            case "Preparar":
                                controller.prepararReceta(recetaTable.getSelectedRow());
                                limpiarCampos();
                                break;
                            case "Despachar":
                                controller.despacharReceta(recetaTable.getSelectedRow());
                                limpiarCampos();
                                break;
                        }
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Error al actualizar: " + ex.getMessage());
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
                recetaTable.setModel(tableModel);


                ordenamientoBusqueda = new TableRowSorter<>(tableModel);
                recetaTable.setRowSorter(ordenamientoBusqueda);
                break;
        }
        this.panel.revalidate();
    }

    private void limpiarCampos() {
        busquedaField.setText("");
        recetaTable.revalidate();
    }
}
