package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

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
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(LIST, null, medicamentos);
    }



}