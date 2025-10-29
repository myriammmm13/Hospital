package presentation.personas.Medico;

import Hospital.Application;
import Hospital.backend.logic.personas.trabajadores.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JPanel MedicoPanel;
    private JPanel ListadoPanel;
    private JPanel BusquedaPanel;
    private JLabel IDLabel;
    private JTextField IDText;
    private JLabel NomLabel;
    private JTextField NomText;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JLabel EspLabel;
    private JTextField EspText;
    private JButton borrarButton;
    private JLabel NombreLabelBusq;
    private JTextField NomTextBusq;
    private JButton buscarButton;
    private JButton reporteButton;
    private JLabel idBusqLabel;
    private JTextField idBusqText;
    private JScrollPane scrollMedico;
    private JTable medicoTable;

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Medico n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(panel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateDelete()) {
                    Medico n = take();
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(panel, "ELIMINACIÓN REALIZADA", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateSearch()) {
                    try {
                        controller.read(idBusqText.getText(), NomTextBusq.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    fillFields(model.getCurrent());
                }
            }
        });

        medicoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && medicoTable.getSelectedRow() != -1) {
                    int row = medicoTable.getSelectedRow();
                    int column = medicoTable.getSelectedColumn();

                    Object value = medicoTable.getValueAt(row, column);
                    String nuevoValor = JOptionPane.showInputDialog(
                            null,
                            "Modificar valor:",
                            value
                    );
                    Medico cambiado = model.getList().get(row);
                    switch (column) {
                        case 0://id
                            cambiado.setId(nuevoValor);
                            break;
                        case 1://nombre
                            cambiado.setNombre(nuevoValor);
                            break;
                        case 2://esp
                            cambiado.setEspecialidad(nuevoValor);
                    }
                    try {
                        controller.update(cambiado);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
        int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.ESPECIALIDAD, TableModel.CLAVE};
        medicoTable.setModel(new TableModel(cols,model.getList()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.ESPECIALIDAD, TableModel.CLAVE};
                medicoTable.setModel(new TableModel(cols,model.getList()));
                break;
            case Model.CURRENT:
                IDText.setText(model.getCurrent().getId());
                NomText.setText(model.getCurrent().getNombre());
                EspText.setText(model.getCurrent().getEspecialidad());
                break;
        }
        this.panel.revalidate();
    }

    public Medico take() {
        Medico e = new Medico();
        e.setId(IDText.getText());
        e.setNombre(NomText.getText());
        e.setEspecialidad(EspText.getText());
        e.setClave(IDText.getText());
        return e;
    }
    public void clearFields() {
        IDText.setText("");
        NomText.setText("");
        EspText.setText("");
        EspText.setBackground(null);
        EspText.setToolTipText(null);
        IDText.setBackground(null);
        NomText.setBackground(null);
        IDText.setToolTipText(null);
        NomText.setToolTipText(null);
    }
    public void fillFields(Medico e) {
        IDText.setText(e.getId());
        NomText.setText(e.getNombre());
        EspText.setText(e.getEspecialidad());
    }

    private boolean validate() {
        boolean valid = true;
        if (IDText.getText().isEmpty()) {
            valid = false;
            IDText.setBackground(Application.BACKGROUND_ERROR);
            IDText.setToolTipText("ID requerido");
        } else {
            IDText.setBackground(null);
            IDText.setToolTipText(null);
        }

        if (NomText.getText().isEmpty()) {
            valid = false;
            NomText.setBackground(Application.BACKGROUND_ERROR);
            NomText.setToolTipText("Nombre requerido");
        } else {
            NomText.setBackground(null);
            NomText.setToolTipText(null);
        }
        if (EspText.getText().isEmpty()) {
            valid = false;
            EspText.setBackground(Application.BACKGROUND_ERROR);
            EspText.setToolTipText("Especialidad requerida");
        } else {
            EspText.setBackground(null);
            EspText.setToolTipText(null);
        }
        return valid;
    }

    private boolean validateSearch() {
        return !idBusqText.getText().isEmpty() || !NomTextBusq.getText().isEmpty();
    }
    private boolean validateDelete() {
        boolean valid = false;

        boolean idVacio = IDText.getText().isEmpty();
        boolean nombreVacio = NomText.getText().isEmpty();
        boolean especialidadVacio = EspText.getText().isEmpty();

        if (idVacio && nombreVacio) {
            IDText.setBackground(Application.BACKGROUND_ERROR);
            IDText.setToolTipText("ID requerido");

            NomText.setBackground(Application.BACKGROUND_ERROR);
            NomText.setToolTipText("Nombre requerido");

            EspText.setBackground(Application.BACKGROUND_ERROR);
            EspText.setToolTipText("Especialidad requerida");
        } else {
            valid = true;

            if (!idVacio) {
                IDText.setBackground(null);
                IDText.setToolTipText(null);
            }

            if (!nombreVacio) {
                NomText.setBackground(null);
                NomText.setToolTipText(null);
            }

            if (!especialidadVacio) {
                EspText.setBackground(null);
                EspText.setToolTipText(null);
            }
        }
        return valid;
    }
}