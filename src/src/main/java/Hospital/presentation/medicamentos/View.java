package Hospital.presentation.medicamentos;

import Hospital.logic.Medicamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel medicamentosPanel;

    public JPanel getPanel() {
        return medicamentosPanel;
    }

    Controller controller;
    Model model;

    private JLabel codigo;
    private JTextField codigofield;
    private JLabel nombre;
    private JTextField nombreField;
    private JLabel descripcion;
    private JTextField descripcionField;
    private JTextField buscarField;
    private JButton guardarButton;
    private JButton modificarButton;
    private JButton buttonEliminar;
    private JButton button1;
    private JButton buttonBuscar;

    public View() {

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicamento m = new Medicamento(
                        codigofield.getText(),
                        nombreField.getText(),
                        descripcionField.getText()
                );
                try {
                    controller.create(m, "");
                    JOptionPane.showMessageDialog(medicamentosPanel, "Medicamento guardado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "Error al guardar: " + ex.getMessage());
                }

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicamento m = new Medicamento(
                        codigofield.getText(),
                        nombreField.getText(),
                        descripcionField.getText()
                );
                try {
                    controller.update(m, "");
                    JOptionPane.showMessageDialog(medicamentosPanel, "Medicamento modificado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "Error al modificar: " + ex.getMessage());
                }

            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.delete(codigofield.getText());
                    JOptionPane.showMessageDialog(medicamentosPanel, "Medicamento eliminado.");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "Error al eliminar: " + ex.getMessage());
                }

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.create(new Medicamento("", "", ""), "ADM-111");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "Error al limpiar: " + ex.getMessage());
                }

            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoBuscado = buscarField.getText().trim();
                if (codigoBuscado.isEmpty()) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "Por favor ingrese un código para buscar.");
                    return;
                }

                try {
                    controller.read(codigoBuscado); // actualiza el modelo con el medicamento encontrado
                    Medicamento m = model.getCurrent();

                    // Actualiza los campos de texto con los datos del medicamento
                    codigofield.setText(m.getCodigo());
                    nombreField.setText(m.getNombre());
                    descripcionField.setText(m.getPresentacion());

                    JOptionPane.showMessageDialog(medicamentosPanel, "Medicamento encontrado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(medicamentosPanel, "No se encontró el medicamento: " + ex.getMessage());
                }
            }
        });
    }
    private void limpiarCampos() {
        codigofield.setText("");
        nombreField.setText("");
        descripcionField.setText("");
        buscarField.setText("");
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
        if (Model.CURRENT.equals(evt.getPropertyName())) {
            Medicamento m = model.getCurrent();
            codigofield.setText(m.getCodigo());
            nombreField.setText(m.getNombre());
            descripcionField.setText(m.getPresentacion());
        }
    }
}