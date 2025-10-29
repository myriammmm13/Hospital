package data;


import Hospital.backend.logic.Medicamento;
import Hospital.backend.logic.personas.Paciente;
import Hospital.backend.logic.personas.Trabajador;
import Hospital.backend.logic.personas.trabajadores.Farmaceutico;
import Hospital.backend.logic.personas.trabajadores.Medico;
import Hospital.backend.logic.recetas.Receta;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medicamento> medicamentos;
    private List<Trabajador> trabajadores;

    private List<Receta> recetas;

    private List<Paciente> pacientes;

    private List<Medico> medicos;

    private List<Farmaceutico> famaceuticos;

    public Data() {
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        famaceuticos = new ArrayList<>();
        trabajadores = new ArrayList<>();
    }

    public void inicializarSiVacio() {
        if (medicamentos.isEmpty()) {
            medicamentos.add(new Medicamento("111", "Acetaminofen", "500mg"));
            medicamentos.add(new Medicamento("112", "Ibuprofeno", "400mg"));
            medicamentos.add(new Medicamento("113", "Amoxicilina", "250mg cápsulas"));
            medicamentos.add(new Medicamento("114", "Loratadina", "10mg tabletas"));
        }

        if (trabajadores.isEmpty()) {
            trabajadores.add(new Trabajador("Ana Ramirez Soto", "ADM-001"));
            trabajadores.add(new Trabajador("Juan Hernandez Jimenez", "MED-002"));
            trabajadores.add(new Trabajador("Marco Perez Garcia", "FAR-003"));
            pacientes.add(new Paciente("Abelardo Nuñez", "1111", "88489371", "hoy"));
        }
    }

    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Trabajador> getTrabajadores() { return trabajadores; }

    public List<Receta> getRecetas() { return recetas; }
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getDoctores() { return medicos; }
    public List<Farmaceutico> getFamaceuticos() { return famaceuticos; }
}