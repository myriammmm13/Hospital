package presentation.personas.Medico;

import logic.personas.trabajadores.Medico;
import presentation.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel<Medico> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Medico> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int ESPECIALIDAD = 2;
    public static final int CLAVE = 3;

    @Override
    protected void initColNames() {
        colNames = new String[4];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
        colNames[ESPECIALIDAD] = "Especialidad";
        colNames[CLAVE] = "Clave";
    }
    @Override
    protected Object getPropetyAt(Medico e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case ESPECIALIDAD:
                return e.getEspecialidad();
                case CLAVE:
                    return e.getClave();
            default:
                return "";
        }
    }

}
