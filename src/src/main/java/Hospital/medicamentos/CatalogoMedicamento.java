package Hospital.medicamentos;

import Hospital.personas.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CatalogoMedicamento {
    private List<Medicamento> medicamentos = new ArrayList<>();

    public void agregar(Medicamento m) {
        medicamentos.add(m);
    }

    public Medicamento buscarPorCodigo(String codigo) {
        for (Medicamento m : medicamentos) {
            if (m.getCodigo().equals(codigo)) return m;
        }
        return null;
    }

    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentos.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarPorPrefijo(String prefijo) { //para la parte gráfica cuando la persona busca los medicamentos que salga por letras
        List<Medicamento> resultados = new ArrayList<>();

        for (Medicamento med : medicamentos) {
            if (med.getNombre().toLowerCase().startsWith(prefijo.toLowerCase())) {
                resultados.add(med);
            }
        }

        return resultados;
    }

    public boolean modificarMedicamento(String codigo, String nuevoNombre, String nuevaPresentacion, Persona identificador) {
        if (! Objects.equals( identificador.getTipoUsuario(), "ADMIN"))
            return false;

        Medicamento m = buscarPorCodigo(codigo);
        if (m != null) {
            m.setNombre(nuevoNombre);
            m.setPresentacion(nuevaPresentacion);
            return true;
        }
        return false;
    }

    public boolean eliminarMedicamento(String codigo, Persona  identificador) {
        if (! Objects.equals( identificador.getTipoUsuario(), "ADMIN"))
            return false;

        Medicamento m = buscarPorCodigo(codigo);
        if (m != null) {
            medicamentos.remove(m);
            return true;
        }
        return false;
    }

}
