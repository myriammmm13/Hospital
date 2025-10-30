package presentation.login;
import logic.personas.Trabajador;

import javax.swing.*;
import java.util.function.BiConsumer;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model, BiConsumer<String, String> onLoginSuccess) {
        this.view = view;
        this.model = model;

        view.addLoginListener(e -> {
            String user = view.getUser();
            String pass = view.getPassword();

            if (model.validate(user, pass)) {
                String tipo = model.getUserType(user);
                view.dispose(); // Cierra login
                onLoginSuccess.accept(user, tipo); // Ejecuta callback
            } else {
                view.showMessage("Credenciales inválidas");
            }
        });

        view.addChangePasswordListener(e -> {
            String user = view.getUser();
            String pass = view.getPassword();

            if (model.validate(user, pass)) {
                ChangePasswordDialog dialog = new ChangePasswordDialog(view);
                dialog.getConfirmButton().addActionListener(ev -> {
                    String nueva1 = dialog.getNewPassword1();
                    String nueva2 = dialog.getNewPassword2();

                    if (!nueva1.equals(nueva2)) {
                        JOptionPane.showMessageDialog(dialog, "Las contraseñas no coinciden");
                        return;
                    }

                    for (Trabajador t : model.getData().getTrabajadores()) {
                        if (t.getId().equals(user)) {
                            t.setClave(nueva1);

                            try {
                                System.out.println("Contraseña actualizada y guardada en XML");
                            } catch (Exception exx) {
                                JOptionPane.showMessageDialog(dialog, "Error al guardar la nueva contraseña");
                                exx.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(dialog, "Contraseña actualizada");
                            dialog.dispose();
                            return;
                        }
                    }
                });

                dialog.setVisible(true);
            } else {
                view.showMessage("Usuario o contraseña incorrectos");
            }
        });

    }
}
