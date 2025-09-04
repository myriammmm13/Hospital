package Hospital;

import Hospital.presentation.personas.Doctor.Controller;
import Hospital.presentation.personas.Doctor.Model;
import Hospital.presentation.personas.Doctor.View;
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
        String cod=new String();
        //hacer todo el proceso del login
        switch (cod[:])
        //solo tengo esto para probar, luego se debe hacer el login e iniciar con eso :)
        Hospital.presentation.personas.Administrador.View view = new Hospital.presentation.personas.Administrador.View();
        Hospital.presentation.personas.Administrador.Model model = new Hospital.presentation.personas.Administrador.Model();
        Hospital.presentation.personas.Administrador.Controller controller = new Hospital.presentation.personas.Administrador.Controller(model, view);

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Login");//se pone el cod de la persona que ingresa
        window.setContentPane(view.getPanel()); //se le debe agregar cada tab por cada caso
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Hospital.presentation.personas.Doctor.View view = new Hospital.presentation.personas.Doctor.View();
        Hospital.presentation.personas.Doctor.Model model = new Hospital.presentation.personas.Doctor.Model();
        Hospital.presentation.personas.Doctor.Controller controller = new Hospital.presentation.personas.Doctor.Controller(model, view);

    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}