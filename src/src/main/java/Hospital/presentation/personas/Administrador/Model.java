package Hospital.presentation.personas.Administrador;

import Hospital.logic.personas.Trabajador;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Trabajador current; //puede ser Doc o farma, solo que tenga el cod ADMIN

    public static final String CURRENT = "current";

    public Model() {
        current = new Trabajador();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Trabajador getCurrent() {
        return current;
    }

    public void setCurrent(Trabajador m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}