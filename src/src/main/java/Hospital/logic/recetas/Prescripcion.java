package Hospital.logic.recetas;

import Hospital.logic.Medicamento;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "prescripcion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prescripcion {

    private Medicamento medicamento;
    private String indicaciones;
    private int duracion; // en días
    private int cantidad; // unidades

    public Prescripcion() {}

    public Prescripcion(Medicamento medicamento, String indicaciones, int duracion, int cantidad) {
        if (medicamento == null) throw new IllegalArgumentException("Medicamento no puede ser nulo");
        if (indicaciones == null || indicaciones.isBlank()) throw new IllegalArgumentException("Indicaciones requeridas");
        if (duracion <= 0) throw new IllegalArgumentException("Duración debe ser positiva");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser positiva");

        this.medicamento = medicamento;
        this.indicaciones = indicaciones.trim();
        this.duracion = duracion;
        this.cantidad = cantidad;
    }

    // Getters
    public Medicamento getMedicamento() {
        return medicamento;
    }


    public String getCodigoMedicamento() {
        return medicamento != null ? medicamento.getCodigo() : null;
    }

    public String getNombre() {
        return medicamento != null ? medicamento.getNombre() : null;
    }

    public String getPresentacion() {
        return medicamento != null ? medicamento.getPresentacion() : null;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters


    public void setIndicaciones(String indicaciones) {
        if (indicaciones != null && !indicaciones.isBlank()) {
            this.indicaciones = indicaciones.trim();
        }
    }

    public void setPrescripcion(Prescripcion p) {
        this.medicamento = p.medicamento;
        this.indicaciones = p.indicaciones;
        this.duracion = p.duracion;
        this.cantidad = p.cantidad;
    }

    public void setDuracion(int duracion) {
        if (duracion > 0) this.duracion = duracion;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) this.cantidad = cantidad;
    }

    // Utilidad
    public String resumen() {
        return String.format("%s (%s): %d unidades, %d días, indicaciones: %s",
                getNombre(), getPresentacion(), cantidad, duracion, indicaciones);
    }
}