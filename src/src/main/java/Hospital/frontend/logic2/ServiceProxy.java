package Hospital.frontend.logic2;

import Hospital.backend.logic.Medicamento;
import Hospital.backend.server.Protocol;
import Hospital.backend.logic.personas.Paciente;
import Hospital.backend.logic.personas.trabajadores.Medico;
import Hospital.backend.logic.recetas.Receta;

import java.io.*;
import java.net.Socket;

public class ServiceProxy {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1234;
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public ServiceProxy() throws Exception {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            throw new Exception("Error conectando con el servidor");
        }
    }
    
    // Sobrescribir métodos del Service para comunicarse con el servidor

    public void agregarMedicamento(Medicamento nuevo, String userId) throws Exception {
        // Ejemplo de implementación
        try {
            out.writeObject(Protocol.CREATE_MEDICO);
            out.writeObject(nuevo);
            out.writeObject(userId);
            String response = (String) in.readObject();
            if (response.equals(Protocol.ERROR)) {
                throw new Exception((String) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new Exception("Error de comunicación con el servidor");
        }
    }
    
    // Implementar el resto de los métodos de Service
    
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {}
    }
}