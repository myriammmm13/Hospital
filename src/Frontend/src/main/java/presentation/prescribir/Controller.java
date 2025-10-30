package presentation.prescribir;

import logic.Medicamento;
import logic.personas.Paciente;
import logic.recetas.Prescripcion;
import logic.recetas.Receta;
import logic.Service;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setPacientes(Service.instance().search(new Paciente()));
        model.setMedicamentos(Service.instance().findAllMedicamentos());
    }

    public void create(Receta r ) throws Exception {
        model.setCurrent(r);
        Service.instance().agregarReceta(r);
    }


    public void clear() {
        model.setCurrent(new Receta());
        view.clearFields();
    }

    public void setPaciente(int row) {
        model.setPaciente(model.getPacientesList().get(row));
    }

    public Medicamento getMedicamento(int row) {
        return model.getMedicamentosList().get(row);
    }

    public void agregarPrescripcion(Prescripcion p) {
        model.agregarPrescripcion(p);
    }

    public void borrarPrescripcion(int row){
        model.borrarPrescripcion(model.getPrescripcionesList().get(row));
    }

    public void actualizarPrescripcion(Prescripcion prescripcion, int row) {
        model.actualizarPrescripcion(prescripcion, row);
    }
}