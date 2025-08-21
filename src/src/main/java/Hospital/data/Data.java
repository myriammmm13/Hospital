package Hospital.data;

import Hospital.medicamentos.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medicamento> medicamentos;

    public Data(){
        medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento("111", "Acetaminofen", "500mg"));
        medicamentos.add(new Medicamento("112", "Ibuprofeno", "400mg"));
        medicamentos.add(new Medicamento("113", "Amoxicilina", "250mg cápsulas"));
        medicamentos.add(new Medicamento("114", "Loratadina", "10mg tabletas"));
    }

}
