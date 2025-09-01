package Hospital.presentation.recetas;

import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Receta;
import Hospital.logic.Service;

import java.util.List;

public class RecetaController {
    RecetaModel model;
    RecetaView view;

    public RecetaController(RecetaModel model, RecetaView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Receta r, String userId) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarReceta(r, userId);
    }

    public void read(Receta r) throws Exception {
        Receta encontrado = Service.instance().obtenerReceta(r);
        model.setCurrent(encontrado);
    }

    public void uodate(Receta r) throws Exception {
        model.setCurrent(r);
        Service.instance().actualizarReceta(r);
    }

    public void delete(Receta r) throws Exception {
        Service.instance().eliminarReceta(r);
        model.setCurrent(new Receta()); // limpiar la vista
    }
}
