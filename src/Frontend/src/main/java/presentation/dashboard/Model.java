package presentation.dashboard;

import Hospital.backend.logic.Medicamento;
import Hospital.backend.logic.recetas.Receta;
import presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    public static final String LIST = "list";
    private Receta current;
    private List<Receta> recetas;
    private List<Medicamento> medicamentos;

    public static final String CURRENT = "current";

    public Model() {
        List<Receta> recetas = new ArrayList<>();
        List<Medicamento> medicamentos = new ArrayList<>();
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

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
        firePropertyChange("recetas", null, recetas);
    }

    public List<Medicamento> getMedicamentos() {
        if (this.medicamentos == null) {
            return new ArrayList<>();
        }
        return this.medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(LIST, null, medicamentos);
    }
}