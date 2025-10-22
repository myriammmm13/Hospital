package Hospital.logic;

import Hospital.Application;
import Hospital.data.Data;
import Hospital.data.personas.FarmaceuticoDao;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;
import Hospital.data.medicamento.MedicamentoDao;
import Hospital.data.personas.PacienteDao;
import Hospital.data.personas.MedicoDao;
import Hospital.data.medicamento.RecetaDao;
import Hospital.data.DataBase;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;
    private Data data;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Service() {
        data = Application.data;
    }

    // CRUD Medicamentos con base de datos
    MedicamentoDao medicamentoDao = new MedicamentoDao(DataBase.instance());

    // CREATE
    public void agregarMedicamento(Medicamento nuevo, String userId) throws Exception {
        try {
            medicamentoDao.create(nuevo);
            System.out.println("Medicamento guardado en base de datos");
        } catch (Exception e) {
            throw new Exception("Ya existe un medicamento con ese ID");
        }
    }

    // READ
    public List<Medicamento> listarMedicamentos() {
        return medicamentoDao.findByNombre(new Medicamento()); // devuelve todos si el filtro está vacío
    }

    public List<Medicamento> obtenerMedicamento(String cod) throws Exception {
        Medicamento filtro = new Medicamento();
        filtro.setNombre(cod);
        List<Medicamento> medicamentosEncontrados = medicamentoDao.findByNombre(filtro);
        if (medicamentosEncontrados == null || medicamentosEncontrados.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }
        return medicamentosEncontrados;
    }

    // UPDATE
    public void actualizarMedicamento(Medicamento actualizado) throws Exception {
        try {
            medicamentoDao.update(actualizado);
            System.out.println("Medicamento actualizado en base de datos");
        } catch (Exception e) {
            throw new Exception("Medicamento no encontrado para actualizar");
        }
    }

    // DELETE
    public void eliminarMedicamento(String cod) throws Exception {
        Medicamento m = new Medicamento();
        m.setCodigo(cod);
        try {
            medicamentoDao.delete(m);
            System.out.println("Medicamento eliminado de base de datos");
        } catch (Exception e) {
            throw new Exception("Medicamento no encontrado para eliminar");
        }
    }


    // CRUD Pacientes con base de datos
    PacienteDao pacienteDao = new PacienteDao();

    // CREATE
    public void agregarPaciente(Paciente nuevo) throws Exception {
        try {
            pacienteDao.create(nuevo);
            System.out.println("Paciente agregado a base de datos");
        } catch (Exception e) {
            throw new Exception("Ya existe un paciente con ese ID");
        }
    }

    // READ
    public List<Paciente> listarPacientes() {
        return pacienteDao.findByNombre(new Paciente("","","","")); // devuelve todos si el filtro está vacío
    }

    public List<Paciente> obtenerPacientes(String id) throws Exception {
        Paciente filtro = new Paciente("", "", "", "");
        filtro.setNombre(id);
        List<Paciente> pacientesEncontrados = pacienteDao.findByNombre(filtro);
        if (pacientesEncontrados == null || pacientesEncontrados.isEmpty()) {
            throw new Exception("Paciente no encontrado");
        }
        return pacientesEncontrados;
    }

    public Paciente obtenerPaciente(String id, String nom) throws Exception {
        try {
            return pacienteDao.read(id); // busca por ID directamente
        } catch (Exception e) {
            Paciente filtro = new Paciente("", nom, "", "");
            List<Paciente> porNombre = pacienteDao.findByNombre(filtro);
            if (porNombre == null || porNombre.isEmpty()) {
                throw new Exception("Paciente no encontrado");
            }
            return porNombre.get(0); // devuelve el primero que coincida
        }
    }

    // UPDATE
    public void actualizarPaciente(Paciente act) throws Exception {
        try {
            pacienteDao.update(act);
            System.out.println("Paciente actualizado en base de datos");
        } catch (Exception e) {
            throw new Exception("Paciente no encontrado para actualizar");
        }
    }

    // DELETE
    public void eliminarPaciente(Paciente r) throws Exception {
        try {
            pacienteDao.delete(r);
            System.out.println("Paciente eliminado de base de datos");
        } catch (Exception e) {
            throw new Exception("Paciente no encontrado para eliminar");
        }
    }

    // CRUD Doctores con base de datos
    MedicoDao medicoDao = new MedicoDao(DataBase.instance());

    // CREATE
    public void agregarDoctor(Medico nuevo) throws Exception {
        try {
            medicoDao.create(nuevo);
            System.out.println("Doctor guardado en base de datos");
        } catch (Exception e) {
            throw new Exception("Ya existe un doctor con ese ID");
        }
    }

    // READ
    public List<Medico> listarDoctores() {
        return medicoDao.findByNombre(new Medico()); // devuelve todos si el filtro está vacío
    }

    public List<Medico> obtenerDoctores(String filtro) throws Exception {
        Medico medicoFiltro = new Medico();
        medicoFiltro.setNombre(filtro);
        List<Medico> doctoresEncontrados = medicoDao.findByNombre(medicoFiltro);
        if (doctoresEncontrados == null || doctoresEncontrados.isEmpty()) {
            throw new Exception("No se encontraron doctores con ese criterio");
        }
        return doctoresEncontrados;
    }

    public Medico obtenerDoctor(String id, String nom) throws Exception {
        try {
            if (id != null && !id.isEmpty()) {
                return medicoDao.read(id); // busca por ID directamente
            } else {
                List<Medico> porNombre = medicoDao.findByNombre(new Medico(" ", "", ""));
                if (porNombre == null || porNombre.isEmpty()) {
                    throw new Exception("Doctor no encontrado");
                }
                return porNombre.get(0); // devuelve el primero que coincida
            }
        } catch (Exception e) {
            throw new Exception("Doctor no encontrado");
        }
    }

    // UPDATE
    public void actualizarDoctor(Medico act) throws Exception {
        try {
            medicoDao.update(act);
            System.out.println("Doctor actualizado en base de datos");
        } catch (Exception e) {
            throw new Exception("Doctor no encontrado para actualizar");
        }
    }

    // DELETE
    public void eliminarDoctor(String id, String nombre) throws Exception {
        try {
            Medico doc = obtenerDoctor(id, nombre);
            medicoDao.delete(doc);
            System.out.println("Doctor eliminado de base de datos");
        } catch (Exception e) {
            throw new Exception("Doctor no encontrado para eliminar");
        }
    }

    // CRUD Farmaceuticos con base de datos
    FarmaceuticoDao farmaceuticoDao = new FarmaceuticoDao(DataBase.instance());

    // CREATE
    public void agregarFarmaceutico(Farmaceutico nuevo) throws Exception {
        try {
            farmaceuticoDao.create(nuevo);
            System.out.println("Farmacéutico guardado en base de datos");
        } catch (Exception e) {
            throw new Exception("Ya existe un farmacéutico con ese ID");
        }
    }

    // READ
    public List<Farmaceutico> listarFarmaceuticos() {
        return farmaceuticoDao.findByNombre(new Farmaceutico()); // devuelve todos si el filtro está vacío
    }

    public List<Farmaceutico> obtenerFarmaceuticos(String id, String nom) throws Exception {
        Farmaceutico filtro = new Farmaceutico();
        filtro.setNombre(id);
        List<Farmaceutico> farmaceuticosEncontrados = farmaceuticoDao.findByNombre(filtro);
        if (farmaceuticosEncontrados == null || farmaceuticosEncontrados.isEmpty()) {
            throw new Exception("Farmacéutico no encontrado");
        }
        return farmaceuticosEncontrados;
    }

    public Farmaceutico obtenerFarmaceutico(String id, String nom) throws Exception {
        try {
            if (id != null && !id.isEmpty()) {
                return farmaceuticoDao.read(id); // busca por ID directamente
            } else {
                Farmaceutico filtro = new Farmaceutico();
                filtro.setNombre(nom);
                List<Farmaceutico> porNombre = farmaceuticoDao.findByNombre(filtro);
                if (porNombre == null || porNombre.isEmpty()) {
                    throw new Exception("Farmacéutico no encontrado");
                }
                return porNombre.get(0); // devuelve el primero que coincida
            }
        } catch (Exception e) {
            throw new Exception("Farmacéutico no encontrado");
        }
    }

    // UPDATE
    public void actualizarFarmaceutico(Farmaceutico act) throws Exception {
        try {
            farmaceuticoDao.update(act);
            System.out.println("Farmacéutico actualizado en base de datos");
        } catch (Exception e) {
            throw new Exception("Farmacéutico no encontrado para actualizar");
        }
    }

    // DELETE
    public void eliminarFarmaceutico(String id, String nom) throws Exception {
        try {
            Farmaceutico farm = obtenerFarmaceutico(id, nom);
            farmaceuticoDao.delete(farm);
            System.out.println("Farmacéutico eliminado de base de datos");
        } catch (Exception e) {
            throw new Exception("Farmacéutico no encontrado para eliminar");
        }
    }

    // CRUD Trabajador/Admin con base de datos

    // CREATE
    public void agregarTrabajador(Trabajador nuevo, String userId) throws Exception {
        if (nuevo instanceof Medico medico) {
            agregarDoctor(medico);
        } else if (nuevo instanceof Farmaceutico farma) {
            agregarFarmaceutico(farma);
        } else {
            throw new Exception("Tipo de trabajador no soportado");
        }
    }

    // READ
    public List<Trabajador> listarTrabajadores() {
        List<Trabajador> todos = new ArrayList<>();
        todos.addAll(listarDoctores());
        todos.addAll(listarFarmaceuticos());
        return todos;
    }

    public List<Trabajador> obtenerTrabajador(String id) throws Exception {
        List<Trabajador> encontrados = new ArrayList<>();
        
        // Buscar en médicos
        try {
            Medico medicoFiltro = new Medico();
            medicoFiltro.setNombre(id);
            List<Medico> medicos = medicoDao.findByNombre(medicoFiltro);
            if (medicos != null) {
                encontrados.addAll(medicos);
            }
        } catch (Exception e) {
            // Continuar buscando en farmacéuticos
        }

        // Buscar en farmacéuticos
        try {
            Farmaceutico farmaFiltro = new Farmaceutico();
            farmaFiltro.setNombre(id);
            List<Farmaceutico> farmaceuticos = farmaceuticoDao.findByNombre(farmaFiltro);
            if (farmaceuticos != null) {
                encontrados.addAll(farmaceuticos);
            }
        } catch (Exception e) {
            // Ignorar si no se encuentran farmacéuticos
        }

        if (encontrados.isEmpty()) {
            throw new Exception("Trabajador no encontrado");
        }
        return encontrados;
    }

    // UPDATE
    public void actualizarTrabajador(Trabajador act, String userId) throws Exception {
        if (act instanceof Medico medico) {
            actualizarDoctor(medico);
        } else if (act instanceof Farmaceutico farma) {
            actualizarFarmaceutico(farma);
        } else {
            throw new Exception("Tipo de trabajador no soportado");
        }
    }

    // DELETE
    public void eliminarTrabajador(String id, String userId) throws Exception {
        boolean eliminado = false;
        
        // Intentar eliminar como médico
        try {
            Medico doc = obtenerDoctor(id, "");
            if (doc != null) {
                eliminarDoctor(id, doc.getNombre());
                eliminado = true;
            }
        } catch (Exception e) {
            // Si no es médico, intentar como farmacéutico
        }

        // Si no se eliminó como médico, intentar como farmacéutico
        if (!eliminado) {
            try {
                Farmaceutico farm = obtenerFarmaceutico(id, "");
                if (farm != null) {
                    eliminarFarmaceutico(id, farm.getNombre());
                    eliminado = true;
                }
            } catch (Exception e) {
                // Si no es farmacéutico tampoco, lanzar excepción
                if (!eliminado) {
                    throw new Exception("Trabajador no encontrado para eliminar");
                }
            }
        }
    }

    // CRUD Recetas con base de datos
    RecetaDao recetaDao = new RecetaDao(DataBase.instance());

    // CREATE
    public void agregarReceta(Receta receta) throws Exception {
        try {
            recetaDao.create(receta);
            System.out.println("Receta guardada en base de datos");
        } catch (Exception e) {
            throw new Exception("Error al registrar la receta: " + e.getMessage());
        }
    }

    // READ
    public List<Receta> listarRecetas() {
        return recetaDao.findAll(); // método para obtener todas las recetas
    }

    public Receta obtenerReceta(Receta r) throws Exception {
        try {
            // Primero intentamos buscar por ID si está disponible
            if (r.getId() != null && !r.getId().isEmpty()) {
                return recetaDao.read(r.getId());
            }
            
            // Si no hay ID, buscamos por los criterios compuestos
            List<Receta> recetas = recetaDao.findByPacienteAndDoctor(r.getPaciente(), r.getDoctor(), r.getFechaConfeccion());
            if (recetas == null || recetas.isEmpty()) {
                throw new Exception("Receta no encontrada");
            }
            return recetas.get(0);
        } catch (Exception e) {
            throw new Exception("Error al buscar la receta: " + e.getMessage());
        }
    }

    // UPDATE
    public void actualizarReceta(Receta r) throws Exception {
        try {
            recetaDao.update(r);
            System.out.println("Receta actualizada en base de datos");
        } catch (Exception e) {
            throw new Exception("Error al actualizar la receta: " + e.getMessage());
        }
    }

    // DELETE
    public void eliminarReceta(Receta r) throws Exception {
        try {
            recetaDao.delete(r);
            System.out.println("Receta eliminada de base de datos");
        } catch (Exception e) {
            throw new Exception("Error al eliminar la receta: " + e.getMessage());
        }
    }

    //más de recetas por dashboard
    public Map<String, Integer> cantidadPorMedicamentoYMes(String nombreMedicamento, LocalDate desde, LocalDate hasta) {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        try {
            List<Receta> recetas = recetaDao.findByDateRange(desde, hasta);
            for (Receta r : recetas) {
                LocalDate fecha = r.getFechaConfeccion();
                if (fecha != null) {
                    String claveMes = fecha.getYear() + "-" + fecha.getMonthValue(); // Ej: "2025-8"
                    for (Prescripcion p : r.getPrescripciones()) {
                        if (p.getNombre().equalsIgnoreCase(nombreMedicamento)) {
                            resultado.put(claveMes, resultado.getOrDefault(claveMes, 0) + p.getCantidad());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener estadísticas: " + e.getMessage());
        }
        return resultado;
    }

    //Demás 

    public List<Paciente> findAllPacientes() {
        return data.getPacientes();
    }
    public List<Medicamento> findAllMedicamentos() {
        return data.getMedicamentos();
    }
    public List<Medico> findAllMedico() {
        return data.getDoctores();
    }
    public List<Farmaceutico> findAllFarmacuetico() {
        return data.getFamaceuticos();
    }


    public List<Paciente> search( Paciente e) {
        return data.getPacientes().stream()
                .filter(i -> i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Paciente::getNombre))
                .collect(Collectors.toList());
    }

    public Trabajador findTrabajadorById(String id) {
        for (Trabajador m : data.getTrabajadores()) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }
}