package Hospital.presentation.personas.Paciente;

import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Paciente current;
    List<Paciente> list;

    public static final String LIST = "list";
    public static final String CURRENT = "current";

    public Model() {
        current = new Paciente();
        list = new ArrayList<Paciente>();
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

    public List<Paciente> getList() {
        return list;
    }
    public void setList(List<Paciente> list) {
        this.list = list;
        firePropertyChange(LIST);
    }
}