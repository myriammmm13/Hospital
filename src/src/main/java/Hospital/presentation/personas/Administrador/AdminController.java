package Hospital.presentation.personas.Administrador;

import Hospital.logic.personas.trabajadores.Administrador;
import Hospital.logic.Service;

import java.util.List;

public class AdminController {
    AdminModel model;
    AdminView view;

    public AdminController(AdminModel model, AdminView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Administrador m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarAdmin(m, userId);
    }

    public void read(String id) throws Exception {
        List<Administrador> encontrados = Service.instance().obtenerAdmin(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Doctor");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Administrador m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarAdmin(m, userId);
    }

    public void delete(String id, String userId) throws Exception {
        Administrador m = new Administrador();
        m.setId(id);
        Service.instance().eliminarDoctor(id, userId);
        model.setCurrent(new Administrador());
    }

}