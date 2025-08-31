package Hospital.logic.personas;

import Hospital.logic.Fecha;

public class Paciente extends Persona {
    private int TelNum;
    private Fecha FechaNacimiento;

    public Paciente(String nombre, String apellido1, String apellido2, String id, int TelNum, Fecha FechaNacimiento) {
        super(nombre, apellido1, apellido2, id);
        this.TelNum = TelNum;
        this.FechaNacimiento = FechaNacimiento;
    }

    public Paciente(){
        super();
        this.TelNum = 0;
        this.FechaNacimiento = null;
    }

    public int getTelNum() {
        return TelNum;
    }

    public void setTelNum(int telNum) {
        TelNum = telNum;
    }
    public Fecha getFechaNacimiento(){
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Fecha FechaNacimiento){
        this.FechaNacimiento=FechaNacimiento;
    }
}
