package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.Service;

import java.util.List;

public class FarmaController {
    FarmaModel model;
    FarmaView view;

    public FarmaController(FarmaModel model, FarmaView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Farmaceutico m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarFarmaceutico(m, userId);
    }

    public void read(String id) throws Exception {
        List<Farmaceutico> encontrados = Service.instance().obtenerFarmaceutico(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Farmaceutico");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Farmaceutico m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarFarmaceutico(m, userId);
    }

    public void delete(String id, String userId) throws Exception {
        Farmaceutico m = new Farmaceutico();
        m.setId(id);
        Service.instance().eliminarFarmaceutico(id, userId);
        model.setCurrent(new Farmaceutico());
    }
}