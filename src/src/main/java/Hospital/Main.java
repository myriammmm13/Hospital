package Hospital;
/*
import Hospital.presentation.personas.Doctor.Controller;
import Hospital.presentation.personas.Doctor.Model;
import Hospital.presentation.personas.Doctor.View;*/
import Hospital.presentation.personas.Administrador.Controller;
import Hospital.presentation.personas.Administrador.Model;
import Hospital.presentation.personas.Administrador.View;
/*import Hospital.presentation.personas.Farmaceutico.Controller;
import Hospital.presentation.personas.Farmaceutico.Model;
import Hospital.presentation.personas.Farmaceutico.View;
*/
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;public class Main {
    public static void main(String[] args) {
        try {UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        //solo tengo esto para probar, luego se debe hacer el login e iniciar con eso :)
        Hospital.presentation.personas.Administrador.View view = new Hospital.presentation.personas.Administrador.View();
        Hospital.presentation.personas.Administrador.Model model = new Hospital.presentation.personas.Administrador.Model();
        Hospital.presentation.personas.Administrador.Controller controller = new Hospital.presentation.personas.Administrador.Controller(model, view);

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Login");
        window.setContentPane(view.getPanel());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}