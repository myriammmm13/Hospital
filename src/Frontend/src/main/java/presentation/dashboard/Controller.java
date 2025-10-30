package presentation.dashboard;

import logic.Medicamento;
import data.Data;

import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view, Data data) {
        this.model = model;
        this.view = view;

        List<Medicamento> listaDeMedicamentos = data.getMedicamentos();
        model.setMedicamentos(listaDeMedicamentos);

        view.setController(this);
        view.setModel(model);
    }
}