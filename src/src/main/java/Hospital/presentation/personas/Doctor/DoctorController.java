package Hospital.presentation.personas.Doctor;

import Hospital.logic.Service;
import Hospital.logic.personas.trabajadores.Doctor;
public class DoctorController {
    DoctorView view;
    DoctorModel model;

    public DoctorController(DoctorView view, DoctorModel model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    public void read (String id) throws Exception{
        Doctor e=new Doctor();
        e.setId(id);
        model.setCurrent(Service.instance().obtenerDoctorExp(id));
    }

    public void create(Doctor n) throws Exception{
        model.Service.instance();
        model.setCurrent(new Doctor());
    }
}