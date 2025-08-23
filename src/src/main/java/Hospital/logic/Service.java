package Hospital.logic;

import Hospital.data.Data;
import Hospital.logic.medicamentos.Medicamento;
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
            if (m.getCodigo().equals(id)) return m;
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




}