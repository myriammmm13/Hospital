package Hospital.presentation.despacho.detalleMed;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.despacho.Controller;
import Hospital.presentation.despacho.Model;
import Hospital.presentation.prescribir.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JDialog {
    private JPanel panel;
    private JTable prescripcionTable;
    private JButton okButton;
    Controller controllerDespacho;
    Hospital.presentation.historico.Controller controllerHistorico;
    Model modelDespacho;
    Hospital.presentation.historico.Model modelHistorico;
    Receta receta;

    public View(Receta receta) {
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
        setTitle("Detalles Receta");
        setSize(400, 250);
        this.receta = receta;

        int[] cols = {TableModel.MEDICAMENTO, TableModel.PRESENTACION, TableModel.CANTIDAD,
                TableModel.INDICACIONES, TableModel.DURACION};
        TableModel tableModel = new TableModel(cols, receta.getPrescripciones());
        prescripcionTable.setModel(tableModel);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.this.setVisible(false);
            }
        });
    }

    public void setControllerDespacho(Controller controller) {
        this.controllerDespacho = controller;
    }

    public void setControllerHistorico(Hospital.presentation.historico.Controller controller) { this.controllerHistorico = controller; }

    public void setModelDespacho(Model model) { this.modelDespacho = model; }

    public void setModelHistorico(Hospital.presentation.historico.Model model) { this.modelHistorico = model; }

}