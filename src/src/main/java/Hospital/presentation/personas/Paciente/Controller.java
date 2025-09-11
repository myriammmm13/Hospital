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

    public void create(Paciente r) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarPaciente(r);
        model.setCurrent(new Paciente());
        model.setList(Service.instance().findAllPacientes());
    }

    public void read(String id, String nom) throws Exception {
        try {
            model.setCurrent(Service.instance().obtenerPaciente(id, nom));
        } catch (Exception ex) {
            Paciente b = new Paciente();
            b.setId(id);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void update(Paciente r) throws Exception {
        model.setCurrent(r);
        Service.instance().actualizarPaciente(r);
    }

    public void delete(Paciente r) throws Exception {
        Service.instance().eliminarPaciente(r);
        model.setCurrent(new Paciente());
    }

    public void clear() {
        model.setCurrent(new Paciente());
    }
}
