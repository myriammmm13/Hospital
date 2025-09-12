package Hospital.presentation.login;

import Hospital.data.Data;
import Hospital.logic.Session;
import Hospital.logic.personas.Trabajador;

public class Model {
    private Data data;

    public Model(Data data) {
        this.data = data;
    }

    public boolean validate(String user, String password) {
        for (Trabajador t : data.getTrabajadores()) {
            if (t.getId().equals(user) && t.getClave().equals(password)) {
                Session.getInstance().setUsuario(user);
                return true;
            }
        }
        return false;
    }

    public String getUserType(String user) {
        for (Trabajador t : data.getTrabajadores()) {
            if (t.getId().equals(user)) {
                return t.getId().split("-")[0];
            }
        }
        return "DESCONOCIDO";
    }

    public Data getData() {
        return data;
    }
}