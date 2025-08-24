package Hospital.presentation.personas.Doctor;

import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.Service;

import java.util.List;

public class DoctorController {
    DoctorModel model;
    DoctorView view;

    public DoctorController(DoctorModel model, DoctorView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }

    public void create(Doctor m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().agregarDoctor(m, userId);
    }

    public void read(String id) throws Exception {
        List<Doctor> encontrados = Service.instance().obtenerDoctor(id);
        if (encontrados.isEmpty()) {
            throw new Exception("No se encontró ningún Doctor");
        }
        model.setCurrent(encontrados.getFirst());
    }

    public void update(Doctor m, String userId) throws Exception {
        model.setCurrent(m);
        Service.instance().actualizarDoctor(m, userId);
    }

    public void delete(String id, String userId) throws Exception {
        Doctor m = new Doctor();
        m.setId(id);
        Service.instance().eliminarDoctor(id, userId);
        model.setCurrent(new Doctor());
    }

}