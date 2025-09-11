package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.presentation.AbstractTableModel;

import java.util.Calendar;
import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int PRESENTACION = 2;

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
        colNames[PRESENTACION] = "Presentacion";
    }

    @Override
    protected Object getPropetyAt(Medicamento m, int col) {
        switch (cols[col]) {
            case ID:
                return m.getCodigo();
            case NOMBRE:
                return m.getNombre();
            case PRESENTACION:
                return m.getPresentacion();
            default:
                return "";
        }
    }

}