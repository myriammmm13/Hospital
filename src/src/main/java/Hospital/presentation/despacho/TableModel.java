package Hospital.presentation.despacho;

import Hospital.logic.recetas.Receta;
import Hospital.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Receta> implements javax.swing.table.TableModel{
    public TableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    public static final int PACIENTE = 0;
    public static final int DOCTOR = 1;
    public static final int PRESCRIPCIONES = 2;
    public static final int FECHA_CONFECCION = 3;
    public static final int ESTADO = 4;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[PACIENTE] = "Paciente";
        colNames[DOCTOR] = "Doctor";
        colNames[PRESCRIPCIONES] = "Prescripciones";
        colNames[FECHA_CONFECCION] = "Fecha de Confección";
        colNames[ESTADO] = "Estado";
    }

    public Receta getRecetaAt(int rowIndex) {
        return getRowAt(rowIndex);
    }

    @Override
    protected Object getPropetyAt(Receta e, int col) {
        switch (cols[col]) {
            case PACIENTE:
                return e.getPaciente().getNombre();
            case DOCTOR:
                return e.getDoctor().getNombre();
            case PRESCRIPCIONES:
                //return e.getCantidad();
                return 0;
            case FECHA_CONFECCION:
                //return e.getFecha_Confeccion();
                return 0;
            case ESTADO:
                return e.getEstado();
            default:
                return "";
        }
    }
}
