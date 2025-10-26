package Hospital.logic;


import java.util.*;
import Hospital.data.ClaveDao;
import Hospital.data.DataBase;
import Hospital.data.personas.*;
import Hospital.data.medicamento.*;
import Hospital.logic.personas.Paciente;
import Hospital.logic.personas.trabajadores.Farmaceutico;
import Hospital.logic.personas.trabajadores.Medico;
import Hospital.logic.recetas.Prescripcion;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private PacienteDao pacienteDao;
    private MedicamentoDao medicamentoDao;
    private MedicoDao medicoDao;
    private FarmaceuticoDao farmaceuticoDao;
    private ClaveDao claveDao;
    private PrescripcionDao prescripcionDao;
    private RecetaDao recetaDao;

    private Service(){
        try{
            pacienteDao = new PacienteDao();
            medicamentoDao = new MedicamentoDao();
            //medicoDao = new MedicoDao();
            //farmaceuticoDao = new FarmaceuticoDao();
            prescripcionDao = new PrescripcionDao();
            recetaDao = new RecetaDao();
            //claveDao = new ClaveDao(); hay que hacerles la agregación de la BD
        }
        catch(Exception e){
            System.exit(-1);
        }
    }

    public void stop(){
        try {
            DataBase.instance().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // =============== PERSONAS ===============
    public void create(Paciente e) throws Exception {
        pacienteDao.create(e);
    }

    public Paciente read(Paciente e) throws Exception {
        return  pacienteDao.read(e.getId());
    }

    public List<Paciente> findAllPaciente() {
        Paciente filtro = new Paciente();
        filtro.setNombre("");
        return pacienteDao.findByNombre(filtro);
    }

    public void create(Medico e) throws Exception {
        medicoDao.create(e);
    }

    public Medico read(Medico e) throws Exception {
        return  medicoDao.read(e.getId());
    }

    public List<Medico> findAllMedico() {
        Medico filtro = new Medico();
        filtro.setNombre("");
        return medicoDao.findByNombre(filtro);
    }

    public void create(Farmaceutico e) throws Exception {
        farmaceuticoDao.create(e);
    }

    public Farmaceutico read(Farmaceutico e) throws Exception {
        return  farmaceuticoDao.read(e.getId());
    }

    public List<Farmaceutico> findAllFarmaceutico() {
        Farmaceutico filtro = new Farmaceutico();
        filtro.setNombre("");
        return farmaceuticoDao.findByNombre(filtro);
    }

    public void create(Medicamento e) throws Exception {
        medicamentoDao.create(e);
    }

    public Medicamento read(Medicamento e) throws Exception {
        return  medicamentoDao.read(e.getCodigo());
    }

    public List<Medicamento> findAllMedicamento() {
        Medicamento filtro = new Medicamento();
        filtro.setNombre("");
        return medicamentoDao.findByNombre(String.valueOf(filtro));
    }

    public void create(Prescripcion e) throws Exception {
        prescripcionDao.create(e);
    }

    public Prescripcion read(Prescripcion e) throws Exception {
        //return prescripcionDao.read(e.getIndicaciones());//hacer metodo
        return null;
    }

    public List<Prescripcion> findAllPrescripcion() {
        Prescripcion filtro = new Prescripcion();
        filtro.setPrescripcion(new Prescripcion());
        //return prescripcionDao.findByNombre(filtro); hacer método
        return null;
    }
}