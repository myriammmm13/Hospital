package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class FarmaModel extends AbstractModel {
    Farmaceutico current;

    public static final String CURRENT = "current";

    public FarmaModel() {
        current = new Farmaceutico();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Farmaceutico getCurrent() {
        return current;
    }

    public void setCurrent(Farmaceutico m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}