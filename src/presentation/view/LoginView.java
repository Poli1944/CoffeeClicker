package presentation.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends BaseView {

    public static final String BTN_START = "start";
    public static final String BTN_BACK = "back_to_start";

    private JButton startButton;
    private JButton backButton;

    private JTextField loginField;
    private JPasswordField passwordField;

    private JLabel errorLabel;

    public LoginView() {
        super("Inicio de Sesión", 42);
        configureView();
    }


    void configureView() {


        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalStrut(20));

        contentPanel.add(new JLabel("Nombre de Usuario o Email:"));
        loginField = new JTextField();
        loginField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(loginField);

        contentPanel.add(Box.createVerticalStrut(20));

        contentPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(passwordField);

        contentPanel.add(Box.createVerticalStrut(10));

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(errorLabel);

        contentPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();

        backButton = new JButton("Volver");
        backButton.setActionCommand(BTN_BACK);
        buttonPanel.add(backButton);

        startButton = new JButton("Iniciar Sesión");
        startButton.setActionCommand(BTN_START);
        buttonPanel.add(startButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    public JButton getStartButton() { return startButton; }
    public JButton getBackButton() { return backButton; }

    public String getLoginField() { return loginField.getText().trim(); }
    public String getPasswordField() { return new String(passwordField.getPassword()); }

    public void showErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public void clearError() {
        errorLabel.setText(" ");
    }
}