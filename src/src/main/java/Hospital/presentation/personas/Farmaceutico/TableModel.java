package Hospital.presentation.personas.Farmaceutico;

import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.presentation.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel<Farmaceutico> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Farmaceutico> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int CLAVE = 2;
    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
        colNames[CLAVE] = "Clave";
    }
    @Override
    protected Object getPropetyAt(Farmaceutico e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case CLAVE:
                return e.getClave();
            default:
                return "";
        }
    }

}