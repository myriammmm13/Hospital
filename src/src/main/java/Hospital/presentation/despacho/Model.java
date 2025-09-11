package Hospital.presentation.despacho;

import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    List<Receta> recetas;
    public static final String RECETAS = "recetas";

    public List<Receta> getRecetasList() {
        return recetas;
    }

    public Model(List<Receta> recetas) {
        recetas = new ArrayList<>();
        this.recetas = recetas;
        firePropertyChange(RECETAS, null, recetas);
    }

    public void procesarReceta(int row){
        Receta r = this.recetas.get(row);
        r.setEstado("En Proceso");
        firePropertyChange(RECETAS, null, recetas);
    }

    public void prepararReceta(int row){
        Receta r = this.recetas.get(row);
        r.setEstado("Lista");
        firePropertyChange(RECETAS, null, recetas);
    }

    public void despacharReceta(int row){
        Receta r = this.recetas.get(row);
        r.setEstado("Entregada");
        firePropertyChange(RECETAS, null, recetas);
    }

    public void descartarReceta(int row){
        Receta r = this.recetas.get(row);
        recetas.remove(r);
        firePropertyChange(RECETAS, null, recetas);
    }
}
