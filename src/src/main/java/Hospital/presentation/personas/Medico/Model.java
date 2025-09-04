package Hospital.presentation.personas.Medico;

import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Doctor current;

    public static final String CURRENT = "current";

    public Model() {
        current = new Doctor();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Doctor getCurrent() {
        return current;
    }

    public void setCurrent(Doctor m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}