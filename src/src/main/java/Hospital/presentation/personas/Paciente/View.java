package Hospital.presentation.personas.Paciente;

import Hospital.Application;
import Hospital.logic.personas.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JLabel IDLabel;
    private JPanel PacientePanel;
    private JPanel busqPanel;
    private JPanel ListPanel;
    private JTextField IDText;
    private JTextField nomText;
    private JLabel NomLabel;
    private JButton GuardarButton;
    private JButton LimpiarButton;
    private JButton BorrarButton;
    private JLabel NombreLabel;
    private JTextField NombreText;
    private JButton BuscarButton;
    private JButton ReporteButton;
    private JLabel numLabel;
    private JTextField numTelText;
    private JLabel idBusqLabel;
    private JTextField idBusqText;
    private JTextField cambiarPorBiblio;
    private JLabel fechaLabel;
    private JScrollPane scrollTable;
    private JTable pacientesTable;

    Model model;
    Controller controller;

    public View() {
        GuardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Paciente n = take();
                    try {
                        controller.create(n);

                        JOptionPane.showMessageDialog(panel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        LimpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        BorrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateDelete()) {
                    Paciente n = take();
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(panel, "ELIMINACIÓN REALIZADA", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        BuscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateSearch()) {
                    try {
                        controller.read(idBusqText.getText(), NombreText.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    fillFields(model.getCurrent());
                }
            }
        });
    }

    public JPanel getPanel() { return panel; }

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID,TableModel.NOMBRE, TableModel.NUMERO, TableModel.FECHA};
                pacientesTable.setModel(new TableModel(cols,model.getList()));
                break;
            case Model.CURRENT:
                IDText.setText(model.getCurrent().getId());
                nomText.setText(model.getCurrent().getNombre());
                numTelText.setText(model.getCurrent().getTelNum());
                cambiarPorBiblio.setText(model.getCurrent().getNombre());//se debe hacer el cambio por la biblioteca de fecha
                break;
        }
        this.panel.revalidate();
    }

    public Paciente take() {
        Paciente e = new Paciente();
        e.setId(IDText.getText());
        e.setNombre(nomText.getText());
        e.setFechaNacimiento(cambiarPorBiblio.getText());//cambiar por la biblioteca
        e.setTelNum(numTelText.getText());
        return e;
    }
    public void clearFields() {
        IDText.setText("");
        nomText.setText("");
        numTelText.setText("");
        numTelText.setBackground(null);
        numTelText.setToolTipText(null);
        IDText.setBackground(null);
        nomText.setBackground(null);
        IDText.setToolTipText(null);
        nomText.setToolTipText(null);
    }
    public void fillFields(Paciente e) {
        IDText.setText(e.getId());
        nomText.setText(e.getNombre());
        numTelText.setText(e.getTelNum());
        //falta numero
    }

    private boolean validate() {
        boolean valid = true;
        if (IDText.getText().isEmpty()) {
            valid = false;
            IDText.setBackground(Application.BACKGROUND_ERROR);
            IDText.setToolTipText("id requerido");
        } else {
            IDText.setBackground(null);
            IDText.setToolTipText(null);
        }

        if (nomText.getText().isEmpty()) {
            valid = false;
            nomText.setBackground(Application.BACKGROUND_ERROR);
            nomText.setToolTipText("Nombre requerido");
        } else {
            nomText.setBackground(null);
            nomText.setToolTipText(null);
        }
        if (numTelText.getText().isEmpty()) {
            valid = false;
            numTelText.setBackground(Application.BACKGROUND_ERROR);
            numTelText.setToolTipText("Nombre requerido");
        } else {
            numTelText.setBackground(null);
            numTelText.setToolTipText(null);
        }
      //hacer el de fecha después :))))
        return valid;
    }

    private boolean validateSearch() {
        return !idBusqText.getText().isEmpty() || !NombreText.getText().isEmpty();
    }
    private boolean validateDelete() {
        boolean valid = false;

        boolean idVacio = IDText.getText().isEmpty();
        boolean nombreVacio = nomText.getText().isEmpty();

        // Si ambos están vacíos, no es válido
        if (idVacio && nombreVacio) {
            IDText.setBackground(Application.BACKGROUND_ERROR);
            IDText.setToolTipText("ID requerido o Nombre requerido");

            nomText.setBackground(Application.BACKGROUND_ERROR);
            nomText.setToolTipText("ID requerido o Nombre requerido");
        } else {
            valid = true;

            // Limpiar errores si tienen datos
            if (!idVacio) {
                IDText.setBackground(null);
                IDText.setToolTipText(null);
            }

            if (!nombreVacio) {
                nomText.setBackground(null);
                nomText.setToolTipText(null);
            }
        }

        return valid;
    }

}
