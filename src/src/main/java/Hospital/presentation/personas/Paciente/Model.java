package Hospital.presentation.personas.Paciente;

import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Paciente current;
    //debe estar llamando a lista de pacientes en data para buscar y demás

    public static final String CURRENT = "current";

    public Model() {
        current = new Paciente();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Paciente getCurrent() {
        return current;
    }

    public void setCurrent(Paciente r) {
        this.current = r;
        firePropertyChange(CURRENT, null, current);
    }
}
