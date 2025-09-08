package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Farmaceutico current;
    List<Farmaceutico> list;

    public static final String LIST = "list";
    public static final String CURRENT = "current";

    public Model() {
        current = new Farmaceutico();
        list = new ArrayList<Farmaceutico>();
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
    public List<Farmaceutico> getList() {
        return list;
    }
    public void setList(List<Farmaceutico> list) {
        this.list = list;
        firePropertyChange(LIST);
    }
}