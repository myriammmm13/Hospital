package Hospital.personas.Trabajadores;

import Hospital.personas.Trabajador;

public class Doctor extends Trabajador {
    private String Especialidad; //maybe hacer especialidad como una clase/lista para que solo la seleccionen

    Doctor(String nombre, String apellido1, String apellido2, String id, String Especialidad) {
        super(nombre, apellido1, apellido2, id);
        this.Especialidad = Especialidad;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }
}
