package Hospital.logic;

//la clave debe ser inicialmente id del usuario, después se puede cambiar
//Ella va contenida en solo en trabajador (doc y farma) porque paciente no tiene
public class Clave {
    private String clave;

    public Clave(){}

    public Clave(String clave) {
        this.clave = clave;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
}
