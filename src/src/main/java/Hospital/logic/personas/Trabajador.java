package Hospital.logic.personas;

import Hospital.logic.Clave;

public class Trabajador extends Persona {
    private Clave clave;

    public Trabajador(String nombre, String apellido1, String apellido2, String id) {
        super(nombre, apellido1, apellido2, id);
        clave=new Clave(id);
    }
    public Trabajador(){
        super();
        clave=new Clave("");
    }
    public String getClave(){
        return clave.getClave();
    }
    public void setClave(String clave){
        this.clave.setClave(clave);
    }
}
