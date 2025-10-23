package Hospital.backend.data;

import Hospital.backend.logic.Clave;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaveDao {
    DataBase db;

    public ClaveDao() {
        db = DataBase.instance();
    }
    public void create(Clave p) throws Exception{
        String sql="insert into Clave (clave) "+"values(?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getClave());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Clave ya existe");
        }
    }

    public Clave read(String clave) throws Exception{
        String sql="select * from Clave p "+
                "where p.clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, clave);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "p");
        }
        else{
            throw new Exception ("Clave no Existe");
        }
    }

    public void update(Clave p) throws Exception{
        String sql="update Clave set clave=? where clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getClave());
        stm.setString(2, p.getClave());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Clave no existe");
        }
    }

    public void delete(Clave o) throws Exception{
        String sql="delete from clave where clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, o.getClave());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Clave no existe");
        }
    }//revisar xd

    public List<Clave> findByNombre(Clave filtro){
        List<Clave> resultado = new ArrayList<Clave>();
        try {
            String sql="select * from Clave p "+
                    "where p.clave like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%"+filtro.getClave()+"%");
            ResultSet rs =  db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs, "p"));
            }
        }catch (SQLException ex) { return null; }
        return resultado;
    }

    private Clave from(ResultSet rs, String alias){
        try {
            Clave p = new Clave();
            p.setClave(rs.getString(alias + ".clave"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
}