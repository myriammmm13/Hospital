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
    }

    public void read(String id, String nom) throws Exception {
        Paciente e = new Paciente();
        e.setId(id);
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
        Service.instance().actualizarPaciente(r);//el ID es el del que hizo login
    }

    public void delete(Paciente r) throws Exception {
        Service.instance().eliminarPaciente(r);//segundo id de login
        model.setCurrent(new Paciente()); // limpiar la vista
    }
    public void clear() {
        view.clearFields();
    }
}
