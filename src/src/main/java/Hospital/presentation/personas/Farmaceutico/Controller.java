package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.Service;
import Hospital.logic.personas.trabajadores.Medico;

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
        model.setCurrent(new Farmaceutico());
        model.setList(Service.instance().findAllFarmacuetico());
    }

    public void read(String id, String nom) throws Exception {
        List<Farmaceutico> encontrados = Service.instance().obtenerFarmaceuticos(id, nom);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Farmaceutico");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Farmaceutico m) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarFarmaceutico(m);
    }

    public void delete(Farmaceutico r) throws Exception {
        Farmaceutico m = new Farmaceutico();
        m.setId(r.getId());
        Service.instance().eliminarFarmaceutico(r.getId(), r.getNombre());
        model.setCurrent(new Farmaceutico());
    }
    public void clear() {
        view.clearFields();
    }
}