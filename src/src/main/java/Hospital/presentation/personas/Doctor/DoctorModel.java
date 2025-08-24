package Hospital.presentation.personas.Doctor;

import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class DoctorModel extends AbstractModel {
    Doctor current;

    public static final String CURRENT = "current";

    public DoctorModel() {
        current = new Doctor();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        //firePropertyChange(CURRENT);
    }

    public Doctor getCurrent() {
        return current;
    }

    public void setCurrent(Doctor current) {
        this.current = current;
        //firePropertyChange(CURRENT);
    }
}