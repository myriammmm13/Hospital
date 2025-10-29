package data.personas;

import Hospital.backend.data.DataBase;
import Hospital.backend.logic.personas.trabajadores.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MedicoDao {
    DataBase db;

    public MedicoDao() {
        db = DataBase.instance();
    }
    public void create(Medico p) throws Exception{
        String sql="insert into Medico (nombre, id, Especialidad) "+"values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(2, p.getId());
        stm.setString(3,p.getEspecialidad());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Medico ya existe");
        }
    }

    public Medico read(String id) throws Exception{
        String sql="select * from Medico p "+
                "where p.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "p");
        }
        else{
            throw new Exception ("Medico no Existe");
        }
    }

    public void update(Medico p) throws Exception{
        String sql="update Medico set nombre=?, Especialidad=? where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(3, p.getEspecialidad());
        stm.setString(2, p.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Medico no existe");
        }
    }

    public void delete(Medico o) throws Exception{
        String sql="delete from Medico where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, o.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Medico no existe");
        }
    }

    public List<Medico> findByNombre(Medico filtro){
        List<Medico> resultado = new ArrayList<Medico>();
        try {
            String sql="select * from Medico p "+
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

    private Medico from(ResultSet rs, String alias){
        try {
            Medico p = new Medico();
            p.setId(rs.getString(alias + ".id"));
            p.setNombre(rs.getString(alias + ".nombre"));
            p.setEspecialidad(rs.getString(alias + ".Especialidad"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
}