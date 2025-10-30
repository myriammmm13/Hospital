package presentation.personas.Medico;

import logic.personas.trabajadores.Medico;
import logic.Service;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllMedico());
    }

    public void create(Medico m ) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarDoctor(m);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedico());
    }

    public void read(String id, String nom) throws Exception {
        try {
            model.setCurrent(Service.instance().obtenerDoctor(id, nom));
        }
        catch (Exception ex) {
            Medico b = new Medico();
            b.setId(id);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void update(Medico m) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarDoctor(m);
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