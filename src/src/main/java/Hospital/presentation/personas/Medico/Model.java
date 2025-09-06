package Hospital.presentation.personas.Medico;

import Hospital.logic.personas.trabajadores.Medico;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Medico current;

    public static final String CURRENT = "current";

    public Model() {
        current = new Medico();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Medico getCurrent() {
        return current;
    }

    public void setCurrent(Medico m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}