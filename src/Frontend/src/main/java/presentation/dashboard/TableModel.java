package presentation.dashboard;

import Hospital.backend.logic.Medicamento;
import presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {

    public static final int MEDICAMENTO = 0;
    public static final int DESDE = 1;
    public static final int HASTA = 2;

    private String fechaDesde;
    private String fechaHasta;

    public TableModel(int[] cols, List<Medicamento> rows, String fechaDesde, String fechaHasta) {
        super(cols, rows);
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[MEDICAMENTO] = "Medicamento";
        colNames[DESDE] = "Desde";
        colNames[HASTA] = "Hasta";
    }

    @Override
    protected Object getPropetyAt(Medicamento m, int col) {
        switch (cols[col]) {
            case MEDICAMENTO:
                return m.getNombre();
            case DESDE:
                return fechaDesde;
            case HASTA:
                return fechaHasta;
            default:
                return "";
        }
    }
}