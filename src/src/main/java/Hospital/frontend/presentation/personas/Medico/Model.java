package Hospital.frontend.presentation.personas.Medico;

import Hospital.backend.logic.personas.trabajadores.Medico;
import Hospital.frontend.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Medico current;
    List<Medico> list;

    public static final String LIST = "list";
    public static final String CURRENT = "current";

    public Model() {
        current = new Medico();
        list = new ArrayList<Medico>();

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
    public List<Medico> getList() {
        return list;
    }
    public void setList(List<Medico> list) {
        this.list = list;
        firePropertyChange(LIST);
    }
}