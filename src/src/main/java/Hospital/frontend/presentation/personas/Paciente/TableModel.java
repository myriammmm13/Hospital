package Hospital.frontend.presentation.personas.Paciente;
import Hospital.backend.logic.personas.Paciente;
import Hospital.frontend.presentation.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel<Paciente> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Paciente> rows) {super(cols, rows);}

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int NUMERO = 2;
    public static final int FECHA = 3;

    @Override
    protected void initColNames() {
        colNames = new String[4];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
        colNames[NUMERO] = "Número";
        colNames[FECHA] = "Fecha Nacimiento";

    }
    @Override
    protected Object getPropetyAt(Paciente e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case NUMERO:
                return e.getTelNum();
            case FECHA:
                return e.getFechaNacimiento();
            default:
                return "";
        }
    }
}