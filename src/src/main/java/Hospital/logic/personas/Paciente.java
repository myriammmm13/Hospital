package Hospital.logic.personas;

public class Paciente extends Persona {
    private String TelNum;
    private String FechaNacimiento;

    public Paciente(String nombre, String apellido1, String apellido2, String id, String TelNum, String FechaNacimiento) {
        super(nombre, apellido1, apellido2, id);
        this.TelNum = TelNum;
        this.FechaNacimiento = FechaNacimiento;
    }

    public Paciente(){
        super();
        this.TelNum = "";
        this.FechaNacimiento = "";
    }

    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }
    public String getFechaNacimiento(){
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento){
        this.FechaNacimiento=FechaNacimiento;
    }
}
