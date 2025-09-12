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
        model.setList(Service.instance().findAllPacientes());
    }

    public void create(Paciente p) throws Exception {
        Service.instance().agregarPaciente(p);
        model.setCurrent(p);
        model.setList(Service.instance().listarPacientes());
    }

    public void read(String id, String nom) throws Exception {
        model.setCurrent(Service.instance().obtenerPaciente(id, nom));
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