package Hospital.data;

public class TestConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Probando conexión a la base de datos...");
            DataBase db = DataBase.instance();
            if (db.getConnection() != null) {
                System.out.println("¡Conexión exitosa!");
                
                // Probar una consulta simple
                String sql = "SELECT 1";
                var stm = db.prepareStatement(sql);
                var rs = db.executeQuery(stm);
                if (rs != null && rs.next()) {
                    System.out.println("Consulta de prueba exitosa");
                }
                
                db.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}