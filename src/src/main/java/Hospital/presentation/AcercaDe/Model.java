package Hospital.presentation.AcercaDe;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Receta current;

    public static final String CURRENT = "current";

    public Model() {
        //current = new Receta(doctorAuxiliar = new Medico(), model.getCurrent().getPaciente(), model.getCurrent().getPrescripciones());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Receta getCurrent() {
        return current;
    }

    public void setCurrent(Receta r) {
        this.current = r;
        firePropertyChange(CURRENT, null, current);
    }
}