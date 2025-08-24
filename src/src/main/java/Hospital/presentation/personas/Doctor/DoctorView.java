package Hospital.presentation.personas.Doctor;

import Hospital.logic.personas.trabajadores.Doctor;
//import Hospital.presentation.Highlighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DoctorView implements PropertyChangeListener {
    public DoctorView(){
//aquí van todos los botones y demás:p
    }


    //---------MVC-----------
    DoctorController controller;
    DoctorModel model;

    public void setController(DoctorController controller) {
        this.controller = controller;
    }

    public void setModel(DoctorModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        return;
         switch (evt.getPropertyName()) {
            case model.CURRENT:
                //aquí le voy poniendo las cosas de set...
                break;
        }
    }
}
