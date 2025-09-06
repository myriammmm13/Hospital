package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.presentation.medicamentos.Model;
import Hospital.presentation.medicamentos.View;

import java.util.List;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        //view.setController(this);
        //no se pq chorchas no sirve
        view.setModel(model);
    }
}
