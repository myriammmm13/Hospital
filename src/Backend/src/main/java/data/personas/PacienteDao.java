package data.personas;

import data.DataBase;
import logic.personas.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    DataBase db;

    public PacienteDao(){
        db= DataBase.instance();
    }
    public void create(Paciente p) throws Exception{
        String sql="insert into Paciente (nombre, id, TelNum, FechaNacimiento) "+"values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(2, p.getId());
        stm.setString(3,p.getTelNum());
        stm.setString(4,p.getFechaNacimiento());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Paciente ya existe");
        }
    }

    public Paciente read(String id) throws Exception{
        String sql="select * from Paciente p "+
                "where p.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "p");
        }
        else{
            throw new Exception ("Paciente no Existe");
        }
    }

    public void update(Paciente p) throws Exception{
        String sql="update Paciente set nombre=?, TelNum=?, FechaNacimiento=? where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(3, p.getTelNum());
        stm.setString(4, p.getFechaNacimiento());
        stm.setString(2, p.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Paciente no existe");
        }
    }

    public void delete(Paciente o) throws Exception{
        String sql="delete from Paciente where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, o.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Paciente no existe");
        }
    }

    public List<Paciente> findByNombre(Paciente filtro){
        List<Paciente> resultado = new ArrayList<Paciente>();
        try {
            String sql="select * from Paciente p "+
                    "where p.nombre like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%"+filtro.getNombre()+"%");
            ResultSet rs =  db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs, "p"));
            }
        }catch (SQLException ex) { return null; }
        return resultado;
    }

    private Paciente from(ResultSet rs, String alias){
        try {
            Paciente p = new Paciente();
            p.setId(rs.getString(alias + ".id"));
            p.setNombre(rs.getString(alias + ".nombre"));
            p.setTelNum(rs.getString(alias + ".TelNum"));
            p.setFechaNacimiento(rs.getString(alias + ".FechaNacimiento"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
}