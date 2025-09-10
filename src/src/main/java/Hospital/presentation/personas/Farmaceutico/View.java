package Hospital.presentation.personas.Farmaceutico;

import Hospital.Application;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.presentation.personas.Farmaceutico.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel FarmaceuticoPanel;
    private JPanel panel;
    private JPanel listadoPanel;
    private JPanel busquedaPanel;
    private JPanel farmaceuticoPanel;
    private JLabel idLabel;
    private JTextField idText;
    private JLabel nombreLabel;
    private JTextField nombreText;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JLabel nomLabel;
    private JTextField nomText;
    private JButton buscarButton;
    private JButton reporteButton;
    private JLabel idBusqLabel;
    private JTextField idBusqText;
    private JScrollPane farmaScroll;
    private JTable farmaceuticoTable;

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
                    Farmaceutico n = take();
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
                    Farmaceutico n = take();
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
                        controller.read(idBusqText.getText(), nombreText.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    fillFields(model.getCurrent());
                }
            }
        });

        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //hay que hacerle la acción xd
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
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE};
                farmaceuticoTable.setModel(new TableModel(cols,model.getList()));
                break;
            case Model.CURRENT:
                idText.setText(model.getCurrent().getId());
                nombreText.setText(model.getCurrent().getNombre());
                break;
        }
        this.panel.revalidate();
    }

    public Farmaceutico take() {
        Farmaceutico e = new Farmaceutico();
        e.setId(idText.getText());
        e.setNombre(nombreText.getText());
        return e;
    }
    public void clearFields() {
        idText.setText("");
        nombreText.setText("");
        idText.setBackground(null);
        nombreText.setBackground(null);
        idText.setToolTipText(null);
        nombreText.setToolTipText(null);
    }
    public void fillFields(Farmaceutico e) {
        idText.setText(e.getId());
        nombreText.setText(e.getNombre());
    }

    private boolean validate() {
        boolean valid = true;
        if (idText.getText().isEmpty()) {
            valid = false;
            idText.setBackground(Application.BACKGROUND_ERROR);
            idText.setToolTipText("id requerido");
        } else {
            idText.setBackground(null);
            idText.setToolTipText(null);
        }

        if (nombreText.getText().isEmpty()) {
            valid = false;
            nombreText.setBackground(Application.BACKGROUND_ERROR);
            nombreText.setToolTipText("Nombre requerido");
        } else {
            nombreText.setBackground(null);
            nombreText.setToolTipText(null);
        }
        return valid;
    }

    private boolean validateSearch() {
        return !idBusqText.getText().isEmpty() || !nomText.getText().isEmpty();
    }
    private boolean validateDelete() {
        boolean valid = false;

        boolean idVacio = idText.getText().isEmpty();
        boolean nombreVacio = nombreText.getText().isEmpty();

        // Si ambos están vacíos, no es válido
        if (idVacio && nombreVacio) {
            idBusqText.setBackground(Application.BACKGROUND_ERROR);
            idBusqText.setToolTipText("ID requerido o Nombre requerido");

            nomText.setToolTipText("ID requerido o Nombre requerido");
        } else {
            valid = true;

            // Limpiar errores si tienen datos
            if (!idVacio) {
                idBusqText.setBackground(null);
                idBusqText.setToolTipText(null);
            }

            if (!nombreVacio) {
                nomText.setBackground(null);
                nomText.setToolTipText(null);
            }
        }
        return valid;
    }
}