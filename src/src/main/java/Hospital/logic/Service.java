package Hospital.logic;

import Hospital.data.Data;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.personas.trabajadores.Medico;
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
    public void agregarMedicamento(Medicamento nuevo) throws Exception {
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
    public void actualizarMedicamento(Medicamento actualizado) throws Exception {
        //validarRol(userId, "ADM");
        for (int i = 0; i < data.getMedicamentos().size(); i++) {
            if (data.getMedicamentos().get(i).getCodigo().equals(actualizado.getCodigo())) {
                data.getMedicamentos().set(i, actualizado);
                return;
            }
        }
        throw new Exception("Medicamento no encontrado para actualizar");
    }

    //DELETE
    public void eliminarMedicamento(String cod) throws Exception {
        //validarRol(userId, "ADM");
        boolean eliminado = data.getMedicamentos().removeIf(m -> m.getCodigo().equals(cod));
        if (!eliminado) throw new Exception("Medicamento no encontrado para eliminar");
    }


    //Paciente
    //Create
    public void agregarPaciente(Paciente nuevo) throws Exception {
        //validarRol(userId, "ADM");
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
    public void actualizarPaciente(Paciente act) throws Exception {
        //validarRol(userId, "ADM");
        for (int i = 0; i < data.getPacientes().size(); i++) {
            if (data.getPacientes().get(i).getId().equals(act.getId())) {
                data.getPacientes().set(i, act);
                return;
            }
        }
        throw new Exception("Paciente no encontrado para actualizar");
    }

    //Delete
    public void eliminarPaciente(String id) throws Exception {
        //validarRol(userId, "ADM");
        boolean eliminado = data.getPacientes().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Paciente no encontrado para eliminar");
    }

    //Doctor
    //Create
    public void agregarDoctor(Medico nuevo) throws Exception {
        //validarRol(userId, "ADM");
        for (Medico m : data.getDoctores()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un doctor con ese ID");
            }
        }
        data.getDoctores().add(nuevo);
    }

    //Read
    public List<Medico> listarDoctores() {
        return data.getDoctores();
    }

    public List<Medico> obtenerDoctor(String id) throws Exception {
        List<Medico> doctoresEncontrados = new ArrayList<>();
        for (Medico m : data.getDoctores()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                doctoresEncontrados.add(m);
        }
        if (doctoresEncontrados.isEmpty()) {
            throw new Exception("Doctor no encontrado");
        }
        return doctoresEncontrados;
    }

    //Update
    public void actualizarDoctor(Medico act) throws Exception {
        //validarRol(userId, "ADM");
        for (int i = 0; i < data.getPacientes().size(); i++) {
            if (data.getDoctores().get(i).getId().equals(act.getId())) {
                data.getDoctores().set(i, act);
                return;
            }
        }
        throw new Exception("Doctor no encontrado para actualizar");
    }

    //Delete
    public void eliminarDoctor(String id) throws Exception {
        //validarRol(userId, "ADM");
        boolean eliminado = data.getDoctores().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Doctor no encontrado para eliminar");
    }

    //Farma
    //Create
    public void agregarFarmaceutico(Farmaceutico nuevo) throws Exception {
        //validarRol(userId, "ADM");
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
    public void actualizarFarmaceutico(Farmaceutico act) throws Exception {
        //validarRol(userId, "ADM");
        for (int i = 0; i < data.getFamaceuticos().size(); i++) {
            if (data.getFamaceuticos().get(i).getId().equals(act.getId())) {
                data.getFamaceuticos().set(i, act);
                return;
            }
        }
        throw new Exception("Farmaceutico no encontrado para actualizar");
    }

    //Delete
    public void eliminarFarmaceutico(String id) throws Exception {
        //validarRol(userId, "ADM");
        boolean eliminado = data.getFamaceuticos().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Farmaceutico no encontrado para eliminar");
    }

    //Trabajador / Admin
    //Create
    public void agregarTrabajador(Trabajador nuevo) throws Exception {
        //validarRol(userId, "ADM");
        for (Trabajador m : data.getTrabajadores()) {
            if (m.getId().equals(nuevo.getId())) {
                throw new Exception("Ya existe un Trabajador con ese ID");
            }
        }
        data.getTrabajadores().add(nuevo);
        if (nuevo instanceof Medico)
            data.getDoctores().add((Medico) nuevo);
        else if(nuevo instanceof Farmaceutico)
            data.getFamaceuticos().add((Farmaceutico) nuevo);
    }

    //Read
    public List<Trabajador> listarTrabajadores() {
        return data.getTrabajadores();
    }

    public List<Trabajador> obtenerTrabajador(String id) throws Exception {
        List<Trabajador> trabajadoresEncontrados = new ArrayList<>();
        for (Trabajador m : data.getTrabajadores()) {
            if (m.getId().contains(id) || m.getNombre().contains(id))
                trabajadoresEncontrados.add(m);
        }
        if (trabajadoresEncontrados.isEmpty()) {
            throw new Exception("Trabajador no encontrado");
        }
        return trabajadoresEncontrados;
    }

    //Update
    public void actualizarTrabajador(Trabajador act) throws Exception {
        //validarRol(userId, "ADM");
        for (int i = 0; i < data.getTrabajadores().size(); i++) {
            if (data.getTrabajadores().get(i).getId().equals(act.getId())) {
                data.getTrabajadores().set(i, act);
                return;
            }
        }
        throw new Exception("Trabajador no encontrado para actualizar");
    }

    //Delete
    public void eliminarTrabajador(String id) throws Exception {
        //validarRol(userId, "ADM");
        boolean eliminado = data.getTrabajadores().removeIf(m -> m.getId().equals(id));
        if (!eliminado) throw new Exception("Trabajador no encontrado para eliminar");
        else {
            eliminarFarmaceutico(id);
            eliminarDoctor(id);
        }//se llama para también eliminarlo a la categoría en la que se creó
    }//revisar o cambiar, funciona pero no me convence
    //create recetas
    public void agregarReceta(Receta receta) throws Exception {
        //validarRol(userId,"MED");
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
            if (re.getPaciente().equals(r.getPaciente()) && re.getDoctor().equals(r.getDoctor()) /*&&
                    re.getFechaConfeccion().equals(r.getFechaConfeccion())*/){
                return re;
            }
        }
        throw new Exception("Receta no encontrada");
    }

    //update recetas
    public void actualizarReceta(Receta r) throws Exception {
        Receta original = obtenerReceta(r);
        original.setPrescripciones(r.getPrescripciones());
        //original.setFechaRetiro(r.getFechaRetiro());
        original.setEstado(r.getEstado());
    }

    //delete recetas
    public void eliminarReceta(Receta r) throws Exception {
        Receta borrado = obtenerReceta(r);
        data.getRecetas().remove(borrado);
    }


}
