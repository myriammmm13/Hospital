package Hospital.data;

import Hospital.logic.medicamentos.Medicamento;
import Hospital.logic.recetas.Receta;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medicamento> medicamentos;
    private List<Receta> recetas;

    public Data(){
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
        medicamentos.add(new Medicamento("111", "Acetaminofen", "500mg"));
        medicamentos.add(new Medicamento("112", "Ibuprofeno", "400mg"));
        medicamentos.add(new Medicamento("113", "Amoxicilina", "250mg cápsulas"));
        medicamentos.add(new Medicamento("114", "Loratadina", "10mg tabletas"));
    }
    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }


    public List<Receta> getRecetas() {
        return recetas;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

}
