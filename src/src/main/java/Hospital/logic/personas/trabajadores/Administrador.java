package Hospital.logic.personas.trabajadores;

import Hospital.logic.personas.Trabajador;

//la diferencia de admin es que a él se le permite realizar todos los cambios en data,
//por eso se crea la clase aparte, todo esto con la búsqueda de tenerlos por contenedores distintos

public class Administrador extends Trabajador {
     public Administrador(String nombre, String apellido1, String apellido2, String id) {
         super(nombre, apellido1, apellido2, id);
     }
}
