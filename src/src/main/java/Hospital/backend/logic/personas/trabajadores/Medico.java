package Hospital.backend.logic.personas.trabajadores;

import Hospital.backend.logic.personas.Trabajador;

public class Medico extends Trabajador {
    private String Especialidad; //maybe hacer especialidad como una clase/lista para que solo la seleccionen

    public Medico(String nombre, String id, String Especialidad) {
        super(nombre, id);
        this.Especialidad = Especialidad;
    }

    public Medico() {
        super();
        this.Especialidad = "";
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }
}
