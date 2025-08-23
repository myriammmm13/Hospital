package Hospital.logic;

import Hospital.data.Data;
import Hospital.logic.personas.Persona; //No estoy seguro si ocupan persona o las subs
import Hospital.logic.medicamentos.Medicamento;
import Hospital.logic.recetas.Receta;

//recreado del código del profe
public class Service {
    private static Service Instance;

    public static Service instance() {
        if (Instance == null) Instance = new Service();
        return Instance;
    }

    private Data data;

    private Service() {
        data = new Data();
    }
}