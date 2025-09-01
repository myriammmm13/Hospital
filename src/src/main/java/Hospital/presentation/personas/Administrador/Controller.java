package Hospital.presentation.personas.Administrador;

import Hospital.logic.Service;
import Hospital.logic.personas.Trabajador;

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

    public void create(Trabajador m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarTrabajador(m, userId);
    }

    public void read(String id) throws Exception {
        List<Trabajador> encontrados = Service.instance().obtenerTrabajador(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Trabajador");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Trabajador m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarTrabajador(m, userId);
    }

    public void delete(String id, String userId) throws Exception {
        Trabajador m = new Trabajador();
        m.setId(id);
        Service.instance().eliminarTrabajador(id, userId);
        model.setCurrent(new Trabajador());
    }

}