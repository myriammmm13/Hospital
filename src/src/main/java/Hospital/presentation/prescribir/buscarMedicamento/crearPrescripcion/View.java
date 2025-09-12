package Hospital.presentation.prescribir.buscarMedicamento.crearPrescripcion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Prescripcion;
import Hospital.presentation.prescribir.Controller;
import Hospital.presentation.prescribir.Model;

public class View extends JDialog implements PropertyChangeListener {

    private JPanel panel;
    private JSpinner cantidadSpinner;
    private JSpinner duracionSpinner;
    private JTextField indicacionesField;
    private JButton BotonGuardarCrearPrescripcion;
    private JButton BotonCancelarDeCrearPrescripcion;

    private Prescripcion prescripcion = null;
    private int row;

    Controller controller;
    Model model;

    public View(Medicamento medicamento){
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(BotonGuardarCrearPrescripcion);
        setLocationRelativeTo(null);
        setTitle(medicamento.getNombre());
        setSize(400, 250);

        BotonGuardarCrearPrescripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(prescripcion != null){
                    prescripcion.setCantidad((int) cantidadSpinner.getValue());
                    prescripcion.setDuracion((int) duracionSpinner.getValue());
                    prescripcion.setIndicaciones(indicacionesField.getText());
                    controller.actualizarPrescripcion(prescripcion, row);
                }
                else {
                    Prescripcion p = new Prescripcion(medicamento, indicacionesField.getText(),
                            (int) duracionSpinner.getValue(), (int) cantidadSpinner.getValue());
                    controller.agregarPrescripcion(p);
                }
                View.this.setVisible(false);
            }
        });

        BotonCancelarDeCrearPrescripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.this.setVisible(false);
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setPrescripcion(Prescripcion p) {
        this.prescripcion = p;
        cantidadSpinner.setValue(p.getCantidad());
        duracionSpinner.setValue(p.getDuracion());
        indicacionesField.setText(p.getIndicaciones());
        setTitle("Editar línea - " + p.getNombre());
    }

    public void setRow(int row) { this.row = row; }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }

}
