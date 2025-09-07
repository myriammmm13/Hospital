package Hospital.presentation.prescribir;

import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Receta;
import Hospital.data.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JButton buscarPacienteButton;
    private JButton buscarMedicamentoButton;
    private JTable PrescripcionTable;
    private JButton fechaBiblioButton;
    private JLabel fechaField;
    private JLabel nombreField;

    private Hospital.presentation.prescribir.buscarPaciente.View buscarPacienteView;
    private Hospital.presentation.prescribir.buscarMedicamento.View buscarMedicamentoView;

    Model model;
    Controller controller;

    public View() {
        buscarPacienteView =  new Hospital.presentation.prescribir.buscarPaciente.View();
        buscarMedicamentoView = new  Hospital.presentation.prescribir.buscarMedicamento.View();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receta r = new Receta(model.getCurrent().getPaciente(), Medico doctor, PrescripcionTable.getModel());
                try {
                    controller.create(r, "");
                    JOptionPane.showMessageDialog(panel, "Receta guardada correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Error al guardar: " + ex.getMessage());
                }

            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.create(new Receta(), "ADM-111");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Error al limpiar: " + ex.getMessage());
                }

            }
        });

        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacienteView.setVisible(true);
            }
        });

        buscarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMedicamentoView.setVisible(true);
            }
        });

        descartarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PrescripcionTable.getSelectedRow()>=0){
                    controller.borrarPrescripcion(PrescripcionTable.getSelectedRow());
                }
            }
        });
    }

    public JPanel getPanel() { return panel; }

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);

        buscarPacienteView.setModel(model);
        model.addPropertyChangeListener(buscarPacienteView);

        buscarMedicamentoView.setModel(model);
        model.addPropertyChangeListener((buscarMedicamentoView));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PRESCRIPCIONES:
                int[] cols = {TableModel.MEDICAMENTO,TableModel.PRESENTACION, TableModel.CANTIDAD,
                        TableModel.INDICACIONES, TableModel.DURACION};
                PrescripcionTable.setModel(new TableModel(cols,model.getPrescripcionesList()));
                break;
            case Model.CURRENT:
                Receta r = model.getCurrent();
                break;
            case Model.PACIENTE:
                if (model.getCurrent().getPaciente() != null)
                    nombreField.setText(model.getCurrent().getPaciente().getNombre());
                else
                    nombreField.setText("No seleccionado");
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        PrescripcionTable.setModel(new TableModel());
    }
}
