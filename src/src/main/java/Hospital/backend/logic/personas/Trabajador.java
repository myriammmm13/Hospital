package Hospital.backend.logic.personas;

import Hospital.backend.logic.Clave;

public class Trabajador extends Persona {
    private Clave clave;

    public Trabajador(String nombre, String id) {
        super(nombre, id);
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
