package Hospital.personas;

public class Paciente extends Persona {
    private int TelNum;
    //private Fecha FechaNacimiento; se debe crear la clase fecha :)

    public Paciente(String nombre, String apellido1, String apellido2, String id, int TelNum) {
        super(nombre, apellido1, apellido2, id);
        this.TelNum = TelNum;
        //this.FechaNacimiento = FechaNacimiento; se debe agregar en el parámetro :)
    }

    public int getTelNum() {
        return TelNum;
    }

    public void setTelNum(int telNum) {
        TelNum = telNum;
    }
    /*public Fecha getFechaNacimiento(){
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Fecha FechaNacimiento){
        this.FechaNacimiento=FechaNacimiento;
    }
    * */
}
