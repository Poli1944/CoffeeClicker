package presentation.view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends BaseView {


    public static final String BTN_START = "start";
    public static final String BTN_BACK = "back_to_start";

    private JButton startButton;
    private JButton backButton;

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private JLabel errorLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;

    public RegisterView() {
        super("Registro", 42);
        configureView();
    }


    void configureView() {



        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalStrut(20));


        usernameLabel = new JLabel("Nombre de Usuario:");
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(usernameField);

        contentPanel.add(Box.createVerticalStrut(20));

        emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(emailField);
        contentPanel.add(new JLabel("Ejemplo: username@gmail.com"));

        contentPanel.add(Box.createVerticalStrut(20));

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(passwordField);

        contentPanel.add(Box.createVerticalStrut(10));

        confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(confirmPasswordField);

        contentPanel.add(Box.createVerticalStrut(10));

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        errorLabel.setPreferredSize(new Dimension(600, 40));
        errorLabel.setMaximumSize(new Dimension(600, 40));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(errorLabel);

        contentPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();

        backButton = new JButton("Volver");
        backButton.setActionCommand(BTN_BACK);
        buttonPanel.add(backButton);

        startButton = new JButton("Registrarse");
        startButton.setActionCommand(BTN_START);
        buttonPanel.add(startButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    public JButton getStartButton() { return startButton; }
    public JButton getBackButton()  { return backButton; }

    public String getUsernameField() { return usernameField.getText().trim(); }
    public String getEmailField() { return emailField.getText().trim(); }
    public String getPasswordField() { return new String(passwordField.getPassword()); }
    public String getConfirmPasswordField() { return new String(confirmPasswordField.getPassword()); }

    public void showErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public void clearError() {
        errorLabel.setText(" ");
    }
}