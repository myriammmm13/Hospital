package Hospital.presentation.medicamentos;

import Hospital.logic.Medicamento;
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

    public void create(Medicamento m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarMedicamento(m, userId);
    }

    public void read(String codigo) throws Exception {
        List<Medicamento> encontrados = Service.instance().obtenerMedicamento(codigo);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún medicamento");
        }
        model.setCurrent(encontrados.get(0));
    }

    public void update(Medicamento m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarMedicamento(m, userId);
    }

    public void delete(String codigo) throws Exception {
        Medicamento m = new Medicamento();
        m.setCodigo(codigo);
        Service.instance().eliminarMedicamento(codigo);
        model.setCurrent(new Medicamento()); // limpiar la vista
    }

}