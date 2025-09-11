package Hospital.presentation.despacho.detalleMed;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.despacho.Controller;
import Hospital.presentation.despacho.Model;
import Hospital.presentation.prescribir.TableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JDialog implements PropertyChangeListener {
    private JPanel panel;
    private JTable prescripcionTable;
    private JButton okButton;
    Controller controller;
    Model model;
    Receta receta;

    public View(Receta receta) {
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
        setTitle("Detalles Receta");
        setSize(400, 250);
        this.receta = receta;

        okButton.addActionListener(new ActionListener() {
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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PRESCRIPCIONES :
                int[] cols = {TableModel.MEDICAMENTO,TableModel.PRESENTACION, TableModel.CANTIDAD,
                        TableModel.INDICACIONES, TableModel.DURACION};
                TableModel tableModel = new TableModel(cols, receta.getPrescripciones());
                prescripcionTable.setModel(tableModel);
                break;
        }
        this.panel.revalidate();
    }
}