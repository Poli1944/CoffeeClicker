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
        contentPanel.add(Box.createVerticalStrut(40));

        usernameLabel = new JLabel("Nombre de Usuario:");
        usernameLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        usernameLabel.setMaximumSize(new Dimension(600, 30));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        contentPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(600, 50));
        usernameField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(usernameField);

        contentPanel.add(Box.createVerticalStrut(15));

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        emailLabel.setMaximumSize(new Dimension(600, 30));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setHorizontalAlignment(JLabel.LEFT);
        contentPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(600, 50));
        emailField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(emailField);

        contentPanel.add(Box.createVerticalStrut(15));

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        passwordLabel.setMaximumSize(new Dimension(600, 30));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(600, 50));
        passwordField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(passwordField);

        contentPanel.add(Box.createVerticalStrut(15));

        confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        confirmPasswordLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        confirmPasswordLabel.setMaximumSize(new Dimension(600, 30));
        confirmPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
        contentPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(600, 50));
        confirmPasswordField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(confirmPasswordField);

        contentPanel.add(Box.createVerticalStrut(15));

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(kavoon.deriveFont(Font.BOLD, 12f));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setMaximumSize(new Dimension(600, 30));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(errorLabel);

        contentPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        backButton = new JButton("Volver");
        backButton.setActionCommand(BTN_BACK);
        backButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
        buttonPanel.add(backButton);

        startButton = new JButton("Registrarse");
        startButton.setActionCommand(BTN_START);
        startButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
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