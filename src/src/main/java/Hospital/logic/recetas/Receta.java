package Hospital.logic.recetas;

import Hospital.data.LocalDateAdapter;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.trabajadores.Medico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Receta {

    @XmlElement
    private Trabajador doctor;

    @XmlElement
    private Paciente paciente;

    @XmlElementWrapper(name = "prescripciones")
    @XmlElement(name = "prescripcion")
    private List<Prescripcion> prescripciones;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaConfeccion;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaRetiro;

    @XmlElement
    private String estado;


    public Receta() {
        prescripciones = new ArrayList<>();
        estado = "Confeccionada";
        fechaConfeccion = LocalDate.now();
        //IDdoctor="";
    }

    public Receta(Trabajador doctor, Paciente paciente, List<Prescripcion> prescripciones) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = new ArrayList<>(prescripciones);
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = LocalDate.now().plusDays(3);
        this.estado = "Confeccionada";
    }

    // Getters
    public Trabajador getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public LocalDate getFechaConfeccion() { return fechaConfeccion; }
    public LocalDate getFechaRetiro() { return fechaRetiro; }
    public String getEstado() { return estado; }

    // Setters
    public void setDoctor(Trabajador doctor) { this.doctor = doctor; }
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