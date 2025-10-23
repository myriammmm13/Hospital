package Hospital.backend.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static final int PORT = 1234;
    private static Server theInstance;
    private List<Worker> workers;
    private ServerSocket serverSocket;
    
    public static Server instance() {
        if (theInstance == null) {
            theInstance = new Server();
        }
        return theInstance;
    }
    
    private Server() {
        workers = Collections.synchronizedList(new ArrayList<Worker>());
    }
    
    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en puerto " + PORT);
        
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + socket.getInetAddress());
            Worker worker = new Worker(socket);
            workers.add(worker);
            worker.start();
        }
    }
    
    public void broadcast(String message) {
        synchronized(workers) {
            workers.forEach(worker -> worker.send(message));
        }
    }
    
    public void remove(Worker worker) {
        workers.remove(worker);
    }
    
    class Worker extends Thread {
        Socket socket;
        // Agregar aquí los streams de entrada/salida
        
        public Worker(Socket socket) {
            this.socket = socket;
        }
        
        public void send(String message) {
            // Implementar envío de mensaje al cliente
        }
        
        @Override
        public void run() {
            try {
                // Implementar el ciclo de lectura de mensajes del cliente
                while (true) {
                    // Leer mensaje del cliente
                    // Procesar mensaje según el protocolo
                    // Enviar respuesta
                }
            } catch (Exception ex) {
                System.out.println("Error en worker: " + ex.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {}
                Server.instance().remove(this);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        Server.instance().start();
    }
}