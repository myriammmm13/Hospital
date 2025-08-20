package Hospital.personas;

import Hospital.Clave;

public abstract class Trabajador extends Persona {
    private Clave clave;

    protected Trabajador(String nombre, String apellido1, String apellido2, String id) {
        super(nombre, apellido1, apellido2, id);
        clave=new Clave(id);
    }
    public String getClave(){
        return clave.getClave();
    }
    public void setClave(String clave){
        this.clave.setClave(clave);
    }
}
