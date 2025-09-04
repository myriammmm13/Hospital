package Hospital.presentation.recetas;

import Hospital.logic.recetas.Receta;
import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractModel;

import java.util.List;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Receta current;
    List<Medicamento> medicamentos;
    List<Paciente> pacientes;

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

    public void setCurrent(Receta r) {
        this.current = r;
        firePropertyChange(CURRENT, null, current);
    }
}
