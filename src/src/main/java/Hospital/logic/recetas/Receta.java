package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.Fecha;

import java.util.List;

public class Receta {
    private Doctor doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    private Fecha fechaConfeccion;
    private Fecha fechaRetiro;
    private String estado;

    public Receta(Doctor doctor, Paciente paciente, List<Prescripcion> prescripciones,
                  Fecha fechaConfeccion, Fecha fechaRetiro) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = prescripciones;
        this.fechaConfeccion = fechaConfeccion;
        this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }

    public Receta(){
        this.doctor = null;
        this.paciente = null;
        this.prescripciones = null;
        this.fechaConfeccion = null;
        this.fechaRetiro = null;
    }

    public Paciente getPaciente() { return paciente; }
    public Doctor getDoctor() { return doctor; }
    public Fecha getFechaConfeccion() { return fechaConfeccion; }
    public Fecha getFechaRetiro() { return fechaRetiro; }
    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public String getEstado() { return estado; }

    public void setPrescripciones(List<Prescripcion> prescripciones) {this.prescripciones = prescripciones;}
    public void setFechaRetiro(Fecha fechaRetiro) {this.fechaRetiro = fechaRetiro;}
    public void setEstado(String estado) {this.estado = estado;}
}
