package Hospital.presentation.historico;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Receta current;
    List<Receta> recetas;

    public static final String CURRENT = "current";

    public Model() {
        current = new Receta();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
    }

    public Receta getCurrent() {
        return current;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setCurrent(Receta r) {
        this.current = r;
        firePropertyChange(CURRENT, null, current);
    }
}
