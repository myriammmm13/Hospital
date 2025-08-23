package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.Fecha;

import java.util.List;

public class RecetaBuilder {
    private Doctor doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    private Fecha fechaConfeccion;
    private Fecha fechaRetiro;
    private String estado;

    public RecetaBuilder doctor(Doctor doctor) {
        this.doctor = doctor;;
        return this;
    }

    public RecetaBuilder paciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public RecetaBuilder agregarPrescripcion(Prescripcion prescripcion) {
        this.prescripciones.add(prescripcion);
        return this;
    }

    public RecetaBuilder eliminarPrescripcion(int posicion) {
        if (posicion >= 0 && posicion < prescripciones.size()) {
            this.prescripciones.remove(posicion);
        }
        return this;
    }

    public RecetaBuilder modificarPrescripcion(int posicion, Prescripcion nueva) {
        if (posicion >= 0 && posicion < prescripciones.size()) {
            this.prescripciones.set(posicion, nueva);
        }
        return this;
    }

    public RecetaBuilder fechaConfeccion(Fecha fechaConfeccion) {
        this.fechaConfeccion =  fechaConfeccion;
        return this;
    }

    public RecetaBuilder fechaRetiro(Fecha fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
        return this;
    }

    public Receta build() {
        //faltan validaciones y excepciones :(
        return new Receta(doctor, paciente, prescripciones, fechaConfeccion, fechaRetiro);
    }
}

