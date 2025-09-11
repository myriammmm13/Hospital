package Hospital.presentation.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    JTextField userField = new JTextField(15);
    JPasswordField passField = new JPasswordField(15);
    JButton loginButton = new JButton("Ingresar");
    JButton changePasswordButton = new JButton("Cambiar contraseña");

    public View() {
        setTitle("Login Hospital");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        panel.add(userField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passField);
        panel.add(new JLabel(""));
        panel.add(loginButton);
        //panel.add(changePasswordButton);

        add(panel);
    }

    public String getUser() {
        return userField.getText();
    }
    public String getPassword() {
        return new String(passField.getPassword());
    }
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void addChangePasswordListener(ActionListener listener) {
        changePasswordButton.addActionListener(listener);
    }

}
