package Hospital.presentation.prescribir;

import Hospital.logic.Medicamento;
import Hospital.logic.Service;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;
import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractModel;

import java.util.List;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Receta current = new  Receta();
    List<Prescripcion> prescripciones;
    List<Medicamento> medicamentos;
    List<Paciente> pacientes;

    public static final String CURRENT = "current";
    public static final String PRESCRIPCIONES = "prescripciones";
    public static final String MEDICAMENTOS = "medicamentos";
    public static final String PACIENTES = "pacientes";
    public static final String PACIENTE = "paciente";

    public Model() {
        Service service = Service.instance();
        pacientes = service.listarPacientes();
        medicamentos = service.listarMedicamentos();
        current = new Receta();
        firePropertyChange(PACIENTES, null, pacientes);
        firePropertyChange(MEDICAMENTOS, null, medicamentos);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Receta getCurrent() {
        return current;
    }

    public void setCurrent(Receta r) {
        this.current = r;
        firePropertyChange(CURRENT, null, current);
        firePropertyChange(PRESCRIPCIONES, null, current.getPrescripciones());
    }

    public List<Prescripcion> getPrescripcionesList() {
        return prescripciones;
    }

    public List<Paciente> getPacientesList() {
        return pacientes;
    }

    public List<Medicamento> getMedicamentosList() {
        return medicamentos;
    }

    public void setPrescripciones(List<Prescripcion> list) {
        this.prescripciones = list;
        firePropertyChange(PRESCRIPCIONES, null, list);
    }

    public void setPaciente(Paciente paciente) {
        this.current.setPaciente(paciente);
        firePropertyChange(PACIENTE, null, paciente);
    }

    public Paciente getPaciente() { return  current.getPaciente(); }

    public void agregarPrescripcion(Prescripcion prescripcion){
        this.current.agregarPrescripcion(prescripcion);
        setPrescripciones(current.getPrescripciones());
    }

    public void borrarPrescripcion(Prescripcion prescripcion){
        this.current.borrarPrescripcion(prescripcion);
        setPrescripciones(current.getPrescripciones());
    }

    public void actualizarPrescripcion(Prescripcion prescripcion, int row) {
        this.current.actualizarPrescripcion(prescripcion, row);
        setPrescripciones(current.getPrescripciones());
    }
}