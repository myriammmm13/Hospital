package presentation.medicamentos;

import logic.Medicamento;
import logic.Service;

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

    public void create(Medicamento m, String userId) throws Exception {
        Service.instance().agregarMedicamento(m, userId);
        List<Medicamento> todos = Service.instance().listarMedicamentos();
        model.setList(todos);
        model.setCurrent(m);
    }


    public void read(String codigo) throws Exception {
        List<Medicamento> encontrados = Service.instance().obtenerMedicamento(codigo);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún medicamento");
        }
        model.setCurrent(encontrados.get(0));
        model.setList(encontrados);
    }

    public void update(Medicamento m) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarMedicamento(m);
    }

    public void delete(String codigo) throws Exception {
        Service.instance().eliminarMedicamento(codigo);
        model.setCurrent(new Medicamento());
    }

}