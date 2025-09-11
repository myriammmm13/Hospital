package Hospital.presentation.historico;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    List<Receta> recetas;
    List<Receta> recetasRecientes;
    public static final String RECETAS = "recetas";
    public static final String PRESCRIPCIONES = "prescripciones";
    public static final String RECETASRECIENTES = "recetasRecientes";

    public List<Receta> getRecetasList() {
        return recetas;
    }

    public Model(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public void addReceta(Receta receta) {
        this.recetasRecientes.add(receta);
    }
}
