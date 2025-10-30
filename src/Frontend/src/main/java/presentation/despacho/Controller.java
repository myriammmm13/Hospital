package presentation.despacho;

import logic.recetas.Receta;
import logic.Service;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.setList(Service.instance().listarRecetas()); // o como sea que se obtienen

        view.setController(this);
        view.setModel(model);
    }

    public void procesarReceta(int row) throws Exception {
        model.procesarReceta(row);
        update(model.getRecetasList().get(row));
    }

    public void prepararReceta(int row) throws Exception {
        model.prepararReceta(row);
        update(model.getRecetasList().get(row));
    }

    public void despacharReceta(int row) throws Exception {
        model.despacharReceta(row);
        update(model.getRecetasList().get(row));
    }

    public void descartarReceta(int row) throws Exception {
        delete(model.getRecetasList().get(row));
        model.descartarReceta(row);
    }

    public void read(Receta r) throws Exception {
        Receta encontrado = Service.instance().obtenerReceta(r);
    }

    public void delete(Receta r) throws Exception {
        Service.instance().eliminarReceta(r);
    }

    public void update(Receta r) throws Exception {
       Service.instance().actualizarReceta(r);
    }

}
