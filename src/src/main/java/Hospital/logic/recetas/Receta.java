package Hospital.logic.recetas;

import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Receta {
    private Medico doctor;
    private Paciente paciente;
    private List<Prescripcion> prescripciones;
    //private Fecha fechaConfeccion;
    //private Fecha fechaRetiro;
    private String estado;

    public Receta(Medico doctor, Paciente paciente, List<Prescripcion> prescripciones,
                  Fecha fechaConfeccion, Fecha fechaRetiro) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.prescripciones = prescripciones;
      //  this.fechaConfeccion = fechaConfeccion;
        //this.fechaRetiro = fechaRetiro;
        this.estado = "Confeccionada";
    }
    public Receta(){
        this.doctor = new Medico();
        this.paciente=new Paciente();
        this.prescripciones = new ArrayList<>();
        //this.fechaConfeccion=new Fecha(); cambio esto por biblioteca
        //fechaRetiro=new fecha();
        estado = "";
    }
}
