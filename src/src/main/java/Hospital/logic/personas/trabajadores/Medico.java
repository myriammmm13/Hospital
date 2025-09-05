package Hospital.logic.personas.trabajadores;

import Hospital.logic.personas.Trabajador;

public class Medico extends Trabajador {
    private String Especialidad; //maybe hacer especialidad como una clase/lista para que solo la seleccionen

    public Medico(String nombre, String apellido1, String apellido2, String id, String Especialidad) {
        super(nombre, apellido1, apellido2, id);
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
