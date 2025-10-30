package data.personas;

import data.DataBase;
import logic.personas.trabajadores.Farmaceutico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmaceuticoDao {
    DataBase db;

    public FarmaceuticoDao() {
        db = DataBase.instance();
    }
    public void create(Farmaceutico p) throws Exception{
        String sql="insert into Farmaceutico (nombre, id) "+"values(?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(2, p.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Farmaceutico ya existe");
        }
    }

    public Farmaceutico read(String id) throws Exception{
        String sql="select * from Farmaceutico p "+
                "where p.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "p");
        }
        else{
            throw new Exception ("Farmaceutico no Existe");
        }
    }

    public void update(Farmaceutico p) throws Exception{
        String sql="update Farmaceutico set nombre=? where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(2, p.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Farmaceutico no existe");
        }
    }

    public void delete(Farmaceutico o) throws Exception{
        String sql="delete from Farmaceutico where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, o.getId());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Farmaceutico no existe");
        }
    }

    public List<Farmaceutico> findByNombre(Farmaceutico filtro){
        List<Farmaceutico> resultado = new ArrayList<Farmaceutico>();
        try {
            String sql="select * from Farmaceutico p "+
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

    private Farmaceutico from(ResultSet rs, String alias){
        try {
            Farmaceutico p = new Farmaceutico();
            p.setId(rs.getString(alias + ".id"));
            p.setNombre(rs.getString(alias + ".nombre"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
}