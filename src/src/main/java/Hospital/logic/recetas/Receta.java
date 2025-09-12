package Hospital.logic.recetas;

import Hospital.data.LocalDateAdapter;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Medico;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "receta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Receta {

    private Medico doctor;
    private Paciente paciente;

    @XmlElementWrapper(name = "prescripciones")
    @XmlElement(name = "prescripcion")
    private List<Prescripcion> prescripciones;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaConfeccion;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaRetiro;

    private String estado;

    public Receta() {
        this.prescripciones = new ArrayList<>();
        this.estado = "Confeccionada";
        this.fechaConfeccion = LocalDate.now();
    }

    // Otros constructores
    public Receta(Medico doctor, Paciente paciente, List<Prescripcion> prescripciones, LocalDate fechaRetiro) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = new ArrayList<>(prescripciones);
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }

    public Receta(Medico medico, Paciente paciente, List<Prescripcion> prescripciones) {
        this.doctor = medico;
        this.paciente = paciente;
        this.prescripciones = new ArrayList<>(prescripciones);
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = LocalDate.now().plusDays(3); // valor por defecto
        this.estado = "Confeccionada";
    }

    // Getters y setters
    public Medico getDoctor() { return doctor; }
    public void setDoctor(Medico doctor) { this.doctor = doctor; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = new ArrayList<>(prescripciones);
    }

    public LocalDate getFechaConfeccion() { return fechaConfeccion; }
    public void setFechaConfeccion(LocalDate fechaConfeccion) { this.fechaConfeccion = fechaConfeccion; }

    public LocalDate getFechaRetiro() { return fechaRetiro; }
    public void setFechaRetiro(LocalDate fechaRetiro) { this.fechaRetiro = fechaRetiro; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }



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