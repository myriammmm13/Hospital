package Hospital.presentation.login;
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
    }
}
