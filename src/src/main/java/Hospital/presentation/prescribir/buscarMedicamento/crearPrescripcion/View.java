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
    private JButton guardarButton;
    private JButton cancelarButton;

    Controller controller;
    Model model;

    public View(Medicamento medicamento){
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setLocationRelativeTo(null);
        setTitle(medicamento.getNombre());
        setSize(400, 250);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prescripcion p = new Prescripcion(medicamento, indicacionesField.getText(),
                        (int)duracionSpinner.getValue(), (int)cantidadSpinner.getValue());
                controller.agregarPrescripcion(p);
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }

}
