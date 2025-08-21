package Hospital.recetas;

import Hospital.personas.Paciente;
import Hospital.personas.trabajadores.Doctor;
import Hospital.medicamentos.Medicamento;
import Hospital.Fecha;

import java.util.List;

public class Receta {
    private Doctor doctor;
    private Paciente paciente;
    private List<Medicamento> prescripcion;
    private Fecha fechaConfeccion; //falta implementar
    private Fecha fechaRetiro;
    private String estado;

    public Receta(Doctor doctor, Paciente paciente, List<Medicamento> prescripcion,
                  Fecha fechaConfeccion, Fecha fechaRetiro, String estado) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripcion = prescripcion;
        this.fechaConfeccion = fechaConfeccion;
        this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }

}
