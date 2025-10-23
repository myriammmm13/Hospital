package Hospital.frontend.presentation.prescribir;

import Hospital.backend.data.Data;
import Hospital.backend.logic.Medicamento;
import Hospital.backend.logic.recetas.Prescripcion;
import Hospital.backend.logic.recetas.Receta;
import Hospital.backend.logic.personas.Paciente;
import Hospital.frontend.presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;

import static Hospital.Application.data;

public class Model extends AbstractModel {
    Receta current;
    //List<Prescripcion> prescripciones;
    List<Medicamento> medicamentos;
    List<Paciente> pacientes;

    public static final String CURRENT = "current";
    public static final String PRESCRIPCIONES = "prescripciones";
    public static final String MEDICAMENTOS = "medicamentos";
    public static final String PACIENTES = "pacientes";
    public static final String PACIENTE = "paciente";
    public static final String PRESCRIPCION = "prescripcion";

    public Model() {
        pacientes = new ArrayList<>();
        medicamentos = new ArrayList<>();
        //prescripciones = new ArrayList<>();
        current = new Receta();
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
        //return prescripciones;
        return current.getPrescripciones();
    }


    public List<Medicamento> getMedicamentosList() {
        return medicamentos;
    }

    public List<Paciente> getPacientes(){return pacientes;}

    /*public void setPrescripciones(List<Prescripcion> list) {
        this.prescripciones = list != null ? list : new ArrayList<>();
        firePropertyChange(PRESCRIPCIONES, null, this.prescripciones);
    }*/

    public void setPacientes(List<Paciente> list) {
        this.pacientes = list != null ? list : new ArrayList<>();
        firePropertyChange(PACIENTES, null, this.pacientes);
    }

    public void setPaciente(Paciente paciente) {
        this.current.setPaciente(paciente);
        firePropertyChange(PACIENTE, null, paciente);
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.current.agregarPrescripcion(prescripcion);
        firePropertyChange(PRESCRIPCION, null, prescripcion);
    }

    public Paciente getPaciente() { return current.getPaciente(); }

    public void agregarPrescripcion(Prescripcion prescripcion){
        this.current.agregarPrescripcion(prescripcion);
        //setPrescripciones(current.getPrescripciones());
        firePropertyChange(PRESCRIPCIONES, null, current.getPrescripciones());
        firePropertyChange(PRESCRIPCION, null, prescripcion);
    }

    public void borrarPrescripcion(Prescripcion prescripcion){
        this.current.borrarPrescripcion(prescripcion);
        //setPrescripciones(current.getPrescripciones());
        firePropertyChange(PRESCRIPCIONES, null, current.getPrescripciones());
        firePropertyChange(PRESCRIPCION, null, prescripcion);
    }

    public void actualizarPrescripcion(Prescripcion prescripcion, int row) {
        this.current.actualizarPrescripcion(prescripcion, row);
        //setPrescripciones(current.getPrescripciones());
        firePropertyChange(PRESCRIPCIONES, null, current.getPrescripciones());
        firePropertyChange(PRESCRIPCION, null, prescripcion);
    }

    public List<Paciente> getPacientesList() {
        return pacientes;
    }

    public void setMedicamentos(List<Medicamento> list) {
        this.medicamentos = list != null ? list : new ArrayList<>();
        firePropertyChange(MEDICAMENTOS, null, this.medicamentos);
    }

    public Data getData() {
        return data;
    }
}