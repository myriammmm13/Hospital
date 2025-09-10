package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.Service;

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

    public void create(Farmaceutico m) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarFarmaceutico(m);
    }

    public void read(String id) throws Exception {
        List<Farmaceutico> encontrados = Service.instance().obtenerFarmaceutico(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Farmaceutico");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Farmaceutico m) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarFarmaceutico(m);
    }

    public void delete(String id) throws Exception {
        Farmaceutico m = new Farmaceutico();
        m.setId(id);
        Service.instance().eliminarFarmaceutico(id);
        model.setCurrent(new Farmaceutico());
    }

    public void clear() {
    }
}