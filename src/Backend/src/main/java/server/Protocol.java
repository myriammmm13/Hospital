package server;

public class Protocol {
    // Comandos del protocolo
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String MESSAGE = "MESSAGE";
    
    // Comandos para CRUD de entidades
    public static final String CREATE_MEDICO = "CREATE_MEDICO";
    public static final String READ_MEDICO = "READ_MEDICO";
    public static final String UPDATE_MEDICO = "UPDATE_MEDICO";
    public static final String DELETE_MEDICO = "DELETE_MEDICO";
    
    public static final String CREATE_PACIENTE = "CREATE_PACIENTE";
    public static final String READ_PACIENTE = "READ_PACIENTE";
    public static final String UPDATE_PACIENTE = "UPDATE_PACIENTE";
    public static final String DELETE_PACIENTE = "DELETE_PACIENTE";
    
    public static final String CREATE_RECETA = "CREATE_RECETA";
    public static final String READ_RECETA = "READ_RECETA";
    public static final String UPDATE_RECETA = "UPDATE_RECETA";
    public static final String DELETE_RECETA = "DELETE_RECETA";
    
    // Respuestas del servidor
    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";
    public static final String USER_CONNECTED = "USER_CONNECTED";
    public static final String USER_DISCONNECTED = "USER_DISCONNECTED";
    public static final String NEW_MESSAGE = "NEW_MESSAGE";
}