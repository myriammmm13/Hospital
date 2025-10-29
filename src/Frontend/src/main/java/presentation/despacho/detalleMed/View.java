package presentation.despacho.detalleMed;

import Hospital.backend.logic.recetas.Receta;
import presentation.despacho.Controller;
import presentation.despacho.Model;
import presentation.prescribir.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JDialog {
    private JPanel panel;
    private JTable prescripcionTable;
    private JButton okButton;
    Controller controllerDespacho;
    presentation.historico.Controller controllerHistorico;
    Model modelDespacho;
    presentation.historico.Model modelHistorico;
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

    public void setControllerHistorico(presentation.historico.Controller controller) { this.controllerHistorico = controller; }

    public void setModelDespacho(Model model) { this.modelDespacho = model; }

    public void setModelHistorico(presentation.historico.Model model) { this.modelHistorico = model; }

}