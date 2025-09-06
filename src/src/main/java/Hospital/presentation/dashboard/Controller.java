package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.presentation.dashboard.Model;
import Hospital.presentation.dashboard.View;

import java.util.List;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }
}
