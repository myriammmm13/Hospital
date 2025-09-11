package Hospital.logic;

import Hospital.Application;
import Hospital.data.Data;
import Hospital.data.XmlPersister;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;

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

    //CRUD Medicamentos
    //CREATE
    public void agregarMedicamento(Medicamento nuevo, String userId) throws Exception {
        for (Medicamento m : data.getMedicamentos()) {
            if (m.getCodigo().equals(nuevo.getCodigo())) {
                throw new Exception("Ya existe un medicamento con ese ID");
            }
        }

        data.getMedicamentos().add(nuevo);

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Medicamento guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar en XML:");
            e.printStackTrace();
        }
    }

    //READ
    public List<Medicamento> listarMedicamentos() {
        return data.getMedicamentos();
    }

    public List<Medicamento> obtenerMedicamento(String cod) throws Exception {
        List<Medicamento> medicamentosEncontrados = new ArrayList<>();
        String codLower = cod.toLowerCase();

        for (Medicamento m : data.getMedicamentos()) {
            if (m.getCodigo().toLowerCase().contains(codLower) ||
                    m.getNombre().toLowerCase().contains(codLower)) {
                medicamentosEncontrados.add(m);
            }
        }

        if (medicamentosEncontrados.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }

        return medicamentosEncontrados;
    }

    //UPDATE
    public void actualizarMedicamento(Medicamento actualizado) throws Exception {
        for (int i = 0; i < data.getMedicamentos().size(); i++) {
            if (data.getMedicamentos().get(i).getCodigo().equals(actualizado.getCodigo())) {
                data.getMedicamentos().set(i, actualizado);

                // Guardar en XML
                try {
                    XmlPersister.instance().store(data);
                    System.out.println("Medicamento actualizado y guardado en XML");
                } catch (Exception e) {
                    System.out.println("Error al guardar actualización en XML:");
                    e.printStackTrace();
                }

                return;
            }
        }
        throw new Exception("Medicamento no encontrado para actualizar");
    }

    //DELETE
    public void eliminarMedicamento(String cod) throws Exception {
        boolean eliminado = data.getMedicamentos().removeIf(m -> m.getCodigo().equals(cod));

        if (!eliminado) throw new Exception("Medicamento no encontrado para eliminar");

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Medicamento eliminado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación en XML:");
            e.printStackTrace();
        }
    }


    // Paciente CRUD con persistencia
    public void agregarPaciente(Paciente nuevo) throws Exception {
        for (Paciente m : data.getPacientes()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un paciente con ese ID");
            }
        }
        data.getPacientes().add(nuevo);

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Paciente agregado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar paciente:");
            e.printStackTrace();
        }
    }

    public List<Paciente> listarPacientes() {
        return data.getPacientes();
    }

    public List<Paciente> obtenerPacientes(String id) throws Exception {
        List<Paciente> pacientesEncontrados = new ArrayList<>();
        for (Paciente m : data.getPacientes()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                pacientesEncontrados.add(m);
        }
        if (pacientesEncontrados.isEmpty()) {
            throw new Exception("Paciente no encontrado");
        }
        return pacientesEncontrados;
    }

    public Paciente obtenerPaciente(String id, String nom) throws Exception {
        for (Paciente m : data.getPacientes()) {
            if (m.getId().equals(id) || m.getNombre().equals(nom))
                return m;
        }
        throw new Exception("Paciente no encontrado");
    }

    public void actualizarPaciente(Paciente act) throws Exception {
        for (int i = 0; i < data.getPacientes().size(); i++) {
            if (data.getPacientes().get(i).getId().equals(act.getId())) {
                data.getPacientes().set(i, act);

                // Guardar en XML
                try {
                    XmlPersister.instance().store(data);
                    System.out.println("Paciente actualizado y guardado en XML");
                } catch (Exception e) {
                    System.out.println("Error al guardar actualización:");
                    e.printStackTrace();
                }

                return;
            }
        }
        throw new Exception("Paciente no encontrado para actualizar");
    }

    public void eliminarPaciente(Paciente r) throws Exception {
        boolean eliminado = data.getPacientes().removeIf(m -> m.getId().equals(r.getId()));
        if (!eliminado) {
            eliminado = data.getPacientes().removeIf(m -> m.getNombre().equals(r.getNombre()));
            if (!eliminado)
                throw new Exception("Paciente no encontrado para eliminar");
        }

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Paciente eliminado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación:");
            e.printStackTrace();
        }
    }

    // CREATE
    public void agregarDoctor(Medico nuevo) throws Exception {
        for (Medico m : data.getDoctores()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un doctor con ese ID");
            }
        }
        data.getDoctores().add(nuevo);
        data.getTrabajadores().add(nuevo); // si también lo registrás como trabajador

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Doctor agregado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar doctor:");
            e.printStackTrace();
        }
    }

    // READ
    public List<Medico> listarDoctores() {
        return data.getDoctores();
    }

    public List<Medico> obtenerDoctores(String id, String nom) throws Exception {
        List<Medico> doctoresEncontrados = new ArrayList<>();
        for (Medico m : data.getDoctores()) {
            if (m.getId().contains(id) || m.getNombre().contains(id)) {
                doctoresEncontrados.add(m);
            }
        }
        if (doctoresEncontrados.isEmpty()) {
            throw new Exception("Doctor no encontrado");
        }
        return doctoresEncontrados;
    }

    public Medico obtenerDoctor(String id, String nom) throws Exception {
        for (Medico m : data.getDoctores()) {
            if (m.getId().equals(id) || m.getNombre().equals(nom)) {
                return m;
            }
        }
        throw new Exception("Doctor no encontrado");
    }

    // UPDATE
    public void actualizarDoctor(Medico act) throws Exception {
        for (int i = 0; i < data.getDoctores().size(); i++) {
            if (data.getDoctores().get(i).getId().equals(act.getId())) {
                data.getDoctores().set(i, act);

                // Guardar en XML
                try {
                    XmlPersister.instance().store(data);
                    System.out.println("Doctor actualizado y guardado en XML");
                } catch (Exception e) {
                    System.out.println("Error al guardar actualización:");
                    e.printStackTrace();
                }

                return;
            }
        }
        throw new Exception("Doctor no encontrado para actualizar");
    }

    // DELETE
    public void eliminarDoctor(String id, String nom) throws Exception {
        boolean eliminado = data.getDoctores().removeIf(m -> m.getId().equals(id));
        if (!eliminado) {
            eliminado = data.getDoctores().removeIf(m -> m.getNombre().equals(nom));
        }
        if (!eliminado) {
            throw new Exception("Doctor no encontrado para eliminar");
        }

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Doctor eliminado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación:");
            e.printStackTrace();
        }
    }

    // CREATE
    public void agregarFarmaceutico(Farmaceutico nuevo) throws Exception {
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un farmacéutico con ese ID");
            }
        }
        data.getFamaceuticos().add(nuevo);
        data.getTrabajadores().add(nuevo); // si también lo registrás como trabajador

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Farmacéutico agregado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar farmacéutico:");
            e.printStackTrace();
        }
    }

    // READ
    public List<Farmaceutico> listarFarmaceuticos() {
        return data.getFamaceuticos();
    }

    public List<Farmaceutico> obtenerFarmaceuticos(String id, String nom) throws Exception {
        List<Farmaceutico> farmaceuticosEncontrados = new ArrayList<>();
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().contains(id) || m.getNombre().contains(id)) {
                farmaceuticosEncontrados.add(m);
            }
        }
        if (farmaceuticosEncontrados.isEmpty()) {
            throw new Exception("Farmacéutico no encontrado");
        }
        return farmaceuticosEncontrados;
    }

    public Farmaceutico obtenerFarmaceutico(String id, String nom) throws Exception {
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().equals(id) || m.getNombre().equals(nom)) {
                return m;
            }
        }
        throw new Exception("Farmacéutico no encontrado");
    }

    // UPDATE
    public void actualizarFarmaceutico(Farmaceutico act) throws Exception {
        for (int i = 0; i < data.getFamaceuticos().size(); i++) {
            if (data.getFamaceuticos().get(i).getId().equals(act.getId())) {
                data.getFamaceuticos().set(i, act);

                // Guardar en XML
                try {
                    XmlPersister.instance().store(data);
                    System.out.println("Farmacéutico actualizado y guardado en XML");
                } catch (Exception e) {
                    System.out.println("Error al guardar actualización:");
                    e.printStackTrace();
                }

                return;
            }
        }
        throw new Exception("Farmacéutico no encontrado para actualizar");
    }

    // DELETE
    public void eliminarFarmaceutico(String id, String nom) throws Exception {
        boolean eliminado = data.getFamaceuticos().removeIf(m -> m.getId().equals(id));
        if (!eliminado) {
            eliminado = data.getFamaceuticos().removeIf(m -> m.getNombre().equals(nom));
        }
        if (!eliminado) {
            throw new Exception("Farmacéutico no encontrado para eliminar");
        }

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Farmacéutico eliminado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación:");
            e.printStackTrace();
        }
    }

    //Trabajador / Admin
    // CREATE
    public void agregarTrabajador(Trabajador nuevo, String userId) throws Exception {
        for (Trabajador m : data.getTrabajadores()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un trabajador con ese ID");
            }
        }

        data.getTrabajadores().add(nuevo);

        if (nuevo instanceof Medico medico) {
            data.getDoctores().add(medico);
        } else if (nuevo instanceof Farmaceutico farma) {
            data.getFamaceuticos().add(farma);
        }

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Trabajador agregado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar trabajador:");
            e.printStackTrace();
        }
    }

    // READ
    public List<Trabajador> listarTrabajadores() {
        return data.getTrabajadores();
    }

    public List<Trabajador> obtenerTrabajador(String id) throws Exception {
        List<Trabajador> encontrados = new ArrayList<>();
        for (Trabajador m : data.getTrabajadores()) {
            if (m.getId().contains(id) || m.getNombre().contains(id)) {
                encontrados.add(m);
            }
        }
        if (encontrados.isEmpty()) {
            throw new Exception("Trabajador no encontrado");
        }
        return encontrados;
    }

    // UPDATE
    public void actualizarTrabajador(Trabajador act, String userId) throws Exception {
        for (int i = 0; i < data.getTrabajadores().size(); i++) {
            if (data.getTrabajadores().get(i).getId().equals(act.getId())) {
                data.getTrabajadores().set(i, act);

                // Guardar en XML
                try {
                    XmlPersister.instance().store(data);
                    System.out.println("Trabajador actualizado y guardado en XML");
                } catch (Exception e) {
                    System.out.println("Error al guardar actualización:");
                    e.printStackTrace();
                }

                return;
            }
        }
        throw new Exception("Trabajador no encontrado para actualizar");
    }

    // DELETE
    public void eliminarTrabajador(String id, String userId) throws Exception {
        boolean eliminado = data.getTrabajadores().removeIf(m -> m.getId().equals(id));

        if (!eliminado) {
            throw new Exception("Trabajador no encontrado para eliminar");
        }

        // También eliminar de subcategorías
        data.getDoctores().removeIf(m -> m.getId().equals(id));
        data.getFamaceuticos().removeIf(m -> m.getId().equals(id));

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Trabajador eliminado y guardado en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación:");
            e.printStackTrace();
        }
    }

    // CREATE
    public void agregarReceta(Receta receta) throws Exception {
        for (Receta r : data.getRecetas()) {
            if (r.equals(receta)) {
                throw new Exception("Ya se ha registrado esta receta");
            }
        }
        data.getRecetas().add(receta);

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Receta agregada y guardada en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar receta:");
            e.printStackTrace();
        }
    }

    // READ
    public List<Receta> listarRecetas() {
        return data.getRecetas();
    }

    public Receta obtenerReceta(Receta r) throws Exception {
        for (Receta re : data.getRecetas()) {
            boolean mismoPaciente = re.getPaciente().equals(r.getPaciente());
            boolean mismoDoctor = re.getDoctor().equals(r.getDoctor());
            boolean mismaFecha = re.getFechaConfeccion() != null && re.getFechaConfeccion().equals(r.getFechaConfeccion());

            if (mismoPaciente && mismoDoctor && mismaFecha) {
                return re;
            }
        }
        throw new Exception("Receta no encontrada");
    }

    // UPDATE
    public void actualizarReceta(Receta r) throws Exception {
        Receta original = obtenerReceta(r);
        original.setPrescripciones(r.getPrescripciones());
        original.setEstado(r.getEstado());
        // original.setFechaRetiro(r.getFechaRetiro()); // si querés incluirlo

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Receta actualizada y guardada en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar actualización:");
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminarReceta(Receta r) throws Exception {
        Receta borrado = obtenerReceta(r);
        data.getRecetas().remove(borrado);

        // Guardar en XML
        try {
            XmlPersister.instance().store(data);
            System.out.println("Receta eliminada y guardada en XML");
        } catch (Exception e) {
            System.out.println("Error al guardar eliminación:");
            e.printStackTrace();
        }
    }

    //más de recetas por dashboard
    public Map<String, Integer> cantidadPorMedicamentoYMes(String nombreMedicamento, LocalDate desde, LocalDate hasta) {
        Map<String, Integer> resultado = new LinkedHashMap<>();

        for (Receta r : data.getRecetas()) {
            LocalDate fecha = r.getFechaConfeccion();
            if (fecha != null && (fecha.isEqual(desde) || fecha.isAfter(desde)) && (fecha.isEqual(hasta) || fecha.isBefore(hasta))) {
                String claveMes = fecha.getYear() + "-" + fecha.getMonthValue(); // Ej: "2025-8"
                for (Prescripcion p : r.getPrescripciones()) {
                    if (p.getNombre().equalsIgnoreCase(nombreMedicamento)) {
                        resultado.put(claveMes, resultado.getOrDefault(claveMes, 0) + p.getCantidad());
                    }
                }
            }
        }

        return resultado;
    }

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
}