package presentation.historico;

import Hospital.backend.logic.Service;
import Hospital.backend.logic.recetas.Receta;
import presentation.AbstractModel;

import java.util.ArrayList;
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
            this.recetasRecientes = new ArrayList<>();
            firePropertyChange(RECETAS, null, recetas);
        }

        public void addReceta(Receta receta) {
            this.recetasRecientes.add(receta);
            firePropertyChange(RECETASRECIENTES, null, recetasRecientes);
        }
    }

