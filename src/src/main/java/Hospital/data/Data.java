package Hospital.data;


import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.recetas.Receta;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElementWrapper(name = "medicamentos")
    @XmlElement(name = "medicamento")
    private List<Medicamento> medicamentos;

    @XmlElementWrapper(name = "trabajadores")
    @XmlElement(name = "trabajador")
    private List<Trabajador> trabajadores;

    @XmlElementWrapper(name = "recetas")
    @XmlElement(name = "receta")
    private List<Receta> recetas;

    @XmlElementWrapper(name = "pacientes")
    @XmlElement(name = "paciente")
    private List<Paciente> pacientes;

    @XmlElementWrapper(name = "medicos")
    @XmlElement(name = "medico")
    private List<Medico> medicos;

    @XmlElementWrapper(name = "farmaceuticos")
    @XmlElement(name = "farmaceutico")
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