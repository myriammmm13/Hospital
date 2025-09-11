package Hospital.presentation.dashboard;

import Hospital.logic.Service;
import Hospital.presentation.dashboard.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = new Model();
        this.view = view;

        view.setController(this);
        view.setModel(model);
    }


}