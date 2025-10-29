package presentation.prescribir;

import Hospital.backend.logic.Service;
import Hospital.backend.logic.Session;
import Hospital.backend.logic.personas.Paciente;
import Hospital.backend.logic.personas.Trabajador;
import Hospital.backend.logic.recetas.Prescripcion;
import Hospital.backend.logic.recetas.Receta;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class View implements PropertyChangeListener {
    private JPanel panelPrincipal;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JButton buscarPacienteButton;
    private JButton buscarMedicamentoButton;
    private JTable PrescripcionTable;
    private JLabel fechaField;
    private JLabel nombreField;
    private JPanel fechaPanel;
    private JPanel controlPanel;
    private JPanel recetaPanel;
    private JPanel medioPanel;
    private JPanel ajustarPanel;
    private JLabel nombrePaciente;
    private JLabel nombreLabel;
    private DatePicker fecha;
    private presentation.prescribir.buscarPaciente.View buscarPacienteView;
    private presentation.prescribir.buscarMedicamento.View buscarMedicamentoView;
    private presentation.prescribir.buscarMedicamento.crearPrescripcion.View CrearPrescripcionView;

    Model model;
    Controller controller;

    public View() {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("yyyy-MM-dd");
        fecha = new DatePicker(settings);
        fechaPanel.setLayout(new BorderLayout());
        fechaPanel.add(fecha, BorderLayout.CENTER);

        buscarPacienteView =  new presentation.prescribir.buscarPaciente.View();
        buscarMedicamentoView = new presentation.prescribir.buscarMedicamento.View();
        buscarPacienteView.setController((controller));
        buscarMedicamentoView.setController((controller));

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel modeloTabla = (TableModel) PrescripcionTable.getModel();
                List<Prescripcion> listaPrescripciones = modeloTabla.getPrescripciones();

                Paciente paciente = model.getCurrent().getPaciente();
                String idTrabajador = Session.getInstance().getUsuario();

                Trabajador doctor = Service.instance().findTrabajadorById(idTrabajador);

                if (doctor == null) {
                    JOptionPane.showMessageDialog(panelPrincipal, "No se encontró el médico con ID: " + idTrabajador);
                    return;
                }

                if (paciente == null) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un paciente.");
                    return;
                }

                if (listaPrescripciones.isEmpty()) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe agregar al menos una prescripción.");
                    return;
                }

                Receta r = new Receta(paciente, listaPrescripciones, doctor);
                r.setFechaRetiro(fecha.getDate());
                model.setCurrent(r);

                try {
                    controller.create(r);
                    JOptionPane.showMessageDialog(panelPrincipal, "Receta guardada correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Error al guardar: " + ex.getMessage());
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {controller.clear();}
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

        detallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PrescripcionTable.getSelectedRow()>=0){
                    Prescripcion pres = model.getPrescripcionesList().get(PrescripcionTable.getSelectedRow());
                    CrearPrescripcionView = new presentation.prescribir.buscarMedicamento.crearPrescripcion.View(pres.getMedicamento());
                    CrearPrescripcionView.setPrescripcion(pres);
                    CrearPrescripcionView.setRow(PrescripcionTable.getSelectedRow());
                    CrearPrescripcionView.setController(controller);
                    CrearPrescripcionView.setModel(model);
                    CrearPrescripcionView.setVisible(true);
                }
            }
        });

    }

    public JPanel getPanel() { return panelPrincipal; }

    public void setController(Controller controller) {
        this.controller = controller;
        buscarPacienteView.setController(controller); // ← esta línea es clave
        buscarMedicamentoView.setController(controller);
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
        propertyChange(new PropertyChangeEvent(model, Model.PRESCRIPCIONES, null, null));

        buscarPacienteView.setModel(model);
        model.addPropertyChangeListener(buscarPacienteView);

        buscarMedicamentoView.setModel(model);
        model.addPropertyChangeListener(buscarMedicamentoView);
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
                    nombrePaciente.setText(model.getCurrent().getPaciente().getNombre());
                else
                    nombrePaciente.setText("No seleccionado");
        }
    }

    public void clearFields() {
        int[] cols = {TableModel.MEDICAMENTO,TableModel.PRESENTACION, TableModel.CANTIDAD,
                TableModel.INDICACIONES, TableModel.DURACION};
        nombrePaciente.setText("No seleccionado");
        PrescripcionTable.setModel(new TableModel(cols,new ArrayList<Prescripcion>()));
        fecha.setText("");
        fecha.getComponentDateTextField().setBackground(null);
        fecha.getComponentDateTextField().setToolTipText(null);
        nombrePaciente.setBackground(null);
        PrescripcionTable.setBackground(null);
        nombrePaciente.setToolTipText(null);
        PrescripcionTable.setToolTipText(null);
    }
}
