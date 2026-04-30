package presentation.view;

import javax.swing.*;
import java.awt.*;

public class StartView extends BaseView {

    private JButton loginButton;
    private JButton registerButton;

    public final static String BTN_REG = "register";
    public final static String BTN_LOG = "login";

    public StartView() {
        super("X Clicker", 52);
        configureView();

    }

    void configureView() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 250, 10));
        contentPanel.add(Box.createVerticalGlue());


        loginButton = new JButton("Iniciar Sesión");

        loginButton.setActionCommand(BTN_LOG);

        loginButton.setFont(kavoon.deriveFont(Font.BOLD, 16f));
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setMaximumSize(new Dimension(200, 50));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(loginButton);

        contentPanel.add(Box.createVerticalStrut(30));

        JPanel registerPanel = new JPanel();

        registerPanel.setPreferredSize(new Dimension(200, 100));
        registerPanel.setMaximumSize(new Dimension(200, 100));

        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel registerLabel = new JLabel("¿No tienes cuenta?");
        registerLabel.setFont(kavoon.deriveFont(Font.BOLD, 12f));
        registerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        registerPanel.add(registerLabel);

        registerButton = new JButton("Registrarse");

        registerButton.setActionCommand(BTN_REG);

        registerButton.setFont(kavoon.deriveFont(Font.BOLD, 16f));
        registerButton.setPreferredSize(new Dimension(200, 50));
        registerButton.setMaximumSize(new Dimension(200, 50));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerPanel.add(registerButton);


        contentPanel.add(registerPanel);

        contentPanel.add(Box.createVerticalGlue());

    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

}
