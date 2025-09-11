package Hospital.presentation.historico;

import Hospital.logic.Medicamento;
import Hospital.logic.Service;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

import java.util.List;

public class Model extends AbstractModel {
    List<Receta> recetas;
    List<Receta> recetasRecientes;
    public static final String RECETAS = "recetas";
    public static final String RECETASRECIENTES = "recetasRecientes";

    public List<Receta> getRecetasList() {
        return recetas;
    }

    public List<Receta> getRecetasRecientes(){ return recetasRecientes; }

    public Model() {
        Service service = Service.instance();
        this.recetas = service.listarRecetas();
        firePropertyChange(RECETAS, null, recetas);
    }

    public void addReceta(Receta receta) {
        this.recetasRecientes.add(receta);
        firePropertyChange(RECETASRECIENTES, null, recetasRecientes);
    }
}
