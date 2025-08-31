package Hospital.presentation.personas.Administrador;

import Hospital.logic.personas.trabajadores.Administrador;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Administrador current;

    public static final String CURRENT = "current";

    public Model() {
        current = new Administrador();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Administrador getCurrent() {
        return current;
    }

    public void setCurrent(Administrador m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}