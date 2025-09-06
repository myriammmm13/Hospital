package Hospital.presentation.AcercaDe;

import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Receta;
import Hospital.logic.Service;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }
};