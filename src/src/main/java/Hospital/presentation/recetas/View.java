package Hospital.presentation.recetas.View;

import Hospital.presentation.recetas.RecetaController;
import Hospital.presentation.recetas.RecetaModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {{
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JTextField textField1;
    private JTable prescripcionTable;
    private JButton guardarRecetaButton;
    private JButton limpiarRecetaButton;
    private JButton descartarMedicamentoRecetaButton;
    private JButton detallesRecetaButton;

    private JPanel panel;

    RecetaController controller;
    RecetaModel model;

    public RecetaView() {
        panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(RecetaController controller) {
        this.controller = controller;
    }

    public void setModel(RecetaModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Lo vas a completar cuando el modelo tenga datos que mostrar
    }
}
}
