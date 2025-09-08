package Hospital.logic.personas;

import Hospital.logic.Fecha;

public class Paciente extends Persona {
    private String TelNum;
    private Fecha FechaNacimiento;

    public Paciente(String nombre, String apellido1, String apellido2, String id, String TelNum, Fecha FechaNacimiento) {
        super(nombre, apellido1, apellido2, id);
        this.TelNum = TelNum;
        this.FechaNacimiento = FechaNacimiento;
    }

    public Paciente(){
        super();
        this.TelNum = "";
        this.FechaNacimiento = null;
    }

    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }
    public Fecha getFechaNacimiento(){
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Fecha FechaNacimiento){
        this.FechaNacimiento=FechaNacimiento;
    }
}
