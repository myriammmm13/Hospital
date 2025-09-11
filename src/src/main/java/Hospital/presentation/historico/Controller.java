package Hospital.presentation.historico;

import Hospital.logic.Service;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.despacho.Model;
import Hospital.presentation.despacho.View;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void read(Receta r) throws Exception {
        Receta encontrado = Service.instance().obtenerReceta(r);
        model.setCurrent(encontrado);
    }

    public void update(Receta r) throws Exception {
        model.setCurrent(r);
        Service.instance().actualizarReceta(r);
    }
}
