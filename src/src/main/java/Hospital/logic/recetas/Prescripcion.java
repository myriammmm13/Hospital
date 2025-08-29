package Hospital.logic.recetas;

import Hospital.logic.Medicamento;

public class Prescripcion {
    private Medicamento medicamento;
    private String indicaciones;
    private int duracion;
    private int cantidad;

    public Prescripcion(Medicamento medicamento, String indicaciones, int duracion, int cantidad) {
        this.medicamento = medicamento;
        this.indicaciones = indicaciones;
        this.duracion = duracion;
        this.cantidad = cantidad;
    }

    public String getNombre(){
        return medicamento.getNombre();
    }

    public String getPresentacion(){
        return medicamento.getPresentacion();
    }

    public int getCantidad(){
        return cantidad;
    }

    public String getIndicaciones(){
        return indicaciones;
    }

    public int getDuracion(){
        return duracion;
    }
    
}
