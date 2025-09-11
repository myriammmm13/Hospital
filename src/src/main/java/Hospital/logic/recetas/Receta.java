package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Medico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {
    private Medico doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    private LocalDate fechaConfeccion;
    private LocalDate fechaRetiro;
    private String estado;

    public Receta(Medico doctor, Paciente paciente, List<Prescripcion> prescripciones, LocalDate fechaRetiro) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = new ArrayList<>(prescripciones);
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }

    public Receta(Medico medico, Paciente paciente, List<Prescripcion> prescripciones) {
        this.doctor = new Medico();
        this.paciente = new Paciente();
        this.prescripciones = new ArrayList<>();
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = LocalDate.now().plusDays(3); // valor por defecto
        this.estado = "Confeccionada";
    }

    // Getters
    public Medico getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public LocalDate getFechaConfeccion() { return fechaConfeccion; }
    public LocalDate getFechaRetiro() { return fechaRetiro; }
    public String getEstado() { return estado; }

    // Setters
    public void setDoctor(Medico doctor) { this.doctor = doctor; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = new ArrayList<>(prescripciones);
    }
    public void setFechaRetiro(LocalDate fechaRetiro) { this.fechaRetiro = fechaRetiro; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos de utilidad
    public void agregarPrescripcion(Prescripcion prescripcion) {
        this.prescripciones.add(prescripcion);
    }

    public void borrarPrescripcion(Prescripcion prescripcion) {
        this.prescripciones.remove(prescripcion);
    }

    public void actualizarPrescripcion(Prescripcion prescripcion, int index) {
        if (index >= 0 && index < prescripciones.size()) {
            this.prescripciones.set(index, prescripcion);
        }
    }

    public boolean estaListaParaRetiro() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaRetiro.minusDays(3)) && !hoy.isAfter(fechaRetiro.plusDays(3));
    }
}