package Hospital.presentation.historico;

import Hospital.logic.Service;
import Hospital.logic.recetas.Receta;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Receta r) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarReceta(r);
    }

    public void read(Receta r) throws Exception {
        Receta encontrado = Service.instance().obtenerReceta(r);
        model.setCurrent(encontrado);
    }

    public void update(Receta r) throws Exception {
        model.setCurrent(r);
        Service.instance().actualizarReceta(r);
    }

    public void delete(Receta r) throws Exception {
        Service.instance().eliminarReceta(r);
        model.setCurrent(new Receta()); // limpiar la vista
    }
}
