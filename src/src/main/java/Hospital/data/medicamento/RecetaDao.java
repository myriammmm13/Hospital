package Hospital.data.medicamento;

import Hospital.data.DataBase;
import Hospital.logic.Medicamento;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.Trabajador;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecetaDao {
    DataBase db;

    public RecetaDao() {
        db = DataBase.instance();
    }

    public void create(Receta r) throws Exception {
        String sql = "insert into Receta (doctor_id, paciente_id, fecha_confeccion, fecha_retiro, estado) values (?, ?, ?, ?, ?)";
        PreparedStatement stm = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, r.getDoctor().getId());
        stm.setString(2, r.getPaciente().getId());
        stm.setDate(3, Date.valueOf(r.getFechaConfeccion()));
        stm.setDate(4, Date.valueOf(r.getFechaRetiro()));
        stm.setString(5, r.getEstado());

        int count = db.executeUpdate(stm);
        if (count == 0) throw new Exception("No se pudo registrar la receta");

        ResultSet rs = stm.getGeneratedKeys();
        if (rs.next()) {
            int recetaId = rs.getInt(1);
            guardarPrescripciones(recetaId, r.getPrescripciones());
        }
    }

    private void guardarPrescripciones(int recetaId, List<Prescripcion> prescripciones) throws Exception {
        String sql = "insert into Prescripcion (medicamento_codigo, indicaciones, duracion, cantidad, receta_id) values (?, ?, ?, ?, ?)";
        for (Prescripcion p : prescripciones) {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, p.getCodigoMedicamento());
            stm.setString(2, p.getIndicaciones());
            stm.setInt(3, p.getDuracion());
            stm.setInt(4, p.getCantidad());
            stm.setInt(5, recetaId);
            db.executeUpdate(stm);
        }
    }

    public List<Receta> findByPaciente(String pacienteId) {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta where paciente_id = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, pacienteId);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs));
            }
        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }

    private Receta from(ResultSet rs) {
        try {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getString("paciente_id"));

            Trabajador doctor = new Trabajador();
            doctor.setId(rs.getString("doctor_id"));

            LocalDate confeccion = rs.getDate("fecha_confeccion").toLocalDate();
            LocalDate retiro = rs.getDate("fecha_retiro").toLocalDate();
            String estado = rs.getString("estado");

            Receta receta = new Receta();
            receta.setDoctor(doctor);
            receta.setPaciente(paciente);
            receta.setFechaRetiro(retiro);
            receta.setEstado(estado);
            receta.setPrescripciones(buscarPrescripciones(rs.getInt("id")));

            return receta;
        } catch (SQLException ex) {
            return null;
        }
    }

    private List<Prescripcion> buscarPrescripciones(int recetaId) {
        List<Prescripcion> lista = new ArrayList<>();
        try {
            String sql = "select p.*, m.nombre, m.presentacion from Prescripcion p join Medicamento m on p.medicamento_codigo = m.codigo where p.receta_id = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, recetaId);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setCodigo(rs.getString("medicamento_codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setPresentacion(rs.getString("presentacion"));

                Prescripcion p = new Prescripcion(
                        m,
                        rs.getString("indicaciones"),
                        rs.getInt("duracion"),
                        rs.getInt("cantidad")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            return null;
        }
        return lista;
    }
}
