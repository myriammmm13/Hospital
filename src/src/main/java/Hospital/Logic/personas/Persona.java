package Hospital.Logic.personas;

public abstract class Persona {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String id;

    protected Persona(String nombre, String apellido1, String apellido2, String id) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Para validación por tipo de usuario
    public String getTipoUsuario() {
        if (id.startsWith("ADMIN-")) return "ADMIN";
        if (id.startsWith("DOC-")) return "MEDICO";
        if (id.startsWith("FARM-")) return "FARMACEUTICO";
        if (id.startsWith("PAC-")) return "PACIENTE";
        return "DESCONOCIDO";
    }



}
