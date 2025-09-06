package Hospital.presentation.personas.Medico;

import Hospital.logic.personas.trabajadores.Medico;
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

    public void create(Medico m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarDoctor(m, userId);
    }

    public void read(String id) throws Exception {
        List<Medico> encontrados = Service.instance().obtenerDoctor(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Doctor");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Medico m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarDoctor(m, userId);
    }

    public void delete(String id, String userId) throws Exception {
        Medico m = new Medico();
        m.setId(id);
        Service.instance().eliminarDoctor(id, userId);
        model.setCurrent(new Medico());
    }

}