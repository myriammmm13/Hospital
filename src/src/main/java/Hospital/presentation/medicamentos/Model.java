package Hospital.presentation.medicamentos;

import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Medicamento current;
    List<Medicamento> medicamentos;
    public static final String CURRENT = "current";
    public static final String LIST = "list";


    public Model() {
        current = new Medicamento();
        medicamentos = new ArrayList<Medicamento>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT, null, current);
        firePropertyChange(LIST);

    }

    public Medicamento getCurrent() {
        return current;
    }

    public void setCurrent(Medicamento m) {
        this.current = m;
        firePropertyChange(CURRENT, null, current);
        firePropertyChange(LIST, null, medicamentos);
    }

    public List<Medicamento> getList() {
        return medicamentos;
    }
    public void setList(List<Medicamento> list) {
        medicamentos = list;
        firePropertyChange(LIST);
    }
}