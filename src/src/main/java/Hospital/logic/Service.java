package Hospital.logic;

import Hospital.data.Data;
import Hospital.logic.medicamentos.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Doctor;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.recetas.Receta;

import java.util.List;

//recreado del código del profe
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
        validarRol(userId,"ADM");
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

    public Medicamento obtenerMedicamento(String id) throws Exception {
        for (Medicamento m : data.getMedicamentos()) {
            if (m.getCodigo().contains(id) || m.getNombre().contains(id)) return m;
        }
        throw new Exception("Medicamento no encontrado");
    }
    //UPDATE
    public void actualizarMedicamento(Medicamento actualizado, String userId) throws Exception {
        validarRol(userId,"ADM");
        for (int i = 0; i < data.getMedicamentos().size(); i++) {
            if (data.getMedicamentos().get(i).getCodigo().equals(actualizado.getCodigo())) {
                data.getMedicamentos().set(i, actualizado);
                return;
            }
        }
        throw new Exception("Medicamento no encontrado para actualizar");
    }
    //DELETE
    public void eliminarMedicamento(String id, String userId) throws Exception {
        validarRol(userId,"ADM");
        boolean eliminado = data.getMedicamentos().removeIf(m -> m.getCodigo().equals(id));
        if (!eliminado) throw new Exception("Medicamento no encontrado para eliminar");
    }


    //Paciente
    //Create
    public void agregarPaciente(Paciente nuevo, String userId) throws Exception {
        validarRol(userId,"ADM");
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

    public Paciente obtenerPaciente(String id) throws Exception {
        for (Paciente m : data.getPacientes()) {
            if (m.getId().contains(id) ||  m.getNombre().contains(id) || m.getApellido1().contains(id) || m.getApellido2().contains(id))
                return m;
        }
        throw new Exception("Paciente no encontrado");
    }
    //Update
    public void actualizarPaciente(Paciente act, String userId) throws Exception {
        validarRol(userId,"ADM");
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
        validarRol(userId,"ADM");
        boolean eliminado = data.getPacientes().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Paciente no encontrado para eliminar");
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

    //Doctor
    //Create
    public void agregarDoctor(Doctor nuevo, String userId) throws Exception {
        validarRol(userId,"ADM");
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

    public Doctor obtenerDoctor(String id) throws Exception {
        for (Doctor m : data.getDoctores()) {
            if (m.getId().contains(id) ||  m.getNombre().contains(id) || m.getApellido1().contains(id) || m.getApellido2().contains(id))
                return m;
        }
        throw new Exception("Doctor no encontrado");
    }

    //Update
    public void actualizarDoctor(Doctor act, String userId) throws Exception {
        validarRol(userId,"ADM");
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
        validarRol(userId,"ADM");
        boolean eliminado = data.getDoctores().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Doctor no encontrado para eliminar");
    }

    //Farma
    //Create
    public void agregarFarmaceutico(Farmaceutico nuevo, String userId) throws Exception {
        validarRol(userId,"ADM");
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

    public Farmaceutico obtenerFarmaceutico(String id) throws Exception {
        for (Farmaceutico m : data.getFamaceuticos()) {
            if (m.getId().contains(id) ||  m.getNombre().contains(id) || m.getApellido1().contains(id) || m.getApellido2().contains(id))
                return m;
        }
        throw new Exception("Farmaceutico no encontrado");
    }

    //Update
    public void actualizarFarmaceutico(Farmaceutico act, String userId) throws Exception {
        validarRol(userId,"ADM");
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
        validarRol(userId,"ADM");
        boolean eliminado = data.getFamaceuticos().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Farmaceutico no encontrado para eliminar");
    }
}
