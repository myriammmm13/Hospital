package Hospital.presentation.prescribir;

import Hospital.Application;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;
import Hospital.logic.personas.trabajadores.Medico;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private JPanel fechaPanel;
    private JPanel controlPanel;
    private JPanel recetaPanel;
    private JPanel medioPanel;
    private JPanel ajustarPanel;
    private JLabel nombrePaciente;
    private JLabel nombreLabel;
    private DatePicker fecha;
    private Hospital.presentation.prescribir.buscarPaciente.View buscarPacienteView;
    private Hospital.presentation.prescribir.buscarMedicamento.View buscarMedicamentoView;
    private Hospital.presentation.prescribir.buscarMedicamento.crearPrescripcion.View CrearPrescripcionView;

    Model model;
    Controller controller;
    Medico doctorAuxiliar; //solo para que no se pegue

    public View() {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("yyyy-MM-dd");
        fecha = new DatePicker(settings);
        fechaPanel.setLayout(new BorderLayout());
        fechaPanel.add(fecha, BorderLayout.CENTER);

        nombrePaciente = new JLabel();
        nombrePaciente.setText("Nombre");
        buscarPacienteView =  new Hospital.presentation.prescribir.buscarPaciente.View();
        buscarMedicamentoView = new  Hospital.presentation.prescribir.buscarMedicamento.View();
        buscarPacienteView.setController((controller));
        buscarMedicamentoView.setController((controller));

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Receta r = take();
                    try {
                        controller.create(r);
                        JOptionPane.showMessageDialog(panelPrincipal, "Receta guardada correctamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelPrincipal, "Error al guardar: " + ex.getMessage());
                    }
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.setCurrent(new Receta(doctorAuxiliar = new Medico(), model.getCurrent().getPaciente(), model.getCurrent().getPrescripciones()) /*id del doctor*/);
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Error al limpiar: " + ex.getMessage());
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

        detallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PrescripcionTable.getSelectedRow()>=0){
                    Prescripcion pres = model.getPrescripcionesList().get(PrescripcionTable.getSelectedRow());
                    CrearPrescripcionView = new Hospital.presentation.
                            prescribir.buscarMedicamento.crearPrescripcion.View(pres.getMedicamento());
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

    private boolean validate() {
        boolean valid = true;
        System.out.println(nombrePaciente.getText());
        if ("No seleccionado".equals(nombrePaciente.getText())) {
            valid = false;
            nombrePaciente.setBackground(Application.BACKGROUND_ERROR);
            nombrePaciente.setToolTipText("ID requerido");
        } else {
            nombrePaciente.setBackground(null);
            nombrePaciente.setToolTipText(null);
        }

        if (PrescripcionTable.getRowCount() == 0) {
            valid = false;
            JOptionPane.showMessageDialog(panelPrincipal, "Debe agregar al menos una prescripción.");
        }
        return valid;
    }

    private void limpiarCampos() {
        nombrePaciente.setText("");
        PrescripcionTable.repaint();
    }

    public Receta take() {
        Receta e = new Receta();
        e.setPaciente(model.getCurrent().getPaciente());
        e.setPrescripciones(model.getCurrent().getPrescripciones());
        return e;
    }

}
