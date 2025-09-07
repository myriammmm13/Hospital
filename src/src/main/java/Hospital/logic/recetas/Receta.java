package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Receta {
    private Medico doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    //private Fecha fechaConfeccion;
    //private Fecha fechaRetiro;
    private String estado;

    public Receta(Medico doctor, Paciente paciente, List<Prescripcion> prescripciones) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = prescripciones;
      //  this.fechaConfeccion = fechaConfeccion;
        //this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }

    public Receta(){
        this.doctor = new Medico();
        this.paciente=new Paciente();
        this.prescripciones = new ArrayList<>();
        //this.fechaConfeccion=new Fecha(); cambio esto por biblioteca
        //fechaRetiro=new fecha();
        estado = "";
    }

    public Paciente getPaciente() { return paciente; }
    public Medico getDoctor() { return doctor; }
    //public Fecha getFechaConfeccion() { return fechaConfeccion; }
    //public Fecha getFechaRetiro() { return fechaRetiro; }
    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public String getEstado() { return estado; }

    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public void setDoctor(Medico doctor){this.doctor = doctor;}
    public void setPrescripciones(List<Prescripcion> prescripciones) {this.prescripciones = prescripciones;}
    //public void setFechaRetiro(Fecha fechaRetiro) {this.fechaRetiro = fechaRetiro;}
    public void setEstado(String estado) {this.estado = estado;}
    public void agregarPrescripcion(Prescripcion prescripcion){
        this.prescripciones.add(prescripcion);
    }
}
