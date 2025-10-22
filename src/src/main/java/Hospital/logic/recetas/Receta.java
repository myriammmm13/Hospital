package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//sin persistencia 
public class Receta {
    // El ID se maneja como String para compatibilidad con la base de datos
    // aunque internamente sea un número entero
    // Identificador único en la base de datos
    private String id;
    
    // Cantidad de prescripciones
    private int cant;
    
    // Referencias a otras entidades
    private Trabajador doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    
    // Fechas y estado
    private LocalDate fechaConfeccion;
    private LocalDate fechaRetiro;
    private String estado;

    public Receta() {
        prescripciones = new ArrayList<>();
        estado = "Confeccionada";
        fechaConfeccion = LocalDate.now();
        cant=0;
        doctor=new Trabajador();
    }

    public Receta(Paciente paciente, List<Prescripcion> prescripciones, Trabajador  doctor) {
        this.paciente = paciente;
        this.prescripciones = new ArrayList<>(prescripciones);
        this.fechaConfeccion = LocalDate.now();
        this.fechaRetiro = LocalDate.now().plusDays(3);
        this.estado = "Confeccionada";
        cant=prescripciones.size();
        this.doctor=doctor;
    }

    // Getters
    public String getId() {
        return id;
    }
    public Trabajador getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public LocalDate getFechaConfeccion() { return fechaConfeccion; }
    public LocalDate getFechaRetiro() { return fechaRetiro; }
    public String getEstado() { return estado; }

    // Setters
    public void setId(String id) { this.id = id; }  // Setter para el ID
    public void setDoctor(Trabajador doctor) { this.doctor = doctor; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = new ArrayList<>(prescripciones);
        cant=prescripciones.size();
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

    public String getCantidad() {
        return String.valueOf(cant);
    }

    public String getFecha_Confeccion() {
        return fechaConfeccion.toString();
    }

    public void setFechaConfeccion(LocalDate confeccion) {
        this.fechaConfeccion = confeccion;
        // Si no se ha establecido la fecha de retiro, la establecemos a 3 días después
        if (this.fechaRetiro == null && confeccion != null) {
            this.fechaRetiro = confeccion.plusDays(3);
        }
    }
}