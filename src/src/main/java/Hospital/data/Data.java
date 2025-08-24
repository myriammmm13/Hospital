package Hospital.data;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Receta;
import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medicamento> medicamentos;
    private List<Receta> recetas;
    private  List<Paciente> pacientes;
    private  List<Doctor> doctores;
    private List<Farmaceutico> famaceuticos;

    public Data(){
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
        pacientes = new ArrayList<>();
        doctores = new ArrayList<>();
        famaceuticos = new ArrayList<>();
        medicamentos.add(new Medicamento("111", "Acetaminofen", "500mg"));
        medicamentos.add(new Medicamento("112", "Ibuprofeno", "400mg"));
        medicamentos.add(new Medicamento("113", "Amoxicilina", "250mg cápsulas"));
        medicamentos.add(new Medicamento("114", "Loratadina", "10mg tabletas"));
    }

    public List<Receta> getRecetas() {
        return recetas;
    }


    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public List<Paciente> getPacientes() {return  pacientes;}

    public List<Doctor> getDoctores() {return doctores;}

    public List<Farmaceutico> getFamaceuticos() {return  famaceuticos;}

}
