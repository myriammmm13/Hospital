package presentation.login;

import presentation.dashboard.Controller;
import presentation.dashboard.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class View extends JFrame {
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);
    private JButton loginButton = new JButton();
    private JButton changePasswordButton = new JButton();
    private JButton cancelButton = new JButton();

    public View() {
        setTitle("Login Hospital");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Imagen decorativa arriba
        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon decorIcon = cargarIcono("/images/longin1.png", 50, 50);
        if (decorIcon != null) {
            iconLabel.setIcon(decorIcon);
        } else {
            iconLabel.setText("Imagen no encontrada");
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(iconLabel, gbc);

        // Usuario
        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("ID:");
        userLabel.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        userField.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(userField, gbc);

        // Contraseña
        gbc.gridwidth = 1;
        JLabel passLabel = new JLabel("CLAVE:");
        passLabel.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        passField.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(passField, gbc);

        // Botón login con ícono
        ImageIcon loginIcon = cargarIcono("/images/ingresar.png", 32, 32);
        if (loginIcon != null) loginButton.setIcon(loginIcon);
        loginButton.setToolTipText("Ingresar");
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);

        // Botón cambiar contraseña con ícono
        ImageIcon changeIcon = cargarIcono("/images/changepassword.png", 32, 32);
        if (changeIcon != null) changePasswordButton.setIcon(changeIcon);
        changePasswordButton.setToolTipText("Cambiar contraseña");
        changePasswordButton.setFocusPainted(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(changePasswordButton, gbc);

        add(panel);
    }

    // Método auxiliar para cargar y escalar íconos
    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image scaled = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return null;
    }

    public String getUser() {
        return userField.getText().trim();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addChangePasswordListener(ActionListener listener) {
        changePasswordButton.addActionListener(listener);
    }

    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void setController(Controller controller) {
    }

    public void setModel(Model model) {
    }
}