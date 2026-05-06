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

    private JLabel loginLabel;
    private JLabel passwordLabel;

    private JLabel errorLabel;

    public LoginView() {
        super("Inicio de Sesión", 42);
        configureView();
    }


    void configureView() {


        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalStrut(80));

        loginLabel = new JLabel("Nombre de Usuario o Email:");
        loginLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        loginLabel.setMaximumSize(new Dimension(600, 30));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setHorizontalAlignment(JLabel.LEFT);

        contentPanel.add(loginLabel);
        loginField = new JTextField();
        loginField.setMaximumSize(new Dimension(600, 100));
        loginField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(loginField);

        contentPanel.add(Box.createVerticalStrut(20));

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(kavoon.deriveFont(Font.PLAIN, 18f));
        passwordLabel.setMaximumSize(new Dimension(600, 30));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);

        contentPanel.add(passwordLabel);


        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(600, 100));
        passwordField.setFont(kavoon.deriveFont(Font.PLAIN, 24f));
        contentPanel.add(passwordField);

        contentPanel.add(Box.createVerticalStrut(10));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(kavoon.deriveFont(Font.BOLD, 14f));
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(errorLabel);

        contentPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();

        backButton = new JButton("Volver");
        backButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
        backButton.setMaximumSize(new Dimension(1000, 50));
        backButton.setActionCommand(BTN_BACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        buttonPanel.add(backButton);

        startButton = new JButton("Iniciar Sesión");
        startButton.setActionCommand(BTN_START);
        startButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
        startButton.setMaximumSize(new Dimension(1000, 50));
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