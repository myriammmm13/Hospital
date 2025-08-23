package Hospital.Logic.recetas;

import Hospital.Logic.personas.Paciente;
import Hospital.Logic.personas.trabajadores.Doctor;
import Hospital.Fecha;

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
}
