package Hospital.presentation.login;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordDialog extends JDialog {
    private final JPasswordField newPassField1 = new JPasswordField(15);
    private final JPasswordField newPassField2 = new JPasswordField(15);
    private final JButton confirmButton = new JButton("Confirmar");

    public ChangePasswordDialog(JFrame parent) {
        super(parent, "Cambiar contraseña", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Estilo visual
        Color fondo = new Color(245, 245, 255);
        Color botonColor = new Color(72, 61, 139);
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(fondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label1 = new JLabel("Nueva contraseña:");
        label1.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);

        newPassField1.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(newPassField1, gbc);

        JLabel label2 = new JLabel("Confirmar contraseña:");
        label2.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label2, gbc);

        newPassField2.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(newPassField2, gbc);

        confirmButton.setFont(fuente);
        confirmButton.setBackground(botonColor);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(confirmButton, gbc);

        add(panel);
    }

    public String getNewPassword1() {
        return new String(newPassField1.getPassword()).trim();
    }

    public String getNewPassword2() {
        return new String(newPassField2.getPassword()).trim();
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}