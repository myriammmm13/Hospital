package Hospital.data.medicamento;

import Hospital.data.DataBase;
import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Prescripcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescripcionDao {
    DataBase db;

    public PrescripcionDao() {
        db = DataBase.instance();
    }

    public void create(Prescripcion p) throws Exception {
        String sql = "insert into Prescripcion (medicamento_codigo, indicaciones, duracion, cantidad) values (?, ?, ?, ?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getCodigoMedicamento());
        stm.setString(2, p.getIndicaciones());
        stm.setInt(3, p.getDuracion());
        stm.setInt(4, p.getCantidad());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("No se pudo registrar la prescripción");
        }
    }

    public List<Prescripcion> findByMedicamento(String nombreMedicamento) {
        List<Prescripcion> resultado = new ArrayList<>();
        try {
            String sql = "select p.*, m.nombre, m.presentacion from Prescripcion p join Medicamento m on p.medicamento_codigo = m.codigo where m.nombre like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%" + nombreMedicamento + "%");
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs));
            }
        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }

    private Prescripcion from(ResultSet rs) {
        try {
            Medicamento m = new Medicamento();
            m.setCodigo(rs.getString("medicamento_codigo"));
            m.setNombre(rs.getString("nombre"));
            m.setPresentacion(rs.getString("presentacion"));

            String indicaciones = rs.getString("indicaciones");
            int duracion = rs.getInt("duracion");
            int cantidad = rs.getInt("cantidad");

            return new Prescripcion(m, indicaciones, duracion, cantidad);
        } catch (SQLException ex) {
            return null;
        }
    }
}
