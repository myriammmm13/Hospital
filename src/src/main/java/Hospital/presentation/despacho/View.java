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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Hospital.presentation.despacho.detalleMed.View detalleMedView;

    Model model;
    Controller controller;

    View(){

        detallesPrescripcionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detalleMedView = new Hospital.presentation.despacho.detalleMed.
                        View(model.getRecetasList().get(getFilaModeloSeleccionada()));
                detalleMedView.setVisible(true);
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                recetaTable.clearSelection();
            }
        });

        descartarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getFilaModeloSeleccionada()>=0){
                    try{
                        controller.descartarReceta(getFilaModeloSeleccionada());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Error al actualizar: " + ex.getMessage());
                    }
                }
            }
        });

        recetaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && getFilaModeloSeleccionada() >= 0) {
                String estado = model.getRecetasList().get(getFilaModeloSeleccionada()).getEstado();
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
                if(getFilaModeloSeleccionada()>=0){
                    try{
                        switch(estadoRecetaButton.getText()){
                            case "Procesar":
                                controller.procesarReceta(getFilaModeloSeleccionada());
                                limpiarCampos();
                                break;
                            case "Preparar":
                                controller.prepararReceta(getFilaModeloSeleccionada());
                                limpiarCampos();
                                break;
                            case "Despachar":
                                controller.despacharReceta(getFilaModeloSeleccionada());
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

        columnaFiltroMap.put("Paciente", TableModel.PACIENTE);
        columnaFiltroMap.put("Doctor", TableModel.DOCTOR);
    }

    private int getFilaModeloSeleccionada() {
        int filaVista = recetaTable.getSelectedRow();
        if (filaVista == -1) return -1;
        return recetaTable.convertRowIndexToModel(filaVista);
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

                List<Receta> recetasFiltradas = model.getRecetasList().stream()
                        .filter(r -> !r.getEstado().equalsIgnoreCase("entregada"))
                        .collect(Collectors.toList());

                TableModel tableModel = new TableModel(cols, recetasFiltradas);
                recetaTable.setModel(tableModel);


                ordenamientoBusqueda = new TableRowSorter<>(tableModel);
                recetaTable.setRowSorter(ordenamientoBusqueda);
                break;
        }
        this.panel.revalidate();
    }

    private void limpiarCampos() {
        busquedaField.setText("");
        recetaTable.clearSelection();
        recetaTable.revalidate();
    }
}
