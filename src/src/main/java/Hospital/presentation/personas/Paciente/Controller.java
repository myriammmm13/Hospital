package Hospital.presentation.personas.Paciente;

import Hospital.logic.Service;
import Hospital.logic.personas.Paciente;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Paciente r, String userId) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarPaciente(r, userId);
    }

    public void read(Paciente r) throws Exception {
        Paciente encontrado = Service.instance().obtenerPaciente(r.getId());
        model.setCurrent(encontrado);
    }

    public void update(Paciente r, String id) throws Exception {
        model.setCurrent(r);
        Service.instance().actualizarPaciente(r,id);//el ID es el del que hizo login
    }

    public void delete(Paciente r, String id) throws Exception {
        Service.instance().eliminarPaciente(r.getId(), id);//segundo id de login
        model.setCurrent(new Paciente()); // limpiar la vista
    }
}
