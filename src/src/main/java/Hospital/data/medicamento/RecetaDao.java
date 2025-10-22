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

            LocalDate confeccion = rs.getDate("fecha_confeccion") != null ? rs.getDate("fecha_confeccion").toLocalDate() : null;
            LocalDate retiro = rs.getDate("fecha_retiro") != null ? rs.getDate("fecha_retiro").toLocalDate() : null;
            String estado = rs.getString("estado");

            Receta receta = new Receta();
            receta.setId(String.valueOf(rs.getInt("id")));
            receta.setDoctor(doctor);
            receta.setPaciente(paciente);
            receta.setFechaConfeccion(confeccion);
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

    public List<Receta> findAll() {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs));
            }
        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }

    public String getId(Receta r) {
        try {
            String sql = "select id from Receta where doctor_id = ? and paciente_id = ? and fecha_confeccion = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, r.getDoctor().getId());
            stm.setString(2, r.getPaciente().getId());
            stm.setDate(3, Date.valueOf(r.getFechaConfeccion()));
            ResultSet rs = db.executeQuery(stm);
            if (rs.next()) {
                return String.valueOf(rs.getInt("id"));
            }
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

    public Receta read(String id) throws Exception {
        String sql = "select * from Receta where id = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(id));
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            throw new Exception("Receta no encontrada");
        }
    }

    public List<Receta> findByPacienteAndDoctor(Paciente paciente, Trabajador doctor, LocalDate fecha) {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta where paciente_id = ? and doctor_id = ? and fecha_confeccion = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, paciente.getId());
            stm.setString(2, doctor.getId());
            stm.setDate(3, Date.valueOf(fecha));
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs));
            }
        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }

    public void update(Receta r) throws Exception {
        try {
            String sql = "update Receta set estado = ?, fecha_retiro = ? where id = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, r.getEstado());
            stm.setDate(2, r.getFechaRetiro() != null ? Date.valueOf(r.getFechaRetiro()) : null);
            stm.setInt(3, Integer.parseInt(r.getId()));
            
            int count = db.executeUpdate(stm);
            if (count == 0) throw new Exception("Receta no encontrada");

            // Actualizar prescripciones
            sql = "delete from Prescripcion where receta_id = ?";
            stm = db.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(r.getId()));
            db.executeUpdate(stm);

            guardarPrescripciones(Integer.parseInt(r.getId()), r.getPrescripciones());
        } catch (SQLException ex) {
            throw new Exception("Error al actualizar la receta");
        }
    }

    public void delete(Receta r) throws Exception {
        try {
            // Primero eliminar prescripciones relacionadas
            String sql = "delete from Prescripcion where receta_id = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(r.getId()));
            db.executeUpdate(stm);

            // Luego eliminar la receta
            sql = "delete from Receta where id = ?";
            stm = db.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(r.getId()));
            int count = db.executeUpdate(stm);
            if (count == 0) throw new Exception("Receta no encontrada");
        } catch (SQLException ex) {
            throw new Exception("Error al eliminar la receta");
        }
    }

    public List<Receta> findByDateRange(LocalDate desde, LocalDate hasta) {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta where fecha_confeccion between ? and ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(desde));
            stm.setDate(2, Date.valueOf(hasta));
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs));
            }
        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }
}
