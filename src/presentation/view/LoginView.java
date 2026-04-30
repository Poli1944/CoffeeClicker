package presentation.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends BaseView {

    private JButton startButton;

    public final static String BTN_START = "start";

    public LoginView() {
        super("Inicio de Sesión", 42);
        configureView();
    }

    void configureView() {

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 0));

        leftPanel.setOpaque(false);
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(500, 0));

        rightPanel.setOpaque(false);
        add(rightPanel, BorderLayout.EAST);


        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(Box.createVerticalStrut(20));

        contentPanel.add(new JLabel("Nombre de Usuario o email"));

        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(600, 50));

        contentPanel.add(usernameField);


        contentPanel.add(Box.createVerticalStrut(20));


        contentPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(600, 50));

        contentPanel.add(passwordField);

        contentPanel.add(Box.createVerticalGlue());

        JPanel ButtonPanel = new JPanel();
        startButton = new JButton("Start");

        startButton.setActionCommand(BTN_START);

        ButtonPanel.add(startButton);
        add(ButtonPanel, BorderLayout.SOUTH);
    }

    public JButton getStartButton() {
        return startButton;
    }
}
