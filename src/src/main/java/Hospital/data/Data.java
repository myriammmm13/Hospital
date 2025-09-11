package Hospital.data;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.Paciente;
import Hospital.logic.recetas.Receta;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medicamento> medicamentos;
    private List<Receta> recetas;
    private  List<Paciente> pacientes; //se deben agregar todos menos los pacientes, para poder ver el cod que trae el user
    private  List<Medico> medicos;
    private List<Farmaceutico> famaceuticos;
    private List<Trabajador> trabajadores;

    public Data(){
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        famaceuticos = new ArrayList<>();
        trabajadores = new ArrayList<>();//se guardan todos los farma y doc aquí

        medicamentos.add(new Medicamento("111", "Acetaminofen", "500mg"));
        medicamentos.add(new Medicamento("112", "Ibuprofeno", "400mg"));
        medicamentos.add(new Medicamento("113", "Amoxicilina", "250mg cápsulas"));
        medicamentos.add(new Medicamento("114", "Loratadina", "10mg tabletas"));

        trabajadores.add(new Trabajador("Ana", "Ramirez", "Soto", "ADM-001", "adm"));
        trabajadores.add(new Trabajador("Juan", "Hernandez", "Jimenez", "MED-002", "med"));
        trabajadores.add(new Trabajador("Marco", "Perez", "Garcia", "FAR-003", "far"));

    }

    public List<Receta> getRecetas() {
        return recetas;
    }
    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public List<Paciente> getPacientes() {return  pacientes;}
    public List<Medico> getDoctores() {return medicos;}
    public List<Farmaceutico> getFamaceuticos() {return  famaceuticos;}
    public List<Trabajador> getTrabajadores() {return trabajadores;}
}
