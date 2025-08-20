package Hospital.personas.Trabajadores;

import Hospital.personas.Trabajador;

public class Farmaceutico extends Trabajador {
    Farmaceutico(String nombre, String apellido1, String apellido2, String id) {
        super(nombre, apellido1, apellido2, id);//luego se debe agregar clave, también lo usa
    }
}
