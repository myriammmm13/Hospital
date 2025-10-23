package Hospital.frontend.presentation.historico;

import Hospital.backend.logic.recetas.Receta;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void addRecetaReciente(Receta recetaSeleccionada){
        model.addReceta(recetaSeleccionada);
    }

}



