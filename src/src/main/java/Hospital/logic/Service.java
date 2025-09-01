package Hospital.logic;

import Hospital.data.Data;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Administrador;
import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.recetas.Receta;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service Instance;

    public static Service instance() {
        if (Instance == null) Instance = new Service();
        return Instance;
    }

    private Data data;

    private Service() {
        data = new Data();
    }

    //Validaciones
    private void validarRol(String userId, String rolEsperado) throws Exception {
        if (!userId.startsWith(rolEsperado)) {
            throw new Exception("Acceso denegado: se requiere rol " + rolEsperado);
        }
    }

    //CRUD Medicamentos
    //CREATE
    public void agregarMedicamento(Medicamento nuevo, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (Medicamento m : data.getMedicamentos()) {
            if (m.getCodigo().equals(nuevo.getCodigo())) {
                throw new Exception("Ya existe un medicamento con ese ID");
            }
        }
        data.getMedicamentos().add(nuevo);
    }

    //READ
    public List<Medicamento> listarMedicamentos() {
        return data.getMedicamentos();
    }

    public List<Medicamento> obtenerMedicamento(String cod) throws Exception {
        List<Medicamento> medicamentosEncontrados = new ArrayList<>();
        for (Medicamento m : data.getMedicamentos()) {
            if (m.getCodigo().contains(cod) || m.getNombre().contains(cod))
                medicamentosEncontrados.add(m);
        }
        if (medicamentosEncontrados.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }
        return medicamentosEncontrados;
    }

    //UPDATE
    public void actualizarMedicamento(Medicamento actualizado, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (int i = 0; i < data.getMedicamentos().size(); i++) {
            if (data.getMedicamentos().get(i).getCodigo().equals(actualizado.getCodigo())) {
                data.getMedicamentos().set(i, actualizado);
                return;
            }
        }
        throw new Exception("Medicamento no encontrado para actualizar");
    }

    //DELETE
    public void eliminarMedicamento(String cod, String userId) throws Exception {
        validarRol(userId, "ADM");
        boolean eliminado = data.getMedicamentos().removeIf(m -> m.getCodigo().equals(cod));
        if (!eliminado) throw new Exception("Medicamento no encontrado para eliminar");
    }


    //Paciente
    //Create
    public void agregarPaciente(Paciente nuevo, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (Paciente m : data.getPacientes()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un paciente con ese ID");
            }
        }
        data.getPacientes().add(nuevo);
    }

    //Read
    public List<Paciente> listarPacientes() {
        return data.getPacientes();
    }

    public List<Paciente> obtenerPaciente(String id) throws Exception {
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

    //Update
    public void actualizarPaciente(Paciente act, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (int i = 0; i < data.getPacientes().size(); i++) {
            if (data.getPacientes().get(i).getId().equals(act.getId())) {
                data.getPacientes().set(i, act);
                return;
            }
        }
        throw new Exception("Paciente no encontrado para actualizar");
    }

    //Delete
    public void eliminarPaciente(String id, String userId) throws Exception {
        validarRol(userId, "ADM");
        boolean eliminado = data.getPacientes().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Paciente no encontrado para eliminar");
    }

    //Doctor
    //Create
    public void agregarDoctor(Doctor nuevo, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (Doctor m : data.getDoctores()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un doctor con ese ID");
            }
        }
        data.getDoctores().add(nuevo);
    }

    //Read
    public List<Doctor> listarDoctores() {
        return data.getDoctores();
    }

    public List<Doctor> obtenerDoctor(String id) throws Exception {
        List<Doctor> doctoresEncontrados = new ArrayList<>();
        for (Doctor m : data.getDoctores()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                doctoresEncontrados.add(m);
        }
        if (doctoresEncontrados.isEmpty()) {
            throw new Exception("Doctor no encontrado");
        }
        return doctoresEncontrados;
    }

    //Update
    public void actualizarDoctor(Doctor act, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (int i = 0; i < data.getPacientes().size(); i++) {
            if (data.getDoctores().get(i).getId().equals(act.getId())) {
                data.getDoctores().set(i, act);
                return;
            }
        }
        throw new Exception("Doctor no encontrado para actualizar");
    }

    //Delete
    public void eliminarDoctor(String id, String userId) throws Exception {
        validarRol(userId, "ADM");
        boolean eliminado = data.getDoctores().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Doctor no encontrado para eliminar");
    }

    //Farma
    //Create
    public void agregarFarmaceutico(Farmaceutico nuevo, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un farmaceutico con ese ID");
            }
        }
        data.getFamaceuticos().add(nuevo);
    }

    //Read
    public List<Farmaceutico> listarFarmaceuticos() {
        return data.getFamaceuticos();
    }

    public List<Farmaceutico> obtenerFarmaceutico(String id) throws Exception {
        List<Farmaceutico> farmaceuticosEncontrados = new ArrayList<>();
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                farmaceuticosEncontrados.add(m);
        }
        if (farmaceuticosEncontrados.isEmpty()) {
            throw new Exception("Farmacuetico no encontrado");
        }
        return farmaceuticosEncontrados;
    }

    //Update
    public void actualizarFarmaceutico(Farmaceutico act, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (int i = 0; i < data.getFamaceuticos().size(); i++) {
            if (data.getFamaceuticos().get(i).getId().equals(act.getId())) {
                data.getFamaceuticos().set(i, act);
                return;
            }
        }
        throw new Exception("Farmaceutico no encontrado para actualizar");
    }

    //Delete
    public void eliminarFarmaceutico(String id, String userId) throws Exception {
        validarRol(userId, "ADM");
        boolean eliminado = data.getFamaceuticos().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Farmaceutico no encontrado para eliminar");
    }

    //Admin
    //Create
    public void agregarAdmin(Administrador nuevo, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (Administrador m : data.getAdmin()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un administrador con ese ID");
            }
        }
        data.getAdmin().add(nuevo);
    }

    //Read
    public List<Administrador> listarAdmins() {
        return data.getAdmin();
    }

    public List<Administrador> obtenerAdmin(String id) throws Exception {
        List<Administrador> adminsEncontrados = new ArrayList<>();
        for (Administrador m : data.getAdmin()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                adminsEncontrados.add(m);
        }
        if (adminsEncontrados.isEmpty()) {
            throw new Exception("Administrador no encontrado");
        }
        return adminsEncontrados;
    }

    //Update
    public void actualizarAdmin(Administrador act, String userId) throws Exception {
        validarRol(userId, "ADM");
        for (int i = 0; i < data.getAdmin().size(); i++) {
            if (data.getAdmin().get(i).getId().equals(act.getId())) {
                data.getAdmin().set(i, act);
                return;
            }
        }
        throw new Exception("Administrador no encontrado para actualizar");
    }

    //Delete
    public void eliminarAdmin(String id, String userId) throws Exception {
        validarRol(userId, "ADM");
        boolean eliminado = data.getAdmin().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Administrador no encontrado para eliminar");
    }

    //create recetas
    public void agregarReceta(Receta receta, String userId) throws Exception {
        validarRol(userId,"MED");
        for (Receta r : data.getRecetas()) {
            if (r.equals(receta)) {
                throw new Exception("Ya se ha registrado esta receta");
            }
        }
        data.getRecetas().add(receta);
    }

    //read recetas
    public List<Receta> listarRecetas() {
        return data.getRecetas();
    }

    public Receta obtenerReceta(Receta r) throws Exception {
        for (Receta re : data.getRecetas()) {
            if (re.getPaciente().equals(r.getPaciente()) && re.getDoctor().equals(r.getDoctor()) &&
                    re.getFechaConfeccion().equals(r.getFechaConfeccion())){
                return re;
            }
        }
        throw new Exception("Receta no encontrada");
    }

    //update recetas
    public void actualizarReceta(Receta r) throws Exception {
        Receta original = obtenerReceta(r);
        original.setPrescripciones(r.getPrescripciones());
        original.setFechaRetiro(r.getFechaRetiro());
        original.setEstado(r.getEstado());
    }

    //delete recetas
    public void eliminarReceta(Receta r) throws Exception {
        Receta borrado = obtenerReceta(r);
        data.getRecetas().remove(borrado);
    }
}
