package Hospital.presentation.prescribir.buscarPaciente;

import Hospital.logic.personas.Paciente;
import Hospital.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Paciente> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Paciente> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int TELEFONO = 2;
    public static final int FEC_NAC = 3;

    @Override
    protected void initColNames() {
        colNames = new String[4];
        colNames[ID] = "ID";
        colNames[NOMBRE] = "Nombre";
        colNames[TELEFONO] = "Telefono";
        colNames[FEC_NAC] = "Fec Nacimiento";

    }
    @Override
    protected Object getPropetyAt(Paciente e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case TELEFONO:
                return e.getTelNum();
            case FEC_NAC:
                return e.getFechaNacimiento();
            default:
                return "";
        }
    }

}
