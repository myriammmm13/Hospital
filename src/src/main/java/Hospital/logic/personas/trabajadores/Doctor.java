package Hospital.logic.personas.trabajadores;

import Hospital.logic.personas.Trabajador;

public class Doctor extends Trabajador {
    private String Especialidad; //maybe hacer especialidad como una clase/lista para que solo la seleccionen

    public Doctor(String nombre, String apellido1, String apellido2, String id, String Especialidad) {
        super(nombre, apellido1, apellido2, id);
        this.Especialidad = Especialidad;
    }

    public Doctor() {
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
