package Hospital.personas;

public class Trabajador extends Persona {
    //private Clave clave;//crear clase Clave plis

    Trabajador(String nombre, String apellido1, String apellido2, String id) {
        super(nombre, apellido1, apellido2, id);
        //this.clave=clave; aquí agrega clave a parámetros
    }
    /*public Clave getClave(){
     *   return clave;
     * }
     *
     * public void setClave(Clave clave){
     *   this.clave=clave;
     * } //Maybe se puede trabajar este set como si fuese un "cambioClave" para reutilizar código
     * */
}
