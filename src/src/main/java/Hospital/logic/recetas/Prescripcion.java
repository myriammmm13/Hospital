package Hospital.logic.recetas;

import Hospital.logic.medicamentos.Medicamento;

public class Prescripcion {
    private Medicamento medicamento;
    private String instrucciones;
    private int plazo;
    private int cantidad;

    public Prescripcion(Medicamento medicamento, String instrucciones, int plazo, int cantidad) {
        this.medicamento = medicamento;
        this.instrucciones = instrucciones;
        this.plazo = plazo;
        this.cantidad = cantidad;
    }
}
