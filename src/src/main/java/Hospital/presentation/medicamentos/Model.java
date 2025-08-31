package Hospital.presentation.medicamentos;

import Hospital.logic.Medicamento;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Medicamento current;

    public static final String CURRENT = "current";

    public Model() {
        current = new Medicamento();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Medicamento getCurrent() {
        return current;
    }

    public void setCurrent(Medicamento m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
    }
}