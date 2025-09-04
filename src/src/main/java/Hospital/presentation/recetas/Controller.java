package Hospital.presentation.recetas;

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

    public void create(Receta r, String userId) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarReceta(r, userId);
    }


}
