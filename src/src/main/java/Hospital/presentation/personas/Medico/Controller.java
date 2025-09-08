package Hospital.presentation.personas.Medico;

import Hospital.logic.personas.Paciente;
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

    public void create(Medico m ) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarDoctor(m);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedico());
    }

    public void read(String id, String nom) throws Exception {
        List<Medico> encontrados = Service.instance().obtenerDoctor(id, nom);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Doctor");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Medico m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarDoctor(m, userId);
    }

    public void delete(Medico r ) throws Exception {
        Medico m = new Medico();
        m.setId(r.getId());
        Service.instance().eliminarDoctor(r.getId(), r.getNombre());
        model.setCurrent(new Medico());
    }
    public void clear() {
        view.clearFields();
    }
}